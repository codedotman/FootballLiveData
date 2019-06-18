package com.live.football.test.footballlive.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.live.football.test.footballlive.models.Competition;
import com.live.football.test.footballlive.models.Match;

@Database(entities = {Competition.class, Match.class}, version = 1, exportSchema = false)
public abstract class CompetitionDatabase extends RoomDatabase {

    private static volatile CompetitionDatabase INSTANCE;

    public abstract CompetitionDao competitionDao();

    public static CompetitionDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CompetitionDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CompetitionDatabase.class, "competition_list")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static CompetitionDatabase getMatchesDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CompetitionDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CompetitionDatabase.class, "match_list")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
