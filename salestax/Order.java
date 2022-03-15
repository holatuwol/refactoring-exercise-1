package salestax;

import java.io.PrintStream;
import java.text.DecimalFormat;

public class Order {
    public Order(Product... products) {
        this.products = products;
    }

    public void computeTaxAndPrice() {
        for (int i = 0; i < products.length; i++) {
            double current_product_tax = products[i].calculateTax();
            products[i].setTax(current_product_tax);
            tax = tax + products[i].getTax();
            total_price = total_price + products[i].getTotalcost();
        }
    }

    public void printOrderDetails(PrintStream out) {
        DecimalFormat twoDecimals = new DecimalFormat("0.00");

        for(int i = 0; i < products.length; i++)
        {
            out.println("1 "+ products[i].getName()+": "+ twoDecimals.format(products[i].getTotalcost()));
        }

        out.println("Sales Tax: "+twoDecimals.format(tax));
        out.println("Total: "+twoDecimals.format(total_price));
    }

    public double tax;
    public double total_price;

    public Product[] products;
}
