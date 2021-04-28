package com.mybotfarm.command.processors;

/**
 * Этот класс обрабатывает команду,
 * если ни один из других обработчиков не был вызван,
 * т.е. если команда введена некорректно.
 */
public class DefaultCommandProcessor extends AbstractCommandProcessor {
    @Override
    public String process() {
        return "Incorrect command, please try again";
    }
}
