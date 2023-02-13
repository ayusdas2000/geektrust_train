package com.geektrust.backend;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("App Test")
class AppTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("#1 Integration test 1")
    public void Application_Test() throws Exception{
        // Arrange
        String arguements = "sample_input/input1.txt";
        String expectedOutput = "ARRIVAL TRAIN_A ENGINE NDL NDL GHY NJP NGP\n"+
        "ARRIVAL TRAIN_B ENGINE NJP GHY AGA BPL PTA\n"+
        "DEPARTURE TRAIN_AB ENGINE ENGINE GHY GHY NJP NJP PTA NDL NDL AGA BPL NGP";
        // Act
        App.run(arguements);
        // Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("#2 Integration test 2")
    public void Application_Test_2() throws Exception{
        String arguements = "sample_input/input2.txt";
        String expectedOutput = "ARRIVAL TRAIN_A ENGINE HYB NGP ITJ\n"+
        "ARRIVAL TRAIN_B ENGINE NJP PTA\n"+
        "DEPARTURE TRAIN_AB ENGINE ENGINE NJP PTA ITJ NGP";
        // Act
        App.run(arguements);
        // Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }
    
    @AfterEach
    public void tearDown() {

        System.setOut(standardOut);

    }

}
