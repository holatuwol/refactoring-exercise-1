package salestax;

public class ImportTax implements Tax {
    public static final ImportTax INSTANCE = new ImportTax();

    static double import_tax_rate = .05;

    @Override
    public double getTax(double price) {
        return price * import_tax_rate;
    }
}
