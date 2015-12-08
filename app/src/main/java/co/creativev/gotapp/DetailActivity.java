package co.creativev.gotapp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    public static final String CHARACTER = "character";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        Bundle extras = getIntent().getExtras();
        GoTCharacter character = extras.getParcelable(CHARACTER);

        DetailFragment detailFragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(CHARACTER, character);
        detailFragment.setArguments(args);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_detail, detailFragment)
                .commit();

        setTitle(character.name);

        TextView characterName = (TextView) findViewById(R.id.text_character_name);
        ImageView characterImage = (ImageView) findViewById(R.id.image_character);
        ImageView houseThumb = (ImageView) findViewById(R.id.image_house);
        TextView description = (TextView) findViewById(R.id.text_description);
        TextView houseName = (TextView) findViewById(R.id.text_house_name);

        characterName.setText(character.name);
        description.setText(character.description);
        houseName.setText(character.house);
        Picasso.with(this)
                .load(character.fullUrl)
                .placeholder(R.drawable.profile_placeholder_full)
                .error(R.drawable.profile_placeholder_error_full)
                .into(characterImage);
        houseThumb.setImageResource(character.houseResId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
