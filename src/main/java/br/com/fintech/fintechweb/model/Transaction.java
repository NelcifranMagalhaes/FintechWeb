package br.com.fintech.fintechweb.model;

import java.util.UUID;

public abstract class Transaction {
    private int id;
    private double value;
    private String createdAt;
    private String description;
    private int fintechUserId;

    public Transaction(int id, double value, String createdAt, String description, int fintechUserId) {
        this.value = value;
        this.createdAt = createdAt;
        this.id = id;
        this.description = description;
        this.fintechUserId = fintechUserId;
    }

    public Transaction(double value, String createdAt, String description, int fintechUserId) {
        this.value = value;
        this.createdAt = createdAt;
        this.description = description;
        this.fintechUserId = fintechUserId;
    }

    public Transaction(int id, double value, String createdAt, String description){
        this.value = value;
        this.createdAt = createdAt;
        this.description = description;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public abstract String getType();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) { this.id = id; }

    public int getFintechUserId() {
        return fintechUserId;
    }

    public void setFintechUserId(int fintechUserId) {
        this.fintechUserId = fintechUserId;
    }
}
