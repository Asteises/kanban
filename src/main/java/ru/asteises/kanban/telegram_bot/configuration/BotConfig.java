package ru.asteises.kanban.telegram_bot.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.asteises.kanban.handler.CallBackHandler;
import ru.asteises.kanban.handler.CommandHandler;
import ru.asteises.kanban.handler.MessageHandler;
import ru.asteises.kanban.service.UserService;
import ru.asteises.kanban.telegram_bot.Bot;

@Configuration
public class BotConfig {

    @Bean
    public Bot bot(@Value("${telegram.bot.token}") String botToken,
                   @Value("${telegram.bot.name}") String botName,
                   UserService userService,
                   CommandHandler commandHandler,
                   MessageHandler messageHandler,
                   CallBackHandler callBackHandler) {
        return new Bot(
                botToken,
                botName,
                userService,
                commandHandler,
                messageHandler,
                callBackHandler);
    }

    @Bean
    public TelegramBotsApi botsApi(Bot bot) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
        return telegramBotsApi;
    }
}
