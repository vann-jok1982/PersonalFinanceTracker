package PersonalFinanceTracker.view.commands;

import PersonalFinanceTracker.view.consoleUi.ConsoleUI;

public class UpdateTransaction extends Commands {

    public UpdateTransaction(ConsoleUI consoleUI) {
        super(consoleUI, "Редактирование транзакции");
    }

    @Override
    void execute() {
        getConsoleUI().updateTransaction();
    }
}
