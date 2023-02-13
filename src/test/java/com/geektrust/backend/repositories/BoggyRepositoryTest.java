package com.geektrust.backend.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;
import com.geektrust.backend.entities.Boggy;
import com.geektrust.backend.entities.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoggyRepositoryTest {
    private BoggyRepository boggyRepository;

    @BeforeEach
    public void setup(){
        HashMap<String, Boggy> boggyMapping = new HashMap<String, Boggy>();
        boggyMapping.put("1", new Boggy("1",  new Station("1", "New Delhi", "NDL", 0)));
        boggyRepository = new BoggyRepository(boggyMapping);
    }

    @Test
    @DisplayName("#1 Save method should save a new boggy")
    public void save_boggy_test(){
        //Arrange
        Boggy boggy = new Boggy("2",new Station("2", "New Jalpaiguri", "NJP", 3000));
        //Act
        boggyRepository.saveBoggy(boggy);
        //Assert
        assertEquals(2, boggyRepository.getBoggyMapping().size());
    }
    
}
