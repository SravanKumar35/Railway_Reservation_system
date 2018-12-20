package com.example.sravankumar.myapplication.Unused;

public class Movie {

    // Store the name of the movie
    private String mName;
    // Store the release date of the movie
    private String mRelease;

    // Constructor that is used to create an instance of the Movie object
    public Movie(String mName, String mRelease) {
        this.mName = mName;
        this.mRelease = mRelease;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmRelease() {
        return mRelease;
    }

    public void setmRelease(String mRelease) {
        this.mRelease = mRelease;
    }
}