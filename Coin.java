package Main;

public class Coin {
    private int ID;
    private String name;
    private String symbol;
    private double price;

    public Coin(int ID, String name, String symbol, double price) {
        this.ID = ID;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
