package com.mybotfarm.command.processors;

/**
 * Класс для обработки команды /start
 * Это первая вызываемая команда в тот момент, когда вы впервые начинаете общаться с ботом
 */
public class StartCommandProcessor extends AbstractCommandProcessor {
    @Override
    public String process() {
        return "Hi there! I'm here to inspire you with the phrases of the famous wise people. " +
                "To get the phrase, type /inspireme . Or use /help to see the languages options";
    }
}
