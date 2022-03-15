package salestax;

public class Order {
    public Order(Product... products) {
        this.products = products;
    }

    public void computeTaxAndPrice() {
        for (int i = 0; i < products.length; i++) {
            double current_product_tax = Main.calculateTax(products[i]);
            products[i].setTax(current_product_tax);
            tax = tax + products[i].getTax();
            total_price = total_price + products[i].getTotalcost();
        }
    }

    public double tax;
    public double total_price;

    public Product[] products;
}
