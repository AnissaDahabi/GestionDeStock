package gestion.model;

public class Sales {

    private int idSuppliers;
    private int idProduct;
    private int idSales;
    private int priceSales;
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


    public Sales(int idsales ,int idSuppliers, int idProduct,  int priceSales,int quantitySales, String dateSales) {
        this.idSales=idsales;
        this.idSuppliers=idSuppliers;
        this.idProduct=idProduct;
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
    public int getPriceSales() {
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

