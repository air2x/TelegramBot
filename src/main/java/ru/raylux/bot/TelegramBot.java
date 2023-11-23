package ru.raylux.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.raylux.lamp.*;

public class TelegramBot extends TelegramLongPollingBot {
    private static final String NAME = "Mixon_AI_bot";
    private static final String TOKEN = "0";
    private static final String NON_STANDART = "https://docs.google.com/forms";
    private static final long IDMIXON = 0;
    private static String tempLamp = "";

    public static String getTempLamp() {
        return tempLamp;
    }

    public static void setTempLamp(String tempLamp) {
        TelegramBot.tempLamp = tempLamp;
    }

    @Override
    public String getBotUsername() {
        return NAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText();
        try {
            if (tempLamp.isEmpty()) {
                geeMessage(chatId, text, update);
            } else {
                if (tempLamp.toLowerCase().contains("rlux")) {
                    Lamp rLux = new RLux();
                    askQuestionLamp(chatId, text, rLux);
                } else if (tempLamp.toLowerCase().contains("alux")) {
                    Lamp aLux = new ALux();
                    askQuestionLamp(chatId, text, aLux);
                } else if (tempLamp.toLowerCase().contains("hlux")) {
                    Lamp hLux = new HLux();
                    askQuestionLamp(chatId, text, hLux);
                } else if (tempLamp.toLowerCase().contains("clux")) {
                    Lamp cLux = new CLux();
                    askQuestionLamp(chatId, text, cLux);
                } else if (tempLamp.toLowerCase().contains("olux")) {
                    Lamp oLux = new OLux();
                    askQuestionLamp(chatId, text, oLux);
                } else if (tempLamp.toLowerCase().contains("ilux")) {
                    Lamp iLux = new ILux();
                    askQuestionLamp(chatId, text, iLux);
                } else if (tempLamp.toLowerCase().contains("plux")) {
                    Lamp pLux = new PLux();
                    askQuestionLamp(chatId, text, pLux);
                } else if (tempLamp.toLowerCase().contains("slux")) {
                    Lamp sLux = new RLux();
                    askQuestionLamp(chatId, text, sLux);
                }
            }
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void geeMessage(String chatId, String text, Update update) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();

        if (text.startsWith("/start")) {
            sendMessage.setChatId(chatId);
            sendMessage.setText("Привет, это Михаил_ИИ! Выбери в меню по какому светильнику у тебя вопрос: " +
                    "\nR-lux" + "\nA-lux" + "\nO-lux" + "\nC-lux" + "\nH-lux" + "\nP-lux" + "\ni-lux" + "\nS-lux" +
                    "\nНестандарт");
        } else if (text.toLowerCase().contains("rlux")
                || text.toLowerCase().contains("alux")
                || text.toLowerCase().contains("olux")
                || text.toLowerCase().contains("clux")
                || text.toLowerCase().contains("hlux")
                || text.toLowerCase().contains("plux")
                || text.toLowerCase().contains("ilux")
                || text.toLowerCase().contains("slux")) {
            askQuestion(chatId, text);
            return;
        } else if (text.toLowerCase().contains("nonstandard")) {
            sendMessage.setChatId(chatId);
            sendMessage.setText("Для расчета нестандарта заполни форму по ссылке: " + NON_STANDART);
        } else {
            sendMessage.setChatId(chatId);
            sendMessage.setText("Привет! То, что ты просишь пока не умею! Переадресую твое сообщение");
            forwardMessageToChat(update.getMessage(), IDMIXON);
        }
        this.execute(sendMessage);
    }

    private void askQuestion(String chatId, String text) throws TelegramApiException {
        tempLamp = text;
        SendMessage outMess = new SendMessage();
        outMess.setChatId(chatId);
        outMess.setText("Напиши свой вопрос");
        execute(outMess);
    }

    private void askQuestionLamp(String chatId, String text, Lamp lamp) throws TelegramApiException {
        SendMessage outMessage = new SendMessage();
        outMessage.setChatId(chatId);
        outMessage.setText(lamp.getMessage(text));
        execute(outMessage);
        setTempLamp("");
    }

    public void forwardMessageToChat(Message message, long chatIdToForward) {
        ForwardMessage forwardMessage = new ForwardMessage();
        forwardMessage.setChatId(chatIdToForward);
        forwardMessage.setFromChatId(message.getChatId().toString());
        forwardMessage.setMessageId(message.getMessageId());
        try {
            execute(forwardMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
