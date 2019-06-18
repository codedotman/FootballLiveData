package com.live.football.test.footballlive.models;

import android.arch.persistence.room.ColumnInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Area_ {

    @SerializedName("id")
    @Expose
    @ColumnInfo(name = "table_area_id")
    private Integer id;
    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "table_rea_name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
