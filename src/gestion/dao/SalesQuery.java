package gestion.dao;

import gestion.model.Sales;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

import java.sql.*;


public class SalesQuery {

    private static int currentSalesId;

    public static boolean addSales(Sales sales) {

        try {
            Connection con =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query = "INSERT INTO Sales (id_sales, id_product, id_supplier, quantity_sales, price_sales, date_sales ) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);

            //Remplissage des paramètres de la requête SQL
            pstmt.setInt(1, sales.getIdSales());
            pstmt.setInt(2, sales.getIdProduct());
            pstmt.setInt(3, sales.getIdSuppliers());
            pstmt.setInt(4, sales.getQuantitySales());
            pstmt.setInt(5, sales.getPriceSales());
            pstmt.setString(6, sales.getDateSales());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delSales(int idSales) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");
            String query = "DELETE FROM Sales WHERE id_sales = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, idSales);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting product: " + e.getMessage());
        }
    }


    public static void editSales(int idSalesInput, TextField idProductInput, int priceSalesSql, TextField quantitySalesInput, TextField dateSalesInput) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query = "UPDATE Sales SET id_product = ?, price_sales = ?, quantity_sales = ?, date_sales= ? WHERE id_sales = ?";
            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setInt(1, Integer.parseInt(String.valueOf(idProductInput.getText())));
            pstmt.setInt(2, Integer.parseInt(String.valueOf(priceSalesSql)));
            pstmt.setInt(2, Integer.parseInt(quantitySalesInput.getText()));
            pstmt.setString(3, String.valueOf(dateSalesInput));
            pstmt.setInt(4, Integer.parseInt(String.valueOf(idSalesInput)));

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Sale edited successfully");
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setGraphic(null);
                alert.initStyle(StageStyle.UTILITY);
                alert.getDialogPane().getStylesheets().add("gestion/resources/sales.css");
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("No changes were made.");
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setGraphic(null);
                alert.initStyle(StageStyle.UTILITY);
                alert.getDialogPane().getStylesheets().add("gestion/resources/sales.css");
            }

            pstmt.close();
            con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Error: " + ex.getMessage());
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.setGraphic(null);
            alert.initStyle(StageStyle.UTILITY);
            alert.getDialogPane().getStylesheets().add("gestion/resources/sales.css");
        }
    }

    public static ObservableList<Sales> showSales(TableView<Sales> salesTable) {
        ObservableList<Sales> salesList = FXCollections.observableArrayList();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query = "Select id_sales, id_product, id_supplier, quantity_sales, price_sales, date_sales from Sales";
            PreparedStatement pstmt = con.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Sales sales = new Sales(
                        rs.getInt("id_sales"),
                        rs.getInt("id_product"),
                        rs.getInt("id_supplier"),
                        rs.getInt("quantity_sales"),
                        rs.getInt("price_sales"),
                        rs.getString("date_sales")
                );
                salesList.add(sales);
            }
            if (salesTable != null) {
                salesTable.setItems(salesList);
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database error: " + e.getMessage());
        }
        return salesList;
    }


    public static ObservableList<Sales> getSalesById() {
        ObservableList<Sales> salesList = FXCollections.observableArrayList();

        String query = "Select * from Sales";

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idSales = rs.getInt("id_sales");
                int idProduct = rs.getInt("id_product");
                int idSupplier = rs.getInt("id_supplier");
                int quantitySales = rs.getInt("quantity_sales");
                int priceSales = rs.getInt("price_sales");
                String dateSales = rs.getString("date_sales");

                Sales sales = new Sales(idSales, idSupplier, idProduct, quantitySales, priceSales, dateSales);
                salesList.add(sales);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesList;
    }
}

