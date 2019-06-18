package com.live.football.test.footballlive.view.Matches;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.live.football.test.footballlive.R;
import com.live.football.test.footballlive.models.Match;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.MatchesViewHolder> {

    private List<Match> resultList = new ArrayList<>();
    public static final String LOG_TAG = "FetchService";


    public MatchesAdapter() {
    }

    @NonNull
    @Override
    public MatchesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.matches_list_item,
                viewGroup, false);
        return new MatchesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesViewHolder matchesViewHolder, int i) {
        Match result = resultList.get(i);
        matchesViewHolder.TeamHome.setText(result.getHomeTeam().getName());
        matchesViewHolder.TeamAway.setText(result.getAwayTeam().getName());
        matchesViewHolder.MatchStatus.setText(result.getStatus());
        matchesViewHolder.HomeScore.setText(result.getScore().getFullTime().getHomeTeam()+"");
        matchesViewHolder.AwayScore.setText(result.getScore().getFullTime().getAwayTeam()+"");
        matchesViewHolder.TimeOfPlay.setText(fDate(result.getUtcDate()));
        matchesViewHolder.MatchDay.setText("MD:"+result.getSeason().getCurrentMatchday()+"");

    }

    @Override
    public int getItemCount() {
        return resultList != null ? resultList.size() : 0;

    }

    public void setItems(List<Match> results) {
        resultList = results;
        notifyDataSetChanged();
    }

    public String fDate(String input){
        String mDate = input;
        String mTime = mDate.substring(mDate.indexOf("T") + 1, mDate.indexOf("Z"));
        mDate = mDate.substring(0, mDate.indexOf("T"));

        SimpleDateFormat match_date = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        match_date.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            Date parsedDate = match_date.parse(mDate + mTime);
            SimpleDateFormat new_date = new SimpleDateFormat("yyyy-MM-dd:HH:mm");
            new_date.setTimeZone(TimeZone.getDefault());
            mDate = new_date.format(parsedDate);
            mTime = mDate.substring(mDate.indexOf(":") + 1);
            mDate = mDate.substring(0, mDate.indexOf(":"));

        }
        catch (Exception e) {
            Log.d(LOG_TAG, "error here!");
            Log.e(LOG_TAG,e.getMessage());
        }

        return mTime;
    }

    public class MatchesViewHolder extends RecyclerView.ViewHolder {
        public TextView TeamHome;
        public TextView TeamAway;
        public TextView TimeOfPlay;
        public TextView PlayTime;
        public TextView AwayScore;
        public TextView HomeScore;
        public TextView MatchStatus;
        public TextView MatchDay;



        public MatchesViewHolder(View itemView) {
            super(itemView);
            TeamHome = itemView.findViewById(R.id.team_home);
            TeamAway = itemView.findViewById(R.id.team_away);
            HomeScore = itemView.findViewById(R.id.home_score);
            AwayScore = itemView.findViewById(R.id.away_score);
            TimeOfPlay = itemView.findViewById(R.id.time_of_play);
            PlayTime = itemView.findViewById(R.id.play_time);
            MatchStatus = itemView.findViewById(R.id.match_status);
            MatchDay = itemView.findViewById(R.id.match_day);



        }
    }
}
