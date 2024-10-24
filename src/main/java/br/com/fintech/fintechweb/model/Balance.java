package br.com.fintech.fintechweb.model;

public class Balance {
    private int id;
    private double value;
    private String createdAt;
    private int fintechUserId;

    public Balance(int id, double value, String createdAt, int fintechUserId) {
        this.id = id;
        this.value = value;
        this.createdAt = createdAt;
        this.fintechUserId = fintechUserId;
    }

    public Balance(double value, String createdAt, int fintechUserId) {
        this.value = value;
        this.createdAt = createdAt;
        this.fintechUserId = fintechUserId;
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

    public int getFintechUserId() {
        return fintechUserId;
    }

    public void setFintechUserId(int fintechUserId) {
        this.fintechUserId = fintechUserId;
    }
}
