package com.example.androidlearningapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name_eng")
    @Expose
    private String nameEng;
    @SerializedName("premiere")
    @Expose
    private String premiere;
    @SerializedName("description")
    @Expose
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

    public boolean equals(Object obj)
    {
        if(obj == this)
            return true;

        if(obj == null)
            return false;

        if(!(getClass() == obj.getClass()))
            return false;
        else
        {
            Movie tmp = (Movie)obj;
            return tmp.getName().equals(this.name) &&
                    tmp.getImage().equals(this.image) &&
                    tmp.getNameEng().equals(this.nameEng) &&
                    tmp.getDescription().equals(this.description) &&
                    tmp.getPremiere().equals(this.premiere);
        }
    }

}
