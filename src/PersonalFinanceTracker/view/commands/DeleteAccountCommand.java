package PersonalFinanceTracker.view.commands;

import PersonalFinanceTracker.view.consoleUi.ConsoleUI;

class DeleteAccountCommand extends Commands {

    public DeleteAccountCommand(ConsoleUI consoleUI) {
        super(consoleUI, "Удалить аккаунт");
    }

    @Override
    void execute() {
        getConsoleUI().deleteAccount();
    }
}