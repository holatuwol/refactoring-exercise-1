package salestax;

import java.io.*;
import java.text.*;
import java.util.ArrayList;
import java.util.List;

public class Main
{

    //Each list is an array of products that make up 1 order
    private static List<Order> orders = new ArrayList<>();

    public static void main(String[] args)
            throws Exception
    {
        createOrderList(); //Method creates orders

        computeTaxAndPrice();

        System.out.println(printInfo()); //prints and formats output
    }

    public static void computeTaxAndPrice() {
        for (Order order : orders) {
            order.computeTaxAndPrice();
        }
    }

    //fills the list array by creating product for each index in the array
    //based on the input list provided
    public static void createOrderList()
    {
        orders.add(
            new Order(
                new Product("book", 12.49),
                new Product("music cd", 14.99, BasicSalesTax.INSTANCE),
                new Product("chocolate bar",0.85))
        );

        orders.add(
            new Order(
                new Product("imported box of chocolates",10.00, ImportTax.INSTANCE),
                new Product("imported bottle of perfume",47.50, BasicSalesTax.INSTANCE, ImportTax.INSTANCE))
        );

        orders.add(
            new Order(
                new Product("imported bottle of perfume",27.99, BasicSalesTax.INSTANCE, ImportTax.INSTANCE),
                new Product("bottle of perfume",18.99, BasicSalesTax.INSTANCE),
                new Product("packet of headache pills",9.75),
                new Product("box of imported chocolates",11.25, ImportTax.INSTANCE))
        );
    }

    public static String printInfo()
        throws Exception
    {
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	PrintStream out = new PrintStream(baos);

        for (int i = 0; i < orders.size(); i++) {
            if (i != 0) {
                out.println("");
                out.println("");
            }

            out.println("Output " + (i+1));

            orders.get(i).printOrderDetails(out);
        }

        return baos.toString("UTF-8");
    }
}
