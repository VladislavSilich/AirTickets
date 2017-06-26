package com.task.example.silich.vladislav.taskwork.network.responce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelResponce {

    @SerializedName("cityNameFrom")
    @Expose
    private String cityNameFrom;
    @SerializedName("cityNameTo")
    @Expose
    private String cityNameTo;
    @SerializedName("iataFrom")
    @Expose
    private String iataFrom;
    @SerializedName("iataTo")
    @Expose
    private String iataTo;
    @SerializedName("airportNameFrom")
    @Expose
    private String airportNameFrom;
    @SerializedName("airportNameTo")
    @Expose
    private String airportNameTo;
    @SerializedName("depDate")
    @Expose
    private String depDate;
    @SerializedName("depTime")
    @Expose
    private String depTime;
    @SerializedName("arrDate")
    @Expose
    private String arrDate;
    @SerializedName("arrTime")
    @Expose
    private String arrTime;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("durationStr")
    @Expose
    private String durationStr;
    @SerializedName("airlineCode")
    @Expose
    private String airlineCode;
    @SerializedName("airlineName")
    @Expose
    private String airlineName;
    @SerializedName("flightNumber")
    @Expose
    private String flightNumber;
    @SerializedName("aircraft")
    @Expose
    private String aircraft;
    @SerializedName("airlineCode2")
    @Expose
    private String airlineCode2;
    @SerializedName("airlineName2")
    @Expose
    private String airlineName2;
    @SerializedName("flightNumber2")
    @Expose
    private String flightNumber2;
    @SerializedName("aircraft2")
    @Expose
    private String aircraft2;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("priceCurrency")
    @Expose
    private String priceCurrency;
    @SerializedName("code")
    @Expose
    private String code;

    public String getCityNameFrom() {
        return cityNameFrom;
    }

    public void setCityNameFrom(String cityNameFrom) {
        this.cityNameFrom = cityNameFrom;
    }

    public String getCityNameTo() {
        return cityNameTo;
    }

    public void setCityNameTo(String cityNameTo) {
        this.cityNameTo = cityNameTo;
    }

    public String getIataFrom() {
        return iataFrom;
    }

    public void setIataFrom(String iataFrom) {
        this.iataFrom = iataFrom;
    }

    public String getIataTo() {
        return iataTo;
    }

    public void setIataTo(String iataTo) {
        this.iataTo = iataTo;
    }

    public String getAirportNameFrom() {
        return airportNameFrom;
    }

    public void setAirportNameFrom(String airportNameFrom) {
        this.airportNameFrom = airportNameFrom;
    }

    public String getAirportNameTo() {
        return airportNameTo;
    }

    public void setAirportNameTo(String airportNameTo) {
        this.airportNameTo = airportNameTo;
    }

    public String getDepDate() {
        return depDate;
    }

    public void setDepDate(String depDate) {
        this.depDate = depDate;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getArrDate() {
        return arrDate;
    }

    public void setArrDate(String arrDate) {
        this.arrDate = arrDate;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDurationStr() {
        return durationStr;
    }

    public void setDurationStr(String durationStr) {
        this.durationStr = durationStr;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

    public String getAirlineCode2() {
        return airlineCode2;
    }

    public void setAirlineCode2(String airlineCode2) {
        this.airlineCode2 = airlineCode2;
    }

    public String getAirlineName2() {
        return airlineName2;
    }

    public void setAirlineName2(String airlineName2) {
        this.airlineName2 = airlineName2;
    }

    public String getFlightNumber2() {
        return flightNumber2;
    }

    public void setFlightNumber2(String flightNumber2) {
        this.flightNumber2 = flightNumber2;
    }

    public String getAircraft2() {
        return aircraft2;
    }

    public void setAircraft2(String aircraft2) {
        this.aircraft2 = aircraft2;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}