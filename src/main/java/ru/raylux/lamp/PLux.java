package ru.raylux.lamp;

import static ru.raylux.bot.TelegramBot.getTempLamp;

public class PLux extends Lamp {
    @Override
    public String getMessage(String text) {
        if ((text.toLowerCase().contains("spot") && text.toLowerCase().contains("rgb"))) {
            return ("Вопрос по " + getTempLamp() + "\nТвой вопрос: " + text + "\nОтвет: не можем");
        } else {
            return "null";
        }
    }
}
