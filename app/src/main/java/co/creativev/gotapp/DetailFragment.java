package co.creativev.gotapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        TextView characterName = (TextView) view.findViewById(R.id.text_character_name);
        ImageView characterImage = (ImageView) view.findViewById(R.id.image_character);
        ImageView houseThumb = (ImageView) view.findViewById(R.id.image_house);
        TextView description = (TextView) view.findViewById(R.id.text_description);
        TextView houseName = (TextView) view.findViewById(R.id.text_house_name);

        GoTCharacter character = getArguments().getParcelable(DetailActivity.CHARACTER);

        characterName.setText(character.name);
        description.setText(character.description);
        houseName.setText(character.house);
        Picasso.with(getContext())
                .load(character.fullUrl)
                .placeholder(R.drawable.profile_placeholder_full)
                .error(R.drawable.profile_placeholder_error_full)
                .into(characterImage);
        houseThumb.setImageResource(character.houseResId);
        return view;
    }
}
