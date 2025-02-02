package gestion.dao;

import gestion.ui.SceneManager;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.sql.*;

public class SuppliersQuery {

    private static int currentSupplierId;

    public static void addSuppliers(TextField idSupplierInput, TextField nameSupplierInput, TextField phoneSupplierInput, TextField addressSupplierInput, TextField emailSupplierInput) {
        try {
            Connection con =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query = "INSERT INTO Supplier (id_supplier, name_supplier, phone_supplier, address_supplier, email_supplier) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);

            //Récupération des données saisies par l'utilisateur
            int idSupplier = Integer.parseInt(idSupplierInput.getText());
            String nameSupplier = nameSupplierInput.getText();
            String phoneSupplier = phoneSupplierInput.getText();
            String addressSupplier = addressSupplierInput.getText();
            String emailSupplier = emailSupplierInput.getText();

            //Remplissage des paramètres de la requête SQL
            pstmt.setInt(1, idSupplier);
            pstmt.setString(2, nameSupplier);
            pstmt.setString(3, phoneSupplier);
            pstmt.setString(4, addressSupplier);
            pstmt.setString(5, emailSupplier);

            //Exécution de la requête SQL
            int rowsAffected = pstmt.executeUpdate();

            //Fermeture de la connexion et du PreparedStatement
            pstmt.close();
            con.close();

            //Message de succès
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success !");
            alert.setHeaderText(null);
            alert.setContentText("Supplier added successfully");
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

    public static void delSuppliers() {
        try {
            // Connexion à la base de données
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            // Préparation de la requête SQL
            String query = "DELETE FROM Products WHERE id_supplier = ?";
            PreparedStatement pstmt = con.prepareStatement(query);

            // Récupération de l'ID saisi par l'utilisateur
            pstmt.setInt(1, currentSupplierId);

            // Exécution de la requête SQL
            int rowsAffected = pstmt.executeUpdate();


            if (rowsAffected > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText(null);
                alert.setContentText("Supplier deleted successfully");
                alert.showAndWait();
            }

            // Fermeture des ressources
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            // Gestion des erreurs SQL
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error encountered while deleting the supplier");
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();

        }
    }




}
