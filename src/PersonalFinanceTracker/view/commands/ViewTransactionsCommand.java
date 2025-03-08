package PersonalFinanceTracker.view.commands;

import PersonalFinanceTracker.view.consoleUi.ConsoleUI;

/**
 * Класс `ViewTransactionsCommand` представляет собой команду для просмотра списка транзакций.
 * Расширяет абстрактный класс `Commands`.
 */
public class ViewTransactionsCommand extends Commands {

    /**
     * Конструктор класса `ViewTransactionsCommand`.
     *
     * @param consoleUI Ссылка на объект `ConsoleUI`, который будет использоваться для выполнения команды.
     */
    public ViewTransactionsCommand(ConsoleUI consoleUI) {
        super(consoleUI, "Просмотреть транзакции");
    }

    /**
     * Выполняет команду просмотра транзакций, вызывая соответствующий метод в `ConsoleUI`.
     */
    @Override
    void execute() {
        getConsoleUI().viewTransactions();
    }
}