package com.webboconnect.taskdefinelabs.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.webboconnect.taskdefinelabs.Model.Matches;
import com.webboconnect.taskdefinelabs.OnClickDelete;
import com.webboconnect.taskdefinelabs.R;

import java.util.List;

public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.ViewHolder> {
    private Context context;
    private List<Matches> matchesList;
    private OnClickDelete onClickDelete;

    public SavedAdapter(Context context, List<Matches> matchesList, OnClickDelete onClickDelete) {
        this.context = context;
        this.matchesList = matchesList;
        this.onClickDelete = onClickDelete;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Matches matches = matchesList.get(position);

        holder.id.setText("id: "+matches.getId());
        holder.name.setText("name: "+matches.getName());
        holder.loc.setText("location: "+matches.getLocation());

        holder.address.setText("Address: "+matches.getAddress());
        holder.venue.setText("venue name: "+matches.getVenue());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDelete.oClickDelete(matches);
            }
        });

    }

    @Override
    public int getItemCount() {
        return matchesList.size();
    }

    public void initList(List<Matches> matches) {
        matchesList = matches;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView id, name, loc, address, venue;
        private ImageView delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id._id);
            name = itemView.findViewById(R.id._name);
            loc = itemView.findViewById(R.id._locations);
            address = itemView.findViewById(R.id._address);
            venue = itemView.findViewById(R.id._venue);
            delete = itemView.findViewById(R.id._delete);
        }
    }
}
