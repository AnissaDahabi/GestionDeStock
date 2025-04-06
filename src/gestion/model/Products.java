package gestion.model;

public class Products {

        private int idProduct;
        private String nameProduct;
        private double priceProduct;
        private int quantityProduct;
        private int supplierId;

        public Products(int idProduct, String nameProduct, double priceProduct, int quantityProduct, int supplierId) {
            this.idProduct = idProduct;
            this.nameProduct = nameProduct;
            this.priceProduct = priceProduct;
            this.quantityProduct = quantityProduct;
            this.supplierId = supplierId;
        }

    public int getIdProduct() { return idProduct; }
    public String getNameProduct() { return nameProduct; }
    public double getPriceProduct() { return priceProduct; }
    public int getQuantityProduct() { return quantityProduct; }
    public int getSupplierId() { return supplierId; }

}
