package br.com.fintech.fintechweb.model;

public class MoneyOut extends Transaction {

    public MoneyOut(double value, String createdAt, String description, int fintechUserId) {
        super(value, createdAt, description, fintechUserId);
    }

    public MoneyOut(int id, double value, String createdAt, String description, int fintechUserId) {
        super(id, value, createdAt, description, fintechUserId);
    }

    @Override
    public String getType() {
        return "Despesas";
    }
}
