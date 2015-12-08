package co.creativev.gotapp;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DetailFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        GoTCharacter character = getArguments().getParcelable(DetailActivity.CHARACTER);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
