package PersonalFinanceTracker.view.consoleUi;

import PersonalFinanceTracker.data.service.TransactionService;
import PersonalFinanceTracker.enamCategory.Category;
import PersonalFinanceTracker.model.User;
import PersonalFinanceTracker.data.model.Transaction;
import PersonalFinanceTracker.view.View;
import PersonalFinanceTracker.view.commands.MainMenu;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ConsoleUI implements View {
    private boolean flag;
    private Scanner sc;
    private MainMenu mainMenu;
    private User currentUser = null;  // Текущий залогиненный пользователь
    private final Map<String, User> users = new HashMap<>(); // Хранение пользователей (email -> User)
//    private final List<Transaction> transactions = new ArrayList<>();
    TransactionService transactionService = new TransactionService();

    public ConsoleUI() {
        flag = true;
        sc = new Scanner(System.in);
        mainMenu = new MainMenu(this, isLoggedIn());
    }

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

    public boolean checkChoice(String choiceStr) {
        if (choiceStr.matches("[0-9]+")) { // Проверить, что это число
            int choice = Integer.parseInt(choiceStr);
            return choice > 0 && choice <= mainMenu.size();
        } else {
            return false;
        }
    }

    public void end() {
        flag = false;
    }

    @Override
    public void printAnswer(int answer) {
        // В данном приложении этот метод пока не используется
    }

    // --- Методы для команд ---

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
    public void showMainMenu() {
        if (currentUser != null) {
            System.out.println("Добро пожаловать, " + currentUser.getName() + "!");

        } else {
            System.out.println("Пожалуйста, войдите или зарегистрируйтесь.");
        }
    }

    public void addTransaction() {
       transactionService.addTransaction(currentUser);
       checkBudget();
    }
    public void updateTransaction(){
        transactionService.updateTransaction(currentUser);
        checkBudget();
    }
    public void viewTransactions() {
        transactionService.viewTransactions(currentUser);
    }

    public void editProfile() {
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

    public void deleteAccount() {
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

    public void showStatistics() {
       transactionService.showStatistics(currentUser);

    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }
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

