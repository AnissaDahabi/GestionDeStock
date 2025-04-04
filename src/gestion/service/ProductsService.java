package gestion.service;

import gestion.dao.ProductsQuery;
import gestion.model.Products;
import javafx.scene.control.Alert;

public class ProductsService {
    private final ProductsQuery productsQuery;

    public ProductsService(ProductsQuery productsQuery) {
        this.productsQuery = productsQuery;
    }

    public static boolean addProducts(int idProducts, String name, double price, int quantity, int supplier) {

        try {
            if (name.isEmpty() || supplier == 0 | idProducts == 0 || price == 0 || quantity == 0) {
                throw new IllegalArgumentException("Please fill all the required fields");
            }
            if (quantity < 0) {
                throw new IllegalArgumentException("Please enter a valid quantity");
            }
            if (price < 0) {
                throw new IllegalArgumentException("Please enter a valid price");
            }

            Products products = new Products(idProducts, name, price, quantity, supplier);

            return ProductsQuery.addProducts(products);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return false;
        }
    }

    public static boolean delProducts(int idProducts) {
        try {
            return ProductsQuery.delProducts(idProducts);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return false;
        }
    }
}