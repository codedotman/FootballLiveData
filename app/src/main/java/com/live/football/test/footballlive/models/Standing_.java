package com.live.football.test.footballlive.models;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Standing_ {

    @SerializedName("stage")
    @Expose
    private String stage;
    @SerializedName("type")
    @Expose
    @Ignore
    private String type;
    @SerializedName("group")
    @Expose
    @Embedded
    private Object group;
    @SerializedName("table")
    @Expose
    private List<Table> table;

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getGroup() {
        return group;
    }

    public void setGroup(Object group) {
        this.group = group;
    }

    public List<Table> getTable() {
        return table;
    }

    public void setTable(List<Table> table) {
        this.table = table;
    }

}
