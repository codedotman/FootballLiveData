package com.live.football.test.footballlive.view.Matches;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.live.football.test.footballlive.R;
import com.live.football.test.footballlive.data.network.Api;
import com.live.football.test.footballlive.data.network.AppExecutors;
import com.live.football.test.footballlive.data.network.Resource;

import com.live.football.test.footballlive.di.di.component.DaggerLiveFootballComponent;
import com.live.football.test.footballlive.di.di.component.LiveFootballComponent;
import com.live.football.test.footballlive.di.di.module.ContextModule;
import com.live.football.test.footballlive.models.Match;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MatchesFragment extends Fragment {

    public MatchesFragment() {
        // Required empty public constructor
    }

    private Api LiveFootballApi;
    private Picasso picasso;
    private View view;
    private MatchesRepository mRepository;
    private MatchViewModel mMatchesViewModel;
    private RecyclerView recyclerView;
    private MatchesAdapter mAdapter;
    private Toolbar toolbar;
    private BottomNavigationView navigation;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_matches, container, false);
        afterDagger();
        mRepository = new MatchesRepository( LiveFootballApi, getActivity().getApplication(), new AppExecutors());

        MatchViewModelFactory factory = new MatchViewModelFactory(mRepository);
        mMatchesViewModel = ViewModelProviders.of(this, factory).get(MatchViewModel.class);
        initViews();
        populateMatches();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy>0 && toolbar.isShown()){
                    toolbar.setVisibility(View.GONE);
                    navigation.setVisibility(View.GONE);
                }else if (dy<0){
                    toolbar.setVisibility(View.VISIBLE);
                    navigation.setVisibility(View.VISIBLE);
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

        mMatchesViewModel.getmAllMatches().observe(this, new Observer<Resource<List<Match>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<Match>> listResource) {
                Toast.makeText(getActivity(), "" + listResource.status, Toast.LENGTH_LONG).show();
                mAdapter.setItems(listResource.data);
                recyclerView.setAdapter(mAdapter);

            }
        });
    }

    private void initViews() {

        toolbar = view.findViewById(R.id.toolkygygjghbar);
        recyclerView = view.findViewById(R.id.matchRecyclerView);
        navigation = getActivity().findViewById(R.id.navigation);
        mAdapter = new MatchesAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }



}
