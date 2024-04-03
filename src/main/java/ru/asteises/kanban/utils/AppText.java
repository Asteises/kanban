package ru.asteises.kanban.utils;

public interface AppText {

    String AFTER_REGISTRATION = "С чего начнем? ";
//    String SET_BOARD_NAME = "\"Now you need to name your board. Its can be any name less then 100 chars and 'board' at the end: first_board or AmmyBoard";
    String SET_BOARD_NAME = "\"Теперь вы можете назвать свою доску. Не более 100 символов включая пробелы. " +
        "В конце ключевое слово 'board'. Для примера: \nмоя доска board или AmmyBoard";
    String BORD_INFO_WELCOME = "Ваша доска: ";

    String NEW_TASK_EXAMPLE = "Быстрое создание задачи, пример: \n" +
            "/newtask Помыть машину";
}
