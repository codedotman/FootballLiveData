package com.live.football.test.footballlive.di.di.module;

import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.live.football.test.footballlive.di.di.ApplicationContext;
import com.live.football.test.footballlive.di.di.LiveFootballApplicationScope;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module(includes = OkHttpClientModule.class)
public class PicassoModule {

    @LiveFootballApplicationScope
    @Provides
    public Picasso picasso(@ApplicationContext Context context, OkHttp3Downloader okHttp3Downloader){
        return new Picasso.Builder(context).
                downloader(okHttp3Downloader).
                build();
    }

    @Provides
    public OkHttp3Downloader okHttp3Downloader(OkHttpClient okHttpClient){
        return new OkHttp3Downloader(okHttpClient);
    }
}
