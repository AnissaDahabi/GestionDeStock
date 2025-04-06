package gestion.service;

import gestion.dao.SalesQuery;
import gestion.model.Sales;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.StageStyle;

public class SalesService {

    public static boolean addSales(int idSales, int idProduct, int idSupplier, int quantity, double price, String date ) {

        try {

            Sales sales = new Sales(idSales, idProduct, idSupplier, quantity, price, date);

            return SalesQuery.addSales(sales);

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


    public static boolean delSales(int idSales){
        try {
            return SalesQuery.delSales(idSales);
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
