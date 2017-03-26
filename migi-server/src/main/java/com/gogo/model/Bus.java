package com.gogo.model;

import java.util.List;

public class Bus {
    private String ID_Bus, state, route, isPenuh, arrivalTime;
    private List Halte;

    public String getID_Bus() {
        return ID_Bus;
    }

    public void setID_Bus(String ID_Bus) {
        this.ID_Bus = ID_Bus;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getIsPenuh() {
        return isPenuh;
    }

    public void setIsPenuh(String isPenuh) {
        this.isPenuh = isPenuh;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public List getHalte() {
        return Halte;
    }

    public void setHalte(List halte) {
        Halte = halte;
    }
}
