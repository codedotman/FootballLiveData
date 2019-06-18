package com.live.football.test.footballlive.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.live.football.test.footballlive.models.Competition;
import com.live.football.test.footballlive.models.Match;

import java.util.List;

@Dao
public interface CompetitionDao {

    @Query("SELECT * from competition_list order by name asc")
    LiveData<List<Competition>> getAllCompetition();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCompetition(List<Competition> competitions);

    @Query("SELECT * from match_list")
    LiveData<List<Match>> getAllMatches();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMatches(List<Match> matches);

}
