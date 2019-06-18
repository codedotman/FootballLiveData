package com.live.football.test.footballlive.view.Competitions;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.live.football.test.footballlive.data.network.Resource;
import com.live.football.test.footballlive.models.Competition;

import java.util.List;


public class CompetitionViewModel extends AndroidViewModel {

    private LiveFootballRepository mRepository;

    public CompetitionViewModel(LiveFootballRepository profileRepository){
        super(profileRepository.getApplication());
        this.mRepository = profileRepository;
    }

    public LiveData<Resource<List<Competition>>> getmAllCompetition() {
        return mRepository.getAllCompetitions();
    }
}
