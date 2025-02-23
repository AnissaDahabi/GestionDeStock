package gestion.dao;

import gestion.model.Products;
import gestion.ui.SceneManager;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.sql.*;


public class ProductsQuery {

    private static int currentProductsId; // stockage de l'id pour ensuite le supprimer
    private String nameProductsSql1;

    public static boolean addProducts(Products products) {

        try {
            Connection con =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query = "INSERT INTO Products (id_product, name_product, price_product, quantity_product, supplier_product) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);

            // Remplissage des paramètres de la requête SQL
            pstmt.setInt(1, products.getIdProduct());
            pstmt.setString(2, products.getNameProduct());
            pstmt.setDouble(3, products.getPriceProduct());
            pstmt.setInt(4, products.getQuantityProduct());
            pstmt.setString(5, products.getSupplierProduct());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delProducts(int idProducts) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");
            String query = "DELETE FROM Products WHERE ID_product = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, idProducts);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting product: " + e.getMessage());
        }
    }

    public static Products getProductById(int idProducts) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");
            String query = "SELECT * FROM Products WHERE ID_product = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, idProducts);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Products(
                        rs.getInt("ID_product"),
                        rs.getString("name_product"),
                        rs.getDouble("price_product"),
                        rs.getInt("quantity_product"),
                        rs.getString("supplier_product")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error getting the product's data: " + e.getMessage());
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