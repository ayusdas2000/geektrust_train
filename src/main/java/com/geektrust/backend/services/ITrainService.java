package com.geektrust.backend.services;

import java.util.List;
import com.geektrust.backend.entities.Train;

public interface ITrainService {
    Train mergeTrain(String trainAName, String trainBName);

    Train travel(String trainName, String routeName, String dest);

    Train createTrain(String trainName, List<String> boggies);    
}
