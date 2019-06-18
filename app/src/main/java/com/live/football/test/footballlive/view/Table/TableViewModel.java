package com.live.football.test.footballlive.view.Table;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;


import com.live.football.test.footballlive.models.Table;

import java.util.List;


public class TableViewModel extends AndroidViewModel{

    private TableRepository mRepository;

    public TableViewModel(TableRepository mRepository) {
        super(mRepository.getApplication());
        this.mRepository = mRepository;
    }

    public LiveData<List<Table>> getmStanding() {
        return mRepository.getmTable();
    }

}
