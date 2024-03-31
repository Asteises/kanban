package ru.asteises.kanban.telegram_bot;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.asteises.kanban.telegram_bot.command.StartCommand;

import java.util.List;

public class Bot extends TelegramLongPollingCommandBot {

    private final String botName;

    public Bot(String botToken, String botName) {

        super(botToken);
        this.botName = botName;
        register(new StartCommand("start", "Start command"));
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    @Override
    public void processNonCommandUpdate(Update update) {

    }

    @Override
    public void processInvalidCommandUpdate(Update update) {
        super.processInvalidCommandUpdate(update);
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }
}
