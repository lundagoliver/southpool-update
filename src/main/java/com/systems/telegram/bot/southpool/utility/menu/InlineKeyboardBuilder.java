package com.systems.telegram.bot.southpool.utility.menu;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class InlineKeyboardBuilder {

    private Long chatId;
    private String text;
    private String parse;

    private List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
    private List<InlineKeyboardButton> row = null;

    private InlineKeyboardBuilder() {}

    public static InlineKeyboardBuilder create() {
        return new InlineKeyboardBuilder();
    }

    public static InlineKeyboardBuilder create(Long chatId) {
        InlineKeyboardBuilder builder = new InlineKeyboardBuilder();
        builder.setChatId(chatId);
        return builder;
    }

    public InlineKeyboardBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public InlineKeyboardBuilder setChatId(Long chatId) {
        this.chatId = chatId;
        return this;
    }

    public InlineKeyboardBuilder setParse(String parse) {
        this.parse = parse;
        return this;
    }
    
    public InlineKeyboardBuilder row() {
        this.row = new ArrayList<>();
        return this;
    }

    public InlineKeyboardBuilder button(String text, String callbackData, String url) {
        row.add(new InlineKeyboardButton().setText(text).setUrl(url).setCallbackData(callbackData));
        return this;
    }

    public InlineKeyboardBuilder endRow() {
        this.keyboard.add(this.row);
        this.row = null;
        return this;
    }


    public SendMessage build() {
        SendMessage message = new SendMessage();

        message.setChatId(chatId);
        message.setText(text);
        message.setParseMode(parse == null ? "HTML" : parse);
        
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        return message;
    }

}
