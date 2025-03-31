package gestion.model;

public class Sales {

    private int idSuppliers;
    private int idProduct;
    private int idSales;
    private double priceSales;
    private int quantitySales;
    private String dateSales;


    public Sales(int idSuppliers, int idProduct, int idSales, double priceSales,int quantitySales, String dateSales) {
        this.idSuppliers = idSuppliers;
        this.idProduct = idProduct;
        this.idSales = idSales;
        this.priceSales = priceSales;
        this.quantitySales = quantitySales;
        this.dateSales = dateSales;
    }

    public int getIdSuppliers() {
        return idSuppliers;
    }
    public void setIdSuppliers(int idSuppliers) {
        this.idSuppliers = idSuppliers;
    }
    public int getIdProduct() {
        return idProduct;
    }
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
    public int getIdSales() {
        return idSales;
    }
    public void setIdSales(int idSales) {
        this.idSales = idSales;
    }
    public String getDateSales() {
        return dateSales;
    }
    public void setDateSales(String dateSales) {
        this.dateSales = dateSales;
    }
    public void setPriceSales(double priceSales) {
        this.priceSales = priceSales;
    }
    public double getPriceSales() {
        return priceSales;
    }
    public void setQuantitySales(int quantitySales) {
        this.quantitySales = quantitySales;
    }
    public int getQuantitySales() {
        return quantitySales;
    }
}

