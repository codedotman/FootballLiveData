package com.live.football.test.footballlive.view.Competitions;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class CompetitionViewModelFactory implements ViewModelProvider.Factory {
    private final LiveFootballRepository mRepository;

    public CompetitionViewModelFactory(LiveFootballRepository mmRepository) {
        this.mRepository = mmRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(CompetitionViewModel.class))
            return (T) new CompetitionViewModel(mRepository);
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
