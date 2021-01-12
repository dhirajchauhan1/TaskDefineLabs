package com.webboconnect.taskdefinelabs.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.webboconnect.taskdefinelabs.Model.Matches;

import java.util.List;

@Dao
public interface SavedDao {
    @Query("SELECT * FROM saved_matches")
    LiveData<List<Matches>> getAllMatches();

    @Query("DELETE FROM saved_matches")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Matches matches);

    @Delete
    void delete(Matches matches);
}