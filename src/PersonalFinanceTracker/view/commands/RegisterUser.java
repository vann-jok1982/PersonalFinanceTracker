package PersonalFinanceTracker.view.commands;

import PersonalFinanceTracker.view.consoleUi.ConsoleUI;

/**
 * Класс `RegisterUser` представляет собой команду для регистрации нового пользователя.
 * Расширяет абстрактный класс `Commands`.
 */
public class RegisterUser extends Commands {

    /**
     * Конструктор класса `RegisterUser`.
     *
     * @param consoleUI Ссылка на объект `ConsoleUI`, который будет использоваться для выполнения команды.
     */
    public RegisterUser(ConsoleUI consoleUI) {
        super(consoleUI, "Регистрация");
    }

    /**
     * Выполняет команду регистрации пользователя, вызывая соответствующий метод в `ConsoleUI`.
     */
    @Override
    void execute() {
        getConsoleUI().registerUser();
    }
}
