package PersonalFinanceTracker.view.commands;

import PersonalFinanceTracker.view.consoleUi.ConsoleUI;

/**
 * Класс `EditProfileCommand` представляет собой команду для редактирования профиля пользователя.
 * Расширяет абстрактный класс `Commands`.
 */
public class EditProfileCommand extends Commands {

    /**
     * Конструктор класса `EditProfileCommand`.
     *
     * @param consoleUI Ссылка на объект `ConsoleUI`, который будет использоваться для выполнения команды.
     */
    public EditProfileCommand(ConsoleUI consoleUI) {
        super(consoleUI, "Редактировать профиль");
    }

    /**
     * Выполняет команду редактирования профиля, вызывая соответствующий метод в `ConsoleUI`.
     */
    @Override
    void execute() {
        getConsoleUI().editProfile();
    }
}