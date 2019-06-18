package com.live.football.test.footballlive.view.Table;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.live.football.test.footballlive.R;
import com.live.football.test.footballlive.data.network.Api;
import com.live.football.test.footballlive.data.network.AppExecutors;
import com.live.football.test.footballlive.di.di.component.DaggerLiveFootballComponent;
import com.live.football.test.footballlive.di.di.component.LiveFootballComponent;
import com.live.football.test.footballlive.di.di.module.ContextModule;
import com.live.football.test.footballlive.models.Table;
import com.squareup.picasso.Picasso;

import java.util.List;


public class TableFragment extends Fragment {

    private static final String ARG_LEAGUE_ID = "LEAGUE_ID";

    public static TableFragment newInstance(int teamId){
        TableFragment fragment = new TableFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_LEAGUE_ID,teamId);
        fragment.setArguments(args);
        return fragment;
    }

    private Api LiveFootballApi;
    private Picasso picasso;
    private View view;
    private TableRepository mRepository;
    private TableViewModel mTableViewModel;
    private RecyclerView recyclerView;
    private TableAdapter mAdapter;
    private Toolbar toolbar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_table, container, false);
        int teamId = getArguments().getInt(ARG_LEAGUE_ID,0);

        afterDagger();
        mRepository = new TableRepository( LiveFootballApi, getActivity().getApplication(), new AppExecutors(), teamId);

        TableViewModelFactory factory = new TableViewModelFactory(mRepository);
        mTableViewModel = ViewModelProviders.of(this, factory).get(TableViewModel.class);
        initViews();
        populateMatches();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy>0 && toolbar.isShown()){
                    toolbar.setVisibility(View.GONE);
                }else if (dy<0){
                    toolbar.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        return view;
    }

    private void afterDagger() {
        LiveFootballComponent daggerRandomUserComponent = DaggerLiveFootballComponent.builder()
                .contextModule(new ContextModule(getActivity()))
                .build();
        picasso = daggerRandomUserComponent.getPicasso();
        LiveFootballApi = daggerRandomUserComponent.getLiveFootballService();
    }

    private void populateMatches() {

        mTableViewModel.getmStanding().observe(this, new Observer<List<Table>>() {
            @Override
            public void onChanged(@Nullable List<Table> listResource) {
                mAdapter.setItems(listResource);

            }
        });
    }

    private void initViews() {
        toolbar = getActivity().findViewById(R.id.toolbar_tabbed);
        recyclerView = view.findViewById(R.id.TableRecyclerView);
        mAdapter = new TableAdapter(getActivity(),picasso);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);

    }

}
