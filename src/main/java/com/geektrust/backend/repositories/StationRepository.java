package com.geektrust.backend.repositories;

import java.util.HashMap;
import java.util.Map;
import com.geektrust.backend.entities.Station;

public class StationRepository implements IStationRepository {
    private HashMap<String, Station> stationMap;
    private Integer autoIncrement = 0;

    public StationRepository() {
        this.stationMap = new HashMap<String, Station>();
    }

    public StationRepository(HashMap<String,Station> stationMap){
        this.stationMap = stationMap;
    }

    @Override
    public Station saveStation(Station station) {
        if(station.getId() == null){
            autoIncrement++;
            Station newStation = new Station(Integer.toString(autoIncrement), station.getStationName(),
                    station.getStationCode(), station.getDistance());
            stationMap.put(newStation.getId(), newStation);
            return newStation;
        }
        stationMap.put(station.getId(), station);
        return station;        
    }

    @Override
    public Station findStationByCode(String stationCode) {
        Station station = stationMap.entrySet().stream().filter(e -> e.getValue().getStationCode().equals(stationCode))
                .map(Map.Entry::getValue).findFirst().get();
        return station;
    }

    @Override
    public HashMap<String, Station> getStationMap() {
        return stationMap;
    }
    
}
