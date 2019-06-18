package com.live.football.test.footballlive.view.Teams;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.live.football.test.footballlive.data.network.Api;
import com.live.football.test.footballlive.models.Teams;
import com.live.football.test.footballlive.models.TeamsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamsRepository {

    private final Application application;
    private final Api LiveFootballApi;
    private static String LOG_TAG = TeamsRepository.class.getSimpleName();
    private int mTeamId;


    public TeamsRepository(Api LiveFootballApi, Application application, int TeamId) {

        this.LiveFootballApi = LiveFootballApi;
        this.application = application;
        this.mTeamId = TeamId;
    }


    public LiveData<List<Teams>> getTeams() {
        final MutableLiveData<List<Teams>> data = new MutableLiveData<>();

        LiveFootballApi.getTeams(mTeamId).enqueue(new Callback<TeamsResponse>() {
            @Override
            public void onResponse(Call<TeamsResponse> call, Response<TeamsResponse> response) {
                if(response.isSuccessful()){
                    data.setValue(response.body().getTeams());
                }
            }

            @Override
            public void onFailure(Call<TeamsResponse> call, Throwable t) {

                Log.e(LOG_TAG, "onFailure: " + t.getMessage());
            }
        });

        return data;
    }

    public Application getApplication() {
        return application;
    }
}



