package PersonalFinanceTracker.view.commands;

import PersonalFinanceTracker.view.consoleUi.ConsoleUI;

class ShowMainMenuCommand extends Commands {

    public ShowMainMenuCommand(ConsoleUI consoleUI) {
        super(consoleUI, "Показать главное меню");
    }

    @Override
    void execute() {
        getConsoleUI().showMainMenu();
    }
}