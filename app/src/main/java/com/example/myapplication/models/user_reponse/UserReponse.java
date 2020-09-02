package com.example.myapplication.models.user_reponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserReponse {

@SerializedName("status")
@Expose
private String status;
@SerializedName("data")
@Expose
private User user;

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public User getUser() {
return user;
}

public void setUser(User user) {
this.user = user;
}

}