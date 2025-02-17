package gestion.model;

public class Products {

        private int idProduct;
        private String nameProduct;
        private double priceProduct;
        private int quantityProduct;
        private String supplierProduct;

        public Products(int idProduct, String nameProduct, double priceProduct, int quantityProduct, String supplierProduct) {
            this.idProduct = idProduct;
            this.nameProduct = nameProduct;
            this.priceProduct = priceProduct;
            this.quantityProduct = quantityProduct;
            this.supplierProduct = supplierProduct;
        }

    public int getIdProduct() { return idProduct; }
    public void setIdProduct(int idProduct) { this.idProduct = idProduct; }

    public String getNameProduct() { return nameProduct; }
    public void setNameProduct(String nameProduct) { this.nameProduct = nameProduct; }

    public double getPriceProduct() { return priceProduct; }
    public void setPriceProduct(double priceProduct) { this.priceProduct = priceProduct; }

    public int getQuantityProduct() { return quantityProduct; }
    public void setQuantityProduct(int quantityProduct) { this.quantityProduct = quantityProduct; }

    public String getSupplierProduct() { return supplierProduct; }
    public void setSupplierProduct(String supplierProduct) { this.supplierProduct = supplierProduct; }

}
