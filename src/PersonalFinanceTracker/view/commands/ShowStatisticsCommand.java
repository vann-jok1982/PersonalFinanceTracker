package PersonalFinanceTracker.view.commands;

import PersonalFinanceTracker.view.consoleUi.ConsoleUI;

/**
 * Класс `ShowStatisticsCommand` представляет собой команду для отображения статистики и аналитики.
 * Расширяет абстрактный класс `Commands`.
 */
public class ShowStatisticsCommand extends Commands {

    /**
     * Конструктор класса `ShowStatisticsCommand`.
     *
     * @param consoleUI Ссылка на объект `ConsoleUI`, который будет использоваться для выполнения команды.
     */
    public ShowStatisticsCommand(ConsoleUI consoleUI) {
        super(consoleUI, "Показать статистику и аналитику");
    }

    /**
     * Выполняет команду отображения статистики и аналитики, вызывая соответствующий метод в `ConsoleUI`.
     */
    @Override
    void execute() {
        getConsoleUI().showStatistics();
    }
}
