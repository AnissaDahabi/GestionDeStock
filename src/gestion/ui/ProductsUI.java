package gestion.ui;

import gestion.dao.ProductsQuery;
import gestion.dao.SuppliersQuery;
import gestion.model.Products;
import gestion.model.Suppliers;
import gestion.service.ProductsService;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;


public class ProductsUI {

    public static Scene createContent() {

        // Return button:
        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");

        returnBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getHomeScene());
        });

        HBox returnBtnContainer = new HBox(10);
        returnBtnContainer.setAlignment(Pos.TOP_RIGHT);
        returnBtnContainer.setPadding(new Insets(10, 10, 10, 10));
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
        idProductsInput.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d{0,4}")) {
                return change;
            }
            return null;
        }));

        Label nameProducts = new Label("Name: ");
        TextField nameProductsInput = new TextField();
        nameProductsInput.setTextFormatter(new TextFormatter<>(change -> {
            String input = change.getControlNewText();
            if (input.isEmpty() || input.matches("[\\p{L}0-9\\s\\-']+")) {
                return change;
            }
            return null;
        }));

        Label priceProducts = new Label("Price: ");
        TextField priceProductsInput = new TextField();
        priceProductsInput.setTextFormatter(new TextFormatter<>(change -> {
            String input = change.getControlNewText();
            if (input.isEmpty() || input.matches("\\d+(\\.\\d{0,2})?")) {
                return change;
            }
            return null;
        }));

        Label quantityProducts = new Label("Quantity: ");
        TextField quantityProductsInput = new TextField();
        quantityProductsInput.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*")) {
                return change;
            }
            return null;
        }));

        Label comboLabel = new Label("Supplier ID: ");
        ComboBox<Suppliers> suppliersComboBox = new ComboBox<>(SuppliersQuery.getSuppliersID());
        suppliersComboBox.setId("suppliersComboBox");
        suppliersComboBox.setItems(SuppliersQuery.getSuppliersID());

        suppliersComboBox.setConverter(new StringConverter<Suppliers>() {
            @Override
            public String toString(Suppliers suppliers) {
                return suppliers != null ? String.valueOf(suppliers.getIdSupplier()) : "";
            }

            @Override
            public Suppliers fromString(String s) {
                return null;
            }
        });
        suppliersComboBox.setCellFactory(lv -> new ListCell<Suppliers>() {
            @Override
            protected void updateItem(Suppliers supplier, boolean empty) {
                super.updateItem(supplier, empty);
                setText(empty || supplier == null ? null : String.valueOf(supplier.getIdSupplier()));
            }
        });

        addProductsGrid.add(idProductsLabel, 0, 0);
        addProductsGrid.add(idProductsInput, 1, 0);
        addProductsGrid.add(nameProducts, 0, 1);
        addProductsGrid.add(nameProductsInput, 1, 1);
        addProductsGrid.add(priceProducts, 0, 2);
        addProductsGrid.add(priceProductsInput, 1, 2);
        addProductsGrid.add(quantityProducts, 0, 3);
        addProductsGrid.add(quantityProductsInput, 1, 3);
        addProductsGrid.add(comboLabel, 0, 4);
        addProductsGrid.add(suppliersComboBox, 1, 4);

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

                if (idProductsInput.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter an ID for the product");
                    //Alert's design
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/products.css");
                    alert.showAndWait();
                    return;
                }

                if (nameProductsInput.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a name for the product");
                    //Alert's design
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/products.css");
                    alert.showAndWait();
                    return;
                }

                if (priceProductsInput.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a price for the product");
                    //Alert's design
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/products.css");
                    alert.showAndWait();
                    return;
                }

                if (priceProductsInput.getText().equals("0")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("The price cannot be 0");
                    //Alert's design
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/products.css");
                    alert.showAndWait();
                    return;
                }

                if (quantityProductsInput.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a quantity for the product");
                    //Alert's design
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/products.css");
                    alert.showAndWait();
                    return;
                }

                int idProducts = Integer.parseInt(idProductsInput.getText());
                String name = nameProductsInput.getText();
                double price = Double.parseDouble(priceProductsInput.getText());
                int quantity = Integer.parseInt(quantityProductsInput.getText());
                Suppliers selectedSupplier = suppliersComboBox.getValue();

                if (selectedSupplier == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Please select a supplier");
                    //Alert's design
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/products.css");
                    alert.showAndWait();
                    return;
                }

                boolean success = ProductsService.addProducts(idProducts, name, price, quantity, selectedSupplier.getIdSupplier());

                if (success) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Product added successfully!");

                    //Alert's design
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/products.css");
                    alert.showAndWait();

                    idProductsInput.clear();
                    nameProductsInput.clear();
                    priceProductsInput.clear();
                    quantityProductsInput.clear();
                    suppliersComboBox.valueProperty().set(null);

                    Stage addProductsScene = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    addProductsScene.setScene(SceneManager.getProductsHomeScene());
                }

            } catch (Exception e) {

                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Something went wrong");
                //Alert's design
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setGraphic(null);
                alert.initStyle(StageStyle.UTILITY);
                alert.getDialogPane().getStylesheets().add("gestion/resources/products.css");
                alert.showAndWait();
            }
        });

        returnBtn.setOnAction(event -> {
            idProductsInput.clear();
            nameProductsInput.clear();
            priceProductsInput.clear();
            quantityProductsInput.clear();
            suppliersComboBox.valueProperty().set(null);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getProductsHomeScene());
        });

        VBox addProducts = new VBox(returnBtnContainer, addTitle, addTxtContainer, addProductsGrid, addProductsContainer);

        Scene addProductsScene = new Scene(addProducts, 300, 600);
        addProductsScene.getStylesheets().add("gestion/resources/products.css");
        return addProductsScene;
    }

    // DELETE PRODUCT SCENE
    public static Scene delProductsScene() {

        // Return button:
        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");

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

        //User input:
        Label idProductsLabel = new Label("ID Number: ");
        TextField idProductsInput = new TextField();
        HBox idProductsContainer = new HBox();
        idProductsContainer.getChildren().add(idProductsLabel);
        idProductsContainer.getChildren().add(idProductsInput);
        idProductsContainer.setId("idProductsContainer");
        idProductsContainer.setAlignment(Pos.CENTER);

        //ComboBox:
        Label comboLabel = new Label("ID number : ");
        ComboBox<Products> productsComboBox = new ComboBox<>(ProductsQuery.getProductsID());
        productsComboBox.setId("productsComboBox");
        productsComboBox.setItems(ProductsQuery.getProductsID());

        productsComboBox.setConverter(new StringConverter<Products>() {
            @Override
            public String toString(Products products) {
                return products != null ? String.valueOf(products.getIdProduct()) : "";
            }

            @Override
            public Products fromString(String s) {
                return null;
            }
        });
        productsComboBox.setCellFactory(lv -> new ListCell<Products>() {
            @Override
            protected void updateItem(Products product, boolean empty) {
                super.updateItem(product, empty);
                setText(empty || product == null ? null : String.valueOf(product.getIdProduct()));
            }
        });

        HBox boxProductsContainer = new HBox();
        boxProductsContainer.getStyleClass().add("boxProductsContainer");
        boxProductsContainer.getChildren().add(comboLabel);
        boxProductsContainer.getChildren().add(productsComboBox);
        boxProductsContainer.setAlignment(Pos.CENTER);
        boxProductsContainer.setSpacing(5);
        boxProductsContainer.setPadding(new Insets(20, 0, 0, 0));


        //Delete button:
        Button submitDeletedProductsBtn = new Button("Delete");
        submitDeletedProductsBtn.getStyleClass().add("submitDeletedProductsBtn");
        HBox delProductsContainer = new HBox(10);
        delProductsContainer.getChildren().add(submitDeletedProductsBtn);
        delProductsContainer.setAlignment(Pos.CENTER);
        delProductsContainer.setPadding(new Insets(250, 10, 10, 10));

        submitDeletedProductsBtn.setOnAction(event -> {

            Products selectedProduct = productsComboBox.getValue();

            if (selectedProduct != null) {

                boolean deleted = ProductsService.delProducts(selectedProduct.getIdProduct());

                if (deleted) {
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle(null);
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Product deleted successfully");
                    productsComboBox.setItems(ProductsQuery.getProductsID());
                    //Alert's design
                    DialogPane dialogPane = successAlert.getDialogPane();
                    dialogPane.setGraphic(null);
                    successAlert.initStyle(StageStyle.UTILITY);
                    successAlert.getDialogPane().getStylesheets().add("gestion/resources/products.css");
                    successAlert.showAndWait();
                }
            } else if (selectedProduct == null){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Please choose a product to delete");
                    //Alert's design
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/products.css");
                    alert.showAndWait();

            } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle(null);
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Something wrong happened..");
                    //Alert's design
                    DialogPane dialogPane = errorAlert.getDialogPane();
                    dialogPane.setGraphic(null);
                    errorAlert.initStyle(StageStyle.UTILITY);
                    errorAlert.getDialogPane().getStylesheets().add("gestion/resources/products.css");
                    errorAlert.showAndWait();
            }
        });

        returnBtn.setOnAction(event -> {
            productsComboBox.valueProperty().set(null);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getProductsHomeScene());
        });

        VBox delProducts1 = new VBox(returnBtnContainer, delTitle1, delTxtContainer1, boxProductsContainer, delProductsContainer);

        Scene delProductsScene1 = new Scene(delProducts1, 300, 600);
        delProductsScene1.getStylesheets().add("gestion/resources/products.css");
        return delProductsScene1;
    }


    // EDIT PRODUCT SCENE 1
    public static Scene editProductsScene1() {

        // Return button:
        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");

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
        Label comboLabel = new Label("ID Number: ");

        ComboBox<Products> productsComboBox = new ComboBox<>(ProductsQuery.getProductsID());
        productsComboBox.setId("productsComboBox");
        productsComboBox.setItems(ProductsQuery.getProductsID());

        productsComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Products products) {
                return products != null ? String.valueOf(products.getIdProduct()) : "";
            }

            @Override
            public Products fromString(String s) {
                return null;
            }
        });
        productsComboBox.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Products product, boolean empty) {
                super.updateItem(product, empty);
                setText(empty || product == null ? null : String.valueOf(product.getIdProduct()));
            }
        });

        HBox boxProductsContainer = new HBox();
        boxProductsContainer.getStyleClass().add("boxProductsContainer");
        boxProductsContainer.getChildren().add(comboLabel);
        boxProductsContainer.getChildren().add(productsComboBox);
        boxProductsContainer.setAlignment(Pos.CENTER);
        boxProductsContainer.setSpacing(5);
        boxProductsContainer.setPadding(new Insets(20, 0, 0, 0));

        //Next button:
        Button submitEditedProductsBtn1 = new Button("Next");
        submitEditedProductsBtn1.getStyleClass().add("submitEditedProductsBtn1");
        HBox editProductsContainer1 = new HBox(10);
        editProductsContainer1.getChildren().add(submitEditedProductsBtn1);
        editProductsContainer1.setAlignment(Pos.CENTER);
        editProductsContainer1.setPadding(new Insets(270, 10, 10, 10));

        submitEditedProductsBtn1.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Products selectedProduct = productsComboBox.getValue();
            if (selectedProduct != null) {

                stage.setScene(SceneManager.getEditProductsScene2(
                        selectedProduct.getIdProduct(),
                        selectedProduct.getNameProduct(),
                        selectedProduct.getPriceProduct(),
                        selectedProduct.getQuantityProduct(),
                        selectedProduct.getSupplierId()
                ));

            } else if (selectedProduct == null){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Please choose a product to edit");
                //Alert's design
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setGraphic(null);
                alert.initStyle(StageStyle.UTILITY);
                alert.getDialogPane().getStylesheets().add("gestion/resources/products.css");
                alert.showAndWait();
            }
        });

        returnBtn.setOnAction(event -> {
            productsComboBox.valueProperty().set(null);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getProductsHomeScene());

        });

        VBox editProducts1 = new VBox(returnBtnContainer, editTitle1, editTxtContainer1, boxProductsContainer, editProductsContainer1);

        Scene editProductsScene1 = new Scene(editProducts1, 300, 600);
        editProductsScene1.getStylesheets().add("gestion/resources/products.css");
        return editProductsScene1;
    }

    // EDIT PRODUCT SCENE 2
    public static Scene editProductsScene2(int idProductsSql, String nameProductsSql, double priceProductsSql, int quantityProductsSql, int supplierIdSql) {

        // Return button:
        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");

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

        Label idProducts = new Label("ID Number: ");
        Label idProductsInput = new Label(" " + idProductsSql );
        idProducts.setId("idProductsLabel");

        Label nameProducts = new Label("Name: ");
        TextField nameProductsInput = new TextField();
        nameProductsInput.setText(nameProductsSql);
        nameProductsInput.setId("nameProductsInput");
        nameProductsInput.setTextFormatter(new TextFormatter<>(change -> {
            String input = change.getControlNewText();
            if (input.isEmpty() || input.matches("[\\p{L}0-9\\s\\-']+")) {
                return change;
            }
            return null;
        }));

        Label priceProducts = new Label("Price: ");
        TextField priceProductsInput = new TextField();
        priceProductsInput.setText(String.valueOf(priceProductsSql));
        priceProductsInput.setId("priceProductsInput");
        priceProductsInput.setTextFormatter(new TextFormatter<>(change -> {
            String input = change.getControlNewText();
            if (input.isEmpty() || input.matches("\\d+(\\.\\d{0,2})?")) {
                return change;
            }
            return null;
        }));

        Label quantityProducts = new Label("Quantity: ");
        TextField quantityProductsInput = new TextField();
        quantityProductsInput.setText(String.valueOf(quantityProductsSql));
        quantityProductsInput.setId("quantityProductsInput");
        quantityProductsInput.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*")) {
                return change;
            }
            return null;
        }));

        Label supplierId = new Label("Supplier: ");

        ComboBox<Suppliers> supplierIdInput = new ComboBox<>(SuppliersQuery.getSuppliersID());
        supplierIdInput.setId("supplierProductsInput");
        supplierIdInput.setItems(SuppliersQuery.getSuppliersID());

        supplierIdInput.setConverter(new StringConverter<>() {
            @Override
            public String toString(Suppliers suppliers) {
                return suppliers != null ? String.valueOf(suppliers.getIdSupplier()) : "";
            }

            @Override
            public Suppliers fromString(String s) {
                return null;
            }
        });

        supplierIdInput.setCellFactory(lv -> new ListCell<Suppliers>() {
            @Override
            protected void updateItem(Suppliers supplier, boolean empty) {
                super.updateItem(supplier, empty);
                setText(empty || supplier == null ? null : String.valueOf(supplier.getIdSupplier()));
            }
        });

        for (Suppliers supplier : SuppliersQuery.getSuppliersID()) {
            if (supplier.getIdSupplier() == supplierIdSql) {
                supplierIdInput.setValue(supplier);
                break;
            }
        }

        editProductsGrid.add(idProducts, 0, 0);
        editProductsGrid.add(idProductsInput, 1, 0);
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

            try {

                if (nameProductsInput.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a name for the product");
                    //Alert's design
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/products.css");
                    alert.showAndWait();
                    return;
                }

                if (priceProductsInput.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a price for the product");
                    //Alert's design
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/products.css");
                    alert.showAndWait();
                    return;
                }

                if (priceProductsInput.getText().equals("0")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("The price cannot be 0");
                    //Alert's design
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/products.css");
                    alert.showAndWait();
                    return;
                }

                if (quantityProductsInput.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a quantity for the product");
                    //Alert's design
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/products.css");
                    alert.showAndWait();
                    return;
                }

                if (supplierIdInput.getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Please select a supplier");
                    //Alert's design
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/products.css");
                    alert.showAndWait();
                    return;
                }

                ProductsQuery.editProducts(idProductsSql, nameProductsInput, priceProductsInput, quantityProductsInput, supplierIdInput);


                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(SceneManager.getProductsHomeScene());

                } catch (Exception e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Something went wrong");
                    //Alert's design
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/products.css");
                    alert.showAndWait();
                }
        });

        returnBtn.setOnAction(event1 -> {
            nameProductsInput.clear();
            priceProductsInput.clear();
            quantityProductsInput.clear();
            supplierIdInput.valueProperty().set(null);
            Stage stage = (Stage) ((Node) event1.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getEditProductsScene1());

        });

        VBox editProducts2 = new VBox(returnBtnContainer, editTitle2, editTxtContainer2, editProductsGrid, editProductsContainer2);

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

        Label searchProducts = new Label("Search:");
        searchProducts.getStyleClass().add("searchProducts");
        TextField searchProductsInput = new TextField();
        searchProductsInput.setPromptText("Search...");
        searchProductsInput.getStyleClass().add("searchProductsInput");

        HBox searchProductsContainer = new HBox();
        searchProductsContainer.getChildren().add(searchProducts);
        searchProductsContainer.getChildren().add(searchProductsInput);
        searchProductsContainer.getStyleClass().add("searchProductsContainer");
        searchProductsContainer.setSpacing(10);

        // Tableau :
        TableView productsTable = new TableView<Products>();
        productsTable.setEditable(true);
        productsTable.setId("productsTable");

        TableColumn<Products, Integer> idProductColumn = new TableColumn<>("ID");
        idProductColumn.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
        idProductColumn.setResizable(false);
        idProductColumn.setReorderable(false);

        TableColumn<Products, String> nameProductColumn = new TableColumn<>("Name");
        nameProductColumn.setCellValueFactory(new PropertyValueFactory<>("nameProduct"));
        nameProductColumn.setResizable(false);
        nameProductColumn.setReorderable(false);

        TableColumn<Products, Double> priceProductColumn = new TableColumn<>("Price");
        priceProductColumn.setCellValueFactory(new PropertyValueFactory<>("priceProduct"));
        priceProductColumn.setResizable(false);
        priceProductColumn.setReorderable(false);

        TableColumn<Products, Integer> quantityProductColumn = new TableColumn<>("Stock");
        quantityProductColumn.setCellValueFactory(new PropertyValueFactory<>("quantityProduct"));
        quantityProductColumn.setResizable(false);
        quantityProductColumn.setReorderable(false);

        TableColumn<Products, String> supplierProductColumn = new TableColumn<>("Supplier");
        supplierProductColumn.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        supplierProductColumn.setResizable(false);
        supplierProductColumn.setReorderable(false);

        idProductColumn.setPrefWidth(40);
        nameProductColumn.setPrefWidth(85);
        priceProductColumn.setPrefWidth(40);
        quantityProductColumn.setPrefWidth(45);
        supplierProductColumn.setPrefWidth(52);

        productsTable.getColumns().addAll(idProductColumn, nameProductColumn, priceProductColumn, quantityProductColumn, supplierProductColumn);

        VBox productsTableContainer = new VBox();
        productsTableContainer.setPadding(new Insets(20, 10, 10, 10));
        productsTableContainer.getChildren().add(productsTable);

        // Search filter:
        ObservableList<Products> data = ProductsQuery.showProducts(productsTable);

        FilteredList<Products> filteredData = new FilteredList<>(data, p -> true);

        searchProductsInput.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(product -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(product.getIdProduct()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (product.getNameProduct().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(product.getPriceProduct()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(product.getQuantityProduct()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(product.getSupplierId()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }

                return false;
            });
        });

        SortedList<Products> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(productsTable.comparatorProperty());
        productsTable.setItems(sortedData);

        VBox showProducts = new VBox(returnBtnContainer, showTitle, searchProductsContainer, productsTableContainer);

        Scene showProductsScene = new Scene(showProducts, 300, 600);
        showProductsScene.getStylesheets().add("gestion/resources/products.css");
        return showProductsScene;
    }
}
