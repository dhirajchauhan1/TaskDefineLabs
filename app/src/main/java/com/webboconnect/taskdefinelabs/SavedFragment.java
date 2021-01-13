package com.webboconnect.taskdefinelabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webboconnect.taskdefinelabs.Adapters.SavedAdapter;
import com.webboconnect.taskdefinelabs.Model.Matches;
import com.webboconnect.taskdefinelabs.repository.SavedRepository;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class SavedFragment extends Fragment implements OnClickDelete {
    private RecyclerView recyclerView;
    private SavedRepository repository;
    private List<Matches> matchesList = new ArrayList<>();
    private SavedAdapter savedAdapter;
    private AllViewModel allViewModel;


    public SavedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved, container, false);

        recyclerView = view.findViewById(R.id.recyclerSaved);
        repository = new SavedRepository(getActivity().getApplication());
        savedAdapter = new SavedAdapter(getContext(), matchesList,this);
        recyclerView.setAdapter(savedAdapter);
        savedAdapter.notifyDataSetChanged();
        allViewModel =new ViewModelProvider(this).get(AllViewModel.class);

        allViewModel.getAllMatches().observe(getActivity(), new Observer<List<Matches>>() {
            @Override
            public void onChanged(List<Matches> matches) {
                savedAdapter.initList(matches);
                recyclerView.setAdapter(savedAdapter);
                savedAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }


    @Override
    public void oClickDelete(Matches matches) {
        repository.delete(matches);
        Toasty.info(getContext(), "Deleted Successfully").show();
    }
}