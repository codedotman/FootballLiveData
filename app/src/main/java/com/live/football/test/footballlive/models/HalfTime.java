package com.live.football.test.footballlive.models;

import android.arch.persistence.room.Embedded;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class HalfTime {

    @SerializedName("homeTeam")
    @Expose
    @Embedded
    private Object homeTeam;
    @SerializedName("awayTeam")
    @Expose
    @Embedded
    private Object awayTeam;

    public Object getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Object homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Object getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Object awayTeam) {
        this.awayTeam = awayTeam;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("homeTeam", homeTeam).append("awayTeam", awayTeam).toString();
    }
}
