package ru.asteises.kanban.telegram_bot;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.asteises.kanban.handler.CallBackHandler;
import ru.asteises.kanban.handler.CommandHandler;
import ru.asteises.kanban.handler.MessageHandler;
import ru.asteises.kanban.service.UserService;
import ru.asteises.kanban.telegram_bot.command.CreateBoardCommand;
import ru.asteises.kanban.telegram_bot.command.StartCommand;

import java.util.List;

@Slf4j
public class Bot extends TelegramLongPollingCommandBot {

    private final String botName;

    private final CommandHandler commandHandler;
    private final MessageHandler messageHandler;
    private final CallBackHandler callBackHandler;

    public Bot(
            String botToken,
            String botName,
            UserService userService,
            CommandHandler commandHandler,
            MessageHandler messageHandler,
            CallBackHandler callBackHandler) {

        super(botToken);
        this.botName = botName;
        this.commandHandler = commandHandler;
        this.messageHandler = messageHandler;
        this.callBackHandler = callBackHandler;
        register(new StartCommand("start", "Start command"));
        register(new CreateBoardCommand("new_board", "Create new board command"));
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

    @SneakyThrows
    @Override
    public void onUpdatesReceived(List<Update> updates) {
        for (Update update : updates) {
            SendMessage sendMessage = new SendMessage();
            if (update.hasMessage()) {
                Message message = update.getMessage();
                if (message.getText().startsWith("/")) {
                    sendMessage = commandHandler.handleCommand(message);
                } else {
                    sendMessage = messageHandler.handleMessage(message);
                }
            } else if (update.hasCallbackQuery()) {
                CallbackQuery callbackQuery = update.getCallbackQuery();
                sendMessage = callBackHandler.handleCallBack(callbackQuery);
            }
            execute(sendMessage);
        }
    }
}
