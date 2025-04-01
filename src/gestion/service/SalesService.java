package gestion.service;

import gestion.dao.SalesQuery;
import gestion.model.Sales;
import javafx.scene.control.Alert;

public class SalesService {

    public static boolean delSales(int idSales){
        try {
            return SalesQuery.delSales(idSales);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return false;
        }
    }
    public static Sales getSales(int idSales){
        try{
            return SalesQuery.getSalesbyId(idSales);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return null;
        }
    }
}
