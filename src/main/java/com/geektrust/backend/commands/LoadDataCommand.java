package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.repositories.IRouteRepository;
import com.geektrust.backend.repositories.IStationRepository;
import com.geektrust.backend.repositories.data.LoadData;

public class LoadDataCommand implements ICommand {
    private final IStationRepository iStationRepository;
    private final IRouteRepository iRouteRepository;

    public LoadDataCommand(IStationRepository iStationRepository, IRouteRepository iRouteRepository) {
        this.iStationRepository = iStationRepository;
        this.iRouteRepository = iRouteRepository;
    }

    @Override
    public void execute(List<String> tokens) {
        LoadData loadData = new LoadData(iStationRepository, iRouteRepository);
        loadData.loadData();        
    }
    
}
