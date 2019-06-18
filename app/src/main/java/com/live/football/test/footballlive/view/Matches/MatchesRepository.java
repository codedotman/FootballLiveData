package com.live.football.test.footballlive.view.Matches;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.live.football.test.footballlive.view.Competitions.LiveFootballRepository;
import com.live.football.test.footballlive.data.network.Api;
import com.live.football.test.footballlive.data.network.ApiResponse;
import com.live.football.test.footballlive.data.network.AppExecutors;
import com.live.football.test.footballlive.data.network.NetworkBoundResource;
import com.live.football.test.footballlive.data.network.RateLimiter;
import com.live.football.test.footballlive.data.network.Resource;
import com.live.football.test.footballlive.db.CompetitionDao;
import com.live.football.test.footballlive.db.CompetitionDatabase;
import com.live.football.test.footballlive.models.Match;
import com.live.football.test.footballlive.models.MatchResponse;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class MatchesRepository {

    private final CompetitionDao mCompetitionDao;
    private final AppExecutors appExecutors;
    private final Application application;
    private Api LiveFootballApi;
    private static String LOG_TAG = LiveFootballRepository.class.getSimpleName();
    private RateLimiter<String> repoListRateLimit = new RateLimiter<>(5, TimeUnit.MINUTES);


    public MatchesRepository(Api LiveFootballApi, Application application, AppExecutors appExecutors) {
        CompetitionDatabase db = CompetitionDatabase.getMatchesDatabase(application);
        this.mCompetitionDao = db.competitionDao();
        this.LiveFootballApi = LiveFootballApi;
        this.appExecutors = appExecutors;
        this.application = application;
    }

    public LiveData<Resource<List<Match>>> getAllMatches() {

        return new NetworkBoundResource<List<Match>, MatchResponse>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull MatchResponse item) {
                Log.d(LOG_TAG, "call to insert results to db");
                mCompetitionDao.insertMatches(item.getMatches());
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Match> data) {
                Log.d(LOG_TAG, "null?" + (data == null));
                Log.d(LOG_TAG, "empty? " + (data.isEmpty()));
                Log.d(LOG_TAG, "rate? " + (repoListRateLimit.shouldFetch("owner")));
                Log.d(LOG_TAG, "should fetch? " + (data.isEmpty() || repoListRateLimit.shouldFetch("owner")));
                return data.isEmpty() || data == null;

            }

            @NonNull
            @Override
            protected LiveData<List<Match>> loadFromDb() {
                Log.d(LOG_TAG, " call to load from db");
                return mCompetitionDao.getAllMatches();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<MatchResponse>> createCall() {
                Log.d(LOG_TAG, "creating a call to network");
                return LiveFootballApi.getMatches();
            }

            @Override
            protected void onFetchFailed() {

            }

            @Override
            protected MatchResponse processResponse(ApiResponse<MatchResponse> response) {
                return super.processResponse(response);
            }
        }.asLiveData();
    }

    public Application getApplication() {
        return application;
    }
}
