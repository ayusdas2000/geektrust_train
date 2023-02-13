package com.geektrust.backend.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import com.geektrust.backend.exceptions.CommandNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CommandInvokerTest {
    private CommandInvoker commandInvoker;

    @Mock
    LoadDataCommand loadDataCommand;

    @Mock
    LoadTrainDataCommand loadTrainDataCommand;

    @Mock
    TravelCommand travelCommand;

    @Mock
    MergeTrainCommand mergeTrainCommand;
    
    @BeforeEach
    void setup(){
        commandInvoker = new CommandInvoker();
        commandInvoker.register("LOAD_DATA", loadDataCommand);
        commandInvoker.register("TRAIN_A", loadTrainDataCommand);
        commandInvoker.register("TRAIN_B", loadTrainDataCommand);
        commandInvoker.register("TRAVEL", travelCommand);
        commandInvoker.register("MERGE", mergeTrainCommand);
    }

    @Test
    @DisplayName("execute command test")
    public void execute_command_test(){
        //Act
        commandInvoker.executeCommand("LOAD_DATA", anyList());
        commandInvoker.executeCommand("TRAIN_A", anyList());
        commandInvoker.executeCommand("TRAVEL", anyList());
        commandInvoker.executeCommand("MERGE", anyList());

        //Assert
        verify(loadDataCommand).execute(anyList());
        verify(loadTrainDataCommand).execute(anyList());
        verify(travelCommand).execute(anyList());
        verify(mergeTrainCommand).execute(anyList());
    }

    @Test
    @DisplayName("Command not present must return exception")
    public void execute_command_must_return_exception_if_command_not_present(){
        //Act and assert
        assertThrows(CommandNotFoundException.class, () -> commandInvoker.executeCommand("NOT_PRESENT", new ArrayList<String>()));
    }


}
