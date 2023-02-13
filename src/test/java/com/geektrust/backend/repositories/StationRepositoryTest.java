package com.geektrust.backend.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;
import com.geektrust.backend.entities.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StationRepositoryTest {
    private StationRepository stationRepository;    

    @BeforeEach
    public void setup(){
        HashMap<String, Station> stationMap = new HashMap<String, Station>();
        stationMap.put("1", new Station("1", "New Delhi", "NDL", 0));
        stationRepository = new StationRepository(stationMap);
    }

    @Test
    @DisplayName("#1 Save station must save station inside repo")
    public void save_station_test(){
        //Arrange
        Station station = new Station("2", "New Jalpaiguri", "NJP", 3000);
        //Act
        stationRepository.saveStation(station);
        //Assert
        assertEquals(2, stationRepository.getStationMap().size());

    }

    @Test
    @DisplayName("#2 Find station by station code test")
    public void find_station_by_code_test(){
        //Act
        Station stn = stationRepository.findStationByCode("NDL");
        assertEquals("1", stn.getId());
    }
}
