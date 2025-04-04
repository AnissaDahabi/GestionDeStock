package gestion.model;

public class Sales {

    private int idSales;
    private int idProduct;
    private int idSuppliers;
    private double priceSales;
    private int quantitySales;
    private String dateSales;

    public Sales(){
        this.idSales=0;
        this.idProduct=0;
        this.idSuppliers=0;
        this.priceSales=0;
        this.quantitySales=0;
        this.dateSales="";
    }


    public Sales(int idsales, int idProduct, int idSuppliers,int quantitySales,  double priceSales, String dateSales) {
        this.idSales=idsales;
        this.idProduct=idProduct;
        this.idSuppliers=idSuppliers;
        this.priceSales=priceSales;
        this.quantitySales=quantitySales;
        this.dateSales=dateSales;
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
        return idSuppliers;
    }
    public void setIdSuppliers(int idSuppliers) {
        this.idSuppliers = idSuppliers;
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

