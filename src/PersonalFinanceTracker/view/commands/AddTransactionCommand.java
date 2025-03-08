package PersonalFinanceTracker.view.commands;

import PersonalFinanceTracker.view.consoleUi.ConsoleUI;

class AddTransactionCommand extends Commands {
    public AddTransactionCommand(ConsoleUI consoleUI) {
        super(consoleUI, "Добавить транзакцию");
    }

    @Override
    void execute() {
        getConsoleUI().addTransaction();
    }
}