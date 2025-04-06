package gestion.model;

public class Sales {

    private int idSales;
    private int idProduct;
    private int idSupplier;
    private double priceSales;
    private int quantitySales;
    private String dateSales;


    public Sales(int idSales, int idProduct, int idSupplier,int quantitySales,  double priceSales, String dateSales) {
        this.idSales = idSales;
        this.idProduct = idProduct;
        this.idSupplier = idSupplier;
        this.priceSales = priceSales;
        this.quantitySales = quantitySales;
        this.dateSales = dateSales;
    }

    public int getIdSales() {
        return idSales;
    }
    public void setIdSales(int idSales) {
        this.idSales = idSales;
    }
    public int getIdProduct() {
        return idProduct;
    }
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
    public int getIdSuppliers() {
        return idSupplier;
    }
    public void setIdSuppliers(int idSupplier) {
        this.idSupplier = idSupplier;
    }
    public double getPriceSales() {
        return priceSales;
    }
    public void setPriceSales(int priceSales) {
        this.priceSales = priceSales;
    }
    public int getQuantitySales() {
        return quantitySales;
    }
    public void setQuantitySales(int quantitySales) {
        this.quantitySales = quantitySales;
    }
    public String getDateSales() {
        return dateSales;
    }
    public void setDateSales(String dateSales) {
        this.dateSales = dateSales;
    }
}

