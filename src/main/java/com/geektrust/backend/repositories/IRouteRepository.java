package com.geektrust.backend.repositories;

import java.util.List;
import com.geektrust.backend.entities.Route;

public interface IRouteRepository {
    Route saveRoute(Route route);

    Route findRouteByName(String routeName);

    List<Route> getAllRoutes();    
}
