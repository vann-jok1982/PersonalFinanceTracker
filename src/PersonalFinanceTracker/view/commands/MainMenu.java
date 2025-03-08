package PersonalFinanceTracker.view.commands;

import PersonalFinanceTracker.view.consoleUi.ConsoleUI;

import java.util.ArrayList;
import java.util.List;

public class MainMenu {
    List<Commands> commandsList;

    public MainMenu(ConsoleUI consoleUI, boolean isLoggedIn) {
        commandsList = new ArrayList<>();
        if (!isLoggedIn) {
            commandsList.add(new RegisterUser(consoleUI));
            commandsList.add(new LoginUser(consoleUI));
        } else {
            commandsList.add(new AddTransactionCommand(consoleUI));
            commandsList.add(new ViewTransactionsCommand(consoleUI));
            commandsList.add(new EditProfileCommand(consoleUI));
            commandsList.add(new DeleteAccountCommand(consoleUI));
            commandsList.add(new ShowStatisticsCommand(consoleUI));
            commandsList.add(new UpdateTransaction(consoleUI));

            commandsList.add(new SetBudgetCommand(consoleUI));


        }
        commandsList.add(new End(consoleUI));
    }

    public String menu() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Выберите действие:\n");
        for (int i = 0; i < commandsList.size(); i++) {
            stringBuilder.append(i + 1);
            stringBuilder.append(". ");
            stringBuilder.append(commandsList.get(i).getDescription());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public void execute(int choice) {
        if (choice > 0 && choice <= commandsList.size()) {
            Commands commands = commandsList.get(choice - 1);
            commands.execute();
        } else {
            System.out.println("Неверный выбор.");
        }
    }

    public int size() {
        return commandsList.size();
    }

    public Commands getCommand(int index) {
        return commandsList.get(index);
    }

    public List<Commands> getCommandsList() {
        return commandsList;
    }
}
