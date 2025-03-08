package PersonalFinanceTracker.view.commands;

import PersonalFinanceTracker.view.consoleUi.ConsoleUI;

/**
 * Класс `LoginUser` представляет собой команду для входа пользователя в систему.
 * Расширяет абстрактный класс `Commands`.
 */
public class LoginUser extends Commands {

    /**
     * Конструктор класса `LoginUser`.
     *
     * @param consoleUI Ссылка на объект `ConsoleUI`, который будет использоваться для выполнения команды.
     */
    public LoginUser(ConsoleUI consoleUI) {
        super(consoleUI, "Вход");
    }

    /**
     * Выполняет команду входа пользователя, вызывая соответствующий метод в `ConsoleUI`.
     */
    @Override
    void execute() {
        getConsoleUI().loginUser();
    }
}