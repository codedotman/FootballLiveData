package com.live.football.test.footballlive.di.di.component;

import com.live.football.test.footballlive.data.network.Api;
import com.live.football.test.footballlive.di.di.LiveFootballApplicationScope;
import com.live.football.test.footballlive.di.di.module.LiveFootballModule;
import com.live.football.test.footballlive.di.di.module.PicassoModule;
import com.squareup.picasso.Picasso;

import dagger.Component;

@LiveFootballApplicationScope
@Component(modules = {LiveFootballModule.class, PicassoModule.class})
public interface LiveFootballComponent {

    Api getLiveFootballService();

    Picasso getPicasso();


}
