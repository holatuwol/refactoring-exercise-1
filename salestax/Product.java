
package salestax;

public class Product
{
    private String product_name;
    private double tax_amount;    //Tax due on the product
    private double total_cost;    //Total cost of the product including tax
    private double product_price; //Product price before tax
    private boolean apply_basic_sales_tax;
    private boolean apply_import_tax;

    public Product(String p_name, double p_price, boolean basic_sales_tax, boolean import_tax)
    {
        product_name = p_name;
        product_price = p_price;
        apply_basic_sales_tax = basic_sales_tax;
        apply_import_tax = import_tax;
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

    //Returns true if basic sales tax apply, false otherwise
    public boolean isTaxable()
    {
        return apply_basic_sales_tax;
    }

    //Returns true if import taxes apply, false otherwise
    public boolean isImportedProduct()
    {
        return apply_import_tax;
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
        total_cost = product_price + tax_amount;
        return total_cost;
    }

}



