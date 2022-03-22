
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

    //calculates the tax for a given product p
    public double calculateTax()
    {
        double total_tax = 0;

        for (Tax tax : taxes) {
            total_tax += tax.getTax(getPrice());
        }

        total_tax = Math.floor(total_tax * precision +.5) / precision;

        return total_tax;
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

    //Set the tax value for the item
    public void setTax(double calculated_tax)
    {
        tax_amount = calculated_tax;
    }

    //returns the total tax on the product
    public double getTax()
    {
        return tax_amount;
    }

    //returns the total cost of the product including tax
    public double getTotalcost()
    {
        return product_price + tax_amount;
    }

}



