package ru.raylux.lamp;

import static ru.raylux.bot.TelegramBot.getTempLamp;

public class RLux extends Lamp {

    @Override
    public String getMessage(String text) {
        if ((text.toLowerCase().contains("24") && text.toLowerCase().contains("vdc")) ||
                (text.toLowerCase().contains("24") && text.toLowerCase().contains("в"))) {
            return ("Вопрос по " + getTempLamp() + "\nТвой вопрос: " + text + "\nОтвет: можем");
        } else {
            return "null";
        }
    }

}
