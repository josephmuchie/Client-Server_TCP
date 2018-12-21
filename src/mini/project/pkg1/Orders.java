package mini.project.pkg1;

/**
 * @author Joseph Muchengeti && Joseph-Ben Okanlawon
 */
public class Orders {

    private int id;
    private String product_name;
    private double price;
    private int quantity;
    private Total total;
    private double grandTotal;

    public Orders() {

    }

    public Orders(int number, String product_name, double price, int quantity, Total total, double grandTotal) {
        this.id = number;
        this.product_name = product_name;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.grandTotal = grandTotal;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getProductName() {
        return product_name;
    }

    public void setProductName(String product_name) {
        this.product_name = product_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

}
