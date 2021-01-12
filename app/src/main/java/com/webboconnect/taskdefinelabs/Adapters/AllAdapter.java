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
import com.webboconnect.taskdefinelabs.Model.categories;
import com.webboconnect.taskdefinelabs.Model.location;
import com.webboconnect.taskdefinelabs.Model.venues;
import com.webboconnect.taskdefinelabs.OnClickSave;
import com.webboconnect.taskdefinelabs.R;

import java.util.HashSet;
import java.util.List;

public class AllAdapter extends RecyclerView.Adapter<AllAdapter.ViewHolder> {
    private Context context;
    private List<venues> venuesList;
    private OnClickSave onClickSave;
    private List<String> savedId;

    public AllAdapter(Context context, List<venues> venuesList, OnClickSave onClickSave, List<String> savedId) {
        this.context = context;
        this.venuesList = venuesList;
        this.onClickSave = onClickSave;
        this.savedId = savedId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        venues venues = venuesList.get(position);
        location loc = venues.getLocation();
        List<categories> cat = venues.getCategories();

        holder.id.setText("id: "+cat.get(0).getId());
        holder.name.setText("name: "+cat.get(0).getName());
        String s = "address: "+ loc.getAddress()+",\n"+
                   "street: "+ loc.getCrossStreet()+",\n"+
                   "lat: "+ loc.getLat()+",\n"+
                   "lang: "+ loc.getLng()+",\n"+
                   "postal code: "+ loc.getPostalCode()+",\n"+
                   "city: "+ loc.getCity()+",\n"+
                   "state: "+ loc.getState()+",\n"+
                   "country: "+ loc.getCountry()+",\n"
                ;

        holder.loc.setText("location: "+s);

        holder.address.setText("Address: "+loc.getFormattedAddress().toString());
        holder.venue.setText("venue name: "+venues.getName());
        Log.e("saveId", savedId.toString());
        if (savedId.contains(cat.get(0).getId())){
            Log.e("id", cat.get(0).getId()+"\n");
            holder._heart.setBackgroundResource(R.drawable.ic_heart_filled);
        }

        Matches matches = new Matches(cat.get(0).getId(), cat.get(0).getName(),s,loc.getFormattedAddress().toString(),venues.getName());

        holder._heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSave.onClickSave(matches);
                if (savedId.contains(cat.get(0).getId())){
                    holder._heart.setBackgroundResource(R.drawable.ic_heart_outline_);
                }
                else {
                    holder._heart.setBackgroundResource(R.drawable.ic_heart_filled);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return venuesList.size();
    }

    public void initList(List<venues> venuesList) {
        this.venuesList = venuesList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView id, name, loc, address, venue;
        private ImageView _heart;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id._id);
            name = itemView.findViewById(R.id._name);
            loc = itemView.findViewById(R.id._locations);
            address = itemView.findViewById(R.id._address);
            venue = itemView.findViewById(R.id._venue);
            _heart = itemView.findViewById(R.id._heart);

        }
    }
}
