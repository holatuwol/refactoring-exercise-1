package salestax;

import java.io.*;
import java.text.*;

public class Main
{
    private static double precision = 20; //Rounds value to .05

    //Each list is an array of products that make up 1 order
    private static Order listOne = null;
    private static Order listTwo = null;
    private static Order listThree = null;

    private static double basic_sales_tax_rate = .10;
    private static double import_tax_rate = .05;
    
    public static void main(String[] args)
            throws Exception
    {
        createOrderList(); //Method creates orders

        //Sums up the total cost and total taxes due for a given order.
        listOne.computeTaxAndPrice();
        listTwo.computeTaxAndPrice();
        listThree.computeTaxAndPrice();

        System.out.println(printInfo()); //prints and formats output
    }

    //fills the list array by creating product for each index in the array
    //based on the input list provided
    public static void createOrderList()
    {
        listOne = new Order(
            new Product("book", 12.49,false,false),
            new Product("music cd", 14.99, true, false),
            new Product("chocolate bar",0.85, false, false));

        listTwo = new Order(
            new Product("imported box of chocolates",10.00, false, true),
            new Product("imported bottle of perfume",47.50, true, true));

        listThree = new Order(
            new Product("imported bottle of perfume",27.99, true, true),
            new Product("bottle of perfume",18.99, true, false),
            new Product("packet of headache pills",9.75, false, false),
            new Product("box of imported chocolates",11.25, false, true));

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

        out.println("Output 1");
        listOne.printOrderDetails(out);

        out.println("");
        out.println("");

        out.println("Output 2");
        listTwo.printOrderDetails(out);

        out.println("");
        out.println("");

        out.println("Output 3");
        listThree.printOrderDetails(out);

        return baos.toString("UTF-8");
    }
}
