package salestax;

public class Order {
    public Order(Product... products) {
        this.products = products;
    }

    public Product[] products;
}
