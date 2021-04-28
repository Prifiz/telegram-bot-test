package com.mybotfarm.command.processors;

public class HelpCommandProcessor extends AbstractCommandProcessor {
    @Override
    public String process() {
        return "Hi there! I'm here to encourage you with the inspirational phrases of the famous wise people.\n" +
                "Just type /inspireme to get the random phrase in English.\n" +
                "The other languages options:\n" +
                "\t/inspireme ru - phrase in Russian\n" +
                "\t/inspireme es - phrase in Spanish\n" +
                "\t/inspireme en - phrase in English (equivalent to the command without parameter";
    }
}
