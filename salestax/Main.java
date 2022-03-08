package salestax;

import java.io.*;
import java.text.*;

public class Main
{
    private static double precision = 20; //Rounds value to .05

    //Each list is an array of products that make up 1 order
    private static Order listOne = null;
    private static double list_one_tax = 0;
    private static double list_one_total_price = 0;

    private static Order listTwo = null;
    private static double list_two_tax = 0;
    private static double list_two_total_price = 0;

    private static Order listThree = null;
    private static double list_three_tax = 0;
    private static double list_three_total_price = 0;

    private static double basic_sales_tax_rate = .10;
    private static double import_tax_rate = .05;
    
    public static void main(String[] args)
            throws Exception
    {
        createOrderList(); //Method creates orders

        //Sums up the total cost and total taxes due for a given order.
        for(int i =  0; i < listOne.products.length; i++)
        {
            double current_product_tax  = calculateTax(listOne.products[i]);
            listOne.products[i].setTax(current_product_tax);
            list_one_tax = list_one_tax + listOne.products[i].getTax();
            list_one_total_price = list_one_total_price + listOne.products[i].getTotalcost();
        }

        for(int i =  0; i < listTwo.products.length; i++)
        {
            double current_product_tax  = calculateTax(listTwo.products[i]);
            listTwo.products[i].setTax(current_product_tax);
            list_two_tax = list_two_tax + listTwo.products[i].getTax();
            list_two_total_price = list_two_total_price + listTwo.products[i].getTotalcost();
        }

        for(int i =  0; i < listThree.products.length; i++)
        {
            double current_product_tax  = calculateTax(listThree.products[i]);
            listThree.products[i].setTax(current_product_tax);
            list_three_tax = list_three_tax + listThree.products[i].getTax();
            list_three_total_price = list_three_total_price + listThree.products[i].getTotalcost();
        }
        
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
        for(int i = 0; i < listOne.products.length; i++)
        {
            out.println("1 "+listOne.products[i].getName()+": "+twoDecimals.format(listOne.products[i].getTotalcost()));
        }

        out.println("Sales Tax: "+twoDecimals.format(list_one_tax));
        out.println("Total: "+twoDecimals.format(list_one_total_price));

        out.println("");
        out.println("");

        out.println("Output 2");
        for(int i = 0; i < listTwo.products.length; i++)
        {
            out.println("1 "+listTwo.products[i].getName()+": "+twoDecimals.format(listTwo.products[i].getTotalcost()));
        }

        out.println("Sales Tax: "+twoDecimals.format(list_two_tax));
        out.println("Total: "+twoDecimals.format(list_two_total_price));

        out.println("");
        out.println("");

        out.println("Output 3");
        for(int i = 0; i < listThree.products.length; i++)
        {
            out.println("1 "+listThree.products[i].getName()+": "+twoDecimals.format(listThree.products[i].getTotalcost()));
        }

        out.println("Sales Tax: "+twoDecimals.format(list_three_tax));
        out.println("Total: "+twoDecimals.format(list_three_total_price));

        return baos.toString("UTF-8");
    }
}
