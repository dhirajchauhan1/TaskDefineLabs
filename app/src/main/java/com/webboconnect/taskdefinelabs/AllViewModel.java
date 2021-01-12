package com.webboconnect.taskdefinelabs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.webboconnect.taskdefinelabs.Model.Matches;
import com.webboconnect.taskdefinelabs.repository.SavedRepository;

import java.util.List;


public class AllViewModel extends AndroidViewModel {

    private SavedRepository repository;
    private LiveData<List<Matches>> getAllMatches;

    public AllViewModel(@NonNull Application application) {
        super(application);
        repository =new SavedRepository(application);
        getAllMatches=repository.getGetAllMatches();
    }

    public void insert(Matches matches)
    {
        repository.insert(matches);
    }

    public LiveData<List<Matches>> getAllMatches()
    {
        return getAllMatches;
    }

}
