package com.webboconnect.taskdefinelabs.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.webboconnect.taskdefinelabs.Model.Matches;
import com.webboconnect.taskdefinelabs.dao.SavedDao;
import com.webboconnect.taskdefinelabs.database.SavedDatabase;

import java.util.List;

public class SavedRepository {
    private SavedDatabase database;
    private LiveData<List<Matches>> getAllMatches;

    public SavedRepository(Application application)
    {
        database = database.getInstance(application);
        getAllMatches=database.dao().getAllMatches();
    }

    public void insert(Matches matches){
        new InsertAsyncTask(database).execute(matches);
    }

    public void delete(Matches matches){
        new DeleteAsyncTask(database).execute(matches);
    }


    public LiveData<List<Matches>> getGetAllMatches()
    {
        return getAllMatches;
    }


    static class InsertAsyncTask extends AsyncTask<Matches, Void, Void> {
        private SavedDao dao;
        InsertAsyncTask(SavedDatabase database)
        {
            dao = database.dao();
        }

        @Override
        protected Void doInBackground(Matches... matches) {
            dao.insert(matches[0]);
            return null;
        }
    }


    public class DeleteAsyncTask extends AsyncTask<Matches, Void, Void>{
        private SavedDao dao;

        public DeleteAsyncTask(SavedDatabase database) {
            dao = database.dao();
        }


        @Override
        protected Void doInBackground(Matches... matches) {
            dao.delete(matches[0]);
            return null;
        }
    }

}
