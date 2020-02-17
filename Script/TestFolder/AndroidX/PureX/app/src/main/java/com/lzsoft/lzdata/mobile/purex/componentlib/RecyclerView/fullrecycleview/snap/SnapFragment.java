package com.lzsoft.lzdata.mobile.purex.componentlib.RecyclerView.fullrecycleview.snap;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.lzsoft.lzdata.mobile.purex.R;

import java.util.ArrayList;

public class SnapFragment extends Fragment {

    public SnapFragment() {
        // Required empty public constructor
    }

    public static SnapFragment newInstance() {
        return new SnapFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fullrecyclerview_snap_fragment, container, false);

        RecyclerView recyclerViewVertical = view.findViewById(R.id.snap_fragment_vertical_recycler_view);
        RecyclerView recyclerView = view.findViewById(R.id.snap_fragment_horizontal_recycler_view);

        SnapHelper snapHelperVertical = new LinearSnapHelper();
        snapHelperVertical.attachToRecyclerView(recyclerViewVertical);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerViewVertical.setHasFixedSize(true);
        recyclerViewVertical.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));

        SnapData dataThe = new SnapData("The","13 songs", R.drawable.album1);
        SnapData dataDeveloper = new SnapData("Developer","8 songs", R.drawable.album2);
        SnapData dataWorld = new SnapData("World","11 songs", R.drawable.album3);
        SnapData dataIs = new SnapData("Is","1 songs", R.drawable.album4);
        SnapData dataYours = new SnapData("Yours","7 songs", R.drawable.album5);
        SnapData dataCom = new SnapData(".com","14 songs", R.drawable.album6);
        SnapData dataAlbun7= new SnapData("album7","17 songs", R.drawable.album7);
        SnapData dataAlbun8= new SnapData("album8","12 songs", R.drawable.album8);
        SnapData dataAlbun9= new SnapData("album9","15 songs", R.drawable.album9);
        SnapData dataAlbun10= new SnapData("album10","12 songs", R.drawable.album10);
        SnapData dataAlbun11= new SnapData("album11", "13 songs",R.drawable.album11);

        ArrayList<SnapData> list = new ArrayList<>();

        list.add(0, dataThe);
        list.add(1, dataDeveloper);
        list.add(2, dataWorld);
        list.add(3, dataIs);
        list.add(4, dataYours);
        list.add(5, dataCom);
        list.add(6, dataAlbun7);
        list.add(7, dataAlbun8);
        list.add(8, dataAlbun9);
        list.add(9, dataAlbun10);
        list.add(10, dataAlbun11);

        SnapHorizontalRecyclerViewAdapter adapter = new SnapHorizontalRecyclerViewAdapter(list, getActivity());
        recyclerView.setAdapter(adapter);

        recyclerViewVertical.setAdapter(adapter);

        return view;
    }

}
