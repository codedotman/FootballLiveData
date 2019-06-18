package com.live.football.test.footballlive.view.Competitions;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.live.football.test.footballlive.R;
import com.live.football.test.footballlive.models.Competition;

import java.util.ArrayList;
import java.util.List;



public class CompetitionAdapter extends RecyclerView.Adapter<CompetitionAdapter.CompetitionViewHolder> {

    private List<Competition> resultList = new ArrayList<>();
    private ListItemClickListener listItemClickListener;

    public interface ListItemClickListener {
        void onListItemClick(int leagueId, String leagueName);
    }

    public CompetitionAdapter(ListItemClickListener listItemClickListener) {
        this.listItemClickListener = listItemClickListener;

    }

    @Override
    public CompetitionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.competition_list_item,
                parent, false);
        return new CompetitionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CompetitionViewHolder holder, int position) {
        Competition result = resultList.get(position);
        holder.textView.setText(result.getName());
    }

    @Override
    public int getItemCount() {
        return resultList != null ? resultList.size() : 0;
    }

    public void setItems(List<Competition> results) {
        resultList = results;
        notifyDataSetChanged();
    }

    public class CompetitionViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        public TextView textView;

        public CompetitionViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtCompetitions);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Competition user = resultList.get(getAdapterPosition());
            int LeagueId = user.getId();
            String LeagueName = user.getName();
            listItemClickListener.onListItemClick(LeagueId,LeagueName);

        }
    }
}
