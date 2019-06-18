package com.live.football.test.footballlive.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;


@Entity(tableName = "match_list")

public class Match {
    @PrimaryKey
    @ColumnInfo(name = "match_id")
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("competition")
    @Expose
    @Embedded
    private MatchCompetition competition;
    @SerializedName("season")
    @Expose
    @Embedded
    private Season season;
    @SerializedName("utcDate")
    @Expose
    private String utcDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("matchday")
    @Expose
    private Integer matchday;
    @SerializedName("stage")
    @Expose
    private String stage;
    @SerializedName("group")
    @Expose
    private String group;
    @SerializedName("lastUpdated")
    @Expose
    private String lastUpdated;
    @SerializedName("score")
    @Expose
    @Embedded
    private Score score;
    @SerializedName("homeTeam")
    @Expose
    @Embedded
    private HomeTeam homeTeam;
    @SerializedName("awayTeam")
    @Expose
    @Embedded
    private AwayTeam awayTeam;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MatchCompetition getCompetition() {
        return competition;
    }

    public void setCompetition(MatchCompetition competition) {
        this.competition = competition;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public String getUtcDate() {
        return utcDate;
    }

    public void setUtcDate(String utcDate) {
        this.utcDate = utcDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getMatchday() {
        return matchday;
    }

    public void setMatchday(Integer matchday) {
        this.matchday = matchday;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public HomeTeam getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(HomeTeam homeTeam) {
        this.homeTeam = homeTeam;
    }

    public AwayTeam getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(AwayTeam awayTeam) {
        this.awayTeam = awayTeam;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("competition", competition).append("season", season).append("utcDate", utcDate).append("status", status).append("matchday", matchday).append("stage", stage).append("group", group).append("lastUpdated", lastUpdated).append("score", score).append("homeTeam", homeTeam).append("awayTeam", awayTeam).toString();
    }
}
