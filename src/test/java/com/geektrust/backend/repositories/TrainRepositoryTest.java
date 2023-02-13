package com.geektrust.backend.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;
import java.util.LinkedList;
import com.geektrust.backend.entities.Boggy;
import com.geektrust.backend.entities.Station;
import com.geektrust.backend.entities.Train;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TrainRepositoryTest {
    private TrainRepository trainRepository;
    
    @BeforeEach
    public void setup(){
        HashMap<String,Train> trainMapping = new HashMap<String,Train>();
        LinkedList<Boggy> boggies = new LinkedList<Boggy>(){
            {
                add(new Boggy("1", new Station("1", "New Delhi", "NDL", 0)));
                add(new Boggy("2",new Station("2", "New Jalpaiguri", "NJP", 3000)));
            }
        };
        Train trainA = new Train("1","abc", boggies);
        trainMapping.put(trainA.getId(), trainA);
        trainRepository = new TrainRepository(trainMapping);
    }

    @Test
    @DisplayName("#1 Save train in repository test")
    public void save_train_in_repo_test(){
        //Arrange
        LinkedList<Boggy> boggies = new LinkedList<Boggy>(){
            {
                add(new Boggy("3", new Station("3", "Chennai", "CHN", 80)));
                add(new Boggy("4",new Station("4", "Salem", "SLM", 2000)));
            }
        };
        Train trainB = new Train("2","def", boggies);
        //Act
        trainRepository.saveTrain(trainB);
        //Assert
        assertEquals(2, trainRepository.getTrainMapping().size());
    }

    @Test
    @DisplayName("#2 Find train by name test")
    public void find_train_by_name_test(){
        //Act
        Train train = trainRepository.findTrainByName("abc");
        //Assert
        assertEquals("1", train.getId());
    }

    @Test
    @DisplayName("#3 Delete train test")
    public void delete_train_test(){
        //Act
        trainRepository.deleteTrain("abc");
        //Assert
        assertEquals(0, trainRepository.getTrainMapping().size());
    }

}
