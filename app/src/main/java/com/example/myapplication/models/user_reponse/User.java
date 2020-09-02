package com.example.myapplication.models.user_reponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("email")
@Expose
private String email;
@SerializedName("phone")
@Expose
private Object phone;
@SerializedName("password")
@Expose
private String password;
@SerializedName("token")
@Expose
private String token;
@SerializedName("avatar")
@Expose
private String avatar;
@SerializedName("age")
@Expose
private Object age;
@SerializedName("gender")
@Expose
private Object gender;
@SerializedName("id_hierarchy")
@Expose
private Integer idHierarchy;
@SerializedName("created_at")
@Expose
private String createdAt;
@SerializedName("updated_at")
@Expose
private String updatedAt;

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

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public Object getPhone() {
return phone;
}

public void setPhone(Object phone) {
this.phone = phone;
}

public String getPassword() {
return password;
}

public void setPassword(String password) {
this.password = password;
}

public String getToken() {
return token;
}

public void setToken(String token) {
this.token = token;
}

public String getAvatar() {
return avatar;
}

public void setAvatar(String avatar) {
this.avatar = avatar;
}

public Object getAge() {
return age;
}

public void setAge(Object age) {
this.age = age;
}

public Object getGender() {
return gender;
}

public void setGender(Object gender) {
this.gender = gender;
}

public Integer getIdHierarchy() {
return idHierarchy;
}

public void setIdHierarchy(Integer idHierarchy) {
this.idHierarchy = idHierarchy;
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

}