package com.example.track_covid_19.ui.india;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.track_covid_19.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.track_covid_19.ui.country.CovidCountry;

import java.util.ArrayList;
import java.util.List;

public class stateadapter extends RecyclerView.Adapter<stateadapter.ViewHolder> implements Filterable{


    private List<india_state> states;
    private List<india_state> statesFull;


    public stateadapter(List<india_state> states, Context context) {
        this.states = states;
        statesFull = new ArrayList<>(states);
    }

    @NonNull
    @Override
    public stateadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_states, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull stateadapter.ViewHolder holder, int position) {
        india_state india_state1= states.get(position);
        holder.tvTotalCases.setText(india_state1.getmCases());
        holder.tvstateName.setText(india_state1.getmindia_state());
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTotalCases,tvstateName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTotalCases = itemView.findViewById(R.id.tvTotalCases);
            tvstateName = itemView.findViewById(R.id.tvstateName);
        }
    }

    @Override
    public  Filter getFilter() {
        return statesFilter;
    }

    private Filter statesFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<india_state> filteredindia_state = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredindia_state.addAll(statesFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (india_state itemindia_state : statesFull) {
                    if (itemindia_state. getmindia_state().toLowerCase().contains(filterPattern)) {
                        filteredindia_state.add(itemindia_state);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredindia_state;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            states.clear();
            states.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
