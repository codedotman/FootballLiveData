package com.live.football.test.footballlive.models;

import android.arch.persistence.room.Embedded;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Standing {


    @SerializedName("filters")
    @Expose
    private Filters filters;
    @SerializedName("competition")
    @Expose
    private Competition_ competition;
    @SerializedName("season")
    @Expose
    @Embedded
    private Season season;
    @SerializedName("standings")
    @Expose
    private List<Standing_> standings;


    public Filters getFilters() {
        return filters;
    }

    public void setFilters(Filters filters) {
        this.filters = filters;
    }

    public Competition_ getCompetition() {
        return competition;
    }

    public void setCompetition(Competition_ competition) {
        this.competition = competition;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public List<Standing_> getStandings() {
        return standings;
    }

    public void setStandings(List<Standing_> standings) {
        this.standings = standings;
    }

}
