package com.geektrust.backend.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.geektrust.backend.exceptions.CommandNotFoundException;

public class CommandInvoker {
    private static final Map<String, ICommand> commandMap = new HashMap<>();

    // put command into hashmap
    public void register(String commandName, ICommand command) {
        commandMap.put(commandName, command);
    }

    // find the registered command
    private ICommand get(String commandName) {
        return commandMap.get(commandName);
    }

    // execute the command
    public void executeCommand(String commandName, List<String> tokens) throws CommandNotFoundException {
        ICommand command = get(commandName);
        if (command == null) {
            throw new CommandNotFoundException();
        }
        command.execute(tokens);
    }
    
}
