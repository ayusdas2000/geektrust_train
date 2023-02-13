package com.geektrust.backend.repositories;

import java.util.HashMap;
import com.geektrust.backend.entities.Train;

public interface ITrainRepository {
    Train saveTrain(Train train);

    Train findTrainByName(String trainName);

    void deleteTrain(String trainName);   

    HashMap<String,Train> getTrainMapping();     
}
