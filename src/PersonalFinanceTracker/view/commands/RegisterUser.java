package PersonalFinanceTracker.view.commands;

import PersonalFinanceTracker.view.consoleUi.ConsoleUI;

class RegisterUser extends Commands {
    public RegisterUser(ConsoleUI consoleUI) {
        super(consoleUI, "Регистрация");
    }

    @Override
    void execute() {
        getConsoleUI().registerUser();
    }
}
