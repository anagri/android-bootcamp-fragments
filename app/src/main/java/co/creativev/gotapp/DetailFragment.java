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

import static co.creativev.gotapp.GoTCharacter.BARATHEON;
import static co.creativev.gotapp.GoTCharacter.BOLTON;
import static co.creativev.gotapp.GoTCharacter.DOTHRAKI;
import static co.creativev.gotapp.GoTCharacter.FACELESS_MEN;
import static co.creativev.gotapp.GoTCharacter.LANNISTER;
import static co.creativev.gotapp.GoTCharacter.STARK;
import static co.creativev.gotapp.GoTCharacter.TARGARYEN;

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
        houseName.setText(getHouseNameResId(character.houseId));
        Picasso.with(getContext())
                .load(character.fullUrl)
                .placeholder(R.drawable.profile_placeholder_full)
                .error(R.drawable.profile_placeholder_error_full)
                .into(characterImage);
        if (character.alive)
            characterName.setTextColor(getResources().getColor(android.R.color.black));
        else
            characterName.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        houseThumb.setImageResource(getHouseResId(character.houseId));
        return view;
    }

    private int getHouseNameResId(int houseId) {
        switch (houseId) {
            case STARK:
                return R.string.stark;
            case LANNISTER:
                return R.string.lannister;
            case BARATHEON:
                return R.string.baratheon;
            case TARGARYEN:
                return R.string.targaryen;
            case BOLTON:
                return R.string.bolton;
            case DOTHRAKI:
                return R.string.dothraki;
            case FACELESS_MEN:
                return R.string.faceless_men;
            default:
                return R.string.unknown_house;
        }
    }

    private int getHouseResId(int houseId) {
        switch (houseId) {
            case STARK:
                return R.drawable.stark;
            case LANNISTER:
                return R.drawable.lannister;
            case BARATHEON:
                return R.drawable.baratheon;
            case TARGARYEN:
                return R.drawable.targaryen;
            case BOLTON:
                return R.drawable.bolton;
            case DOTHRAKI:
                return R.drawable.dothraki;
            case FACELESS_MEN:
                return R.drawable.faceless;
            default:
                return R.drawable.house_placeholder;
        }
    }
}
