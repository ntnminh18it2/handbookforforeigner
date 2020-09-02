package com.example.myapplication.models.place_reponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Place {
@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("image")
@Expose
private String image;
@SerializedName("introduce")
@Expose
private String introduce;
@SerializedName("overview")
@Expose
private String overview;
@SerializedName("arrayImageView")
@Expose
private String arrayImageView;
@SerializedName("id_ingredient")
@Expose
private Integer idIngredient;
@SerializedName("lat")
@Expose
private String lat;
@SerializedName("lng")
@Expose
private String lng;
@SerializedName("id_menu")
@Expose
private Integer idMenu;
@SerializedName("created_at")
@Expose
private Object createdAt;
@SerializedName("updated_at")
@Expose
private Object updatedAt;

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

public String getIntroduce() {
return introduce;
}

public void setIntroduce(String introduce) {
this.introduce = introduce;
}

public String getOverview() {
return overview;
}

public void setOverview(String overview) {
this.overview = overview;
}

public String getArrayImageView() {
return arrayImageView;
}

public void setArrayImageView(String arrayImageView) {
this.arrayImageView = arrayImageView;
}

public Integer getIdIngredient() {
return idIngredient;
}

public void setIdIngredient(Integer idIngredient) {
this.idIngredient = idIngredient;
}

public String getLat() {
return lat;
}

public void setLat(String lat) {
this.lat = lat;
}

public String getLng() {
return lng;
}

public void setLng(String lng) {
this.lng = lng;
}

public Integer getIdMenu() {
return idMenu;
}

public void setIdMenu(Integer idMenu) {
this.idMenu = idMenu;
}

public Object getCreatedAt() {
return createdAt;
}

public void setCreatedAt(Object createdAt) {
this.createdAt = createdAt;
}

public Object getUpdatedAt() {
return updatedAt;
}

public void setUpdatedAt(Object updatedAt) {
this.updatedAt = updatedAt;
}

}