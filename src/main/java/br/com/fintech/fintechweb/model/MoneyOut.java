package br.com.fintech.fintechweb.model;

public class MoneyOut extends Transaction {
    private String category;

    public MoneyOut(double value, String createdAt, String description, int fintechUserId, String category) {
        super(value, createdAt, description, fintechUserId);
        this.category = category;
    }

    public MoneyOut(int id, double value, String createdAt, String description, int fintechUserId, String category) {
        super(id, value, createdAt, description, fintechUserId);
        this.category = category;
    }

    public MoneyOut(int id, double value, String createdAt, String description, String category) {
        super(id, value, createdAt, description);
        this.category = category;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getType() {
        return "Despesas";
    }
}
