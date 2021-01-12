package com.webboconnect.taskdefinelabs.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "saved_matches", indices = @Index(value = {"id"},unique = true))
public class Matches {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "venue")
    private String venue;

    public Matches(String id, String name, String location, String address, String venue) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.address = address;
        this.venue = venue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}