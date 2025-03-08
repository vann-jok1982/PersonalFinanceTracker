package PersonalFinanceTracker.view.commands;

import PersonalFinanceTracker.view.consoleUi.ConsoleUI;

class ViewTransactionsCommand extends Commands {
    public ViewTransactionsCommand(ConsoleUI consoleUI) {
        super(consoleUI, "Просмотреть транзакции");
    }

    @Override
    void execute() {
        getConsoleUI().viewTransactions();
    }
}