package PersonalFinanceTracker.view.commands;

import PersonalFinanceTracker.view.consoleUi.ConsoleUI;

public class DeleteTransaction extends Commands {

    public DeleteTransaction(ConsoleUI consoleUI) {
        super(consoleUI, "Удалить транзакцию");
    }

    @Override
    void execute() {
        getConsoleUI().deleteTransaction();
    }
}
