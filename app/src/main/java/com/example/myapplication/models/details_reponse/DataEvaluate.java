package com.example.myapplication.models.details_reponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class DataEvaluate {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("id_user")
@Expose
private Integer idUser;
@SerializedName("id_place")
@Expose
private Integer idPlace;
@SerializedName("comment")
@Expose
private String comment;
@SerializedName("rating")
@Expose
private Integer rating;
@SerializedName("like")
@Expose
private Integer like;
@SerializedName("created_at")
@Expose
private String createdAt;
@SerializedName("updated_at")
@Expose
private String updatedAt;
@SerializedName("name")
@Expose
private String name;
@SerializedName("avatar")
@Expose
private String avatar;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public Integer getIdUser() {
return idUser;
}

public void setIdUser(Integer idUser) {
this.idUser = idUser;
}

public Integer getIdPlace() {
return idPlace;
}

public void setIdPlace(Integer idPlace) {
this.idPlace = idPlace;
}

public String getComment() {
return comment;
}

public void setComment(String comment) {
this.comment = comment;
}

public Integer getRating() {
return rating;
}

public void setRating(Integer rating) {
this.rating = rating;
}

public Integer getLike() {
return like;
}

public void setLike(Integer like) {
this.like = like;
}

public String getCreatedAt() {
return createdAt;
}

public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

public String getUpdatedAt() {
return updatedAt;
}

public void setUpdatedAt(String updatedAt) {
this.updatedAt = updatedAt;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getAvatar() {
return avatar;
}

public void setAvatar(String avatar) {
this.avatar = avatar;
}

}