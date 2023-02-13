package com.geektrust.backend.repositories;

import java.util.HashMap;
import com.geektrust.backend.entities.Station;

public interface IStationRepository {
    Station saveStation(Station station);

    Station findStationByCode(String stationCode); 
    
    HashMap<String, Station> getStationMap();
}
