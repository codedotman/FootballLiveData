package com.live.football.test.footballlive.view.Matches;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.live.football.test.footballlive.data.network.Resource;
import com.live.football.test.footballlive.models.Match;

import java.util.List;



public class MatchViewModel extends AndroidViewModel {

    private MatchesRepository mRepository;

    public MatchViewModel(MatchesRepository mRepository) {
        super(mRepository.getApplication());
        this.mRepository = mRepository;
    }

    public LiveData<Resource<List<Match>>> getmAllMatches() {
        return mRepository.getAllMatches();
    }
}
