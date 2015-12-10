package co.creativev.gotapp;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public class GoTOnlineAdapter extends BaseAdapter {
    public static final long HTTP_TIMEOUT = 10 * 1000;
    public static final long CACHE_SIZE = 1024 * 1024 * 10l;  // 10 MB HTTP Cache
    private final OkHttpClient okHttpClient;
    private final List<GoTCharacter> characters;
    private final LayoutInflater inflater;
    private final Context context;
    private final GoTService goTService;
    private int page = 0;

    public GoTOnlineAdapter(Context context) {
        this.context = context;
        okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.setWriteTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS);
        File cacheDir = new File(context.getCacheDir(), "http-cache");
        Cache cache = new Cache(cacheDir, CACHE_SIZE);
        okHttpClient.setCache(cache);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setClient(new OkClient(okHttpClient))
                .setEndpoint("http://10.10.0.59:3000/")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gson))
                .build();
        goTService = restAdapter.create(GoTService.class);

        characters = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 1 + characters.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position < getCount() - 1 ? 0 : 1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public GoTCharacter getItem(int position) {
        return getItemViewType(position) == 0 ? characters.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return getItemViewType(position) == 0 ? characters.get(position).id : -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int viewType = getItemViewType(position);
        if (viewType == 1) {
            return loadMoreView(convertView, parent);
        } else {
            return gotView(position, convertView, parent);
        }
    }

    private View gotView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = inflater.inflate(R.layout.list_item_got, parent, false);
            view.setTag(new ViewHolder(
                    ((ImageView) view.findViewById(R.id.image_character)),
                    ((TextView) view.findViewById(R.id.text_character))
            ));
        } else {
            view = convertView;
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        GoTCharacter character = characters.get(position);
        holder.textView.setText(character.name);
        Picasso.with(context)
                .load(character.thumbUrl)
                .placeholder(R.drawable.profile_placeholder)
                .error(R.drawable.profile_placeholder_error)
                .into(holder.imageView);
        return view;
    }

    private View loadMoreView(View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        } else {
            view = convertView;
        }
        ((TextView) view).setText("Load More");
        return view;
    }

    public void loadMore() {
        new AsyncTask<Integer, Void, List<GoTCharacter>>() {
            @Override
            protected List<GoTCharacter> doInBackground(Integer... params) {
                return goTService.getCharacters(params[0]);
            }

            @Override
            protected void onPostExecute(List<GoTCharacter> goTCharacter) {
                characters.addAll(goTCharacter);
                page++;
                notifyDataSetChanged();
            }
        }.execute(page + 1);
    }

    public static class ViewHolder {
        private final ImageView imageView;
        private final TextView textView;

        public ViewHolder(ImageView imageView, TextView textView) {
            this.imageView = imageView;
            this.textView = textView;
        }
    }

}
