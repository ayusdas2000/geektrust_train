package com.geektrust.backend.helpers;

import java.util.Comparator;
import java.util.List;
import com.geektrust.backend.entities.Boggy;
import com.geektrust.backend.entities.Route;
import com.geektrust.backend.entities.Station;
import com.geektrust.backend.repositories.IRouteRepository;

public class SortBoggies implements Comparator<Boggy>{
    IRouteRepository iRouteRepository;
    public SortBoggies(IRouteRepository iRouteRepository){
        this.iRouteRepository = iRouteRepository;
    }

    @Override
    public int compare(Boggy o1, Boggy o2) {
        if(o1.getDestStation() == null){
            return -1;
        }
        if(o2.getDestStation() == null){
            return 1;
        }
        List<Route> routes = iRouteRepository.getAllRoutes();
        // Find the dest station
        Station o1Station = o1.getDestStation();
        // Find in which route it belongs and get the distance of hyderabad in that
        // route
        int hyderabadDistanceOfo1 = 0;
        for (Route route : routes) {
            if (route.getStations().contains(o1Station)) {
                hyderabadDistanceOfo1 = route.getHyderabadDistance();
            }
        }

        // similarly calculate hyderabad distance of o2
        Station o2Station = o2.getDestStation();
        int hyderabadDistanceOfo2 = 0;
        for (Route route : routes) {
            if (route.getStations().contains(o2Station)) {
                hyderabadDistanceOfo2 = route.getHyderabadDistance();
            }
        }

        int o1DistanceToTravel = o1Station.getDistance() - hyderabadDistanceOfo1;
        int o2DistanceToTravel = o2Station.getDistance() - hyderabadDistanceOfo2;

        return o2DistanceToTravel - o1DistanceToTravel;
    }

}