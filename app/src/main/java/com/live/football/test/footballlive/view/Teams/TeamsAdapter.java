package com.live.football.test.footballlive.view.Teams;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.live.football.test.footballlive.R;
import com.live.football.test.footballlive.models.Teams;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder> {

    private List<Teams> resultList = new ArrayList<>();
    private Context mContext;
    private Picasso mPicasso;

    public TeamsAdapter(Context context, Picasso picasso) {
        this.mContext = context;
        this.mPicasso = picasso;

    }

    @NonNull
    @Override
    public TeamsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.teams_list_item,
                viewGroup, false);
        return new TeamsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamsViewHolder teamsViewHolder, int i) {
        Teams result = resultList.get(i);
        teamsViewHolder.TeamName.setText(result.getName());
        if (result.getCrestUrl() !=null && !result.getCrestUrl().isEmpty()) {
            mPicasso.with(mContext)
                    .load(result.getCrestUrl()).error(R.drawable.default_crest)
                    .into(teamsViewHolder.TeamCrest);
        } else{
            teamsViewHolder.TeamCrest.setImageResource(R.drawable.default_crest);
        }


    }
    @Override
    public int getItemCount() {
        return resultList != null ? resultList.size() : 0;
    }


    public void setItems(List<Teams> results) {
        resultList = results;
        notifyDataSetChanged();
    }


    public class TeamsViewHolder extends RecyclerView.ViewHolder {
        public TextView TeamName;
        public ImageView TeamCrest;


        public TeamsViewHolder(View itemView) {
            super(itemView);
            TeamName = itemView.findViewById(R.id.list_team_name);
            TeamCrest = itemView.findViewById(R.id.team_img);

        }

    }
}
