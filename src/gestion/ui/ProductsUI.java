package gestion.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Text;



public class ProductsUI {

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
        HBox productsTitle = new HBox(new Label("Products"));
        productsTitle.getStyleClass().add("productsTitle");
        productsTitle.setAlignment(Pos.CENTER);

        // Buttons:
        Button addProductBtn = new Button("Add a product");
        addProductBtn.getStyleClass().add("addProductBtn");
        Button delProductBtn = new Button("Remove a product");
        delProductBtn.getStyleClass().add("delProductBtn");
        Button editProductBtn = new Button("Edit a product");
        editProductBtn.getStyleClass().add("editProductBtn");
        Button showProductBtn = new Button("Show all products");
        showProductBtn.getStyleClass().add("showProductBtn");

        VBox productsBtnContainer = new VBox(40);
        productsBtnContainer.getStyleClass().add("productsBtnContainer");
        productsBtnContainer.setAlignment(Pos.CENTER);
        productsBtnContainer.getChildren().add(addProductBtn);
        productsBtnContainer.getChildren().add(delProductBtn);
        productsBtnContainer.getChildren().add(editProductBtn);
        productsBtnContainer.getChildren().add(showProductBtn);

        addProductBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getAddProductScene());
        });

        VBox homeProducts = new VBox(returnBtnContainer, productsTitle, productsBtnContainer);

        Scene productsScene = new Scene(homeProducts, 300, 600);
        productsScene.getStylesheets().add("gestion/resources/style.css");

        return productsScene;
    }

    public static Scene addProductScene() {

        // Return button:
        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");

        returnBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getProductsHomeScene());
        });

        HBox returnBtnContainer = new HBox(10); // HBox = container horizontal
        returnBtnContainer.setAlignment(Pos.TOP_RIGHT);
        returnBtnContainer.setPadding(new Insets(10, 10, 10, 10)); //équivalent du <style> blabla </style> en html
        returnBtnContainer.getChildren().add(returnBtn);

        // Title:
        VBox addTitle = new VBox(new Label("Add a product"));
        addTitle.setAlignment(Pos.CENTER);
        addTitle.getStyleClass().add("addTitle");

        Text addTxt = new Text();
        addTxt.setText("Please enter the details of the new product to be added into the inventory.");
        addTxt.setId("addTxt");
        addTxt.setWrappingWidth(300);
        Pane txtContainer = new Pane();
        txtContainer.getChildren().add(addTxt);
        txtContainer.setId("txtContainer");

        // User input:
        GridPane addProductGrid = new GridPane();
        addProductGrid.getStyleClass().add("addProductGrid");

        addProductGrid.setVgap(10);
        addProductGrid.setHgap(10);

        Label idProduct = new Label("ID Number: ");
        TextField idProductInput = new TextField();

        Label nameProduct = new Label("Name: ");
        TextField nameProductInput = new TextField();

        Label priceProduct = new Label("Price: ");
        TextField priceProductInput = new TextField();

        Label quantityProduct = new Label("Quantity: ");
        TextField quantityProductInput = new TextField();

        Label supplierProduct = new Label("Supplier: ");
        TextField supplierProductInput = new TextField();

        addProductGrid.add(idProduct, 0, 0);
        addProductGrid.add(idProductInput, 1, 0);
        addProductGrid.add(nameProduct, 0, 1);
        addProductGrid.add(nameProductInput, 1, 1);
        addProductGrid.add(priceProduct, 0, 2);
        addProductGrid.add(priceProductInput, 1, 2);
        addProductGrid.add(quantityProduct, 0, 3);
        addProductGrid.add(quantityProductInput, 1, 3);
        addProductGrid.add(supplierProduct, 0, 4);
        addProductGrid.add(supplierProductInput, 1, 4);


        VBox addProducts = new VBox(returnBtnContainer, addTitle, txtContainer, addProductGrid);

        VBox.setMargin(txtContainer, new Insets(40, 10, 10, 10));

        Scene addProductScene = new Scene(addProducts, 300, 600);
        addProductScene.getStylesheets().add("gestion/resources/style.css");
        return addProductScene;
    }

}
