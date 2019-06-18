package com.live.football.test.footballlive.data.network;

import android.arch.lifecycle.LiveData;

import com.live.football.test.footballlive.models.CompetitionResponse;
import com.live.football.test.footballlive.models.MatchResponse;
import com.live.football.test.footballlive.models.Standing;
import com.live.football.test.footballlive.models.Teams;
import com.live.football.test.footballlive.models.TeamsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("competitions")
    LiveData<ApiResponse<CompetitionResponse>> getCompetitions();

    @GET("matches")
    LiveData<ApiResponse<MatchResponse>> getMatches();

    @GET("competitions/{id}/standings")
    Call<Standing> getCompetitionStandings(@Path("id") int id);

    @GET("competitions/{id}/teams")
    Call<TeamsResponse> getTeams(@Path("id") int id);
}
