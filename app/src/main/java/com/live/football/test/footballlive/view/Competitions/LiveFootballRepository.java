package com.live.football.test.footballlive.view.Competitions;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.live.football.test.footballlive.data.network.Api;
import com.live.football.test.footballlive.data.network.ApiResponse;
import com.live.football.test.footballlive.data.network.NetworkBoundResource;
import com.live.football.test.footballlive.data.network.Resource;
import com.live.football.test.footballlive.db.CompetitionDao;
import com.live.football.test.footballlive.data.network.AppExecutors;
import com.live.football.test.footballlive.db.CompetitionDatabase;
import com.live.football.test.footballlive.models.Competition;
import com.live.football.test.footballlive.models.CompetitionResponse;

import java.util.List;


public class LiveFootballRepository {

    private final CompetitionDao mCompetitionDao;
    private final AppExecutors appExecutors;
    private final Application application;
    private Api LiveFootballApi;

    public LiveFootballRepository(Api LiveFootballApi, Application application, AppExecutors appExecutors) {

        CompetitionDatabase db = CompetitionDatabase.getDatabase(application);
        this.mCompetitionDao = db.competitionDao();
        this.LiveFootballApi = LiveFootballApi;
        this.appExecutors = appExecutors;
        this.application = application;
    }

    public LiveData<Resource<List<Competition>>> getAllCompetitions() {

        return new NetworkBoundResource<List<Competition>, CompetitionResponse>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull CompetitionResponse item) {
                mCompetitionDao.insertCompetition(item.getCompetitions());
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Competition> data) {
               return data.isEmpty() || data == null;

            }

            @NonNull
            @Override
            protected LiveData<List<Competition>> loadFromDb() {
                return mCompetitionDao.getAllCompetition();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<CompetitionResponse>> createCall() {
                return LiveFootballApi.getCompetitions();
            }

            @Override
            protected void onFetchFailed() {

            }

            @Override
            protected CompetitionResponse processResponse(ApiResponse<CompetitionResponse> response) {
                return super.processResponse(response);
            }
        }.asLiveData();
    }

    public Application getApplication() {
        return application;
    }
}
