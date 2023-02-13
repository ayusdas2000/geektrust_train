package com.geektrust.backend.config;

import com.geektrust.backend.commands.CommandInvoker;
import com.geektrust.backend.commands.ICommand;
import com.geektrust.backend.commands.LoadDataCommand;
import com.geektrust.backend.commands.LoadTrainDataCommand;
import com.geektrust.backend.commands.MergeTrainCommand;
import com.geektrust.backend.commands.TravelCommand;
import com.geektrust.backend.repositories.BoggyRepository;
import com.geektrust.backend.repositories.IBoggyRepository;
import com.geektrust.backend.repositories.IRouteRepository;
import com.geektrust.backend.repositories.IStationRepository;
import com.geektrust.backend.repositories.ITrainRepository;
import com.geektrust.backend.repositories.RouteRepository;
import com.geektrust.backend.repositories.StationRepository;
import com.geektrust.backend.repositories.TrainRepository;
import com.geektrust.backend.services.ITrainService;
import com.geektrust.backend.services.TrainService;

public class ApplicationConfig {
    private IRouteRepository iRouteRepository = new RouteRepository();
    private ITrainRepository iTrainRepository = new TrainRepository();
    private IBoggyRepository iBoggyRepository = new BoggyRepository();
    private IStationRepository iStationRepository = new StationRepository();

    private ITrainService iTrainService = new TrainService(iRouteRepository, iTrainRepository, iBoggyRepository,
            iStationRepository);

    private ICommand loadDataCommand = new LoadDataCommand(iStationRepository, iRouteRepository);
    private ICommand loadTrainDataCommand = new LoadTrainDataCommand(iTrainService);
    private ICommand travelCommand = new TravelCommand(iTrainService);
    private ICommand mergeTrainCommand = new MergeTrainCommand(iTrainService);

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker() {
        commandInvoker.register("LOAD_DATA", loadDataCommand);
        commandInvoker.register("TRAIN_A", loadTrainDataCommand);
        commandInvoker.register("TRAIN_B", loadTrainDataCommand);
        commandInvoker.register("TRAVEL", travelCommand);
        commandInvoker.register("MERGE", mergeTrainCommand);
        return commandInvoker;
    }    
}
