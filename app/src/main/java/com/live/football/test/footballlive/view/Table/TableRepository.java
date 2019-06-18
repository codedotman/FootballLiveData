package com.live.football.test.footballlive.view.Table;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.live.football.test.footballlive.data.network.Api;
import com.live.football.test.footballlive.data.network.AppExecutors;
import com.live.football.test.footballlive.db.CompetitionDao;
import com.live.football.test.footballlive.db.CompetitionDatabase;
import com.live.football.test.footballlive.models.Standing;
import com.live.football.test.footballlive.models.Table;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TableRepository {

    private final CompetitionDao mCompetitionDao;
    private final AppExecutors appExecutors;
    private final Application application;
    private Api LiveFootballApi;
    private int mTeamId;
    private static String LOG_TAG = TableRepository.class.getSimpleName();


    public TableRepository(Api LiveFootballApi, Application application, AppExecutors appExecutors, int TeamId) {

        CompetitionDatabase db = CompetitionDatabase.getDatabase(application);
        this.mCompetitionDao = db.competitionDao();
        this.LiveFootballApi = LiveFootballApi;
        this.appExecutors = appExecutors;
        this.application = application;
        this.mTeamId = TeamId;
    }

    public LiveData<List<Table>> getmTable() {

        final MutableLiveData<List<Table>> data = new MutableLiveData<>();

        LiveFootballApi.getCompetitionStandings(mTeamId).enqueue(new Callback<Standing>() {
            @Override
            public void onResponse(Call<Standing> call, Response<Standing> response) {
                if(response.isSuccessful()){
                    try {
                        data.setValue(response.body().getStandings().get(0).getTable());
                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Standing> call, Throwable t) {
                Log.e(LOG_TAG, "onFailure: " + t.getMessage());
            }
        });

        return data;
    }


    public Application getApplication() {
        return application;
    }
}


