package PersonalFinanceTracker.view.commands;

import PersonalFinanceTracker.view.consoleUi.ConsoleUI;

class EditProfileCommand extends Commands {

    public EditProfileCommand(ConsoleUI consoleUI) {
        super(consoleUI, "Редактировать профиль");
    }

    @Override
    void execute() {
        getConsoleUI().editProfile();
    }
}