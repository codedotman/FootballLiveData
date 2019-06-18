package com.live.football.test.footballlive.di.di.module;

import android.content.Context;

import com.live.football.test.footballlive.di.di.ApplicationContext;
import com.live.football.test.footballlive.di.di.LiveFootballApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @ApplicationContext
    @LiveFootballApplicationScope
    @Provides
    public Context context(){ return context.getApplicationContext(); }
}
