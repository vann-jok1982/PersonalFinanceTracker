package PersonalFinanceTracker.data.model;

import PersonalFinanceTracker.enamCategory.Category;

import java.time.LocalDate;

public class Transaction {
    private static int nextId = 1;
    private int id;
    private String userEmail;
    private double amount;
    private String description;
    private Category category;
    private LocalDate date;

    public Transaction(String userEmail, double amount, String description, Category category, LocalDate date) {
        this.id = nextId++;
        this.userEmail = userEmail;
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public double getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}



