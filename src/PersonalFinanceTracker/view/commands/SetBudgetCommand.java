package PersonalFinanceTracker.view.commands;

import PersonalFinanceTracker.view.consoleUi.ConsoleUI;

public class SetBudgetCommand extends Commands {
    public SetBudgetCommand(ConsoleUI consoleUI) {
        super(consoleUI, "Установить месячный бюджет");
    }

    @Override
    void execute() {
        getConsoleUI().setBudget();
    }
}
