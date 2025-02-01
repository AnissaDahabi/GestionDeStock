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
        Button delProductBtn = new Button("Delete a product");
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
        delProductBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getDelProductScene1());
        });

        VBox homeProducts = new VBox(returnBtnContainer, productsTitle, productsBtnContainer);

        Scene productsScene = new Scene(homeProducts, 300, 600);
        productsScene.getStylesheets().add("gestion/resources/style.css");

        return productsScene;
    }

    //ADD PRODUCT SCENE
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

        // Title and txt:
        VBox addTitle = new VBox(new Label("Add a product"));
        addTitle.setAlignment(Pos.CENTER);
        addTitle.getStyleClass().add("addTitle");

        Text addTxt = new Text();
        addTxt.setText("Please enter the details of the new product to be deleted from the inventory.");
        addTxt.setId("addTxt");
        addTxt.setWrappingWidth(280);
        HBox addTxtContainer = new HBox();
        addTxtContainer.getChildren().add(addTxt);
        addTxtContainer.setId("addTxtContainer");
        addTxtContainer.setAlignment(Pos.CENTER);

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

        addProductGrid.setAlignment(Pos.CENTER);

        addProductGrid.setPadding(new Insets(40, 0, 0, 0));

        Button submitAddedProductBtn = new Button("Add");
        submitAddedProductBtn.getStyleClass().add("submitAddedProductBtn");
        HBox addProductContainer = new HBox(10);
        addProductContainer.getChildren().add(submitAddedProductBtn);
        addProductContainer.setAlignment(Pos.CENTER);
        addProductContainer.setPadding(new Insets(100, 10, 10, 10));

        submitAddedProductBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getProductsHomeScene());
        });

        VBox addProducts = new VBox(returnBtnContainer, addTitle, addTxtContainer, addProductGrid,addProductContainer);

        Scene addProductScene = new Scene(addProducts, 300, 600);
        addProductScene.getStylesheets().add("gestion/resources/style.css");
        return addProductScene;
    }

    // DELETE PRODUCT SCENE 1
    public static Scene delProductScene1() {

        // Return button:
        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");

        returnBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getProductsHomeScene());
        });

        // Title and txt:
        VBox delTitle1 = new VBox(new Label("Delete a product"));
        delTitle1.setAlignment(Pos.CENTER);
        delTitle1.getStyleClass().add("delTitle1");

        Text delTxt1 = new Text();
        delTxt1.setText("Please enter the ID number of the product you want to delete from the inventory");
        delTxt1.setId("delTxt1");
        delTxt1.setWrappingWidth(280);
        HBox delTxtContainer1 = new HBox();
        delTxtContainer1.getChildren().add(delTxt1);
        delTxtContainer1.setId("delTxtContainer1");
        delTxtContainer1.setAlignment(Pos.CENTER);

        HBox returnBtnContainer = new HBox(10); // HBox = container horizontal
        returnBtnContainer.setAlignment(Pos.TOP_RIGHT);
        returnBtnContainer.setPadding(new Insets(10, 10, 10, 10)); //équivalent du <style> blabla </style> en html
        returnBtnContainer.getChildren().add(returnBtn);

        //User input
        Label idProduct = new Label("ID Number: ");
        TextField idProductInput = new TextField();
        HBox idProductContainer = new HBox();
        idProductContainer.getChildren().add(idProduct);
        idProductContainer.getChildren().add(idProductInput);
        idProductContainer.setId("idProductContainer");
        idProductContainer.setAlignment(Pos.CENTER);

        //Next button:
        Button submitDeletedProductBtn1 = new Button("Next");
        submitDeletedProductBtn1.getStyleClass().add("submitDeletedProductBtn1");
        HBox delProductContainer1 = new HBox(10);
        delProductContainer1.getChildren().add(submitDeletedProductBtn1);
        delProductContainer1.setAlignment(Pos.CENTER);
        delProductContainer1.setPadding(new Insets(250, 10, 10, 10));

        submitDeletedProductBtn1.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getDelProductScene2());
        });

        VBox delProducts1 = new VBox(returnBtnContainer, delTitle1, delTxtContainer1, idProductContainer, delProductContainer1);

        Scene delProductScene1 = new Scene(delProducts1, 300, 600);
        delProductScene1.getStylesheets().add("gestion/resources/style.css");
        return delProductScene1;
    }

    // DELETE PRODUCT SCENE 2
    public static Scene delProductScene2() {

        // Return button:
        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");

        returnBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getDelProductScene1());
        });

        HBox returnBtnContainer = new HBox(10); // HBox = container horizontal
        returnBtnContainer.setAlignment(Pos.TOP_RIGHT);
        returnBtnContainer.setPadding(new Insets(10, 10, 10, 10)); //équivalent du <style> blabla </style> en html
        returnBtnContainer.getChildren().add(returnBtn);

        // Title and txt:
        VBox delTitle2 = new VBox(new Label("Delete a product"));
        delTitle2.setAlignment(Pos.CENTER);
        delTitle2.getStyleClass().add("delTitle2");

        Text delTxt2 = new Text();
        delTxt2.setText("Please enter the details of the new product to be added into the inventory");
        delTxt2.setId("delTxt2");
        delTxt2.setWrappingWidth(280);
        HBox delTxtContainer2 = new HBox();
        delTxtContainer2.getChildren().add(delTxt2);
        delTxtContainer2.setId("delTxtContainer2");
        delTxtContainer2.setAlignment(Pos.CENTER);

        // User input:
        GridPane delProductGrid = new GridPane();
        delProductGrid.getStyleClass().add("addProductGrid");

        delProductGrid.setVgap(10);
        delProductGrid.setHgap(10);

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

        delProductGrid.add(idProduct, 0, 0);
        delProductGrid.add(idProductInput, 1, 0);
        delProductGrid.add(nameProduct, 0, 1);
        delProductGrid.add(nameProductInput, 1, 1);
        delProductGrid.add(priceProduct, 0, 2);
        delProductGrid.add(priceProductInput, 1, 2);
        delProductGrid.add(quantityProduct, 0, 3);
        delProductGrid.add(quantityProductInput, 1, 3);
        delProductGrid.add(supplierProduct, 0, 4);
        delProductGrid.add(supplierProductInput, 1, 4);

        delProductGrid.setAlignment(Pos.CENTER);
        delProductGrid.setPadding(new Insets(40, 0, 0, 0));

        Button submitDeletedProductBtn2 = new Button("Delete");
        submitDeletedProductBtn2.getStyleClass().add("submitDeletedProductBtn2");
        HBox delProductContainer2 = new HBox(10);
        delProductContainer2.getChildren().add(submitDeletedProductBtn2);
        delProductContainer2.setAlignment(Pos.CENTER);
        delProductContainer2.setPadding(new Insets(130, 10, 10, 10));

        submitDeletedProductBtn2.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getProductsHomeScene());
        });

        VBox delProducts2 = new VBox(returnBtnContainer, delTitle2, delTxtContainer2, delProductGrid,delProductContainer2);

        Scene delProductScene2 = new Scene(delProducts2, 300, 600);
        delProductScene2.getStylesheets().add("gestion/resources/style.css");
        return delProductScene2;
    }


}
