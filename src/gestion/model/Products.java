package gestion.model;

public class Products {

        private int idProduct;
        private String nameProduct;
        private double priceProduct;
        private int quantityProduct;
        private String productSupplier;

        public Products(int id, String name) {
            this.idProduct = id;
            this.nameProduct = name;
            this.priceProduct = 0;
            this.quantityProduct = 0;
            this.productSupplier = "";
        }

    public int getIdProduct() { return idProduct; }
    public void setIdProduct(int idProduct) { this.idProduct = idProduct; }

    public String getNameProduct() { return nameProduct; }
    public void setNameProduct(String nameProduct) { this.nameProduct = nameProduct; }

    public double getPriceProduct() { return priceProduct; }
    public void setPriceProduct(double priceProduct) { this.priceProduct = priceProduct; }

    public int getQuantityProduct() { return quantityProduct; }
    public void setQuantityProduct(int quantityProduct) { this.quantityProduct = quantityProduct; }

    public String getProductSupplier() { return productSupplier; }
    public void setProductSupplier(String productSupplier) { this.productSupplier = productSupplier; }

}
