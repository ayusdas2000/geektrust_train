package com.geektrust.backend.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;
import java.util.LinkedList;
import com.geektrust.backend.entities.Route;
import com.geektrust.backend.entities.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RouteRepositoryTest {
    private RouteRepository routeRepository;
    
    @BeforeEach
    public void setup(){
        HashMap<String, Route> routeMapping = new HashMap<String,Route>();
        LinkedList<Station> stations = new LinkedList<Station>(){
            {
                add(new Station("1", "New Delhi", "NDL", 0));
                add(new Station("2", "New Jalpaiguri", "NJP", 3000));
            }
        };
        routeMapping.put("1", new Route("1", "abc1", stations, 1200));
        routeRepository = new RouteRepository(routeMapping);
    }

    @Test
    @DisplayName("#1 Save route must save route in repo")
    public void save_route_test(){
        //Arrange
        LinkedList<Station> stationsToBeAdded = new LinkedList<Station>(){
            {
                add(new Station("3", "Chennai", "CHN", 80));
                add(new Station("4", "Salem", "SLM", 2000));
            }
        };
        Route route = new Route("2", "def2", stationsToBeAdded, 2000);
        //Act
        routeRepository.saveRoute(route);
        //Assert
        assertEquals(2, routeRepository.getAllRoutes().size());
    }

    @Test
    @DisplayName("#2 Find Route by route name test")
    public void find_route_by_name_test(){
        //Act
        Route route = routeRepository.findRouteByName("abc1");
        //Assert
        assertEquals("1", route.getId());
    }
}
