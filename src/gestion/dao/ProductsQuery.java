package gestion.dao;

import gestion.ui.SceneManager;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.sql.*;

public class ProductsQuery {

    public static void addProduct(TextField idProductInput, TextField nameProductInput, TextField priceProductInput, TextField quantityProductInput, TextField supplierProductInput) {
        try {
            Connection con =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query = "INSERT INTO Products (id_product, name_product, price_product, quantity_product, supplier_product) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);

            // Récupération des données saisies par l'utilisateur
            int idProduct = Integer.parseInt(idProductInput.getText());
            String nameProduct = nameProductInput.getText();
            Double priceProduct = Double.parseDouble(priceProductInput.getText());
            int quantityProduct = Integer.parseInt(quantityProductInput.getText());
            String supplierProduct = supplierProductInput.getText();

            // Remplissage des paramètres de la requête SQL
            pstmt.setInt(1, idProduct);
            pstmt.setString(2, nameProduct);
            pstmt.setDouble(3, priceProduct);
            pstmt.setInt(4, quantityProduct);
            pstmt.setString(5, supplierProduct);

            // Exécution de la requête SQL
            int rowsAffected = pstmt.executeUpdate();

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

    private static int currentProductId; // stockage de l'id pour ensuite le supprimer

    public static void showDelProduct(TextField idProductInput, Stage stage) {
        try {
            // Connexion à la base de données
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            // Préparation de la requête SQL
            String query = "SELECT id_product, name_product FROM Products WHERE id_product = ?";
            PreparedStatement pstmt = con.prepareStatement(query);

            // Récupération de l'ID saisi par l'utilisateur
            int idProduct = Integer.parseInt(idProductInput.getText());

            // Remplissage du paramètre de la requête SQL
            pstmt.setInt(1, idProduct);

            // Exécution de la requête SQL
            ResultSet resultSet = pstmt.executeQuery();

            // Récupérer l'ID et le nom du produit
            if (resultSet.next()) {

            int idProductSql = resultSet.getInt("id_product");
            String nameProductSql = resultSet.getString("name_product");

            currentProductId = idProductSql;


            stage.setScene(SceneManager.getDelProductScene2(idProductSql, nameProductSql));

            }
            // Fermeture des ressources
            resultSet.close();
            pstmt.close();
            con.close();

        } catch (SQLException ex) {
            // Gestion des erreurs SQL
            ex.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error encountered");
            alert.setContentText("Error: " + ex.getMessage());
            alert.showAndWait();
        }
    }

    public static void delProduct() {
        try {
            // Connexion à la base de données
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            // Préparation de la requête SQL
            String query = "DELETE FROM Products WHERE id_product = ?";
            PreparedStatement pstmt = con.prepareStatement(query);

            // Récupération de l'ID saisi par l'utilisateur
            pstmt.setInt(1, currentProductId);

            // Exécution de la requête SQL
            int rowsAffected = pstmt.executeUpdate();


            if (rowsAffected > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText(null);
                alert.setContentText("Product deleted successfully");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
               alert.setHeaderText("Error encountered while deleting the product");
               alert.setContentText("Error, no product found with this ID");
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
}