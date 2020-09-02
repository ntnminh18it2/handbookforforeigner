package com.example.myapplication.models.menu_reponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuReponse {

@SerializedName("message")
@Expose
private String message;
@SerializedName("statuscode")
@Expose
private String statuscode;
@SerializedName("total")
@Expose
private Integer total;
@SerializedName("data")
@Expose
private List<Menu> data = null;

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public String getStatuscode() {
return statuscode;
}

public void setStatuscode(String statuscode) {
this.statuscode = statuscode;
}

public Integer getTotal() {
return total;
}

public void setTotal(Integer total) {
this.total = total;
}

public List<Menu> getData() {
return data;
}

public void setData(List<Menu> data) {
this.data = data;
}

}