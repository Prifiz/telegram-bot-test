package com.mybotfarm.command;

import org.glassfish.grizzly.utils.Pair;

/**
 * Класс для разбора введенной пользователем команды
  */
public class CommandParser {

    private static final String COMMAND_PREFIX = "/";
    private static final String COMMAND_TEXT_DELIMETER = " ";

    public ParsedCommand getParsedCommand(String text) {
        ParsedCommand result = new ParsedCommand();
        String trimmedText = text.strip();

        if (!isCommand(trimmedText)) {
            return result;
        }

        String pureCommand = getPureCommand(trimmedText);

        Pair<String, String> commandWithText = getCommandAndText(pureCommand);
        result.setCommand(getCommandFromText(commandWithText.getFirst()));
        result.setText(commandWithText.getSecond());
        return result;
    }

    private String getPureCommand(String command) {
        return command.replaceFirst("/", "");
    }

    private Command getCommandFromText(String text) {
        String upperCaseText = text.toUpperCase().trim();
        Command command = Command.NONE;
        try {
            command = Command.valueOf(upperCaseText);
        } catch (IllegalArgumentException e) {
            // что-то пошло не так...
        }
        return command;
    }

    private boolean isCommand(String command) {
        // TODO
        //  нужно реализовать этот метод
    }

    private Pair<String, String> getCommandAndText(String commandString) {
        Pair<String, String> result = new Pair<>();
        if (commandString.contains(COMMAND_TEXT_DELIMETER)) {
            result.setFirst(commandString.substring(0, commandString.indexOf(COMMAND_TEXT_DELIMETER)));
            result.setSecond(commandString.substring(commandString.indexOf(COMMAND_TEXT_DELIMETER) + 1));
            return result;
        } else {
            return new Pair<>(commandString, "");
        }
    }
}
