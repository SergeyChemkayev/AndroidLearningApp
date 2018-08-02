package com.example.androidlearningapp;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Movie {
    @SerializedName("image")
    private String image;
    @SerializedName("name")
    private String name;
    @SerializedName("name_eng")
    private String nameEng;
    @SerializedName("premiere")
    private String premiere;
    @SerializedName("description")
    private String description;

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
}
