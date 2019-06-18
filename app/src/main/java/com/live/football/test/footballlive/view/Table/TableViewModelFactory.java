package com.live.football.test.footballlive.view.Table;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;


public class TableViewModelFactory implements ViewModelProvider.Factory {

    private final TableRepository mRepository;

    public TableViewModelFactory(TableRepository mRepository) {
        this.mRepository = mRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(TableViewModel.class))
            return (T) new TableViewModel(mRepository);
        throw new IllegalArgumentException("Unknown ViewModel class");

    }
}
