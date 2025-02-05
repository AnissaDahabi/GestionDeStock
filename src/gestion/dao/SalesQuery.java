package gestion.dao;

import gestion.ui.SceneManager;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.sql.*;



public class SalesQuery {

    private static int currentSalesId;

    public static void addSales(TextField idSalesInput, TextField nameSalesSupplierInput, TextField nameSalesProductInput, TextField quantitySalesProductInput, TextField priceSalesInput) {
        try {
            Connection con =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query = "INSERT INTO Sales (id_sales, name_sales_supplier, name_sales_product, quantity_sales_product, price_sales) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);

            //Récupération des données saisies par l'utilisateur
            int idSupplier = Integer.parseInt(idSalesInput.getText());
            String nameSalesSupplier = nameSalesSupplierInput.getText();
            String nameSalesProduct = nameSalesProductInput.getText();
            int quantitySalesProduct = Integer.parseInt(quantitySalesProductInput.getText());
            int priceSales = Integer.parseInt(priceSalesInput.getText());

            //Remplissage des paramètres de la requête SQL
            pstmt.setInt(1, idSupplier);
            pstmt.setString(2, nameSalesSupplier);
            pstmt.setString(3, nameSalesProduct);
            pstmt.setInt(4, quantitySalesProduct);
            pstmt.setInt(5, priceSales);

            //Exécution de la requête SQL
            int rowsAffected = pstmt.executeUpdate();

            //Fermeture de la connexion et du PreparedStatement
            pstmt.close();
            con.close();

            //Message de succès
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success !");
            alert.setHeaderText(null);
            alert.setContentText("Sale added successfully");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error" + e.getMessage());
            alert.showAndWait();
        }


    }

}
