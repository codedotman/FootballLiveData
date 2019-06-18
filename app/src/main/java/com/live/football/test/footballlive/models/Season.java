package com.live.football.test.footballlive.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class Season {

    @ColumnInfo(name = "match_season_id")
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("currentMatchday")
    @Expose
    private Integer currentMatchday;
    @SerializedName("winner")
    @Expose
    @Embedded
    private Object winner;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getCurrentMatchday() {
        if(currentMatchday != null){
            return currentMatchday;

        }
        else {
            return 0;
        }
    }

    public void setCurrentMatchday(Integer currentMatchday) {
        this.currentMatchday = currentMatchday;
    }

    public Object getWinner() {
        return winner;
    }

    public void setWinner(Object winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("startDate", startDate).append("endDate", endDate).append("currentMatchday", currentMatchday).append("winner", winner).toString();
    }
}
