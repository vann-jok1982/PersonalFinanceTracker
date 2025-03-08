package PersonalFinanceTracker.data.model;

import PersonalFinanceTracker.enamCategory.Category;

import java.time.LocalDate;

/**
 * Класс Transaction представляет собой запись о финансовой транзакции.
 * Каждая транзакция связана с пользователем и имеет сумму, описание, категорию и дату.
 */
public class Transaction {

    /**
     * Статическое поле для автоматической генерации уникальных идентификаторов транзакций.
     */
    private static int nextId = 1;

    /**
     * Уникальный идентификатор транзакции.
     */
    private int id;

    /**
     * Email пользователя, которому принадлежит транзакция.
     */
    private String userEmail;

    /**
     * Сумма транзакции (положительное значение для дохода, отрицательное для расхода).
     */
    private double amount;

    /**
     * Описание транзакции.
     */
    private String description;

    /**
     * Категория транзакции (например, доход, еда, транспорт и т.д.).
     */
    private Category category;

    /**
     * Дата транзакции.
     */
    private LocalDate date;

    /**
     * Конструктор класса Transaction.
     *
     * @param userEmail   Email пользователя, совершившего транзакцию.
     * @param amount      Сумма транзакции.
     * @param description Описание транзакции.
     * @param category    Категория транзакции.
     * @param date        Дата транзакции.
     */
    public Transaction(String userEmail, double amount, String description, Category category, LocalDate date) {
        this.id = nextId++; // Присваиваем уникальный ID и увеличиваем счетчик
        this.userEmail = userEmail;
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.date = date;
    }

    /**
     * Возвращает уникальный идентификатор транзакции.
     *
     * @return Уникальный идентификатор транзакции.
     */
    public int getId() {
        return id;
    }

    /**
     * Возвращает email пользователя, которому принадлежит транзакция.
     *
     * @return Email пользователя.
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Возвращает сумму транзакции.
     *
     * @return Сумма транзакции.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Возвращает категорию транзакции.
     *
     * @return Категория транзакции.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Возвращает дату транзакции.
     *
     * @return Дата транзакции.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Возвращает описание транзакции.
     *
     * @return Описание транзакции.
     */
    public String getDescription() {
        return description;
    }
}



