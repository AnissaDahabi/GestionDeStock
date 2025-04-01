package gestion.model;

public class Reports {

    private String nameSupplier;
    private String nameProduct;
    private double priceSales;
    private int quantitySales;
    private String dateSales;

    public Reports(String nameSupplier, String nameProduct, double priceSales, int quantitySales, String dateSales) {
        this.nameSupplier = nameSupplier;
        this.nameProduct = nameProduct;
        this.priceSales = priceSales;
        this.quantitySales = quantitySales;
        this.dateSales = dateSales;
    }



    public String getNameSupplier() {
        return nameSupplier;
    }
    public void setNameSupplier(String nameSupplier) {
        this.nameSupplier = nameSupplier;
    }
    public String getNameProduct() {
        return nameProduct;
    }
    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }
    public double getPriceSales() {
        return priceSales;
    }
    public void setPriceSales(double priceSales) {
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
