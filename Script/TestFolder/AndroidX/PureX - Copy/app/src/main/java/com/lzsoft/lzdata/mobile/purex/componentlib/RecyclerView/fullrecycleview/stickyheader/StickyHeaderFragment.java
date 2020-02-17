package com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview.stickyheader;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lzsoft.lzdata.mobile.purex.R;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersTouchListener;

/**
 * Created by javierg on 18/09/2017.
 */

public class StickyHeaderFragment extends Fragment {

    public StickyHeaderFragment() {
        // Required empty public constructor
    }

    public static StickyHeaderFragment newInstance() {
        return new StickyHeaderFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fullrecyclerview_sticky_header_fragment, container, false);

        final RecyclerView recyclerView = view.findViewById(R.id.recyclerview);

        // Set adapter populated with example dummy data
        final AnimalsHeadersAdapter adapter = new AnimalsHeadersAdapter(getActivity());
        adapter.addAll(getDummyDataSet());
        recyclerView.setAdapter(adapter);

        // Set layout manager
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // Add the sticky headers decoration
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(adapter);
        recyclerView.addItemDecoration(headersDecor);


        // Add touch listeners
        StickyRecyclerHeadersTouchListener touchListener =
                new StickyRecyclerHeadersTouchListener(recyclerView, headersDecor);
        touchListener.setOnHeaderClickListener((View header, int position, long headerId) -> {
            Toast.makeText(getActivity(), "Header position: " + position + ", id: " + headerId,
                    Toast.LENGTH_SHORT).show();
        });
        recyclerView.addOnItemTouchListener(touchListener);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                headersDecor.invalidateHeaders();
            }
        });

        return view;
    }

    private String[] getDummyDataSet() {
        return getResources().getStringArray(R.array.animals);
    }

}
