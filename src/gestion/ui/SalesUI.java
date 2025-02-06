package gestion.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.spreadsheet.Grid;

public class SalesUI {


    public static Scene createContent() {

        // Return button:
        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");

        returnBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getHomeScene());
        });

        HBox returnBtnContainer = new HBox(10); // HBox = container horizontal
        returnBtnContainer.setAlignment(Pos.TOP_RIGHT);
        returnBtnContainer.setPadding(new Insets(10, 10, 10, 10)); //équivalent du <style> blabla </style> en html
        returnBtnContainer.getChildren().add(returnBtn);

        // Title:
        HBox salesTitle = new HBox(new Label("Sales"));
        salesTitle.getStyleClass().add("salesTitle");
        salesTitle.setAlignment(Pos.CENTER);

        // Buttons:
        Button addSalesBtn = new Button("Add a sale");
        addSalesBtn.getStyleClass().add("addSalesBtn");
        Button delSalesBtn = new Button("Delete a sale");
        delSalesBtn.getStyleClass().add("delSalesBtn");
        Button editSalesBtn = new Button("Edit a sale");
        editSalesBtn.getStyleClass().add("editSalesBtn");
        Button showSalesBtn = new Button("Show all sales");
        showSalesBtn.getStyleClass().add("showSalesBtn");

        VBox salesBtnContainer = new VBox(40);
        salesBtnContainer.getStyleClass().add("salesBtnContainer");
        salesBtnContainer.setAlignment(Pos.CENTER);
        salesBtnContainer.getChildren().add(addSalesBtn);
        salesBtnContainer.getChildren().add(delSalesBtn);
        salesBtnContainer.getChildren().add(editSalesBtn);
        salesBtnContainer.getChildren().add(showSalesBtn);

        addSalesBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getAddSalesScene());
        });

        delSalesBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getDelSalesScene1());
        });

        editSalesBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getEditSalesScene1());
        });


        VBox homeSales = new VBox(returnBtnContainer, salesTitle, salesBtnContainer);

        Scene salesScene = new Scene(homeSales, 300, 600);
        salesScene.getStylesheets().add("gestion/resources/sales.css");


        return salesScene;
    }

    public static Scene addSalesScene(){
        // Return button:
        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");

        returnBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getSalesHomeScene());
        });

        HBox returnBtnContainer = new HBox(10);
        returnBtnContainer.setAlignment(Pos.TOP_RIGHT);
        returnBtnContainer.setPadding(new Insets(10, 10, 10, 10));
        returnBtnContainer.getChildren().add(returnBtn);

        // Title and txt:
        VBox addTitle = new VBox(new Label("Add a sale"));
        addTitle.setAlignment(Pos.CENTER);
        addTitle.getStyleClass().add("addTitle");

        Text addTxt = new Text();
        addTxt.setText("Please enter the details of the new sale to be added into the database");
        addTxt.setId("addTxt");
        addTxt.setWrappingWidth(280);
        HBox addTxtContainer = new HBox();
        addTxtContainer.getChildren().add(addTxt);
        addTxtContainer.setId("addTxtContainer");
        addTxtContainer.setAlignment(Pos.CENTER);

        // User input:
        GridPane addSalesGrid = new GridPane();
        addSalesGrid.getStyleClass().add("addSalesGrid");

        addSalesGrid.setVgap(10);
        addSalesGrid.setHgap(10);

        Label idSales = new Label("ID Number: ");
        TextField idSalesInput = new TextField();

        Label nameSalesSupplier = new Label("Supplier name: ");
        TextField nameSalesSupplierInput = new TextField();

        Label nameSalesProducts = new Label("Product name: ");
        TextField nameSalesProductInput = new TextField();

        Label quantitySalesProduct = new Label("Quantity: ");
        TextField quantitySalesProductInput = new TextField();

        Label priceSales = new Label("Price: ");
        TextField priceSalesInput = new TextField();


        addSalesGrid.add(idSales, 0, 0);
        addSalesGrid.add(idSalesInput, 1, 0);
        addSalesGrid.add(nameSalesSupplier, 0, 1);
        addSalesGrid.add(nameSalesSupplierInput, 1, 1);
        addSalesGrid.add(nameSalesProducts, 0, 2);
        addSalesGrid.add(nameSalesProductInput, 1, 2);
        addSalesGrid.add(quantitySalesProduct, 0, 3);
        addSalesGrid.add(quantitySalesProductInput, 1, 3);
        addSalesGrid.add(priceSales, 0, 4);
        addSalesGrid.add(priceSalesInput, 1, 4);

        addSalesGrid.setAlignment(Pos.CENTER);

        addSalesGrid.setPadding(new Insets(40, 0, 0, 0));

        Button submitAddedSalesBtn = new Button("Add");
        submitAddedSalesBtn.getStyleClass().add("submitAddedSalesBtn");
        HBox addSalesContainer = new HBox(10);
        addSalesContainer.getChildren().add(submitAddedSalesBtn);
        addSalesContainer.setAlignment(Pos.CENTER);
        addSalesContainer.setPadding(new Insets(100, 10, 10, 10));

        submitAddedSalesBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            gestion.dao.SalesQuery.addSales(idSalesInput, nameSalesSupplierInput, nameSalesProductInput, quantitySalesProductInput, priceSalesInput);
            idSalesInput.clear();
            nameSalesSupplierInput.clear();
            nameSalesProductInput.clear();
            quantitySalesProductInput.clear();
            priceSalesInput.clear();
            stage.setScene(SceneManager.getSalesHomeScene());
        });

        VBox addSales = new VBox(returnBtnContainer, addTitle, addTxtContainer, addSalesGrid,addSalesContainer);

        Scene addSalesScene = new Scene(addSales, 300, 600);
        addSalesScene.getStylesheets().add("gestion/resources/sales.css");

        return addSalesScene;
    }

    public static Scene delSalesScene1() {
        // Return button:
        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");

        returnBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getSalesHomeScene());
        });

        HBox returnBtnContainer = new HBox(10);
        returnBtnContainer.setAlignment(Pos.TOP_RIGHT);
        returnBtnContainer.setPadding(new Insets(10, 10, 10, 10));
        returnBtnContainer.getChildren().add(returnBtn);

        // Title and txt:
        VBox delTitle1 = new VBox(new Label("Delete a sale"));
        delTitle1.setAlignment(Pos.CENTER);
        delTitle1.getStyleClass().add("delTitle1");

        Text delTxt1 = new Text();
        delTxt1.setText("Please enter the ID number of the sale you want to delete from the database");
        delTxt1.setId("delTxt1");
        delTxt1.setWrappingWidth(280);
        HBox delTxtContainer1 = new HBox();
        delTxtContainer1.getChildren().add(delTxt1);
        delTxtContainer1.setId("delTxtContainer1");
        delTxtContainer1.setAlignment(Pos.CENTER);

        //User input
        Label idSalesLabel = new Label("ID Number: ");
        TextField idSalesInput = new TextField();
        HBox idSalesContainer = new HBox();
        idSalesContainer.getChildren().add(idSalesLabel);
        idSalesContainer.getChildren().add(idSalesInput);
        idSalesContainer.setId("idSalesContainer");
        idSalesContainer.setAlignment(Pos.CENTER);


        //Next button:
        Button submitDeletedSalesBtn1 = new Button("Next");
        submitDeletedSalesBtn1.getStyleClass().add("submitDeletedSalesBtn1");
        HBox delSalesContainer1 = new HBox(10);
        delSalesContainer1.getChildren().add(submitDeletedSalesBtn1);
        delSalesContainer1.setAlignment(Pos.CENTER);
        delSalesContainer1.setPadding(new Insets(250, 10, 10, 10));

        submitDeletedSalesBtn1.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            gestion.dao.SalesQuery.showDelSales(idSalesInput, stage);
            idSalesInput.clear();
        });

        VBox delSuppliers1 = new VBox(returnBtnContainer, delTitle1, delTxtContainer1, idSalesContainer, delSalesContainer1);

        Scene delSuppliersScene1 = new Scene(delSuppliers1, 300, 600);
        delSuppliersScene1.getStylesheets().add("gestion/resources/suppliers.css");
        return delSuppliersScene1;
    }

    public static Scene delSalesScene2(int idSalesSql, String nameSalesSuppliersSql, String nameSalesProductsSql) {
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
        Label nameSalesProducts = new Label("Name: " + nameSalesProductsSql);

        VBox inputResult = new VBox(10, idSales, nameSalesSuppliers, nameSalesProducts);
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

        VBox delSales2 = new VBox(returnBtnContainer, delTitle2, delTxtContainer2, inputResult,delSalesContainer2);

        Scene delSalesScene2 = new Scene(delSales2, 300, 600);
        delSalesScene2.getStylesheets().add("gestion/resources/sales.css");
        return delSalesScene2;
    }

    public static Scene editSalesScene1() {

            // Return button:
            Button returnBtn = new Button("Return");
            returnBtn.getStyleClass().add("returnBtn");

            returnBtn.setOnAction(event -> {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(SceneManager.getSalesHomeScene());
            });

            HBox returnBtnContainer = new HBox(10);
            returnBtnContainer.setAlignment(Pos.TOP_RIGHT);
            returnBtnContainer.setPadding(new Insets(10, 10, 10, 10));
            returnBtnContainer.getChildren().add(returnBtn);

            // Title and txt:
            VBox editTitle1 = new VBox(new Label("Edit sales"));
            editTitle1.setAlignment(Pos.CENTER);
            editTitle1.getStyleClass().add("editTitle1");

            Text editTxt1 = new Text();
            editTxt1.setText("Please enter the ID number of the sale you want to edit");
            editTxt1.setId("editTxt1");
            editTxt1.setWrappingWidth(280);
            HBox editTxtContainer1 = new HBox();
            editTxtContainer1.getChildren().add(editTxt1);
            editTxtContainer1.setId("delTxtContainer1");
            editTxtContainer1.setAlignment(Pos.CENTER);

            //User input
          /*  Label idSales = new Label("ID Number: ");
            TextField idSalesInput = new TextField();
            TextField idSalesSuppliersInput = new TextField();
            TextField idSalesProductsInput = new TextField();
            HBox idSalesContainer = new HBox();
            idSalesContainer.getChildren().add(idSales);
            idSalesContainer.getChildren().add(idSalesInput);
            idSalesContainer.getChildren().add(idSalesSuppliersInput);
            idSalesContainer.getChildren().add(idSalesProductsInput);
            idSalesContainer.setId("idSalesContainer");
            idSalesContainer.setAlignment(Pos.CENTER);
*/

            //User input

            GridPane editSalesGrid = new GridPane();
            editSalesGrid.getStyleClass().add("editSalesGrid");

            editSalesGrid.setVgap(10);
            editSalesGrid.setHgap(10);

            Label idSales = new Label("ID Number: ");
            TextField idSalesInput = new TextField();

            Label nameSalesSuppliers = new Label("Supplier name: ");
            TextField nameSalesSuppliersInput = new TextField();

            Label nameSalesProducts = new Label("Product name: ");
            TextField nameSalesProductsInput = new TextField();

            editSalesGrid.add(idSales, 0, 0);
            editSalesGrid.add(idSalesInput, 1, 0);
            editSalesGrid.add(nameSalesSuppliers, 0, 1);
            editSalesGrid.add(nameSalesSuppliersInput, 1, 1);
            editSalesGrid.add(nameSalesProducts, 0, 2);
            editSalesGrid.add(nameSalesProductsInput, 1, 2);

            editSalesGrid.setAlignment(Pos.CENTER);

            editSalesGrid.setPadding(new Insets(40,0,0,0));



            //Next button:
            Button submitEditedSalesBtn1 = new Button("Next");
            submitEditedSalesBtn1.getStyleClass().add("submitEditedSalesBtn1");
            HBox editSalesContainer1 = new HBox(10);
            editSalesContainer1.getChildren().add(submitEditedSalesBtn1);
            editSalesContainer1.setAlignment(Pos.CENTER);
            editSalesContainer1.setPadding(new Insets(270, 10, 10, 10));

            submitEditedSalesBtn1.setOnAction(event -> {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                gestion.dao.SalesQuery.showEditedSales(idSalesInput, stage);
                idSalesInput.clear();
            });

            VBox editSales1 = new VBox(returnBtnContainer, editTitle1, editTxtContainer1, editSalesGrid, editSalesContainer1);

            Scene editSalesScene1 = new Scene(editSales1, 300, 600);
            editSalesScene1.getStylesheets().add("gestion/resources/sales.css");
            return editSalesScene1;
    }

    public static Scene editSalesScene2(int idSalesSql, String nameSalesSuppliersSql, String nameSalesProductSql, int quantitySalesProductSql, String priceSalesSql) {
// Return button:
        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");

        returnBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getEditSalesScene1());
        });

        HBox returnBtnContainer = new HBox(10);
        returnBtnContainer.setAlignment(Pos.TOP_RIGHT);
        returnBtnContainer.setPadding(new Insets(10, 10, 10, 10));
        returnBtnContainer.getChildren().add(returnBtn);

        // Title and txt:
        VBox editTitle2 = new VBox(new Label("Edit sales"));
        editTitle2.setAlignment(Pos.CENTER);
        editTitle2.getStyleClass().add("editTitle2");

        Text editTxt2 = new Text();
        editTxt2.setText("What would you like to edit?");
        editTxt2.setId("editTxt2");
        editTxt2.setWrappingWidth(280);
        HBox editTxtContainer2 = new HBox();
        editTxtContainer2.getChildren().add(editTxt2);
        editTxtContainer2.setId("editTxtContainer2");
        editTxtContainer2.setAlignment(Pos.CENTER);

        // User input:
        GridPane editSalesGrid = new GridPane();
        editSalesGrid.getStyleClass().add("editSalesGrid");

        editSalesGrid.setVgap(10);
        editSalesGrid.setHgap(10);

        Label idSales = new Label("ID Number: " + idSalesSql );
        idSales.setId("idSalesLabel");

        Label nameSalesSuppliers = new Label("Supplier name: ");
        TextField nameSalesSuppliersInput = new TextField();
        nameSalesSuppliersInput.setPromptText(nameSalesSuppliersSql);
        nameSalesSuppliersInput.setId("nameSalesSuppliersInput");

        Label nameSalesProduct = new Label("Product name: ");
        TextField nameSalesProductInput = new TextField();
        nameSalesProductInput.setPromptText(String.valueOf(nameSalesProductSql));
        nameSalesProductInput.setId("nameSalesProductInput");

        Label quantitySalesProduct = new Label("Product quantity: ");
        TextField quantitySalesProductInput = new TextField();
        quantitySalesProductInput.setPromptText(String.valueOf(quantitySalesProductSql));
        quantitySalesProductInput.setId("quantitySalesProductInput");

        Label priceSales = new Label("Price: ");
        TextField priceSalesInput = new TextField();
        priceSalesInput.setPromptText(priceSalesSql);
        priceSalesInput.setId("priceSalesInput");

        editSalesGrid.add(idSales, 0, 0);
        editSalesGrid.add(nameSalesSuppliers, 0, 1);
        editSalesGrid.add(nameSalesSuppliersInput, 1, 1);
        editSalesGrid.add(nameSalesProduct, 0, 2);
        editSalesGrid.add(nameSalesProductInput, 1, 2);
        editSalesGrid.add(quantitySalesProduct, 0, 3);
        editSalesGrid.add(quantitySalesProductInput, 1, 3);
        editSalesGrid.add(priceSales, 0, 4);
        editSalesGrid.add(priceSalesInput, 1, 4);

        editSalesGrid.setAlignment(Pos.CENTER);
        editSalesGrid.setPadding(new Insets(40, 0, 0, 0));

        Button submitEditedSalesBtn2 = new Button("Edit");
        submitEditedSalesBtn2.getStyleClass().add("submitEditedSalesBtn2");
        HBox editSalesContainer2 = new HBox(10);
        editSalesContainer2.getChildren().add(submitEditedSalesBtn2);
        editSalesContainer2.setAlignment(Pos.CENTER);
        editSalesContainer2.setPadding(new Insets(145, 10, 10, 10));

        submitEditedSalesBtn2.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            gestion.dao.SalesQuery.editSales(nameSalesSuppliersInput, nameSalesProductInput, quantitySalesProductInput, priceSalesInput);
            stage.setScene(SceneManager.getSalesHomeScene());
        });

        VBox editSales2 = new VBox(returnBtnContainer, editTitle2, editTxtContainer2, editSalesGrid,editSalesContainer2);

        Scene editSalesScene2 = new Scene(editSales2, 300, 600);
        editSalesScene2.getStylesheets().add("gestion/resources/sales.css");
        return editSalesScene2;
    }


}
