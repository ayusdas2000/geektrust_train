package com.geektrust.backend.repositories;

import java.util.HashMap;
import java.util.Map;
import com.geektrust.backend.entities.Train;

public class TrainRepository implements ITrainRepository {
    private HashMap<String, Train> trainMapping;
    private Integer autoIncrement = 0;

    public TrainRepository() {
        this.trainMapping = new HashMap<String, Train>();
    }

    public TrainRepository(HashMap<String,Train> trainMapping){
        this.trainMapping = trainMapping;
    }

    @Override
    public Train saveTrain(Train train) {
        if(train.getId() == null){
            autoIncrement++;
        Train newTrain = new Train(Integer.toString(autoIncrement), train.getTrainName(), train.getBoggies());
        trainMapping.put(newTrain.getId(), newTrain);
        return newTrain;
        }
        trainMapping.put(train.getId(), train);
        return train;
    }

    @Override
    public Train findTrainByName(String trainName) {
        Train train = trainMapping.entrySet().stream().filter(e -> e.getValue().getTrainName().equals(trainName))
                .map(Map.Entry::getValue).findFirst().get();
        return train;
    }

    @Override
    public void deleteTrain(String trainName) {
        // Find the train
        Train train = trainMapping.entrySet().stream().filter(e -> e.getValue().getTrainName().equals(trainName))
                .map(Map.Entry::getValue).findFirst().get();
        trainMapping.remove(train.getId());
        
    }

    @Override
    public HashMap<String, Train> getTrainMapping() {
        return trainMapping;
    }
    
}
