package gestion.model;

public class Products {

        private int idProduct;
        private String nameProduct;
        private double priceProduct;
        private int quantityProduct;
        private int supplierId;

        public Products() {
            this.idProduct = 0;
            this.nameProduct = "";
            this.priceProduct = 0;
            this.quantityProduct = 0;
            this.supplierId = 0;
        }

        public Products(int idProduct, String nameProduct, double priceProduct, int quantityProduct, int supplierId) {
            this.idProduct = idProduct;
            this.nameProduct = nameProduct;
            this.priceProduct = priceProduct;
            this.quantityProduct = quantityProduct;
            this.supplierId = supplierId;
        }

    public int getIdProduct() { return idProduct; }
    public void setIdProduct(int idProduct) { this.idProduct = idProduct; }

    public String getNameProduct() { return nameProduct; }
    public void setNameProduct(String nameProduct) { this.nameProduct = nameProduct; }

    public double getPriceProduct() { return priceProduct; }
    public void setPriceProduct(double priceProduct) { this.priceProduct = priceProduct; }

    public int getQuantityProduct() { return quantityProduct; }
    public void setQuantityProduct(int quantityProduct) { this.quantityProduct = quantityProduct; }

    public int getSupplierId() { return supplierId; }
    public void setSupplierId(int supplierId) { this.supplierId = supplierId; }

}
