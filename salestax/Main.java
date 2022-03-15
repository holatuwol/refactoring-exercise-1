package salestax;

import java.io.*;
import java.text.*;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    private static double precision = 20; //Rounds value to .05

    //Each list is an array of products that make up 1 order
    private static List<Order> orders = new ArrayList<>();

    private static double basic_sales_tax_rate = .10;
    private static double import_tax_rate = .05;
    
    public static void main(String[] args)
            throws Exception
    {
        createOrderList(); //Method creates orders

        //Sums up the total cost and total taxes due for a given order.
        for (Order order : orders) {
            order.computeTaxAndPrice();
        }

        System.out.println(printInfo()); //prints and formats output
    }

    //fills the list array by creating product for each index in the array
    //based on the input list provided
    public static void createOrderList()
    {
        orders.add(
            new Order(
            new Product("book", 12.49,false,false),
            new Product("music cd", 14.99, true, false),
            new Product("chocolate bar",0.85, false, false))
        );

        orders.add(
                new Order(
            new Product("imported box of chocolates",10.00, false, true),
            new Product("imported bottle of perfume",47.50, true, true))
        );

        orders.add(
                new Order(
            new Product("imported bottle of perfume",27.99, true, true),
            new Product("bottle of perfume",18.99, true, false),
            new Product("packet of headache pills",9.75, false, false),
            new Product("box of imported chocolates",11.25, false, true))
        );
    }
    //calculates the tax for a given product p
    public static double calculateTax(Product p)
    {
        double sales_tax = 0;
        double import_tax = 0;
        double total_tax = 0;
        
        //if basic sales tax applies
        if(p.isTaxable() == true)
        {
            sales_tax = p.getPrice() * basic_sales_tax_rate;
        }

        //if import taxes apply
        if(p.isImportedProduct() == true)
        {
           import_tax = p.getPrice() * import_tax_rate;
        }
        total_tax = sales_tax + import_tax;
        total_tax = Math.floor(total_tax * precision +.5)/precision;
        return total_tax;
    }

    public static String printInfo()
        throws Exception
    {
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	PrintStream out = new PrintStream(baos);

        //ensures that only two decimal places are shown
        DecimalFormat twoDecimals = new DecimalFormat("0.00");

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
