package model;

public class Item {
    private String code;
    private String description;
    private double unitprice;
    private int qty;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Item(String code, String description, double unitprice, int qty) {
        this.code = code;
        this.description = description;
        this.unitprice = unitprice;
        this.qty = qty;
    }
}
