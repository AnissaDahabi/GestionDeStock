package gestion.service;

import gestion.dao.ProductsQuery;
import gestion.model.Products;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.StageStyle;

public class ProductsService {
    private final ProductsQuery productsQuery;

    public ProductsService(ProductsQuery productsQuery) {
        this.productsQuery = productsQuery;
    }

    public static boolean addProducts(int idProducts, String name, double price, int quantity, int supplier) {

        try {

            Products products = new Products(idProducts, name, price, quantity, supplier);

            return ProductsQuery.addProducts(products);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Something went wrong");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.setGraphic(null);
            alert.initStyle(StageStyle.UTILITY);
            alert.getDialogPane().getStylesheets().add("gestion/resources/sales.css");
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
            alert.setContentText("Something went wrong");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.setGraphic(null);
            alert.initStyle(StageStyle.UTILITY);
            alert.getDialogPane().getStylesheets().add("gestion/resources/sales.css");
            alert.showAndWait();
            return false;
        }
    }
}