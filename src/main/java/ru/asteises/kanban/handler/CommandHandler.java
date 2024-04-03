package ru.asteises.kanban.handler;

import javassist.NotFoundException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface CommandHandler {

    SendMessage handleCommand(Message command) throws NotFoundException;
}
