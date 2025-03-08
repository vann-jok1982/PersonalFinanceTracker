package PersonalFinanceTracker.view.commands;

import PersonalFinanceTracker.view.consoleUi.ConsoleUI;

/**
 * Класс `ShowMainMenuCommand` представляет собой команду для отображения главного меню.
 * Расширяет абстрактный класс `Commands`.
 */
public class ShowMainMenuCommand extends Commands {

    /**
     * Конструктор класса `ShowMainMenuCommand`.
     *
     * @param consoleUI Ссылка на объект `ConsoleUI`, который будет использоваться для выполнения команды.
     */
    public ShowMainMenuCommand(ConsoleUI consoleUI) {
        super(consoleUI, "Показать главное меню");
    }

    /**
     * Выполняет команду отображения главного меню, вызывая соответствующий метод в `ConsoleUI`.
     */
    @Override
    void execute() {
        getConsoleUI().showMainMenu();
    }
}