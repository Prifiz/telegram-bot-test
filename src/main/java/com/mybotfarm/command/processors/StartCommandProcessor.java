package com.mybotfarm.command.processors;

public class StartCommandProcessor extends AbstractCommandProcessor {
    @Override
    public String process() {
        return "Hi there! I'm here to inspire you with the phrases of the famous wise people. " +
                "To get the phrase, type /inspireme . Or use /help to see the languages options";
    }
}
