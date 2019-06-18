package com.live.football.test.footballlive.view.Teams;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;


public class TeamsViewModelFactory implements ViewModelProvider.Factory {

    private final TeamsRepository mRepository;

    public TeamsViewModelFactory(TeamsRepository mRepository) {
        this.mRepository = mRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(TeamsViewModel.class))
            return (T) new TeamsViewModel(mRepository);
        throw new IllegalArgumentException("Unknown ViewModel class");

    }
}
