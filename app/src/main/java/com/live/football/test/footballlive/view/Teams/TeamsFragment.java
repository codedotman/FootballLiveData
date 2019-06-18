package com.live.football.test.footballlive.view.Teams;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.live.football.test.footballlive.R;
import com.live.football.test.footballlive.data.network.Api;
import com.live.football.test.footballlive.di.di.component.DaggerLiveFootballComponent;
import com.live.football.test.footballlive.di.di.component.LiveFootballComponent;
import com.live.football.test.footballlive.di.di.module.ContextModule;
import com.live.football.test.footballlive.models.Teams;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TeamsFragment extends Fragment {

    private static final String ARG_LEAGUE_ID = "LEAGUE_ID";


    public static TeamsFragment newInstance(int leagueId){
        TeamsFragment fragment = new TeamsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_LEAGUE_ID,leagueId);
        fragment.setArguments(args);
        return fragment;
    }

    private Api LiveFootballApi;
    private Picasso picasso;
    private View view;
    private TeamsRepository mRepository;
    private TeamsViewModel mTeamsViewModel;
    private RecyclerView recyclerView;
    private TeamsAdapter mAdapter;
    private Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_teams, container, false);
        int teamId = getArguments().getInt(ARG_LEAGUE_ID,0);

        afterDagger();
        mRepository = new TeamsRepository( LiveFootballApi, getActivity().getApplication(), teamId);

        TeamsViewModelFactory factory = new TeamsViewModelFactory(mRepository);
        mTeamsViewModel = ViewModelProviders.of(this, factory).get(TeamsViewModel.class);
        initViews();
        populateTeams();

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

    private void initViews() {
        toolbar = getActivity().findViewById(R.id.toolbar_tabbed);
        recyclerView = view.findViewById(R.id.TeamsRecyclerView);
        mAdapter = new TeamsAdapter(getActivity(),picasso);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(mAdapter);

    }

    private void populateTeams() {

        mTeamsViewModel.getTeams().observe(this, new Observer<List<Teams>>() {
            @Override
            public void onChanged(@Nullable List<Teams> listResource) {
                mAdapter.setItems(listResource);

            }
        });
    }

}
