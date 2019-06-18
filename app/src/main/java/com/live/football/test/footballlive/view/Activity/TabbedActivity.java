package com.live.football.test.footballlive.view.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.live.football.test.footballlive.R;
import com.live.football.test.footballlive.view.Adapter.TabAdapter;
import com.live.football.test.footballlive.view.Fixtures.FixturesFragment;
import com.live.football.test.footballlive.view.Table.TableFragment;
import com.live.football.test.footballlive.view.Teams.TeamsFragment;

public class TabbedActivity extends AppCompatActivity {

    public static String LEAGUE_ID_EXTRA = "LEAGUE_ID_EXTRA";
    public static String LEAGUE_NAME_EXTRA = "LEAGUE_NAME_EXTRA";
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        int leagueId = getIntent().getIntExtra(LEAGUE_ID_EXTRA, -1);
        String teamName = getIntent().getStringExtra(LEAGUE_NAME_EXTRA);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar_tabbed);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        toolbarTitle = findViewById(R.id.toolbar_title);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        toolbarTitle.setText(teamName);
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(TableFragment.newInstance(leagueId), "Table");
        adapter.addFragment(new FixturesFragment(), "Fixtures");
        adapter.addFragment(TeamsFragment.newInstance(leagueId), "Teams");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(3);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
