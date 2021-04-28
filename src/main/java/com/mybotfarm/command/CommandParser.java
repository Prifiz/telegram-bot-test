package com.mybotfarm.command;

import org.glassfish.grizzly.utils.Pair;

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
            // something went wrong
        }
        return command;
    }

    private boolean isCommand(String command) {
        return command.startsWith(COMMAND_PREFIX);
    }

    private boolean isCommandForMe(String command) {
        return true;
    }

    private Pair<String, String> getCommandAndText(String commandString) {
        Pair<String, String> result = new Pair<>();

        if (commandString.contains(" ")) {
            result.setFirst(commandString.substring(0, commandString.indexOf(COMMAND_TEXT_DELIMETER)));
            result.setSecond(commandString.substring(commandString.indexOf(COMMAND_TEXT_DELIMETER) + 1));
            return result;
        } else {
            return new Pair<>(commandString, "");
        }
    }
}
