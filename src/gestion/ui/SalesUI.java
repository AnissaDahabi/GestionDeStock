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

        Label nameSalesSupplier = new Label("Name: ");
        TextField nameSalesSupplierInput = new TextField();

        Label nameSalesProducts = new Label("Name: ");
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
}
