package com.example.myapplication.models.event_reponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("image")
@Expose
private String image;
@SerializedName("updated_at")
@Expose
private String updated_at;

public String getUpdated_at() {
        return updated_at;
    }

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getImage() {
return image;
}

public void setImage(String image) {
this.image = image;
}

}