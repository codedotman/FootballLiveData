package com.live.football.test.footballlive.models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Competition_ {

    @SerializedName("id")
    @Expose
    @ColumnInfo(name = "table_competition_id")
    private Integer id;
    @SerializedName("area")
    @Expose
    @Embedded
    private Area_ area;
    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "table_competition_name")
    private String name;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("plan")
    @Expose
    private String plan;
    @SerializedName("lastUpdated")
    @Expose
    private String lastUpdated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Area_ getArea() {
        return area;
    }

    public void setArea(Area_ area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
