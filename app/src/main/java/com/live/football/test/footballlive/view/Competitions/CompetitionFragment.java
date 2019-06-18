package com.live.football.test.footballlive.view.Competitions;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.live.football.test.footballlive.R;
import com.live.football.test.footballlive.view.Activity.TabbedActivity;
import com.live.football.test.footballlive.data.network.Api;
import com.live.football.test.footballlive.data.network.AppExecutors;
import com.live.football.test.footballlive.data.network.Resource;
import com.live.football.test.footballlive.di.di.component.DaggerLiveFootballComponent;
import com.live.football.test.footballlive.di.di.component.LiveFootballComponent;
import com.live.football.test.footballlive.di.di.module.ContextModule;
import com.live.football.test.footballlive.models.Competition;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CompetitionFragment extends Fragment implements CompetitionAdapter.ListItemClickListener {

    public CompetitionFragment() {

    }

    private Api LiveFootballApi;
    private Picasso picasso;
    private RecyclerView recyclerView;
    private View view;
    private CompetitionAdapter mAdapter;
    private LiveFootballRepository mRepository;
    private CompetitionViewModel mCompetitionViewModel;
    private Toolbar toolbar;
    private BottomNavigationView navigation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_competition, container, false);

        afterDagger();
        mRepository = new LiveFootballRepository( LiveFootballApi, getActivity().getApplication(), new AppExecutors());

        CompetitionViewModelFactory factory = new CompetitionViewModelFactory(mRepository);
        mCompetitionViewModel = ViewModelProviders.of(this, factory).get(CompetitionViewModel.class);
        initViews();
        populateUsers();

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

    private void initViews() {
        recyclerView = view.findViewById(R.id.competitionRecyclerView);
        toolbar = view.findViewById(R.id.toolbggygyygar);
        navigation = getActivity().findViewById(R.id.navigation);
        mAdapter = new CompetitionAdapter(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void afterDagger() {
        LiveFootballComponent daggerRandomUserComponent = DaggerLiveFootballComponent.builder()
                .contextModule(new ContextModule(getActivity()))
                .build();
        picasso = daggerRandomUserComponent.getPicasso();
        LiveFootballApi = daggerRandomUserComponent.getLiveFootballService();
    }

    private void populateUsers() {

        mCompetitionViewModel.getmAllCompetition().observe(this, new Observer<Resource<List<Competition>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<Competition>> listResource) {
                Toast.makeText(getActivity(), "" + listResource.status, Toast.LENGTH_LONG).show();
                mAdapter.setItems(listResource.data);
                recyclerView.setAdapter(mAdapter);

            }
        });
    }

    @Override
    public void onListItemClick(int leagueId, String leagueName) {
        Intent tabbedDetailActivity = new Intent(getActivity(), TabbedActivity.class);
        tabbedDetailActivity.putExtra(TabbedActivity.LEAGUE_ID_EXTRA, leagueId);
        tabbedDetailActivity.putExtra(TabbedActivity.LEAGUE_NAME_EXTRA,leagueName);
        startActivity(tabbedDetailActivity);
    }
}
