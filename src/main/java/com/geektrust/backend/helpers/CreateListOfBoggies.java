package com.geektrust.backend.helpers;

import java.util.LinkedList;
import java.util.List;
import com.geektrust.backend.entities.Boggy;
import com.geektrust.backend.entities.Station;
import com.geektrust.backend.repositories.IBoggyRepository;
import com.geektrust.backend.repositories.IStationRepository;

public class CreateListOfBoggies {
    private static Boggy createBoggy(IBoggyRepository iBoggyRepository,IStationRepository iStationRepository, String boggyName){
        Boggy boggy;
        if (boggyName.equals("ENGINE")) {
            boggy = new Boggy(null);
            boggy = iBoggyRepository.saveBoggy(boggy);
        } else {
            // Find the station
            Station stn = iStationRepository.findStationByCode(boggyName);
            boggy = new Boggy(stn);
            boggy = iBoggyRepository.saveBoggy(boggy);
        }
        return boggy;
    }
    public static LinkedList<Boggy> createListOfBoggies(IBoggyRepository iBoggyRepository, IStationRepository iStationRepository, List<String> boggies){
        LinkedList<Boggy> newBoggies = new LinkedList<Boggy>();
        for (String str : boggies) {
            newBoggies.add(createBoggy(iBoggyRepository,iStationRepository,str));
        }
        return newBoggies;
    }    
}
