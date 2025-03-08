package PersonalFinanceTracker.view.commands;

import PersonalFinanceTracker.view.consoleUi.ConsoleUI;

class LoginUser extends Commands {
    public LoginUser(ConsoleUI consoleUI) {
        super(consoleUI, "Вход");
    }

    @Override
    void execute() {
        getConsoleUI().loginUser();
    }
}