package com.example.myapplication.models.menu_reponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Menu {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("icons")
@Expose
private String icons;
@SerializedName("id_table")
@Expose
private Integer idTable;
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

public String getIcons() {
return icons;
}

public void setIcons(String icons) {
this.icons = icons;
}

public Integer getIdTable() {
return idTable;
}

public void setIdTable(Integer idTable) {
this.idTable = idTable;
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