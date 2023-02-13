package com.geektrust.backend.repositories.data;

import java.util.LinkedList;
import com.geektrust.backend.entities.Route;
import com.geektrust.backend.entities.Station;
import com.geektrust.backend.repositories.IRouteRepository;
import com.geektrust.backend.repositories.IStationRepository;

public class LoadData implements IData{
    private final IStationRepository iStationRepository;
    private final IRouteRepository iRouteRepository;

    public LoadData(IStationRepository iStationRepository, IRouteRepository iRouteRepository) {
        this.iStationRepository = iStationRepository;
        this.iRouteRepository = iRouteRepository;
    }

    @Override
    public void loadData() {
        LinkedList<Station> stationsOfRoute1 = new LinkedList<Station>();

        Station stn = new Station("Chennai", "CHN", 0);
        Station savedStation = iStationRepository.saveStation(stn);
        stationsOfRoute1.add(savedStation);

        stn = new Station("Salem", "SLM", 350);
        savedStation = iStationRepository.saveStation(stn);
        stationsOfRoute1.add(savedStation);

        stn = new Station("Bangalore", "BLR", 550);
        savedStation = iStationRepository.saveStation(stn);
        stationsOfRoute1.add(savedStation);

        stn = new Station("Kurnool", "KRN", 900);
        savedStation = iStationRepository.saveStation(stn);
        stationsOfRoute1.add(savedStation);

        stn = new Station("Hyderabad", "HYB", 1200);
        savedStation = iStationRepository.saveStation(stn);
        stationsOfRoute1.add(savedStation);

        stn = new Station("Nagpur", "NGP", 1600);
        savedStation = iStationRepository.saveStation(stn);
        stationsOfRoute1.add(savedStation);

        stn = new Station("Itarsi", "ITJ", 1900);
        savedStation = iStationRepository.saveStation(stn);
        stationsOfRoute1.add(savedStation);

        stn = new Station("Bhopal", "BPL", 2000);
        savedStation = iStationRepository.saveStation(stn);
        stationsOfRoute1.add(savedStation);

        stn = new Station("Agra", "AGA", 2500);
        savedStation = iStationRepository.saveStation(stn);
        stationsOfRoute1.add(savedStation);

        stn = new Station("New Delhi", "NDL", 2700);
        savedStation = iStationRepository.saveStation(stn);
        stationsOfRoute1.add(savedStation);

        Route route1 = new Route("TRAIN_A", stationsOfRoute1, 1200);
        iRouteRepository.saveRoute(route1);

        // Saving the stations of other route
        LinkedList<Station> stationsOfRoute2 = new LinkedList<Station>();
        stn = new Station("Trivandrum", "TVC", 0);
        savedStation = iStationRepository.saveStation(stn);
        stationsOfRoute2.add(savedStation);

        stn = new Station("Shoranpur", "SRR", 300);
        savedStation = iStationRepository.saveStation(stn);
        stationsOfRoute2.add(savedStation);

        stn = new Station("Mangalore", "MAQ", 600);
        savedStation = iStationRepository.saveStation(stn);
        stationsOfRoute2.add(savedStation);

        stn = new Station("Madgaon", "MAO", 1000);
        savedStation = iStationRepository.saveStation(stn);
        stationsOfRoute2.add(savedStation);

        stn = new Station("Pune", "PNE", 1400);
        savedStation = iStationRepository.saveStation(stn);
        stationsOfRoute2.add(savedStation);

        stn = new Station("Hyderabad", "HYB", 2000);
        savedStation = iStationRepository.saveStation(stn);
        stationsOfRoute2.add(savedStation);

        stn = new Station("Nagpur", "NGP", 2400);
        savedStation = iStationRepository.saveStation(stn);
        stationsOfRoute2.add(savedStation);

        stn = new Station("Itarsi", "ITJ", 2700);
        savedStation = iStationRepository.saveStation(stn);
        stationsOfRoute2.add(savedStation);

        stn = new Station("Bhopal", "BPL", 2800);
        savedStation = iStationRepository.saveStation(stn);
        stationsOfRoute2.add(savedStation);

        stn = new Station("Patna", "PTA", 3800);
        savedStation = iStationRepository.saveStation(stn);
        stationsOfRoute2.add(savedStation);

        stn = new Station("New Jalpaiguri", "NJP", 4200);
        savedStation = iStationRepository.saveStation(stn);
        stationsOfRoute2.add(savedStation);

        stn = new Station("Guwahati", "GHY", 4700);
        savedStation = iStationRepository.saveStation(stn);
        stationsOfRoute2.add(savedStation);

        Route route2 = new Route("TRAIN_B", stationsOfRoute2, 2000);
        iRouteRepository.saveRoute(route2);
        
    }
    
}
