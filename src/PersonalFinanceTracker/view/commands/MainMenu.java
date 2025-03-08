package PersonalFinanceTracker.view.commands;

import PersonalFinanceTracker.view.consoleUi.ConsoleUI;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс `MainMenu` представляет собой главное меню приложения.
 * Он содержит список доступных команд в зависимости от того, вошел ли пользователь в систему.
 */
public class MainMenu {

    /**
     * Список команд, доступных в главном меню.
     */
    private List<Commands> commandsList;

    /**
     * Конструктор класса `MainMenu`.
     *
     * @param consoleUI   Ссылка на объект `ConsoleUI`, который будет использоваться для выполнения команд.
     * @param isLoggedIn  Флаг, указывающий, вошел ли пользователь в систему (true) или нет (false).
     */
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
            commandsList.add(new DeleteTransaction(consoleUI));
        }
        commandsList.add(new End(consoleUI));
    }

    /**
     * Формирует строку, представляющую меню с нумерованным списком команд и их описаниями.
     *
     * @return Строка, представляющая меню.
     */
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

    /**
     * Выполняет команду, соответствующую указанному выбору пользователя.
     *
     * @param choice Номер выбранной команды.
     */
    public void execute(int choice) {
        if (choice > 0 && choice <= commandsList.size()) {
            Commands commands = commandsList.get(choice - 1);
            commands.execute();
        } else {
            System.out.println("Неверный выбор.");
        }
    }

    /**
     * Возвращает количество команд в меню.
     *
     * @return Количество команд.
     */
    public int size() {
        return commandsList.size();
    }

    /**
     * Возвращает команду по указанному индексу.
     *
     * @param index Индекс команды.
     * @return Команда по указанному индексу.
     */
    public Commands getCommand(int index) {
        return commandsList.get(index);
    }

    /**
     * Возвращает список команд, доступных в меню.
     *
     * @return Список команд.
     */
    public List<Commands> getCommandsList() {
        return commandsList;
    }
}
