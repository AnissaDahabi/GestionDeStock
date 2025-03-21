package gestion.model;

public class Sales {

    private int idSuppliers;
    private int idProduct;
    private int idSales;
    private String nameSales;
    private String dateSales;


    public Sales(int idSuppliers, int idProduct, int idSales, String nameSales, String dateSales) {
        this.idSuppliers = idSuppliers;
        this.idProduct = idProduct;
        this.idSales = idSales;
        this.nameSales = nameSales;
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
    public String getNameSales() {
        return nameSales;
    }
    public void setNameSales(String nameSales) {
        this.nameSales = nameSales;
    }
    public String getDateSales() {
        return dateSales;
    }
    public void setDateSales(String dateSales) {
        this.dateSales = dateSales;
    }

}

