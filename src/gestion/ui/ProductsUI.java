package gestion.ui;

import gestion.dao.ProductsQuery;
import gestion.model.Products;
import gestion.service.ProductsService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
            stage.setScene(SceneManager.getDelProductsScene());
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

        Label idProductsLabel = new Label("ID Number: ");
        TextField idProductsInput = new TextField();

        Label nameProducts = new Label("Name: ");
        TextField nameProductsInput = new TextField();

        Label priceProducts = new Label("Price: ");
        TextField priceProductsInput = new TextField();

        Label quantityProducts = new Label("Quantity: ");
        TextField quantityProductsInput = new TextField();

        Label supplierId = new Label("Supplier: ");
        TextField supplierIdInput = new TextField();


        addProductsGrid.add(idProductsLabel, 0, 0);
        addProductsGrid.add(idProductsInput, 1, 0);
        addProductsGrid.add(nameProducts, 0, 1);
        addProductsGrid.add(nameProductsInput, 1, 1);
        addProductsGrid.add(priceProducts, 0, 2);
        addProductsGrid.add(priceProductsInput, 1, 2);
        addProductsGrid.add(quantityProducts, 0, 3);
        addProductsGrid.add(quantityProductsInput, 1, 3);
        addProductsGrid.add(supplierId, 0, 4);
        addProductsGrid.add(supplierIdInput, 1, 4);

        addProductsGrid.setAlignment(Pos.CENTER);

        addProductsGrid.setPadding(new Insets(40, 0, 0, 0));

        Button submitAddedProductsBtn = new Button("Add");
        submitAddedProductsBtn.getStyleClass().add("submitAddedProductsBtn");
        HBox addProductsContainer = new HBox(10);
        addProductsContainer.getChildren().add(submitAddedProductsBtn);
        addProductsContainer.setAlignment(Pos.CENTER);
        addProductsContainer.setPadding(new Insets(100, 10, 10, 10));

        submitAddedProductsBtn.setOnAction(event -> {
            try {
                int idProducts = Integer.parseInt(idProductsInput.getText());
                String name = nameProductsInput.getText();
                double price = Double.parseDouble(priceProductsInput.getText());
                int quantity = Integer.parseInt(quantityProductsInput.getText());
                int supplier = Integer.parseInt(supplierIdInput.getText());

                boolean success = ProductsService.addProducts(idProducts, name, price, quantity, supplier);

                if (success) {

                    AlertsProducts.showSuccessAddProduct("Product added successfully");

                    idProductsInput.clear();
                    nameProductsInput.clear();
                    priceProductsInput.clear();
                    quantityProductsInput.clear();
                    supplierIdInput.clear();

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(SceneManager.getProductsHomeScene());
                } else {
                    AlertsProducts.showErrorAddProduct("Something went wrong");
                }
            } catch (Exception e) {
                AlertsProducts.showErrorAddProduct("Something went wrong");
            }

        });

        VBox addProducts = new VBox(returnBtnContainer, addTitle, addTxtContainer, addProductsGrid,addProductsContainer);

        Scene addProductsScene = new Scene(addProducts, 300, 600);
        addProductsScene.getStylesheets().add("gestion/resources/products.css");
        return addProductsScene;
    }

    // DELETE PRODUCT SCENE
    public static Scene delProductsScene() {

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
        Button submitDeletedProductsBtn = new Button("Next");
        submitDeletedProductsBtn.getStyleClass().add("submitDeletedProductsBtn");
        HBox delProductsContainer = new HBox(10);
        delProductsContainer.getChildren().add(submitDeletedProductsBtn);
        delProductsContainer.setAlignment(Pos.CENTER);
        delProductsContainer.setPadding(new Insets(250, 10, 10, 10));

        submitDeletedProductsBtn.setOnAction(event -> {
            try {
                int idProducts = Integer.parseInt(idProductsInput.getText());

                Products products = ProductsService.getProductById(idProducts);

                if (products == null) {
                    AlertsProducts.showErrorDelProduct2("No product found with this ID number: " + idProducts);
                    return;
                }

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete " + products.getNameProduct() + " from the database ?");

                ButtonType yes = new ButtonType("Yes");
                ButtonType no = new ButtonType("No");
                alert.getButtonTypes().setAll(yes, no);

                alert.showAndWait().ifPresent(button -> {
                    if (button == yes) {
                        boolean deleted = ProductsService.delProducts(idProducts);
                        if (deleted) {
                            AlertsProducts.showSuccessDelProduct("Product deleted successfully!");
                            idProductsInput.clear();
                        }
                    }
                });
            } catch (NumberFormatException e) {
                AlertsProducts.showErrorDelProduct("Please enter a valid ID");
            }
        });

        VBox delProducts1 = new VBox(returnBtnContainer, delTitle1, delTxtContainer1, idProductsContainer, delProductsContainer);

        Scene delProductsScene1 = new Scene(delProducts1, 300, 600);
        delProductsScene1.getStylesheets().add("gestion/resources/products.css");
        return delProductsScene1;
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
            ProductsQuery.showEditedProducts(idProductsInput, stage);
            idProductsInput.clear();
        });

        VBox editProducts1 = new VBox(returnBtnContainer, editTitle1, editTxtContainer1, idProductsContainer, editProductsContainer1);

        Scene editProductsScene1 = new Scene(editProducts1, 300, 600);
        editProductsScene1.getStylesheets().add("gestion/resources/products.css");
        return editProductsScene1;
    }

    // EDIT PRODUCT SCENE 2
    public static Scene editProductsScene2(int idProductsSql, String nameProductsSql, double priceProductsSql, int quantityProductsSql, int supplierIdSql) {

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
        nameProductsInput.setText(nameProductsSql);
        nameProductsInput.setId("nameProductsInput");

        Label priceProducts = new Label("Price: ");
        TextField priceProductsInput = new TextField();
        priceProductsInput.setText(String.valueOf(priceProductsSql));
        priceProductsInput.setId("priceProductsInput");

        Label quantityProducts = new Label("Quantity: ");
        TextField quantityProductsInput = new TextField();
        quantityProductsInput.setText(String.valueOf(quantityProductsSql));
        quantityProductsInput.setId("quantityProductsInput");

        Label supplierId = new Label("Supplier: ");
        TextField supplierIdInput = new TextField();
        supplierIdInput.setText(String.valueOf(supplierIdSql));
        supplierIdInput.setId("supplierProductsInput");

        editProductsGrid.add(idProducts, 0, 0);
        editProductsGrid.add(nameProducts, 0, 1);
        editProductsGrid.add(nameProductsInput, 1, 1);
        editProductsGrid.add(priceProducts, 0, 2);
        editProductsGrid.add(priceProductsInput, 1, 2);
        editProductsGrid.add(quantityProducts, 0, 3);
        editProductsGrid.add(quantityProductsInput, 1, 3);
        editProductsGrid.add(supplierId, 0, 4);
        editProductsGrid.add(supplierIdInput, 1, 4);

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
            ProductsQuery.editProducts(nameProductsInput, priceProductsInput, quantityProductsInput, supplierIdInput);
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
        searchProductsContainer.setSpacing(10);


        TableView productsTable = new TableView<Products>();
        productsTable.setEditable(true);
        productsTable.setId("productsTable");

        TableColumn idProductColumn = new TableColumn<Products, Integer>("ID");
        idProductColumn.setCellValueFactory(new PropertyValueFactory<Products, Integer>("idProduct"));


        TableColumn nameProductColumn = new TableColumn<Products, String>("Name");
        nameProductColumn.setCellValueFactory(new PropertyValueFactory<Products, String>("nameProduct"));


        TableColumn priceProductColumn = new TableColumn<Products, Double>("Price");
        priceProductColumn.setCellValueFactory(new PropertyValueFactory<Products, Double>("priceProduct"));


        TableColumn quantityProductColumn = new TableColumn<Products, Integer>("Quantity");
        quantityProductColumn.setCellValueFactory(new PropertyValueFactory<Products, Integer>("quantityProduct"));


        TableColumn supplierProductColumn = new TableColumn<Products, String>("Supplier");
        supplierProductColumn.setCellValueFactory(new PropertyValueFactory<Products, String>("supplierId"));

        productsTable.getColumns().addAll(idProductColumn, nameProductColumn, priceProductColumn, quantityProductColumn, supplierProductColumn);

        productsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        ProductsQuery.showProducts(productsTable);

        VBox productsTableContainer = new VBox();
        productsTableContainer.setPadding(new Insets(20, 20, 20, 20));
        productsTableContainer.getChildren().add(productsTable);

        VBox showProducts = new VBox(returnBtnContainer, showTitle, sortFilterContainer, searchProductsContainer, productsTableContainer);

        Scene showProductsScene = new Scene(showProducts, 300, 600);
        showProductsScene.getStylesheets().add("gestion/resources/products.css");
        return showProductsScene;
    }
}
