package gestion.dao;

import gestion.model.Sales;
import gestion.ui.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.sql.*;


public class SalesQuery {

    private static int currentSalesId;

    public static boolean addSales(Sales sales) {
        try {
            Connection con =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query = "INSERT INTO Sales (id_sales, id_supplier, id_product, quantity_sales, price_sales, date_sales ) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);

            //Remplissage des paramètres de la requête SQL
            pstmt.setInt(1, sales.getIdSales());
            pstmt.setInt(2, sales.getIdSuppliers());
            pstmt.setInt(3, sales.getIdProduct());
            pstmt.setInt(4, sales.getQuantitySales());
            pstmt.setInt(5, sales.getPriceSales());
            pstmt.setString(6, sales.getDateSales());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }

    public static boolean  delSales(int idSales) {
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

   /* public static Scene delSales2(int idSalesSql, String nameSalesSuppliersSql, String nameSalesProductSql) {

        // Return button:
        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");

        returnBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getDelSalesScene1());
        });

        HBox returnBtnContainer = new HBox(10);
        returnBtnContainer.setAlignment(Pos.TOP_RIGHT);
        returnBtnContainer.setPadding(new Insets(10, 10, 10, 10));
        returnBtnContainer.getChildren().add(returnBtn);

        // Title and txt:
        VBox delTitle2 = new VBox(new Label("Delete a sale"));
        delTitle2.setAlignment(Pos.CENTER);
        delTitle2.getStyleClass().add("delTitle2");

        Text delTxt2 = new Text();
        delTxt2.setText("Is this the sale you want to delete from the database ?");
        delTxt2.setId("delTxt2");
        delTxt2.setWrappingWidth(280);
        HBox delTxtContainer2 = new HBox();
        delTxtContainer2.getChildren().add(delTxt2);
        delTxtContainer2.setId("delTxtContainer2");
        delTxtContainer2.setAlignment(Pos.CENTER);

        // Id and name of sale selected by user
        Label idSales = new Label("ID Number: " + idSalesSql);
        Label nameSalesSuppliers = new Label("Name: " + nameSalesSuppliersSql);
        Label nameSalesProduct = new Label("Name: " + nameSalesProductSql);

        VBox inputResult = new VBox(10, idSales, nameSalesSuppliers, nameSalesProduct);
        inputResult.setId("inputResult");
        inputResult.setAlignment(Pos.CENTER);
        inputResult.setPadding(new Insets(50, 10, 10, 10));

        Button submitDeletedSalesBtn2 = new Button("Confirm");
        submitDeletedSalesBtn2.getStyleClass().add("submitDeletedSalesBtn2");
        HBox delSalesContainer2 = new HBox(10);
        delSalesContainer2.getChildren().add(submitDeletedSalesBtn2);
        delSalesContainer2.setAlignment(Pos.CENTER);
        delSalesContainer2.setPadding(new Insets(130, 10, 10, 10));

        submitDeletedSalesBtn2.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            gestion.dao.SalesQuery.delSales();
            stage.setScene(SceneManager.getSalesHomeScene());
        });

        VBox delSales2 = new VBox(returnBtnContainer, delTitle2, delTxtContainer2, inputResult, delSalesContainer2);

        Scene delSalesScene2 = new Scene(delSales2, 300, 600);
        delSalesScene2.getStylesheets().add("gestion/resources/sales.css");
        return delSalesScene2;
    }
*/



    public static void editSales1() {

    }

    public static void showEditedSales(TextField idSalesInput, Stage stage) {
        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query = "SELECT id_sales, id_supplier, id_product, quantity_sales, price_sales,date_sales FROM Sales WHERE id_sales = ?";
            PreparedStatement pstmt = con.prepareStatement(query);

            try {
                currentSalesId = Integer.parseInt(idSalesInput.getText());
                pstmt.setInt(1, currentSalesId);

            } catch (NumberFormatException ex) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid sale ID, please enter an integer");
                alert.setContentText("Error: " + ex.getMessage());
                alert.showAndWait();
                return;
            }

            try (ResultSet resultSet = pstmt.executeQuery()) {

                if (resultSet.next()) {
                    int idSalesSql = resultSet.getInt("id_sales");
                    int  idsuppliersSql = resultSet.getInt("id_supplier");
                    int idProductSql = resultSet.getInt("id_product");
                    int quantitySalesProduct = resultSet.getInt("quantity_sales");
                    int priceSales = resultSet.getInt("price_sales");
                    String dateSalesSql = resultSet.getString("date_sales");

                    stage.setScene(SceneManager.getEditSalesScene2(idSalesSql, idsuppliersSql, idProductSql, quantitySalesProduct, priceSales, dateSalesSql));

                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Sale not found");
                    alert.setContentText("No sale found with this ID");
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

    public static void editSales(int idSalesInput, Label idsuppliersInput, Label idProductInput, TextField quantitySalesInput, TextField priceSalesInput, TextField dateSalesInput) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query = "UPDATE Sales SET id_supplier = ?, id_product = ?, quantity_sales = ?, price_sales = ?, date_sales= ? WHERE id_sales = ?";
            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setInt(1, Integer.parseInt(idsuppliersInput.getText()));
            pstmt.setInt(2, Integer.parseInt(idProductInput.getText()));

            try {
                pstmt.setInt(3, Integer.parseInt(quantitySalesInput.getText()));
            } catch (NumberFormatException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid quantity value, please enter an integer");
                alert.setContentText("Error: " + e.getMessage());
            }

            try {
                pstmt.setInt(4, Integer.parseInt(priceSalesInput.getText()));
            } catch (NumberFormatException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid price value, please enter an integer");
                alert.setContentText("Error: " + e.getMessage());
            }

            try {
                pstmt.setString(5, dateSalesInput.getText());
            } catch (NumberFormatException e ) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid date value, please enter an integer");
                alert.setContentText("Error: " + e.getMessage());
            }

            pstmt.setInt(6, Integer.parseInt(String.valueOf(idSalesInput)));

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText(null);
                alert.setContentText("Sale edited successfully");
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
            alert.setHeaderText("Error encountered while editing the sale");
            alert.setContentText("Error: " + ex.getMessage());
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

            while (rs.next()){
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
    public static Sales getSalesbyId(int idSales) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");
            String query = "Select * from Sales where id_sales = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, idSales);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Sales(
                        rs.getInt("id_sales"),
                        rs.getInt("id_product"),
                        rs.getInt("id_supplier"),
                        rs.getInt("quantity_sales"),
                        rs.getInt("price_sales"),
                        rs.getString("date_sales")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error getting sales data: " + e.getMessage());
        }
    }
    public static ObservableList<Sales> getSalesbyId() {
        ObservableList<Sales> salesList = FXCollections.observableArrayList();

        String query = "Select * from Sales";

        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int idsales = rs.getInt("id_sales");
                int idsupplier = rs.getInt("id_supplier");
                int idproduct = rs.getInt("id_product");
                int quantitysales = rs.getInt("quantity_sales");
                int pricesales = rs.getInt("price_sales");
                String datesales = rs.getString("date_sales");

                Sales sales = new Sales(idsales, idsupplier, idproduct, quantitysales, pricesales, datesales);
                salesList.add(sales);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesList;
    }

}

