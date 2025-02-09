package gestion.dao;

import gestion.ui.SceneManager;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.sql.*;


public class ProductsQuery {

    public static void addProducts(TextField idProductsInput, TextField nameProductsInput, TextField priceProductsInput, TextField quantityProductsInput, TextField supplierProductsInput) {
        try {
            Connection con =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query = "INSERT INTO Products (id_product, name_product, price_product, quantity_product, supplier_product) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);

            // Récupération des données saisies par l'utilisateur
            int idProducts = Integer.parseInt(idProductsInput.getText());
            String nameProducts = nameProductsInput.getText();
            Double priceProducts = Double.parseDouble(priceProductsInput.getText());
            int quantityProducts = Integer.parseInt(quantityProductsInput.getText());
            String supplierProducts = supplierProductsInput.getText();

            // Remplissage des paramètres de la requête SQL
            pstmt.setInt(1, idProducts);
            pstmt.setString(2, nameProducts);
            pstmt.setDouble(3, priceProducts);
            pstmt.setInt(4, quantityProducts);
            pstmt.setString(5, supplierProducts);

            // Exécution de la requête SQL
           pstmt.executeUpdate();

            // Fermeture de la connexion et du PreparedStatement
            pstmt.close();
            con.close();

            // Message de succès
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText(null);
            alert.setContentText("Product added successfully");
            alert.showAndWait();
        } catch (SQLException ex) {
            ex.printStackTrace();

            // Message d'échec
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error encountered while adding the product");
            alert.setContentText("Error: " + ex.getMessage());
            alert.showAndWait();
        }
    }

    private static int currentProductsId; // stockage de l'id pour ensuite le supprimer

    public static void showDelProducts(TextField idProductsInput, Stage stage) {
        try {
            // Connexion à la base de données
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            // Préparation de la requête SQL
            String query = "SELECT id_product, name_product FROM Products WHERE id_product = ?";
            PreparedStatement pstmt = con.prepareStatement(query);

            // Récupération de l'ID saisi par l'utilisateur
            int idProducts = Integer.parseInt(idProductsInput.getText());

            // Remplissage du paramètre de la requête SQL
            pstmt.setInt(1, idProducts);

            // Exécution de la requête SQL
            ResultSet resultSet = pstmt.executeQuery();


            if (resultSet.next()) {

            int idProductsSql = resultSet.getInt("id_product");
            String nameProductsSql = resultSet.getString("name_product");

            currentProductsId = idProductsSql;


            stage.setScene(SceneManager.getDelProductScene2(idProductsSql, nameProductsSql));

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

    public static void delProducts() {
        try {
            // Connexion à la base de données
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            // Préparation de la requête SQL
            String query = "DELETE FROM Products WHERE id_product = ?";
            PreparedStatement pstmt = con.prepareStatement(query);

            // Récupération de l'ID saisi par l'utilisateur
            pstmt.setInt(1, currentProductsId);

            // Exécution de la requête SQL
            int rowsAffected = pstmt.executeUpdate();


            if (rowsAffected > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText(null);
                alert.setContentText("Product deleted successfully");
                alert.showAndWait();
            }

            // Fermeture des ressources
            pstmt.close();
            con.close();
        } catch (SQLException ex) {
            // Gestion des erreurs SQL
            ex.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error encountered while deleting the product");
            alert.setContentText("Error: " + ex.getMessage());
            alert.showAndWait();

        }
    }

    public static void showEditedProducts(TextField idProductsInput,  Stage stage) {
        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query = "SELECT id_product, name_product, price_product, quantity_product, supplier_product FROM Products WHERE id_product = ?";
            PreparedStatement pstmt = con.prepareStatement(query);

            try {
                currentProductsId  = Integer.parseInt(idProductsInput.getText());
                pstmt.setInt(1, currentProductsId );

            } catch (NumberFormatException ex) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Product ID, please enter an integer");
                alert.setContentText("Error: " + ex.getMessage());
                alert.showAndWait();
                return;
            }

            try (ResultSet resultSet = pstmt.executeQuery()) {

                if (resultSet.next()) {
                    int idProductsSql = resultSet.getInt("id_product");
                    String nameProductsSql = resultSet.getString("name_product");
                    double priceProductsSql = resultSet.getDouble("price_product");
                    int quantityProductsSql = resultSet.getInt("quantity_product");
                    String supplierProductsSql = resultSet.getString("supplier_product");

                    stage.setScene(SceneManager.getEditProductsScene2(idProductsSql, nameProductsSql, priceProductsSql, quantityProductsSql, supplierProductsSql));

                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Product not found");
                    alert.setContentText("No product found with this ID");
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

    public static void editProducts(TextField nameProductsInput, TextField priceProductsInput, TextField quantityProductsInput, TextField supplierProductsInput) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query = "UPDATE Products SET name_product = ?, price_product = ?, quantity_product = ?, supplier_product = ? WHERE id_product = ?";
            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setString(1, nameProductsInput.getText());

            try {
                pstmt.setDouble(2, Double.parseDouble(priceProductsInput.getText()));
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid input");
                alert.setContentText("Please enter a valid number for price");
                return;
            }
            try {
                pstmt.setInt(3, Integer.parseInt(quantityProductsInput.getText()));
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid input");
                alert.setContentText("Please enter a valid number for quantity");
                return;
            }

            pstmt.setString(4, supplierProductsInput.getText());
            pstmt.setInt(5, currentProductsId);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText(null);
                alert.setContentText("Product edited successfully");
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
            alert.setHeaderText("Error encountered while editing the product");
            alert.setContentText("Error: " + ex.getMessage());
            alert.showAndWait();
        }
    }
}