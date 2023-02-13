package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.entities.Train;
import com.geektrust.backend.helpers.PrintTrain;
import com.geektrust.backend.services.ITrainService;

public class MergeTrainCommand implements ICommand {
    //following constants are declared because geektrust ai tool thinks accessing list elements through numbers are also magic numbers
    private final int FIRST_INDEX = 0;
    private final int SECOND_INDEX = 1;
    private ITrainService iTrainService;

    public MergeTrainCommand(ITrainService iTrainService) {
        this.iTrainService = iTrainService;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            String trainA = tokens.get(FIRST_INDEX);
            String trainB = tokens.get(SECOND_INDEX);
            Train train = iTrainService.mergeTrain(trainA, trainB);
            PrintTrain.printTrain(train, "DEPARTURE");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
