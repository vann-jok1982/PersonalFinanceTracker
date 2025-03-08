package PersonalFinanceTracker.data.service;

import PersonalFinanceTracker.data.model.Transaction;
import PersonalFinanceTracker.enamCategory.Category;
import PersonalFinanceTracker.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TransactionService {

    private final List<Transaction> transactions = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(User user) {
        System.out.println("Введите сумму транзакции:");
        double amount;
        try {
            amount = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Некорректный формат суммы.");
            return;
        }

        System.out.println("Введите категорию (INCOME, FOOD, TRANSPORT, ENTERTAINMENT, OTHER):");
        Category category;
        try {
            category = Category.valueOf(sc.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Некорректная категория.");
            return;
        }

        System.out.println("Введите дату транзакции (YYYY-MM-DD):");
        LocalDate date;
        try {
            date = LocalDate.parse(sc.nextLine());
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Некорректный формат даты.");
            return;
        }

        System.out.println("Введите описание транзакции:");
        String description = sc.nextLine();

        Transaction newTransaction = new Transaction(user.getEmail(), amount, description, category, date);
        transactions.add(newTransaction);
        System.out.println("Транзакция добавлена!");
    }
    public void updateTransaction(User user) {
        System.out.println("Введите id транзакции:");
        int id = Integer.parseInt(sc.nextLine());
        for (Transaction transaction : transactions) {
            if (transaction.getId() == id) {
                System.out.println("Введите новую сумму транзакции:");
                double amount;
                try {
                    amount = Double.parseDouble(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Некорректный формат суммы.");
                    return;
                }
                System.out.println("Введите новую категорию (INCOME, FOOD, TRANSPORT, ENTERTAINMENT, OTHER) :");
                Category category;
                try {
                    category = Category.valueOf(sc.nextLine().toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Некорректная категория.");
                    return;
                }
                System.out.println("Введите новую дату транзакции (YYYY-MM-DD):");
                LocalDate date;
                try {
                    date = LocalDate.parse(sc.nextLine());
                } catch (java.time.format.DateTimeParseException e) {
                    System.out.println("Некорректный формат даты.");
                    return;
                }

                System.out.println("Введите новое описание транзакции:");
                String description = sc.nextLine();
                Transaction newTransaction = new Transaction(user.getEmail(), amount, description, category, date);
                transactions.set(id-1, newTransaction);
            }
        }
    }
    public void deleteTransaction() {
        System.out.println("Введите номер транзакции , которую нужно удалить :");
        int id = Integer.parseInt(sc.nextLine());
        for (Transaction transaction : transactions) {
            if (transaction.getId() == id) {
                transactions.remove(transaction);
            }
        }
    }

    public void viewTransactions(User currentUser) {

        if (transactions.isEmpty()) {
            System.out.println("Нет транзакций для отображения.");
            return;
        }

        System.out.println("ID  |  Сумма  |  Категория  |  Дата  |  Описание");
        System.out.println("----------------------------------------------------");
        for (Transaction transaction : transactions) {
            if (transaction.getUserEmail().equals(currentUser.getEmail()))
                System.out.printf("%-3d|  %-6.2f|  %-9s|  %-10s|  %s%n",
                        transaction.getId(),
                        transaction.getAmount(),
                        transaction.getCategory(),
                        transaction.getDate(),
                        transaction.getDescription());
        }
    }
    public void showStatistics(User currentUser) {
        if (currentUser == null) {
            System.out.println("Пожалуйста, войдите в систему, чтобы просмотреть статистику.");
            return;
        }

        double balance = transactions.stream()
                .filter(t -> t.getUserEmail().equals(currentUser.getEmail()))
                .mapToDouble(Transaction::getAmount)
                .sum();
        System.out.println("Текущий баланс: " + balance);

        Map<Category, Double> expensesByCategory = transactions.stream()
                .filter(t -> t.getUserEmail().equals(currentUser.getEmail()) && t.getAmount() < 0)
                .collect(Collectors.groupingBy(Transaction::getCategory, Collectors.summingDouble(Transaction::getAmount)));

        System.out.println("Анализ расходов по категориям:");
        expensesByCategory.forEach((category, amount) -> System.out.println(category + ": " + amount));

    }
}
