package PersonalFinanceTracker.view.commands;

import PersonalFinanceTracker.view.consoleUi.ConsoleUI;

/**
 * Класс `End` представляет собой команду для завершения работы приложения.
 * Расширяет абстрактный класс `Commands`.
 */
public class End extends Commands {

    /**
     * Конструктор класса `End`.
     *
     * @param consoleUI Ссылка на объект `ConsoleUI`, который будет использоваться для выполнения команды.
     */
    public End(ConsoleUI consoleUI) {
        super(consoleUI, "Завершить работу"); // description устанавливаем тут
    }

    /**
     * Выполняет команду завершения работы, вызывая соответствующий метод в `ConsoleUI`.
     */
    @Override
    void execute() {
        getConsoleUI().end();
    }
}
