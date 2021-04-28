package com.mybotfarm;

import com.mybotfarm.command.CommandParser;
import com.mybotfarm.command.ParsedCommand;
import com.mybotfarm.command.processors.CommandProcessorFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class MyTestBot extends TelegramLongPollingBot {

    final int RECONNECT_PAUSE = 10000;

    @Override
    public String getBotUsername() {
        return "TestFirstPrifizBot";
    }

    @Override
    public String getBotToken() {
        return "1648563007:AAEt2-v-g35er6eoScdHdG_yvI5_S1qhymI";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();
        String inputText = update.getMessage().getText();

        ParsedCommand parsedCommand = new CommandParser().getParsedCommand(inputText);
        String answer = new CommandProcessorFactory().getProcessor(parsedCommand).process();
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(answer);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void botConnect() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(this);
        } catch (TelegramApiException e) {
            try {
                Thread.sleep(RECONNECT_PAUSE);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
                return;
            }
            botConnect();
        }
    }
}
