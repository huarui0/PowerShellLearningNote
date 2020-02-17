package com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview.expandable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzsoft.lzdata.mobile.purex.R;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview.expandable.GenreDataFactory.makeGenres;

public class ExpandableFragment extends Fragment {

    public ExpandableAdapter adapter;

    public ExpandableFragment() {
        // Required empty public constructor
    }

    public static ExpandableFragment newInstance() {

        return new ExpandableFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.expandable_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        adapter = new ExpandableAdapter(makeGenres());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

}
