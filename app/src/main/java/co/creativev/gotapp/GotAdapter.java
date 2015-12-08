package co.creativev.gotapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class GotAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    private Cursor cursor;

    public GotAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        DatabaseHelper dbHelper = DatabaseHelper.getInstance(context);
        cursor = dbHelper.getAllRows();
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public GoTCharacter getItem(int position) {
        cursor.moveToPosition(position);
        return new GoTCharacter(
                cursor.getInt(cursor.getColumnIndexOrThrow(GoTCharacter._ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(GoTCharacter.NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(GoTCharacter.THUMB_URL)),
                cursor.getString(cursor.getColumnIndexOrThrow(GoTCharacter.FULL_URL)),
                true,
                cursor.getString(cursor.getColumnIndexOrThrow(GoTCharacter.HOUSE)),
                cursor.getInt(cursor.getColumnIndexOrThrow(GoTCharacter.HOUSE_RES_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(GoTCharacter.DESCRIPTION)));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.list_item_got, parent, false);
        } else {
            view = convertView;
        }

        ImageView characterThumb = (ImageView) view.findViewById(R.id.image_character);
        TextView characterName = (TextView) view.findViewById(R.id.text_character);
        Picasso.with(context)
                .load(getItem(position).thumbUrl)
                .placeholder(R.drawable.profile_placeholder)
                .error(R.drawable.profile_placeholder_error)
                .into(characterThumb);
        characterName.setText(getItem(position).name);

        return view;
    }

    public void onStart() {
        safeCloseCursor();
        cursor = DatabaseHelper.getInstance(context).getAllRows();
        notifyDataSetChanged();
    }

    public void onStop() {
        safeCloseCursor();
    }

    private void safeCloseCursor() {
        if (cursor != null && !cursor.isClosed()) cursor.close();
    }
}
