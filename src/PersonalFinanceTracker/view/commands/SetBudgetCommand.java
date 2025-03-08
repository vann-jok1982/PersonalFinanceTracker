package PersonalFinanceTracker.view.commands;

import PersonalFinanceTracker.view.consoleUi.ConsoleUI;

/**
 * Класс `SetBudgetCommand` представляет собой команду для установки месячного бюджета пользователя.
 * Расширяет абстрактный класс `Commands`.
 */
public class SetBudgetCommand extends Commands {

    /**
     * Конструктор класса `SetBudgetCommand`.
     *
     * @param consoleUI Ссылка на объект `ConsoleUI`, который будет использоваться для выполнения команды.
     */
    public SetBudgetCommand(ConsoleUI consoleUI) {
        super(consoleUI, "Установить месячный бюджет");
    }

    /**
     * Выполняет команду установки месячного бюджета, вызывая соответствующий метод в `ConsoleUI`.
     */
    @Override
    void execute() {
        getConsoleUI().setBudget();
    }
}