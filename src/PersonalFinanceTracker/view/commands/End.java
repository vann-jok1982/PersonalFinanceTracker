package PersonalFinanceTracker.view.commands;

import PersonalFinanceTracker.view.consoleUi.ConsoleUI;

public class End extends Commands{
    public End(ConsoleUI consoleUI) {
        super(consoleUI,"Завершить работу");// description устанавливаем тут
    }

    @Override
    void execute() {
        getConsoleUI().end();
    }
}
