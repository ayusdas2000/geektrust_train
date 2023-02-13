package com.geektrust.backend.helpers;

import java.util.LinkedList;
import java.util.List;
import com.geektrust.backend.entities.Boggy;
import com.geektrust.backend.entities.Station;

public class GenerateListOfBoggiesToBeRemoved {
    private static void detachedBoggies(Station station, LinkedList<Boggy> boggies,List<Boggy> boggiesToBeRemoved){
        for (Boggy boggy : boggies) {
            if (boggy.getDestStation()!= null && boggy.getDestStation().equals(station)) {
                boggiesToBeRemoved.add(boggy);
            }
        }
    }
    public static List<Boggy> generateListOfBoggiesToBeRemoved(LinkedList<Station> stations,LinkedList<Boggy> boggies,String dest ){
        List<Boggy> boggiesToBeRemoved = new LinkedList<Boggy>();
        for (Station stn : stations) {
            if (stn.getStationCode().equals(dest)) {
                break;
            }
            detachedBoggies(stn, boggies,boggiesToBeRemoved);       
        }  
        return boggiesToBeRemoved;      
    }  
}
