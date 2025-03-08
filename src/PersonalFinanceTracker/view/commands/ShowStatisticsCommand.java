package PersonalFinanceTracker.view.commands;

import PersonalFinanceTracker.view.consoleUi.ConsoleUI;

class ShowStatisticsCommand extends Commands {

    public ShowStatisticsCommand(ConsoleUI consoleUI) {
        super(consoleUI, "Показать статистику и аналитику");
    }

    @Override
    void execute() {
        getConsoleUI().showStatistics();
    }
}
