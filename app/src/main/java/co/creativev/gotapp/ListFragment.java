package co.creativev.gotapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListFragment extends Fragment {
//    private GotAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ListView characetersList = (ListView) view.findViewById(R.id.list);
//        adapter = new GotAdapter(getContext());
//        characetersList.setAdapter(adapter);
        final GoTOnlineAdapter adapter = new GoTOnlineAdapter(getContext());
        characetersList.setAdapter(adapter);
        characetersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (adapter.getItemViewType(position) == 1) {
                    adapter.loadMore();
                } else {
                    GoTCharacter gotCharacter = adapter.getItem(position);
                    Intent intent = new Intent(getContext(), DetailActivity.class);
                    intent.putExtra(DetailActivity.CHARACTER, gotCharacter);
                    startActivity(intent);
                }
            }
        });

        return view;
    }

}
