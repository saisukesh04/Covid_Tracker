package com.example.covidtracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidtracker.R;
import com.example.covidtracker.classes.Statewise;

import java.util.List;

public class StatesAdapter extends RecyclerView.Adapter<StatesAdapter.ViewHolder> {

    private List<Statewise> stateData;
    private Context context;

    public StatesAdapter(Context context, List<Statewise> stateData) {
        this.context = context;
        this.stateData = stateData;
    }

    @NonNull
    @Override
    public StatesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.state_item_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatesAdapter.ViewHolder holder, int position) {
        Statewise statewise = stateData.get(position);
        holder.state_name_text.setText(statewise.getState());
        holder.total_count.setText(statewise.getConfirmed());
        holder.active_count.setText(statewise.getActive());
        holder.recovered_count.setText(statewise.getRecovered());
        holder.death_count.setText(statewise.getDeaths());
        holder.increase_total_count.setText(statewise.getDeltaconfirmed());
        holder.increase_active_count.setText(statewise.getDeltaconfirmed());
        holder.increase_recovered_count.setText(statewise.getDeltarecovered());
        holder.increase_death_count.setText(statewise.getDeltadeaths());
    }

    @Override
    public int getItemCount() {
        return stateData != null ? stateData.size() :0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView total_count, active_count, recovered_count, death_count, state_name_text;
        TextView increase_total_count, increase_active_count, increase_recovered_count, increase_death_count;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            state_name_text = itemView.findViewById(R.id.state_name_text);
            total_count = itemView.findViewById(R.id.total_count);
            active_count = itemView.findViewById(R.id.active_count);
            recovered_count = itemView.findViewById(R.id.recovered_count);
            death_count = itemView.findViewById(R.id.death_count);
            increase_total_count = itemView.findViewById(R.id.increase_total_count);
            increase_active_count = itemView.findViewById(R.id.increase_active_count);
            increase_recovered_count = itemView.findViewById(R.id.increase_recovered_count);
            increase_death_count = itemView.findViewById(R.id.increase_death_count);
        }
    }
}
