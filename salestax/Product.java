
package salestax;

public class Product
{
    private static double precision = 20; //Rounds value to .05

    private String product_name;
    private double tax_amount;    //Tax due on the product
    private double product_price; //Product price before tax

    private Tax[] taxes;

    public Product(String p_name, double p_price, Tax... p_taxes)
    {
        product_name = p_name;
        product_price = p_price;
        taxes = p_taxes;
    }

    //Returns the products name
    public String getName()
    {
        return product_name;
    }

    //Returns the products price before tax
    public double getPrice()
    {
        return product_price;
    }

    //returns the total tax on the product
    public double getTax()
    {
        tax_amount = 0;

        for (Tax tax : taxes) {
            tax_amount += tax.getTax(getPrice());
        }

        tax_amount = Math.floor(tax_amount * precision +.5) / precision;

        return tax_amount;
    }

    //returns the total cost of the product including tax
    public double getTotalcost()
    {
        return product_price + getTax();
    }

}



