package gestion.dao;

import gestion.ui.SceneManager;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.sql.*;


public class SalesQuery {

    private static int currentSalesId;

    public static void addSales(TextField idSalesInput, TextField namesupplierInput, TextField nameproductInput, TextField quantitysalesInput, TextField priceSalesInput, TextField datesSalesInput) {
        try {
            Connection con =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query = "INSERT INTO Sales (id_sales, name_supplier, name_product, quantity_sales, price_sales, date_sales ) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);

            //Récupération des données saisies par l'utilisateur
            int idsales = Integer.parseInt(idSalesInput.getText());
            String namesupplier = namesupplierInput.getText();
            String nameproduct = nameproductInput.getText();
            int quantitySalesProduct = Integer.parseInt(quantitysalesInput.getText());
            int priceSales = Integer.parseInt(priceSalesInput.getText());
            String dateSales = datesSalesInput.getText();

            //Remplissage des paramètres de la requête SQL
            pstmt.setInt(1, idsales);
            pstmt.setString(2, namesupplier);
            pstmt.setString(3, nameproduct);
            pstmt.setInt(4, quantitySalesProduct);
            pstmt.setInt(5, priceSales);
            pstmt.setString(6, dateSales);

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

    public static void delSales() {
        try {
            // Connexion à la base de données
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            // Préparation de la requête SQL
            String query = "DELETE FROM Sales WHERE id_sales = ?";
            PreparedStatement pstmt = con.prepareStatement(query);

            // Récupération de l'ID saisi par l'utilisateur
            pstmt.setInt(1, currentSalesId);

            // Exécution de la requête SQL
            int rowsAffected = pstmt.executeUpdate();


            if (rowsAffected > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText(null);
                alert.setContentText("Sale deleted successfully");
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
            alert.setHeaderText("Error encountered while deleting the sale");
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();

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

    public static void showDelSales(TextField idSalesInput, Stage stage) {
        try {
            // Connexion à la base de données
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            // Préparation de la requête SQL
            String query = "SELECT id_supplier, namesuppliers, name_sales_product, date_sales FROM Sales WHERE id_supplier = ?";
            PreparedStatement pstmt = con.prepareStatement(query);

            // Récupération de l'ID saisi par l'utilisateur
            int idSales = Integer.parseInt(idSalesInput.getText());

            // Remplissage du paramètre de la requête SQL
            pstmt.setInt(1, idSales);

            // Exécution de la requête SQL
            ResultSet resultSet = pstmt.executeQuery();


            if (resultSet.next()) {

                int idSalesSql = resultSet.getInt("id_sales");
                String namesuppliersSql = resultSet.getString("name_sales_supplier");
                String nameSalesProductSql = resultSet.getString("name_sales_product");
                String dateSalesSql = resultSet.getString("date_sales");

                currentSalesId = idSalesSql;


                stage.setScene(SceneManager.getDelSalesScene2(idSalesSql, namesuppliersSql, nameSalesProductSql, dateSalesSql));

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

    public static void editSales1() {

    }

    public static void showEditedSales(TextField idSalesInput, Stage stage) {
        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query = "SELECT id_sales, namesuppliers, name_sales_product, quantity_sales_product, price_sales,date_sales FROM Sales WHERE id_sales = ?";
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
                    String namesuppliersSql = resultSet.getString("name_sales_supplier");
                    String nameSalesProductSql = resultSet.getString("name_sales_product");
                    int quantitySalesProduct = resultSet.getInt("quantity_sales");
                    String priceSales = resultSet.getString("price_sales");
                    String dateSalesSql = resultSet.getString("date_sales");

                    stage.setScene(SceneManager.getEditSalesScene2(idSalesSql, namesuppliersSql, nameSalesProductSql, quantitySalesProduct, priceSales, dateSalesSql));

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

    public static void editSales(TextField namesuppliersInput, TextField nameSalesProductInput, TextField quantitySalesProductInput, TextField priceSalesInput, TextField dateSalesInput) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query = "UPDATE Sales SET namesupplier = ?, name_sales_product = ?, quantity_sales_product = ?, price_sales = ? WHERE id_sales = ?";
            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setString(1, namesuppliersInput.getText());
            pstmt.setString(2, nameSalesProductInput.getText());

            try {
                pstmt.setInt(3, Integer.parseInt(quantitySalesProductInput.getText()));
            } catch (NumberFormatException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid quantity value, please enter an integer");
                alert.setContentText("Error: " + e.getMessage());
            }

            try {
                pstmt.setDouble(4, Double.parseDouble(priceSalesInput.getText()));
            } catch (NumberFormatException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid price value, please enter an integer");
                alert.setContentText("Error: " + e.getMessage());
            }

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

}

