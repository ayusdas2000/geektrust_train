package com.geektrust.backend.entities;

public class Station extends BaseEntitiy {
    private String stationName;
    private String stationCode;
    private int distance;

    public Station(String id, String stationName, String stationCode, int distance) {
        this.id = id;
        this.stationName = stationName;
        this.stationCode = stationCode;
        this.distance = distance;
    }

    public Station(String stationName, String stationCode, int distance) {
        this.stationName = stationName;
        this.stationCode = stationCode;
        this.distance = distance;
    }

    public String getStationName() {
        return stationName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public int getDistance() {
        return distance;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Station))
            return false;
        Station station = (Station) o;
        return getId().equals(station.getId());
    } 
}
