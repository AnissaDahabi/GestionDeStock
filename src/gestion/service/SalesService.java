package gestion.service;

import gestion.dao.SalesQuery;
import gestion.model.Sales;
import javafx.scene.control.Alert;

public class SalesService {
    private SalesQuery salesQuery;

public SalesService(SalesQuery salesQuery) {this.salesQuery = salesQuery; }

    public static boolean addSales(int idSales, int idproduct, int idsupplier, int quantity, double price, String date ) {

        try {
            if (date.isEmpty() || idsupplier == 0 || idproduct == 0  || idSales == 0 || price == 0 || quantity == 0) {
                throw new IllegalArgumentException("Please fill all the required fields");
            }
            if (quantity < 0) {
                throw new IllegalArgumentException("Please enter a valid quantity");
            }
            if (price < 0) {
                throw new IllegalArgumentException("Please enter a valid price");
            }

            Sales sales = new Sales(idSales, idproduct, idsupplier, quantity, price, date);

            return SalesQuery.addSales(sales);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
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
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return false;
        }
    }
}
