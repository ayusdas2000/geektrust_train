package com.geektrust.backend.helpers;

import java.util.ArrayList;
import java.util.List;
import com.geektrust.backend.entities.Boggy;
import com.geektrust.backend.entities.Train;

public class PrintTrain {
    public static void printTrain(Train t,String arrivalOrDeparture){
        List<String> boggies = new ArrayList<String>();
        for(Boggy boggy:t.getBoggies()){
            if(boggy.getDestStation() == null){
                boggies.add("ENGINE");
            }else{
                boggies.add(boggy.getDestStationCode());
            }
        }
        System.out.print(arrivalOrDeparture + " "+ t.getTrainName() + " " + String.join(" ",boggies));
        System.out.println();
    }    
}
