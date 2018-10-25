package com.example.androidlearningapp.movies.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Movie implements MovieElement, Parcelable {
    public static final int TYPE = 1;
    private String image;
    private String name;
    private String nameEng;
    private String premiere;
    private String description;

    private Movie(Parcel parcel){
        String[] data = new String[5];
        parcel.readStringArray(data);
        image = data[0];
        name = data[1];
        nameEng = data[2];
        premiere = data[3];
        description = data[4];
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public void setPremiere(String premiere) {
        this.premiere = premiere;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getNameEng() {
        return nameEng;
    }

    public String getPremiere() {
        return premiere;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int getType() {
        return TYPE;
    }

    @Override
    public String getId() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(getImage(), movie.getImage()) &&
                Objects.equals(getName(), movie.getName()) &&
                Objects.equals(getNameEng(), movie.getNameEng()) &&
                Objects.equals(getPremiere(), movie.getPremiere()) &&
                Objects.equals(getDescription(), movie.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getImage(), getName(), getNameEng(), getPremiere(), getDescription());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{image, name, nameEng, premiere, description});
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>(){

        @Override
        public Movie createFromParcel(Parcel parcel){
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int size){
            return new Movie[size];
        }
    };
}
