package com.live.football.test.footballlive.view.Teams;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.live.football.test.footballlive.models.Teams;

import java.util.List;

public class TeamsViewModel extends AndroidViewModel {

    private TeamsRepository mRepository;

    public TeamsViewModel(TeamsRepository mRepository) {
        super(mRepository.getApplication());
        this.mRepository = mRepository;
    }

    public LiveData<List<Teams>> getTeams() {
        return mRepository.getTeams();
    }

}
