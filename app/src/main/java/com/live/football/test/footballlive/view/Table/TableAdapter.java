package com.live.football.test.footballlive.view.Table;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.live.football.test.footballlive.R;
import com.live.football.test.footballlive.models.Table;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TableViewHolder> {

    private List<Table> resultList = new ArrayList<>();
    private Context mContext;
    private Picasso mPicasso;

    public TableAdapter(Context context, Picasso picasso) {
        this.mContext = context;
        this.mPicasso = picasso;
    }

    @NonNull
    @Override
    public TableViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.table_item,
                viewGroup, false);
        return new TableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TableViewHolder tableViewHolder, int i) {
        Table result = resultList.get(i);
        tableViewHolder.TeamName.setText(result.getTeam().getName()+"");
        tableViewHolder.TeamPosition.setText(result.getPosition()+"");
        tableViewHolder.TeamPoints.setText(result.getPoints()+"");
        tableViewHolder.TeamGDiff.setText(result.getGoalDifference()+"");
        tableViewHolder.MatchesPlayed.setText(result.getPlayedGames()+"");
        mPicasso.with(mContext)
                .load(result.getTeam().getCrestUrl()).error(R.drawable.default_crest)
                .into(tableViewHolder.TeamCrest);

    }

    @Override
    public int getItemCount() {
        return resultList != null ? resultList.size() : 0;
    }

    public void setItems(List<Table> results) {
        resultList = results;
        notifyDataSetChanged();
    }

    public class TableViewHolder extends RecyclerView.ViewHolder {
        public TextView  TeamName;
        public ImageView TeamCrest;
        public TextView  TeamPosition;
        public TextView  TeamPoints;
        public TextView  TeamGDiff;
        public TextView  MatchesPlayed;

        public TableViewHolder(View itemView) {
            super(itemView);
            TeamName = itemView.findViewById(R.id.team_name);
            TeamCrest = itemView.findViewById(R.id.team_crest);
            TeamPosition = itemView.findViewById(R.id.team_position);
            TeamPoints = itemView.findViewById(R.id.team_points);
            TeamGDiff = itemView.findViewById(R.id.goal_diff);
            MatchesPlayed = itemView.findViewById(R.id.matches_played);

        }

    }
}
