package com.example.guess;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.guess.MainActivity.playersListAttempts;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.history_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, final int position) {
        holder.tvPlace.setText(position + 1 + ". ");
        holder.tvName.setText(playersListAttempts.get(position).name + " - ");
        holder.tvAttemptNoHistory.setText(String.valueOf(playersListAttempts.get(position).attempts));
    }

    @Override
    public int getItemCount() {
        return playersListAttempts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvPlace, tvName, tvAttemptNoHistory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPlace = itemView.findViewById(R.id.tvPlace);
            tvName = itemView.findViewById(R.id.tvName);
            tvAttemptNoHistory = itemView.findViewById(R.id.tvAttemptNoHistory);
        }
    }
}
