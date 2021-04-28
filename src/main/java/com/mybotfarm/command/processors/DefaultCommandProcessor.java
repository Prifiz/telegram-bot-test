package com.mybotfarm.command.processors;

public class DefaultCommandProcessor extends AbstractCommandProcessor {
    @Override
    public String process() {
        return "Некорректная команда";
    }
}
