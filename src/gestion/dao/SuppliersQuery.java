package gestion.dao;

import gestion.ui.SceneManager;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.sql.*;

public class SuppliersQuery {

    private static int currentSuppliersId;
    //private static int currentPhoneSuppliers;

    public static void addSuppliers(TextField idSuppliersInput, TextField nameSuppliersInput, TextField phoneSuppliersInput, TextField addressSuppliersInput, TextField emailSuppliersInput, TextField productsInput) {
        try {
            Connection con =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query = "INSERT INTO Suppliers (id_supplier, name_supplier, phone_supplier, address_supplier, email_supplier, id_product) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);

            //Récupération des données saisies par l'utilisateur
            int idSuppliers = Integer.parseInt(idSuppliersInput.getText());
            String nameSuppliers = nameSuppliersInput.getText();
            int phoneSuppliers = Integer.parseInt(phoneSuppliersInput.getText());
            String addressSuppliers = addressSuppliersInput.getText();
            String emailSuppliers = emailSuppliersInput.getText();
            int productsId = Integer.parseInt(productsInput.getText());

            //Remplissage des paramètres de la requête SQL
            pstmt.setInt(1, idSuppliers);
            pstmt.setString(2, nameSuppliers);
            pstmt.setInt(3, phoneSuppliers);
            pstmt.setString(4, addressSuppliers);
            pstmt.setString(5, emailSuppliers);
            pstmt.setInt(6, productsId);

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
            String query = "DELETE FROM Suppliers WHERE id_supplier = ?";
            PreparedStatement pstmt = con.prepareStatement(query);

            // Récupération de l'ID saisi par l'utilisateur
            pstmt.setInt(1, currentSuppliersId);

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

    public static void showDelSuppliers(TextField idSuppliersInput, Stage stage) {
        try {
            // Connexion à la base de données
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            // Préparation de la requête SQL
            String query = "SELECT id_supplier, name_supplier FROM Suppliers WHERE id_supplier = ?";
            PreparedStatement pstmt = con.prepareStatement(query);

            // Récupération de l'ID saisi par l'utilisateur
            int idSuppliers = Integer.parseInt(idSuppliersInput.getText());

            // Remplissage du paramètre de la requête SQL
            pstmt.setInt(1, idSuppliers);

            // Exécution de la requête SQL
            ResultSet resultSet = pstmt.executeQuery();


            if (resultSet.next()) {

                int idSuppliersSql = resultSet.getInt("id_supplier");
                String nameSuppliersSql = resultSet.getString("name_supplier");

                currentSuppliersId = idSuppliersSql;


                stage.setScene(SceneManager.getDelSuppliersScene2(idSuppliersSql, nameSuppliersSql));

            }
            // Fermeture des ressources
            resultSet.close();
            pstmt.close();
            con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error encountered");
            alert.setContentText("Error: " + ex.getMessage());
            alert.showAndWait();
        }
    }

    public static void showEditedSuppliers(TextField idSuppliersInput,  Stage stage) {
        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query = "SELECT id_supplier, name_supplier, phone_supplier, address_supplier, email_supplier FROM Suppliers WHERE id_supplier = ?";
            PreparedStatement pstmt = con.prepareStatement(query);

            try {
                currentSuppliersId  = Integer.parseInt(idSuppliersInput.getText());
                pstmt.setInt(1, currentSuppliersId );

            } catch (NumberFormatException ex) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Supplier ID, please enter an integer");
                alert.setContentText("Error: " + ex.getMessage());
                alert.showAndWait();
                return;
            }

            try (ResultSet resultSet = pstmt.executeQuery()) {

                if (resultSet.next()) {
                    int idSuppliersSql = resultSet.getInt("id_supplier");
                    String nameSuppliersSql = resultSet.getString("name_supplier");
                    int phoneSuppliersSql = resultSet.getInt("phone_supplier");
                    String addressSuppliersSql = resultSet.getString("address_supplier");
                    String emailSuppliersSql = resultSet.getString("email_supplier");

                    stage.setScene(SceneManager.getEditSuppliersScene2(idSuppliersSql, nameSuppliersSql, phoneSuppliersSql, addressSuppliersSql, emailSuppliersSql));

                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Supplier not found");
                    alert.setContentText("No supplier found with this ID");
                    alert.showAndWait();
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error encountered");
            alert.setContentText("Error: " + ex.getMessage());
            alert.showAndWait();
        }
    }

    public static void editSuppliers(TextField nameSuppliersInput, TextField phoneSuppliersInput, TextField addressSuppliersInput, TextField emailSuppliersInput) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query = "UPDATE Suppliers SET name_supplier = ?, phone_supplier = ?, address_supplier = ?, email_supplier = ? WHERE id_supplier = ?";
            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setString(1, nameSuppliersInput.getText());

            try {
                pstmt.setInt(2, Integer.parseInt(phoneSuppliersInput.getText()));
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid input");
                alert.setContentText("Please enter a valid phone number");
                return;
            }

            pstmt.setString(3, addressSuppliersInput.getText());
            pstmt.setString(4, emailSuppliersInput.getText());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText(null);
                alert.setContentText("Supplier edited successfully");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("No Changes");
                alert.setHeaderText(null);
                alert.setContentText("No changes were made.");
                alert.showAndWait();
            }

            pstmt.close();
            con.close();

        } catch (SQLException ex) {
            // Gestion des erreurs SQL
            ex.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error encountered while editing the supplier");
            alert.setContentText("Error: " + ex.getMessage());
            alert.showAndWait();
        }
    }




}



