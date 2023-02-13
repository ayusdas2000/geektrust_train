package com.geektrust.backend.services;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import com.geektrust.backend.entities.Boggy;
import com.geektrust.backend.entities.Route;
import com.geektrust.backend.entities.Train;
import com.geektrust.backend.exceptions.TrainEndedException;
import com.geektrust.backend.helpers.CreateListOfBoggies;
import com.geektrust.backend.helpers.GenerateListOfBoggiesToBeRemoved;
import com.geektrust.backend.helpers.SortBoggies;
import com.geektrust.backend.repositories.IBoggyRepository;
import com.geektrust.backend.repositories.IRouteRepository;
import com.geektrust.backend.repositories.IStationRepository;
import com.geektrust.backend.repositories.ITrainRepository;

public class TrainService implements ITrainService {
    private final IRouteRepository iRouteRepository;
    private final ITrainRepository iTrainRepository;
    private final IBoggyRepository iBoggyRepository;
    private final IStationRepository iStationRepository;

    public TrainService(IRouteRepository iRouteRepository, ITrainRepository iTrainRepository,
            IBoggyRepository iBoggyRepository, IStationRepository iStationRepository) {
        this.iRouteRepository = iRouteRepository;
        this.iTrainRepository = iTrainRepository;
        this.iBoggyRepository = iBoggyRepository;
        this.iStationRepository = iStationRepository;
    }

    @Override
    public Train mergeTrain(String trainAName, String trainBName) {
        Train trainA = iTrainRepository.findTrainByName(trainAName);
        Train trainB = iTrainRepository.findTrainByName(trainBName);

        //Find the list of boggies
        LinkedList<Boggy> boggiesOfA = trainA.getBoggies();
        LinkedList<Boggy> boggiesOfB = trainB.getBoggies();
        if (boggiesOfA.isEmpty() && boggiesOfB.isEmpty()) {
            throw new TrainEndedException("JOURNEY_ENDED");
        }
        boggiesOfA.addAll(boggiesOfB);


        Collections.sort(boggiesOfA, new SortBoggies(iRouteRepository));

        //Remove Hyderabad from the list of boggies in merged train
        while(boggiesOfA.getLast().getDestStation()!=null && boggiesOfA.getLast().getDestStation().getStationCode().equals("HYB")){
            boggiesOfA.removeLast();
        }
        Train AB = new Train("TRAIN_AB", boggiesOfA);
        iTrainRepository.deleteTrain(trainAName);
        iTrainRepository.deleteTrain(trainBName);
        iTrainRepository.saveTrain(AB);
        return AB;
    }

    @Override
    public Train travel(String trainName, String routeName, String dest) {
        // Find the train
        Train train = iTrainRepository.findTrainByName(trainName);
        // Find the route
        Route route = iRouteRepository.findRouteByName(routeName);

        // travel upto dest
        List<Boggy> boggiesToBeRemoved = GenerateListOfBoggiesToBeRemoved.generateListOfBoggiesToBeRemoved(route.getStations(), train.getBoggies(), dest);


        for(Boggy boggy :  boggiesToBeRemoved){
            train.deleteBoggy(boggy);
        }
        iTrainRepository.saveTrain(train);
        return train;
    }

    @Override
    public Train createTrain(String trainName, List<String> boggies) {
        LinkedList<Boggy> newBoggies = CreateListOfBoggies.createListOfBoggies(iBoggyRepository, iStationRepository, boggies);
        Train newTrain = new Train(trainName, newBoggies);
        Train savedTrain = iTrainRepository.saveTrain(newTrain);
        return savedTrain;
    }
    
}
