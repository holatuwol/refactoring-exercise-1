package salestax;

public class BasicSalesTax implements Tax {
    public static final BasicSalesTax INSTANCE = new BasicSalesTax();

    static double basic_sales_tax_rate = .10;

    @Override
    public double getTax(double price) {
        return price * basic_sales_tax_rate;
    }
}
