package PersonalFinanceTracker.view.commands;

import PersonalFinanceTracker.view.consoleUi.ConsoleUI;

/**
 * Класс `UpdateTransaction` представляет собой команду для редактирования существующей транзакции.
 * Расширяет абстрактный класс `Commands`.
 */
public class UpdateTransaction extends Commands {

    /**
     * Конструктор класса `UpdateTransaction`.
     *
     * @param consoleUI Ссылка на объект `ConsoleUI`, который будет использоваться для выполнения команды.
     */
    public UpdateTransaction(ConsoleUI consoleUI) {
        super(consoleUI, "Редактирование транзакции");
    }

    /**
     * Выполняет команду редактирования транзакции, вызывая соответствующий метод в `ConsoleUI`.
     */
    @Override
    void execute() {
        getConsoleUI().updateTransaction();
    }
}
