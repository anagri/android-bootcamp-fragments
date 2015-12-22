package co.creativev.gotapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GoTOnlineAdapter extends BaseAdapter {
    private final List<GoTCharacter> characters;
    private final LayoutInflater inflater;
    private final Context context;
    private final GoTService goTService;
    private int page = 0;

    public GoTOnlineAdapter(Context context, GoTService goTService) {
        this.context = context;
        this.goTService = goTService;
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
        final ProgressDialog dialog = ProgressDialog.show(context, null, "Loading", true);
        new AsyncTask<Integer, Void, List<GoTCharacter>>() {
            @Override
            protected List<GoTCharacter> doInBackground(Integer... params) {
                try {
                    return goTService.getCharacters(params[0]);
                } catch (Exception e) {
                    Log.e(GoTApplication.LOG_TAG, "Error while fetching data", e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(List<GoTCharacter> goTCharacter) {
                dialog.dismiss();
                if (goTCharacter == null) {
                    Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                    return;
                }
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
