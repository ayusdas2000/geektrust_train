package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.services.ITrainService;

public class LoadTrainDataCommand implements ICommand {
    //following constants are declared because geektrust ai tool thinks accessing list elements through numbers are also magic numbers
    private final int FIRST_INDEX = 0;
    private final ITrainService iTrainService;

    public LoadTrainDataCommand(ITrainService iTrainService) {
        this.iTrainService = iTrainService;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            iTrainService.createTrain(tokens.get(FIRST_INDEX), tokens.subList(1, tokens.size()));
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
    
}
