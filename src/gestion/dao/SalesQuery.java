package gestion.dao;

import gestion.model.Products;
import gestion.model.Sales;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class SalesQuery {


    public static boolean addSales(Sales sales) {

        try {
            Connection con =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query = "INSERT INTO Sales (id_sales, id_product, id_supplier, quantity_sales, price_sales, date_sales ) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);

            // Remplissage des paramètres de la requête SQL
            pstmt.setInt(1, sales.getIdSales());
            pstmt.setInt(2, sales.getIdProduct());
            pstmt.setInt(3, sales.getIdSuppliers());
            pstmt.setInt(4, sales.getQuantitySales());
            pstmt.setDouble(5, sales.getPriceSales());

            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            LocalDate date = LocalDate.parse(sales.getDateSales(), inputFormatter);
            String formattedDate = date.format(outputFormatter);
            pstmt.setString(6, formattedDate);


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


    public static void editSales(int idSalesInput, ComboBox<Products> idProductInput, TextField idSupplierInput, double priceSalesSql, int quantitySalesInput, LocalDate dateSalesInput) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query1 = "Select id_sales, id_product, id_supplier, quantity_sales, price_sales, date_sales from Sales WHERE id_sales = ?";
            PreparedStatement pstmt1 = con.prepareStatement(query1);
            pstmt1.setInt(1, idSalesInput);

            ResultSet rs = pstmt1.executeQuery();

            if (!rs.next()) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("No sale found with this ID");
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setGraphic(null);
                alert.initStyle(StageStyle.UTILITY);
                alert.getDialogPane().getStylesheets().add("gestion/resources/sales.css");
                alert.showAndWait();

                rs.close();
                pstmt1.close();
                con.close();
                return;
            }

            int currentIdProduct = rs.getInt("id_product");
            int currentIdSupplier = rs.getInt("id_supplier");
            int currentQuantitySales = rs.getInt("quantity_sales");
            Double currentPriceSales = rs.getDouble("price_sales");
            String currentDateSales = rs.getString("date_sales");

            rs.close();
            pstmt1.close();

            int newIdProduct = idProductInput.getValue().getIdProduct();
            int newIdSupplier = Integer.parseInt(String.valueOf(idSupplierInput.getText()));
            int newQuantitySales = Integer.parseInt(String.valueOf(quantitySalesInput));
            Double newPriceSales = Double.parseDouble(String.valueOf(priceSalesSql));
            String newDate = dateSalesInput.toString();

            boolean noChanges = currentIdProduct == newIdProduct &&
                    currentIdSupplier == newIdSupplier &&
                    currentQuantitySales == newQuantitySales &&
                    currentPriceSales == newPriceSales &&
                    currentDateSales.equals(newDate);

            if (noChanges) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("No changes were made.");
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setGraphic(null);
                alert.initStyle(StageStyle.UTILITY);
                alert.getDialogPane().getStylesheets().add("gestion/resources/sales.css");
                alert.showAndWait();
            } else {
                String query = "UPDATE Sales SET id_product = ?, id_supplier = ?, quantity_sales = ?, price_sales = ?, date_sales = ? WHERE id_sales = ?";
                PreparedStatement pstmt = con.prepareStatement(query);

                pstmt.setInt(1, idProductInput.getValue().getIdProduct());
                pstmt.setInt(2, Integer.parseInt(String.valueOf(idSupplierInput.getText())));
                pstmt.setInt(3, Integer.parseInt(String.valueOf(quantitySalesInput)));
                pstmt.setDouble(4, Double.parseDouble(String.valueOf(priceSalesSql)));

                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                pstmt.setString(5, dateSalesInput.format(outputFormatter));
                pstmt.setInt(6, Integer.parseInt(String.valueOf(idSalesInput)));


                pstmt.executeUpdate();

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Sale edited successfully");
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setGraphic(null);
                alert.initStyle(StageStyle.UTILITY);
                alert.getDialogPane().getStylesheets().add("gestion/resources/sales.css");
                alert.showAndWait();

                pstmt.close();
            }

            con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Something went wrong");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.setGraphic(null);
            alert.initStyle(StageStyle.UTILITY);
            alert.getDialogPane().getStylesheets().add("gestion/resources/sales.css");
            alert.showAndWait();
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
                double priceSales = rs.getDouble("price_sales");
                String dateSales = rs.getString("date_sales");

                Sales sales = new Sales(idSales, idProduct, idSupplier, quantitySales, priceSales, dateSales);
                salesList.add(sales);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesList;
    }
}

