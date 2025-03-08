package PersonalFinanceTracker.view.commands;

import PersonalFinanceTracker.view.consoleUi.ConsoleUI;

/**
 * Абстрактный класс `Commands` является базовым классом для всех команд в приложении.
 * Он содержит общие поля и методы, необходимые для выполнения команд через ConsoleUI.
 */
public abstract class Commands {

    /**
     * Ссылка на объект `ConsoleUI`, который используется для взаимодействия с пользователем.
     */
    private ConsoleUI consoleUI;

    /**
     * Описание команды, которое отображается в меню.
     */
    private String description;

    /**
     * Конструктор класса `Commands`.
     *
     * @param consoleUI   Ссылка на объект `ConsoleUI`.
     * @param description Описание команды.
     */
    public Commands(ConsoleUI consoleUI, String description) {
        this.consoleUI = consoleUI;
        this.description = description;
    }

    /**
     * Абстрактный метод для выполнения команды. Должен быть реализован в классах-наследниках.
     */
    abstract void execute();

    /**
     * Возвращает описание команды.
     *
     * @return Описание команды.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Возвращает ссылку на объект `ConsoleUI`.
     *
     * @return Ссылка на объект `ConsoleUI`.
     */
    public ConsoleUI getConsoleUI() {
        return consoleUI;
    }
}