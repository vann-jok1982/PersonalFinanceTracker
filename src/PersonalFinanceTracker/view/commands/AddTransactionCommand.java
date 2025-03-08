package PersonalFinanceTracker.view.commands;

import PersonalFinanceTracker.view.consoleUi.ConsoleUI;

/**
 * Класс `AddTransactionCommand` представляет собой команду для добавления новой транзакции.
 * Расширяет абстрактный класс `Commands`.
 */
public class AddTransactionCommand extends Commands {

    /**
     * Конструктор класса `AddTransactionCommand`.
     *
     * @param consoleUI Ссылка на объект `ConsoleUI`, который будет использоваться для выполнения команды.
     */
    public AddTransactionCommand(ConsoleUI consoleUI) {
        super(consoleUI, "Добавить транзакцию");
    }

    /**
     * Выполняет команду добавления транзакции, вызывая соответствующий метод в `ConsoleUI`.
     */
    @Override
    void execute() {
        getConsoleUI().addTransaction();
    }
}