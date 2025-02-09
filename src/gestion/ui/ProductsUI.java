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
        Button addBtnProducts = new Button("Add a product");
        addBtnProducts.getStyleClass().add("addBtnProducts");
        Button delBtnProducts = new Button("Delete a product");
        delBtnProducts.getStyleClass().add("delBtnProducts");
        Button editBtnProducts = new Button("Edit a product");
        editBtnProducts.getStyleClass().add("editBtnProducts");
        Button showBtnProducts = new Button("Show all products");
        showBtnProducts.getStyleClass().add("showBtnProducts");

        VBox productsBtnContainer = new VBox(40);
        productsBtnContainer.getStyleClass().add("productsBtnContainer");
        productsBtnContainer.setAlignment(Pos.CENTER);
        productsBtnContainer.getChildren().add(addBtnProducts);
        productsBtnContainer.getChildren().add(delBtnProducts);
        productsBtnContainer.getChildren().add(editBtnProducts);
        productsBtnContainer.getChildren().add(showBtnProducts);

        addBtnProducts.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getAddProductsScene());
        });
        delBtnProducts.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getDelProductsScene1());
        });
        editBtnProducts.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getEditProductsScene1());
        });

        showBtnProducts.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getShowProductsScene());
        });

        VBox homeProducts = new VBox(returnBtnContainer, productsTitle, productsBtnContainer);

        Scene productsScene = new Scene(homeProducts, 300, 600);
        productsScene.getStylesheets().add("gestion/resources/products.css");

        return productsScene;
    }

    //ADD PRODUCT SCENE
    public static Scene addProductsScene() {

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
        GridPane addProductsGrid = new GridPane();
        addProductsGrid.getStyleClass().add("addProductGrid");

        addProductsGrid.setVgap(10);
        addProductsGrid.setHgap(10);

        Label idProducts = new Label("ID Number: ");
        TextField idProductsInput = new TextField();

        Label nameProducts = new Label("Name: ");
        TextField nameProductsInput = new TextField();

        Label priceProducts = new Label("Price: ");
        TextField priceProductsInput = new TextField();

        Label quantityProducts = new Label("Quantity: ");
        TextField quantityProductsInput = new TextField();

        Label supplierProducts = new Label("Supplier: ");
        TextField supplierProductsInput = new TextField();


        addProductsGrid.add(idProducts, 0, 0);
        addProductsGrid.add(idProductsInput, 1, 0);
        addProductsGrid.add(nameProducts, 0, 1);
        addProductsGrid.add(nameProductsInput, 1, 1);
        addProductsGrid.add(priceProducts, 0, 2);
        addProductsGrid.add(priceProductsInput, 1, 2);
        addProductsGrid.add(quantityProducts, 0, 3);
        addProductsGrid.add(quantityProductsInput, 1, 3);
        addProductsGrid.add(supplierProducts, 0, 4);
        addProductsGrid.add(supplierProductsInput, 1, 4);

        addProductsGrid.setAlignment(Pos.CENTER);

        addProductsGrid.setPadding(new Insets(40, 0, 0, 0));

        Button submitAddedProductsBtn = new Button("Add");
        submitAddedProductsBtn.getStyleClass().add("submitAddedProductsBtn");
        HBox addProductsContainer = new HBox(10);
        addProductsContainer.getChildren().add(submitAddedProductsBtn);
        addProductsContainer.setAlignment(Pos.CENTER);
        addProductsContainer.setPadding(new Insets(100, 10, 10, 10));

        submitAddedProductsBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            gestion.dao.ProductsQuery.addProducts(idProductsInput, nameProductsInput, priceProductsInput, quantityProductsInput, supplierProductsInput);
            idProductsInput.clear();
            nameProductsInput.clear();
            priceProductsInput.clear();
            quantityProductsInput.clear();
            supplierProductsInput.clear();
            stage.setScene(SceneManager.getProductsHomeScene());
        });

        VBox addProducts = new VBox(returnBtnContainer, addTitle, addTxtContainer, addProductsGrid,addProductsContainer);

        Scene addProductsScene = new Scene(addProducts, 300, 600);
        addProductsScene.getStylesheets().add("gestion/resources/products.css");
        return addProductsScene;
    }

    // DELETE PRODUCT SCENE 1
    public static Scene delProductsScene1() {

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
        Label idProductsLabel = new Label("ID Number: ");
        TextField idProductsInput = new TextField();
        HBox idProductsContainer = new HBox();
        idProductsContainer.getChildren().add(idProductsLabel);
        idProductsContainer.getChildren().add(idProductsInput);
        idProductsContainer.setId("idProductsContainer");
        idProductsContainer.setAlignment(Pos.CENTER);

        //Next button:
        Button submitDeletedProductsBtn1 = new Button("Next");
        submitDeletedProductsBtn1.getStyleClass().add("submitDeletedProductsBtn1");
        HBox delProductsContainer1 = new HBox(10);
        delProductsContainer1.getChildren().add(submitDeletedProductsBtn1);
        delProductsContainer1.setAlignment(Pos.CENTER);
        delProductsContainer1.setPadding(new Insets(250, 10, 10, 10));

        submitDeletedProductsBtn1.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            gestion.dao.ProductsQuery.showDelProducts(idProductsInput, stage);
            idProductsInput.clear();
        });

        VBox delProducts1 = new VBox(returnBtnContainer, delTitle1, delTxtContainer1, idProductsContainer, delProductsContainer1);

        Scene delProductsScene1 = new Scene(delProducts1, 300, 600);
        delProductsScene1.getStylesheets().add("gestion/resources/products.css");
        return delProductsScene1;
    }

    // DELETE PRODUCT SCENE 2
    public static Scene delProductsScene2(int idProductsSql, String nameProductsSql) {

        // Return button:
        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");

        returnBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getDelProductsScene1());
        });

        HBox returnBtnContainer = new HBox(10);
        returnBtnContainer.setAlignment(Pos.TOP_RIGHT);
        returnBtnContainer.setPadding(new Insets(10, 10, 10, 10));
        returnBtnContainer.getChildren().add(returnBtn);

        // Title and txt:
        VBox delTitle2 = new VBox(new Label("Delete product"));
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

        // Id and name of product selected by user
        Label idProducts = new Label("ID Number: " + idProductsSql);
        Label nameProducts = new Label("Name: " + nameProductsSql);

        VBox inputResult = new VBox(10, idProducts, nameProducts);
        inputResult.setId("inputResult");
        inputResult.setAlignment(Pos.CENTER);
        inputResult.setPadding(new Insets(50, 10, 10, 10));

        Button submitDeletedProductsBtn2 = new Button("Confirm");
        submitDeletedProductsBtn2.getStyleClass().add("submitDeletedProductsBtn2");
        HBox delProductsContainer2 = new HBox(10);
        delProductsContainer2.getChildren().add(submitDeletedProductsBtn2);
        delProductsContainer2.setAlignment(Pos.CENTER);
        delProductsContainer2.setPadding(new Insets(130, 10, 10, 10));

        submitDeletedProductsBtn2.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            gestion.dao.ProductsQuery.delProducts();
            stage.setScene(SceneManager.getProductsHomeScene());
        });

        VBox delProducts2 = new VBox(returnBtnContainer, delTitle2, delTxtContainer2, inputResult,delProductsContainer2);

        Scene delProductsScene2 = new Scene(delProducts2, 300, 600);
        delProductsScene2.getStylesheets().add("gestion/resources/products.css");
        return delProductsScene2;
    }

    // EDIT PRODUCT SCENE 1
    public static Scene editProductsScene1() {

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
        Label idProducts = new Label("ID Number: ");
        TextField idProductsInput = new TextField();
        HBox idProductsContainer = new HBox();
        idProductsContainer.getChildren().add(idProducts);
        idProductsContainer.getChildren().add(idProductsInput);
        idProductsContainer.setId("idProductsContainer");
        idProductsContainer.setAlignment(Pos.CENTER);

        //Next button:
        Button submitEditedProductsBtn1 = new Button("Next");
        submitEditedProductsBtn1.getStyleClass().add("submitEditedProductsBtn1");
        HBox editProductsContainer1 = new HBox(10);
        editProductsContainer1.getChildren().add(submitEditedProductsBtn1);
        editProductsContainer1.setAlignment(Pos.CENTER);
        editProductsContainer1.setPadding(new Insets(270, 10, 10, 10));

        submitEditedProductsBtn1.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            gestion.dao.ProductsQuery.showEditedProducts(idProductsInput, stage);
            idProductsInput.clear();
        });

        VBox editProducts1 = new VBox(returnBtnContainer, editTitle1, editTxtContainer1, idProductsContainer, editProductsContainer1);

        Scene editProductsScene1 = new Scene(editProducts1, 300, 600);
        editProductsScene1.getStylesheets().add("gestion/resources/products.css");
        return editProductsScene1;
    }

    // EDIT PRODUCT SCENE 2
    public static Scene editProductsScene2(int idProductsSql, String nameProductsSql, double priceProductsSql, int quantityProductsSql, String supplierProductsSql) {

        // Return button:
        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");

        returnBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getEditProductsScene1());
        });

        HBox returnBtnContainer = new HBox(10);
        returnBtnContainer.setAlignment(Pos.TOP_RIGHT);
        returnBtnContainer.setPadding(new Insets(10, 10, 10, 10));
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
        GridPane editProductsGrid = new GridPane();
        editProductsGrid.getStyleClass().add("editProductsGrid");

        editProductsGrid.setVgap(10);
        editProductsGrid.setHgap(10);

        Label idProducts = new Label("ID Number: " + idProductsSql );
        idProducts.setId("idProductsLabel");

        Label nameProducts = new Label("Name: ");
        TextField nameProductsInput = new TextField();
        nameProductsInput.setPromptText(nameProductsSql);
        nameProductsInput.setId("nameProductsInput");

        Label priceProducts = new Label("Price: ");
        TextField priceProductsInput = new TextField();
        priceProductsInput.setPromptText(String.valueOf(priceProductsSql));
        priceProductsInput.setId("priceProductsInput");

        Label quantityProducts = new Label("Quantity: ");
        TextField quantityProductsInput = new TextField();
        quantityProductsInput.setPromptText(String.valueOf(quantityProductsSql));
        quantityProductsInput.setId("quantityProductsInput");

        Label supplierProducts = new Label("Supplier: ");
        TextField supplierProductsInput = new TextField();
        supplierProductsInput.setPromptText(supplierProductsSql);
        supplierProductsInput.setId("supplierProductsInput");

        editProductsGrid.add(idProducts, 0, 0);
        editProductsGrid.add(nameProducts, 0, 1);
        editProductsGrid.add(nameProductsInput, 1, 1);
        editProductsGrid.add(priceProducts, 0, 2);
        editProductsGrid.add(priceProductsInput, 1, 2);
        editProductsGrid.add(quantityProducts, 0, 3);
        editProductsGrid.add(quantityProductsInput, 1, 3);
        editProductsGrid.add(supplierProducts, 0, 4);
        editProductsGrid.add(supplierProductsInput, 1, 4);

        editProductsGrid.setAlignment(Pos.CENTER);
        editProductsGrid.setPadding(new Insets(40, 0, 0, 0));

        Button submitEditedProductsBtn2 = new Button("Edit");
        submitEditedProductsBtn2.getStyleClass().add("submitEditedProductsBtn2");
        HBox editProductsContainer2 = new HBox(10);
        editProductsContainer2.getChildren().add(submitEditedProductsBtn2);
        editProductsContainer2.setAlignment(Pos.CENTER);
        editProductsContainer2.setPadding(new Insets(145, 10, 10, 10));

        submitEditedProductsBtn2.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            gestion.dao.ProductsQuery.editProducts(nameProductsInput, priceProductsInput, quantityProductsInput, supplierProductsInput);
            stage.setScene(SceneManager.getProductsHomeScene());
        });

        VBox editProducts2 = new VBox(returnBtnContainer, editTitle2, editTxtContainer2, editProductsGrid,editProductsContainer2);

        Scene editProductsScene2 = new Scene(editProducts2, 300, 600);
        editProductsScene2.getStylesheets().add("gestion/resources/products.css");
        return editProductsScene2;
    }

    //SHOW PRODUCTS SCENE
    public static Scene showProductsScene() {

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

        Label searchProducts = new Label("Search by ID: ");
        searchProducts.getStyleClass().add("searchProducts");
        TextField searchProductsInput = new TextField();
        searchProductsInput.getStyleClass().add("searchProductsInput");

        Button confirmBtn = new Button("Confirm");
        confirmBtn.getStyleClass().add("confirmBtn");

        HBox searchProductsContainer = new HBox();
        searchProductsContainer.getChildren().add(searchProducts);
        searchProductsContainer.getChildren().add(searchProductsInput);
        searchProductsContainer.getChildren().add(confirmBtn);
        searchProductsContainer.getStyleClass().add("searchProductsContainer");

        VBox showProducts = new VBox(returnBtnContainer, showTitle, sortFilterContainer, searchProductsContainer);

        Scene showProductsScene = new Scene(showProducts, 300, 600);
        showProductsScene.getStylesheets().add("gestion/resources/products.css");
        return showProductsScene;
    }
}
