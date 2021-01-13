package com.webboconnect.taskdefinelabs;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.EventLogTags;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.webboconnect.taskdefinelabs.Adapters.AllAdapter;
import com.webboconnect.taskdefinelabs.Model.Matches;
import com.webboconnect.taskdefinelabs.Model.data;
import com.webboconnect.taskdefinelabs.Model.venues;
import com.webboconnect.taskdefinelabs.network.ApiClient;
import com.webboconnect.taskdefinelabs.network.ApiInterface;
import com.webboconnect.taskdefinelabs.repository.SavedRepository;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllFragment extends Fragment implements OnClickSave {
    private RecyclerView recyclerView;
    private List<venues> venuesList = new ArrayList<>();
    private AllAdapter adapter;
    private SavedRepository repository;
    private List<Matches> matchesList = new ArrayList<>();
    private List<String> savedId = new ArrayList<>();
    private AllViewModel allViewModel;

    public AllFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_all, container, false);

       recyclerView = view.findViewById(R.id.recyclerAll);
        repository = new SavedRepository(getActivity().getApplication());
        adapter = new AllAdapter(getContext(),venuesList, this, savedId);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        allViewModel =new ViewModelProvider(this).get(AllViewModel.class);
        networkRequest();
        allViewModel.getAllMatches().observe(getActivity(), new Observer<List<Matches>>() {
            @Override
            public void onChanged(List<Matches> matches) {
                savedId.clear();
                for (int i = 0; i<matches.size(); i++){
                    savedId.add(matches.get(i).getId());
                }
            }
        });

        return view;
    }

    private void networkRequest() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
//?ll=40.7484,-73.9857&oauth_token=NPKYZ3WZ1VYMNAZ2FLX1WLECAWSMUVOQZOIDBN53F3LVZBPQ&v=20180616/
        Call<data> call = apiInterface.getData("40.7484,-73.9857","NPKYZ3WZ1VYMNAZ2FLX1WLECAWSMUVOQZOIDBN53F3LVZBPQ","20180616");
        call.enqueue(new Callback<data>() {
            @Override
            public void onResponse(Call<data> call, Response<data> response) {
                Log.d("res", new Gson().toJson(response.body().getResponse().getVenues()));
                venuesList = response.body().getResponse().getVenues();
                adapter.initList(venuesList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<data> call, Throwable t) {
                Log.d("res", t.getMessage()+"   ggg");
            }
        });
    }

    @Override
    public void onClickSave(Matches matches) {
        if (savedId.contains(matches.getId())){
            repository.delete(matches);
            Toasty.info(getContext(), "Deleted Successfully").show();
        }
        else {
            allViewModel.insert(matches);
            Toasty.success(getContext(), "Saved Successfully").show();
        }
       //adapter.notifyDataSetChanged();

    }
}