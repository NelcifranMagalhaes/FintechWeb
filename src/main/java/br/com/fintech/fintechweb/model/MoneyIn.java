package br.com.fintech.fintechweb.model;


public class MoneyIn extends Transaction {

    public MoneyIn(double value, String createdAt, String description, int fintechUserId) {
        super(value, createdAt, description, fintechUserId);
    }

    public MoneyIn(int id, double value, String createdAt, String description, int fintechUserId) {
        super(id, value, createdAt, description, fintechUserId);
    }

    public MoneyIn(int id, double value, String createdAt, String description) {
        super(id, value, createdAt, description);
    }
    @Override
    public String getType() {
        return "Receita";
    }

    @Override
    public String getCategory() {
        return "";
    }

    @Override
    public void setCategory(String category) {

    }

}
