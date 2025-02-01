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
        editProductBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getEditProductScene1());
        });

        showProductBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getShowProductScene());
        });

        VBox homeProducts = new VBox(returnBtnContainer, productsTitle, productsBtnContainer);

        Scene productsScene = new Scene(homeProducts, 300, 600);
        productsScene.getStylesheets().add("gestion/resources/products.css");

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
        addTxt.setText("Please enter the details of the new product to be added into the inventory");
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
        addProductScene.getStylesheets().add("gestion/resources/products.css");
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

        HBox returnBtnContainer = new HBox(10); // HBox = container horizontal
        returnBtnContainer.setAlignment(Pos.TOP_RIGHT);
        returnBtnContainer.setPadding(new Insets(10, 10, 10, 10)); //équivalent du <style> blabla </style> en html
        returnBtnContainer.getChildren().add(returnBtn);

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
        delProductScene1.getStylesheets().add("gestion/resources/products.css");
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
        delTxt2.setText("Is this the product you want to delete from the inventory?");
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

        Button submitDeletedProductBtn2 = new Button("Confirm");
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
        delProductScene2.getStylesheets().add("gestion/resources/products.css");
        return delProductScene2;
    }

    // EDIT PRODUCT SCENE 1
    public static Scene editProductScene1() {

        // Return button:
        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");

        returnBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getProductsHomeScene());
        });

        HBox returnBtnContainer = new HBox(10);
        returnBtnContainer.setAlignment(Pos.TOP_RIGHT);
        returnBtnContainer.setPadding(new Insets(10, 10, 10, 10));
        returnBtnContainer.getChildren().add(returnBtn);

        // Title and txt:
        VBox editTitle1 = new VBox(new Label("Edit product"));
        editTitle1.setAlignment(Pos.CENTER);
        editTitle1.getStyleClass().add("editTitle1");

        Text editTxt1 = new Text();
        editTxt1.setText("Please enter the ID number of the product you want to edit");
        editTxt1.setId("editTxt1");
        editTxt1.setWrappingWidth(280);
        HBox editTxtContainer1 = new HBox();
        editTxtContainer1.getChildren().add(editTxt1);
        editTxtContainer1.setId("delTxtContainer1");
        editTxtContainer1.setAlignment(Pos.CENTER);

        //User input
        Label idProduct = new Label("ID Number: ");
        TextField idProductInput = new TextField();
        HBox idProductContainer = new HBox();
        idProductContainer.getChildren().add(idProduct);
        idProductContainer.getChildren().add(idProductInput);
        idProductContainer.setId("idProductContainer");
        idProductContainer.setAlignment(Pos.CENTER);

        //Next button:
        Button submitEditedProductBtn1 = new Button("Next");
        submitEditedProductBtn1.getStyleClass().add("submitEditedProductBtn1");
        HBox editProductContainer1 = new HBox(10);
        editProductContainer1.getChildren().add(submitEditedProductBtn1);
        editProductContainer1.setAlignment(Pos.CENTER);
        editProductContainer1.setPadding(new Insets(270, 10, 10, 10));

        submitEditedProductBtn1.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getEditProductScene2());
        });

        VBox editProducts1 = new VBox(returnBtnContainer, editTitle1, editTxtContainer1, idProductContainer, editProductContainer1);

        Scene editProductScene1 = new Scene(editProducts1, 300, 600);
        editProductScene1.getStylesheets().add("gestion/resources/products.css");
        return editProductScene1;
    }

    // DELETE PRODUCT SCENE 2
    public static Scene editProductScene2() {

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
        VBox editTitle2 = new VBox(new Label("Edit product"));
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
        GridPane editProductGrid = new GridPane();
        editProductGrid.getStyleClass().add("editProductGrid");

        editProductGrid.setVgap(10);
        editProductGrid.setHgap(10);

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

        editProductGrid.add(idProduct, 0, 0);
        editProductGrid.add(idProductInput, 1, 0);
        editProductGrid.add(nameProduct, 0, 1);
        editProductGrid.add(nameProductInput, 1, 1);
        editProductGrid.add(priceProduct, 0, 2);
        editProductGrid.add(priceProductInput, 1, 2);
        editProductGrid.add(quantityProduct, 0, 3);
        editProductGrid.add(quantityProductInput, 1, 3);
        editProductGrid.add(supplierProduct, 0, 4);
        editProductGrid.add(supplierProductInput, 1, 4);

        editProductGrid.setAlignment(Pos.CENTER);
        editProductGrid.setPadding(new Insets(40, 0, 0, 0));

        Button submitEditedProductBtn2 = new Button("Edit");
        submitEditedProductBtn2.getStyleClass().add("submitEditedProductBtn2");
        HBox editProductContainer2 = new HBox(10);
        editProductContainer2.getChildren().add(submitEditedProductBtn2);
        editProductContainer2.setAlignment(Pos.CENTER);
        editProductContainer2.setPadding(new Insets(145, 10, 10, 10));

        submitEditedProductBtn2.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getProductsHomeScene());
        });

        VBox editProducts2 = new VBox(returnBtnContainer, editTitle2, editTxtContainer2, editProductGrid,editProductContainer2);

        Scene editProductScene2 = new Scene(editProducts2, 300, 600);
        editProductScene2.getStylesheets().add("gestion/resources/products.css");
        return editProductScene2;
    }

    //SHOW PRODUCTS SCENE
    public static Scene showProductScene() {

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
        VBox showTitle = new VBox(new Label("Products Inventory"));
        showTitle.setAlignment(Pos.CENTER);
        showTitle.getStyleClass().add("showTitle");

        Label sortProducts = new Label("Sort Products: ");
        sortProducts.getStyleClass().add("sortProducts");

        ObservableList<String> sortOptions =
                FXCollections.observableArrayList(
                        "alphabetical order",
                        "ascending order",
                        "descending order"
                );
        final ComboBox sortFilter = new ComboBox(sortOptions);
        sortFilter.setId("sortFilter");

        HBox sortFilterContainer = new HBox();
        sortFilterContainer.getChildren().add(sortProducts);
        sortFilterContainer.getChildren().add(sortFilter);
        sortFilterContainer.getStyleClass().add("sortFilterContainer");


        VBox showProducts = new VBox(returnBtnContainer, showTitle, sortFilterContainer);

        Scene showProductScene = new Scene(showProducts, 300, 600);
        showProductScene.getStylesheets().add("gestion/resources/products.css");
        return showProductScene;
    }

}
