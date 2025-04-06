package gestion.dao;

import gestion.model.Products;
import gestion.model.Suppliers;
import gestion.ui.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.*;


public class ProductsQuery {

    private static int currentProductsId;

    public static boolean addProducts(Products products) {

        try {
            Connection con =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");
                    System.out.println("Database connection established.");

            String query = "INSERT INTO Products (id_product, name_product, price_product, quantity_product, id_supplier) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);

            // Remplissage des paramètres de la requête SQL
            pstmt.setInt(1, products.getIdProduct());
            pstmt.setString(2, products.getNameProduct());
            pstmt.setDouble(3, products.getPriceProduct());
            pstmt.setInt(4, products.getQuantityProduct());
            pstmt.setInt(5, products.getSupplierId());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delProducts(int idProducts) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");
            String query = "DELETE FROM Products WHERE id_product = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, idProducts);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting product: " + e.getMessage());
        }
    }


    public static void editProducts(int idProductsInput, TextField nameProductsInput, TextField priceProductsInput, TextField quantityProductsInput, ComboBox<Suppliers> supplierIdInput) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query1 = "SELECT id_product, name_product, price_product, quantity_product, id_supplier FROM Products WHERE id_product = ?";
            PreparedStatement pstmt1 = con.prepareStatement(query1);
            pstmt1.setInt(1, idProductsInput);

            ResultSet rs = pstmt1.executeQuery();

            if (!rs.next()) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("No product found with this ID");
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setGraphic(null);
                alert.initStyle(StageStyle.UTILITY);
                alert.getDialogPane().getStylesheets().add("gestion/resources/products.css");
                alert.showAndWait();

                rs.close();
                pstmt1.close();
                con.close();
                return;
            }

            String currentName = rs.getString("name_product");
            Double currentPrice = rs.getDouble("price_product");
            int currentQuantity = rs.getInt("quantity_product");
            int currentSupplier = rs.getInt("id_supplier");

            rs.close();
            pstmt1.close();

            String newName = nameProductsInput.getText();
            Double newPrice = Double.parseDouble(priceProductsInput.getText());
            int newQuantity = Integer.parseInt(quantityProductsInput.getText());
            int newSupplier = supplierIdInput.getValue().getIdSupplier();

            boolean noChanges = currentName.equals(newName) &&
                    currentPrice.equals(newPrice) &&
                    currentQuantity == newQuantity &&
                    currentSupplier == newSupplier;

            if (noChanges) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("No changes were made.");
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setGraphic(null);
                alert.initStyle(StageStyle.UTILITY);
                alert.getDialogPane().getStylesheets().add("gestion/resources/products.css");
                alert.showAndWait();

            } else {
                String query2 = "UPDATE Products SET name_product = ?, price_product = ?, quantity_product = ?, id_supplier = ? WHERE id_product = ?";
                PreparedStatement pstmt = con.prepareStatement(query2);

                pstmt.setString(1, nameProductsInput.getText());
                pstmt.setDouble(2, Double.parseDouble(priceProductsInput.getText()));
                pstmt.setInt(3, Integer.parseInt(quantityProductsInput.getText()));
                pstmt.setInt(4, supplierIdInput.getValue().getIdSupplier());
                pstmt.setInt(5, Integer.parseInt(String.valueOf(idProductsInput)));

                pstmt.executeUpdate();

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Product edited successfully");
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setGraphic(null);
                alert.initStyle(StageStyle.UTILITY);
                alert.getDialogPane().getStylesheets().add("gestion/resources/products.css");
                alert.showAndWait();

                pstmt.close();
            }

            con.close();

        } catch (SQLException ex) {

            ex.printStackTrace();

        }
    }

    public static ObservableList<Products> showProducts(TableView<Products> productsTable) {
        ObservableList<Products> productsList = FXCollections.observableArrayList();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query = "SELECT id_product, name_product, price_product, quantity_product, id_supplier FROM Products";
            PreparedStatement pstmt = con.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                Products products = new Products(
                        rs.getInt("id_product"),
                        rs.getString("name_product"),
                        rs.getDouble("price_product"),
                        rs.getInt("quantity_product"),
                        rs.getInt("id_supplier")
                );
                productsList.add(products);
            }
            if (productsTable != null) {
                productsTable.setItems(productsList);
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database error: " + e.getMessage());
        }
        return productsList;
    }


    public static ObservableList<Products> getProductsID() {
        ObservableList<Products> productsList = FXCollections.observableArrayList();

        String query = "SELECT * FROM Products";

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_product");
                String name = rs.getString("name_product");
                double price = rs.getDouble("price_product");
                int quantity = rs.getInt("quantity_product");
                int id_supplier = rs.getInt("id_supplier");

                Products products = new Products(id, name, price, quantity, id_supplier);
                productsList.add(products);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productsList;
    }
}