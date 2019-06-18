package com.live.football.test.footballlive.models;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class Score {

    @SerializedName("winner")
    @Expose
    @Embedded
    private Object winner;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("fullTime")
    @Expose
    @Embedded
    private FullTime fullTime;
    @SerializedName("halfTime")
    @Expose
    @Embedded
    private HalfTime halfTime;
    @SerializedName("extraTime")
    @Expose
    @Embedded
    private ExtraTime extraTime;
    @SerializedName("penalties")
    @Expose
    @Embedded
    private Penalties penalties;

    public Object getWinner() {
        return winner;
    }

    public void setWinner(Object winner) {
        this.winner = winner;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public FullTime getFullTime() {
        return fullTime;
    }

    public void setFullTime(FullTime fullTime) {
        this.fullTime = fullTime;
    }

    public HalfTime getHalfTime() {
        return halfTime;
    }

    public void setHalfTime(HalfTime halfTime) {
        this.halfTime = halfTime;
    }

    public ExtraTime getExtraTime() {
        return extraTime;
    }

    public void setExtraTime(ExtraTime extraTime) {
        this.extraTime = extraTime;
    }

    public Penalties getPenalties() {
        return penalties;
    }

    public void setPenalties(Penalties penalties) {
        this.penalties = penalties;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("winner", winner).append("duration", duration).append("fullTime", fullTime).append("halfTime", halfTime).append("extraTime", extraTime).append("penalties", penalties).toString();
    }
}
