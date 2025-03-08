package PersonalFinanceTracker.view.consoleUi;

import PersonalFinanceTracker.data.model.Transaction;
import PersonalFinanceTracker.data.service.TransactionService;
import PersonalFinanceTracker.model.User;
import PersonalFinanceTracker.view.View;
import PersonalFinanceTracker.view.commands.MainMenu;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Класс `ConsoleUI` реализует интерфейс `View` и представляет собой консольный интерфейс пользователя.
 * Он отвечает за взаимодействие с пользователем через консоль, обработку ввода и вызов соответствующих
 * методов для выполнения операций.
 */
public class ConsoleUI implements View {

    /**
     * Флаг, определяющий, должно ли приложение продолжать работу.
     */
    private boolean flag;

    /**
     * Объект `Scanner` для чтения ввода пользователя из консоли.
     */
    private Scanner sc;

    /**
     * Объект `MainMenu`, представляющий главное меню приложения.
     */
    private MainMenu mainMenu;

    /**
     * Текущий залогиненный пользователь.
     */
    private User currentUser = null;

    /**
     * Хранилище пользователей, где ключ - email, а значение - объект `User`.
     */
    private final Map<String, User> users = new HashMap<>();

    /**
     * Объект `TransactionService` для управления транзакциями.
     */
    TransactionService transactionService = new TransactionService();

    /**
     * Конструктор класса `ConsoleUI`.
     * Инициализирует флаг работы, сканер для ввода, и создает главное меню.
     */
    public ConsoleUI() {
        flag = true;
        sc = new Scanner(System.in);
        mainMenu = new MainMenu(this, isLoggedIn());
    }

    /**
     * Запускает основной цикл работы приложения.
     * Выводит приветствие, отображает меню и обрабатывает выбор пользователя до тех пор,
     * пока флаг `flag` не будет установлен в `false`.
     */
    @Override
    public void start() {
        System.out.println("Приветствую тебя пользователь!");
        while (flag) {
            mainMenu = new MainMenu(this, isLoggedIn());
            System.out.println(mainMenu.menu());
            String choiceStr = sc.nextLine();
            if (checkChoice(choiceStr)) {
                int choice = Integer.parseInt(choiceStr);
                mainMenu.execute(choice);
            } else {
                System.out.println("Некорректный ввод. Пожалуйста, введите число от 1 до " + mainMenu.size());
            }
        }
    }

    /**
     * Проверяет, является ли введенный пользователем выбор допустимым.
     *
     * @param choiceStr Строка, представляющая выбор пользователя.
     * @return `true`, если выбор допустим (является числом в пределах диапазона меню), `false` в противном случае.
     */
    public boolean checkChoice(String choiceStr) {
        if (choiceStr.matches("[0-9]+")) { // Проверить, что это число
            int choice = Integer.parseInt(choiceStr);
            return choice > 0 && choice <= mainMenu.size();
        } else {
            return false;
        }
    }

    /**
     * Завершает работу приложения, устанавливая флаг `flag` в `false`.
     */
    public void end() {
        flag = false;
    }

    /**
     * Метод для вывода ответа (в данном приложении пока не используется).
     *
     * @param answer Ответ, который нужно вывести.
     */
    @Override
    public void printAnswer(int answer) {
        // В данном приложении этот метод пока не используется
    }

    // --- Методы для команд ---
    /**
     * Регистрирует нового пользователя.
     * Запрашивает у пользователя email, пароль и имя, проверяет, не занят ли email,
     * и создает нового пользователя, добавляя его в хранилище `users`.
     */
    public void registerUser() {
        System.out.println("Введите email:");
        String email = sc.nextLine();
        if (users.containsKey(email)) {
            System.out.println("Этот email уже зарегистрирован.");
            return;
        }

        System.out.println("Введите пароль:");
        String password = sc.nextLine();
        System.out.println("Введите имя:");
        String name = sc.nextLine();

        User newUser = new User(email, password, name);
        users.put(email, newUser);
        System.out.println("Регистрация успешна!");
    }

    /**
     * Аутентифицирует пользователя.
     * Запрашивает у пользователя email и пароль, проверяет, существует ли пользователь с таким email
     * и соответствует ли введенный пароль сохраненному паролю пользователя.
     * В случае успеха устанавливает текущего пользователя (`currentUser`).
     */
    public void loginUser() {
        System.out.println("Введите email:");
        String email = sc.nextLine();
        System.out.println("Введите пароль:");
        String password = sc.nextLine();

        User user = users.get(email);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            System.out.println("Вход выполнен, " + currentUser.getName() + "!");
        } else {
            System.out.println("Неверный email или пароль.");
        }
    }

    /**
     * Отображает главное меню.
     * Выводит приветствие для залогиненного пользователя или предлагает войти/зарегистрироваться,
     * если пользователь не залогинен.
     */
    public void showMainMenu() {
        if (currentUser != null) {
            System.out.println("Добро пожаловать, " + currentUser.getName() + "!");

        } else {
            System.out.println("Пожалуйста, войдите или зарегистрируйтесь.");
        }
    }

    /**
     * Добавляет новую транзакцию.
     * Вызывает метод `addTransaction` из `transactionService` для добавления транзакции
     * и затем вызывает метод `checkBudget` для проверки бюджета.  Требует, чтобы был залогинен текущий пользователь.
     */
    public void addTransaction() {
        if (currentUser == null) {
            System.out.println("Пожалуйста, войдите в систему, чтобы добавить транзакцию.");
            return;
        }
        transactionService.addTransaction(currentUser);
        checkBudget();
    }

    /**
     * Обновляет существующую транзакцию.
     * Вызывает метод `updateTransaction` из `transactionService` для обновления транзакции
     * и затем вызывает метод `checkBudget` для проверки бюджета. Требует, чтобы был залогинен текущий пользователь.
     */
    public void updateTransaction() {
        if (currentUser == null) {
            System.out.println("Пожалуйста, войдите в систему, чтобы обновить транзакцию.");
            return;
        }
        transactionService.updateTransaction(currentUser);
        checkBudget();
    }
    /**
     * Удаляет транзакцию для текущего пользователя.
     * Вызывает метод `deleteTransaction` из `TransactionService` для удаления транзакции.
     * Для выполнения этой операции пользователь должен быть залогинен.
     */
    public void deleteTransaction() {
        if (currentUser == null) {
            System.out.println("Пожалуйста, войдите в систему, чтобы обновить транзакцию.");
            return;
        }
        transactionService.deleteTransaction();
    }

    /**
     * Просматривает список транзакций.
     * Вызывает метод `viewTransactions` из `transactionService` для отображения транзакций
     * текущего пользователя. Требует, чтобы был залогинен текущий пользователь.
     */
    public void viewTransactions() {
        if (currentUser == null) {
            System.out.println("Пожалуйста, войдите в систему, чтобы просмотреть транзакции.");
            return;
        }
        transactionService.viewTransactions(currentUser);
    }

    /**
     * Позволяет пользователю редактировать свой профиль.
     * Запрашивает у пользователя новое имя, email и пароль. Если пользователь вводит значение,
     * то соответствующее поле профиля обновляется. Перед обновлением email проверяется,
     * не занят ли он другим пользователем.
     */
    public void editProfile() {
        if (currentUser == null) {
            System.out.println("Пожалуйста, войдите в систему, чтобы редактировать профиль.");
            return;
        }

        System.out.println("Введите новое имя (или оставьте пустым, чтобы не менять):");
        String newName = sc.nextLine();
        if (!newName.isEmpty()) {
            currentUser.setName(newName);
        }

        System.out.println("Введите новый email (или оставьте пустым, чтобы не менять):");
        String newEmail = sc.nextLine();
        if (!newEmail.isEmpty()) {
            if (users.containsKey(newEmail)) {
                System.out.println("Этот email уже зарегистрирован.");
                return;
            }
            users.remove(currentUser.getEmail());
            currentUser.setEmail(newEmail);
            users.put(newEmail, currentUser);
        }

        System.out.println("Введите новый пароль (или оставьте пустым, чтобы не менять):");
        String newPassword = sc.nextLine();
        if (!newPassword.isEmpty()) {
            currentUser.setPassword(newPassword);
        }

        System.out.println("Профиль обновлен!");
    }

    /**
     * Позволяет пользователю удалить свой аккаунт.
     * Запрашивает подтверждение удаления аккаунта. Если пользователь подтверждает удаление,
     * то аккаунт удаляется из хранилища `users`, а текущий пользователь (`currentUser`) становится `null`.
     */
    public void deleteAccount() {
        if (currentUser == null) {
            System.out.println("Пожалуйста, войдите в систему, чтобы удалить аккаунт.");
            return;
        }

        System.out.println("Вы уверены, что хотите удалить аккаунт? (да/нет)");
        String confirmation = sc.nextLine();
        if (confirmation.equalsIgnoreCase("да")) {
            users.remove(currentUser.getEmail());
            currentUser = null;
            System.out.println("Аккаунт удален.");
        } else {
            System.out.println("Удаление отменено.");
        }
    }

    /**
     * Отображает статистику и аналитику текущего пользователя.
     * Вызывает метод `showStatistics` из `transactionService` для отображения статистики.
     */
    public void showStatistics() {
        if (currentUser == null) {
            System.out.println("Пожалуйста, войдите в систему, чтобы просмотреть статистику.");
            return;
        }
        transactionService.showStatistics(currentUser);
    }

    /**
     * Проверяет, залогинен ли пользователь.
     *
     * @return `true`, если пользователь залогинен (`currentUser` не равен `null`), `false` в противном случае.
     */
    public boolean isLoggedIn() {
        return currentUser != null;
    }

    /**
     * Устанавливает месячный бюджет для текущего пользователя.
     * Запрашивает у пользователя сумму месячного бюджета, проверяет корректность ввода (должно быть положительное число),
     * и устанавливает бюджет для текущего пользователя.
     */
    public void setBudget() {
        if (currentUser == null) {
            System.out.println("Пожалуйста, войдите в систему.");
            return;
        }

        System.out.println("Введите месячный бюджет:");
        double budget;
        try {
            budget = Double.parseDouble(sc.nextLine());
            if (budget <= 0) {
                System.out.println("Бюджет должен быть больше нуля.");
                return;
            }
            currentUser.setMonthlyBudget(budget);
            System.out.println("Месячный бюджет установлен на " + budget);
        } catch (NumberFormatException e) {
            System.out.println("Некорректный формат бюджета.");
        }
    }

    /**
     * Проверяет, не превышены ли расходы текущего пользователя в текущем месяце установленный бюджет.
     * Если бюджет превышен, выводит сообщение с информацией о бюджете и расходах.
     */
    private void checkBudget() {
        if (currentUser == null) {
            return; // Ничего не делаем, если пользователь не вошел в систему
        }

        if (currentUser.getMonthlyBudget() == 0) {
            return; // Если бюджет не установлен, не проверяем
        }

        LocalDate now = LocalDate.now();
        LocalDate startOfMonth = now.withDayOfMonth(1); // Начало текущего месяца
        double expensesThisMonth = transactionService.getTransactions().stream()
                .filter(transaction -> transaction.getUserEmail().equals(currentUser.getEmail()) &&
                        transaction.getAmount() < 0 && // Только расходы
                        transaction.getDate().compareTo(startOfMonth) >= 0 && // Только в этом месяце
                        transaction.getDate().compareTo(now) <= 0) // Только до сегодня
                .mapToDouble(Transaction::getAmount)
                .sum();

        // expensesThisMonth - это отрицательное число, поэтому делаем его положительным для сравнения
        if (Math.abs(expensesThisMonth) > currentUser.getMonthlyBudget()) {
            System.out.println("\nВнимание! Превышен месячный бюджет.");
            System.out.println("Бюджет: " + currentUser.getMonthlyBudget());
            System.out.println("Расходы в этом месяце: " + Math.abs(expensesThisMonth));
        }
    }
}

