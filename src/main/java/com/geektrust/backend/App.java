package com.geektrust.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.geektrust.backend.commands.CommandInvoker;
import com.geektrust.backend.config.ApplicationConfig;
import com.geektrust.backend.exceptions.CommandNotFoundException;

public class App {
	public static void run(String commandLineArgs) {
		ApplicationConfig applicationConfig = new ApplicationConfig();
		CommandInvoker commandInvoker = applicationConfig.getCommandInvoker();
		commandInvoker.executeCommand("LOAD_DATA", null);
		String inputFile = commandLineArgs;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(inputFile));
			String line = reader.readLine();
			while (line != null) {
				List<String> tokens = Arrays.asList(line.split(" "));
				commandInvoker.executeCommand(tokens.get(0), tokens);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException | CommandNotFoundException e) {
			e.printStackTrace();
		}
		commandInvoker.executeCommand("TRAVEL", Arrays.asList("TRAIN_A", "TRAIN_A", "HYB"));
		commandInvoker.executeCommand("TRAVEL", Arrays.asList("TRAIN_B", "TRAIN_B", "HYB"));
		commandInvoker.executeCommand("MERGE", Arrays.asList("TRAIN_A", "TRAIN_B"));
	}

	public static void main(String[] args) {
		run(args[0]);
	}

}
