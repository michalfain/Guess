package com.example.guess;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GuessAdapter extends RecyclerView.Adapter<GuessAdapter.ViewHolder> {
    List<Attempt> attemptList;
    Context context;

    public GuessAdapter(List<Attempt> attemptList, Context context) {
        this.attemptList = attemptList;
        this.context = context;
    }

    @NonNull
    @Override
    public GuessAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GuessAdapter.ViewHolder holder, final int position) {
        holder.tvAttemptNo.setText(attemptList.get(position).counter + ".");
        holder.tvGuess.setText(attemptList.get(position).guess + " -");
        holder.tvScore.setText(attemptList.get(position).score);
    }

    @Override
    public int getItemCount() {
        return attemptList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvAttemptNo, tvGuess, tvScore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAttemptNo = itemView.findViewById(R.id.tvAttemptNo);
            tvGuess = itemView.findViewById(R.id.etGuess);
            tvScore = itemView.findViewById(R.id.tvScore);
        }
    }
}
