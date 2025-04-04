package gestion.service;

import gestion.dao.ProductsQuery;
import gestion.dao.SuppliersQuery;
import gestion.model.Suppliers;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.StageStyle;

public class SuppliersService {

    private static SuppliersQuery suppliersQuery;
    private final ProductsQuery productsQuery;

    public SuppliersService(SuppliersQuery suppliersQuery, ProductsQuery productsQuery) {
        this.suppliersQuery = suppliersQuery;
        this.productsQuery = productsQuery;
    }


    public static boolean addSuppliers(int idSuppliers, String name, String phone, String address, String email) {

        try {
            Suppliers suppliers = new Suppliers(idSuppliers, name, phone, address, email);

            if(name.isEmpty() || phone.isEmpty() || address.isEmpty() || email.isEmpty()) {
                throw new IllegalArgumentException("Please fill all the required fields");
            }
            if (idSuppliers==0) {
                throw new IllegalArgumentException("Please fill all the required fields");
            }
            if (!email.contains("@")) {
                throw new IllegalArgumentException("Please fill a valid email address");
            }

            return SuppliersQuery.addSuppliers(suppliers);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.setGraphic(null);
            alert.initStyle(StageStyle.UTILITY);
            alert.getDialogPane().getStylesheets().add("gestion/resources/suppliers.css");
            alert.showAndWait();
            return false;
        }
    }





    public static Suppliers getSuppliers(int idSupplier) {
        try {
            return SuppliersQuery.getSuppliersId(idSupplier);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.setGraphic(null);
            alert.initStyle(StageStyle.UTILITY);
            alert.getDialogPane().getStylesheets().add("gestion/resources/suppliers.css");
            alert.showAndWait();
            return null;
        }
    }

    public static boolean delSuppliers(int idSupplier) {
        try {
            return SuppliersQuery.delSuppliers(idSupplier);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.setGraphic(null);
            alert.initStyle(StageStyle.UTILITY);
            alert.getDialogPane().getStylesheets().add("gestion/resources/suppliers.css");
            alert.showAndWait();
            return false;
        }
    }
}







