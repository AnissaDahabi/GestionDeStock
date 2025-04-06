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
    public int getIdProduct() {
        return idProduct;
    }
    public int getIdSuppliers() {
        return idSupplier;
    }
    public double getPriceSales() {
        return priceSales;
    }
    public int getQuantitySales() {
        return quantitySales;
    }
    public String getDateSales() {
        return dateSales;
    }

}

