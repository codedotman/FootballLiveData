package com.live.football.test.footballlive.di.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.live.football.test.footballlive.data.network.Api;
import com.live.football.test.footballlive.data.network.LiveDataCallAdapterFactory;
import com.live.football.test.footballlive.di.di.LiveFootballApplicationScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = OkHttpClientModule.class)
public class LiveFootballModule {

    @Provides
    public Api LiveFootballApi(Retrofit retrofit){
        return retrofit.create(Api.class);
    }

    @LiveFootballApplicationScope
    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient,
                             GsonConverterFactory gsonConverterFactory, Gson gson){
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.football-data.org/v2/")
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    public Gson gson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    public GsonConverterFactory gsonConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }

}
