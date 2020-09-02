package com.example.myapplication.models.details_reponse;

import java.util.List;

import com.example.myapplication.models.place_reponse.Place;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailsReponse {

@SerializedName("message")
@Expose
private String message;
@SerializedName("statuscode")
@Expose
private String statuscode;
@SerializedName("totalPlace")
@Expose
private Integer totalPlace;
@SerializedName("totalEvaluate")
@Expose
private Integer totalEvaluate;
@SerializedName("data")
@Expose
private List<Place> data = null;
@SerializedName("dataEvaluate")
@Expose
private List<DataEvaluate> dataEvaluate = null;

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

public Integer getTotalPlace() {
return totalPlace;
}

public void setTotalPlace(Integer totalPlace) {
this.totalPlace = totalPlace;
}

public Integer getTotalEvaluate() {
return totalEvaluate;
}

public void setTotalEvaluate(Integer totalEvaluate) {
this.totalEvaluate = totalEvaluate;
}

public List<Place> getData() {
return data;
}

public void setData(List<Place> data) {
this.data = data;
}

public List<DataEvaluate> getDataEvaluate() {
return dataEvaluate;
}

public void setDataEvaluate(List<DataEvaluate> dataEvaluate) {
this.dataEvaluate = dataEvaluate;
}

}