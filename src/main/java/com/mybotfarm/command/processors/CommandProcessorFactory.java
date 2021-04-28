package com.mybotfarm.command.processors;

import com.mybotfarm.command.ParsedCommand;

public class CommandProcessorFactory {

    public AbstractCommandProcessor getProcessor(ParsedCommand parsedCommand) {
        switch (parsedCommand.getCommand()) {
            case START:
                return new StartCommandProcessor();
            case INSPIREME:
                return new InspireMeCommandProcessor(parsedCommand.getText());
            case HELP:
                return new HelpCommandProcessor();
            default:
                return new DefaultCommandProcessor();
        }
    }
}
