package PersonalFinanceTracker.view.commands;

import PersonalFinanceTracker.view.consoleUi.ConsoleUI;

/**
 * Класс `DeleteAccountCommand` представляет собой команду для удаления аккаунта пользователя.
 * Расширяет абстрактный класс `Commands`.
 */
public class DeleteAccountCommand extends Commands {

    /**
     * Конструктор класса `DeleteAccountCommand`.
     *
     * @param consoleUI Ссылка на объект `ConsoleUI`, который будет использоваться для выполнения команды.
     */
    public DeleteAccountCommand(ConsoleUI consoleUI) {
        super(consoleUI, "Удалить аккаунт");
    }

    /**
     * Выполняет команду удаления аккаунта, вызывая соответствующий метод в `ConsoleUI`.
     */
    @Override
    void execute() {
        getConsoleUI().deleteAccount();
    }
}