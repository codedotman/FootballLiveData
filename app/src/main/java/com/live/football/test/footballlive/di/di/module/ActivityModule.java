package com.live.football.test.footballlive.di.di.module;

import android.app.Activity;
import android.content.Context;

import com.live.football.test.footballlive.di.di.LiveFootballApplicationScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final Context context;

    ActivityModule(Activity context){
        this.context = context;
    }

    @Named("activity_context")
    @LiveFootballApplicationScope
    @Provides
    public Context context(){ return context; }
}
