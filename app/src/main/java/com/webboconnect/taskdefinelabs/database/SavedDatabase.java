package com.webboconnect.taskdefinelabs.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.webboconnect.taskdefinelabs.Model.Matches;
import com.webboconnect.taskdefinelabs.dao.SavedDao;


@Database(entities = {Matches.class}, version = 1)
public abstract class SavedDatabase extends RoomDatabase {
    private static final String DATABASE_NAME="MatchesDatabase";

    public abstract SavedDao dao();

    private static volatile SavedDatabase INSTANCE;
    public static SavedDatabase getInstance(Context context){
        if(INSTANCE == null)
        {
            synchronized (SavedDatabase.class){
                if(INSTANCE == null)
                {
                    INSTANCE= Room.databaseBuilder(context, SavedDatabase.class,
                            DATABASE_NAME)
                            .addCallback(callback)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    static Callback callback=new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new SavedDatabase.PopulateAsynTask(INSTANCE);
        }
    };
    static class PopulateAsynTask extends AsyncTask<Void, Void, Void>
    {
        private SavedDao dao;

        PopulateAsynTask(SavedDatabase database)
        {
            dao=database.dao();

        }
        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAll();
            return null;
        }
    }
}