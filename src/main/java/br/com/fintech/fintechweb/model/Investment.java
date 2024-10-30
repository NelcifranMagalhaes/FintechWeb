package br.com.fintech.fintechweb.model;

public class Investment {
    private int id;
    private double value;
    private String createdAt;
    private String description;
    private int fintechUserId;

    public Investment(int id, double value, String createdAt, String description, int fintechUserId) {
        this.id = id;
        this.value = value;
        this.createdAt = createdAt;
        this.description = description;
        this.fintechUserId = fintechUserId;
    }

    public Investment(double value, String createdAt, String description, int fintechUserId) {
        this.value = value;
        this.createdAt = createdAt;
        this.description = description;
        this.fintechUserId = fintechUserId;
    }

    public Investment(int id, double value, String createdAt, String description) {
        this.id = id;
        this.value = value;
        this.createdAt = createdAt;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFintechUserId() {
        return fintechUserId;
    }

    public void setFintechUserId(int fintechUserId) {
        this.fintechUserId = fintechUserId;
    }
}
