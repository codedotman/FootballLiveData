package com.live.football.test.footballlive.view.Matches;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;


public class MatchViewModelFactory implements ViewModelProvider.Factory {
    private final MatchesRepository mRepository;

    public MatchViewModelFactory(MatchesRepository mRepository) {
        this.mRepository = mRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(MatchViewModel.class))
            return (T) new MatchViewModel(mRepository);
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
