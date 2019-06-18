package com.live.football.test.footballlive.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class FullTime {

    @SerializedName("homeTeam")
    @Expose
    private Integer homeTeam;
    @SerializedName("awayTeam")
    @Expose
    private Integer awayTeam;

    public Integer getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Integer homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Integer getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Integer awayTeam) {
        this.awayTeam = awayTeam;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("homeTeam", homeTeam).append("awayTeam", awayTeam).toString();
    }
}
