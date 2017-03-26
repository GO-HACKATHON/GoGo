package com.gogo.model;

public class Response {
    private String busTrajectory;
    private String addressFrom;
    private String addressTo;
    private String arrivalTime;
    private String arrivalTimeAlternative;
    private String nearestBusStop;

    public Response() {}

    public String getBusTrajectory() {
        return busTrajectory;
    }

    public void setBusTrajectory(String busTrajectory) {
        this.busTrajectory = busTrajectory;
    }

    public String getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(String addressTo) {
        this.addressTo = addressTo;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getArrivalTimeAlternative() {
        return arrivalTimeAlternative;
    }

    public void setArrivalTimeAlternative(String arrivalTimeAlternative) {
        this.arrivalTimeAlternative = arrivalTimeAlternative;
    }

    public String getNearestBusStop() {
        return nearestBusStop;
    }

    public void setNearestBusStop(String nearestBusStop) {
        this.nearestBusStop = nearestBusStop;
    }
}
