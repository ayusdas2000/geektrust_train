package com.geektrust.backend.entities;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RouteTest {
    private Route route;

    @BeforeEach()
    void setup(){
        LinkedList<Station> stns = new LinkedList<Station>(){
            {
                add(new Station("1", "New Delhi", "NDL", 0));
                add(new Station("2", "New Jalpaiguri", "NJP", 3000));
            }       
        };
        route = new Route("1", "abc", stns, 1400);
    }

    @Test
    @DisplayName("#1 Check if station is present")
    public void check_if_station_present(){
        //Arrange
        Station stn = new Station("1", "New Delhi", "NDL", 0);
        //Assert
        assertTrue(route.isStationPresent(stn));
    }

    
}
