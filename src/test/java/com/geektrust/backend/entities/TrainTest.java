package com.geektrust.backend.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TrainTest {
    private Train train;
    
    @BeforeEach()
    public void setup(){
        LinkedList<Boggy> boggies = new LinkedList<Boggy>(){
            {
                add(new Boggy("1", new Station("1", "New Delhi", "NDL", 0)));
                add(new Boggy("2",new Station("2", "New Jalpaiguri", "NJP", 3000)));
            }
        };
        train = new Train("1", "abc", boggies);
    }

    @Test
    @DisplayName("#1 Add boggy test")
    public void add_boggy_in_train(){
        //Arrange
        Boggy boggy = new Boggy("3",new Station("2", "New Jalpaiguri", "NJP", 3000));
        //Act
        train.addBoggy(boggy);
        //Assert
        assertEquals(3, train.getBoggies().size());
    }

    @Test
    @DisplayName("#2 Delete boggy test")
    public void delete_boggy_in_train(){
        //Arrange
        Boggy boggy = new Boggy("1", new Station("1", "New Delhi", "NDL", 0));
        //Act
        train.deleteBoggy(boggy);
        //Assert
        assertEquals(1, train.getBoggies().size());

    }
}
