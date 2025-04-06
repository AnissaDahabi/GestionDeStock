package gestion.ui;

import gestion.dao.SalesQuery;
import gestion.dao.ProductsQuery;
import gestion.model.Products;
import gestion.model.Sales;
import gestion.service.SalesService;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;


import java.time.LocalDate;

public class SalesUI {

    public static Scene createContent() {


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


        HBox salesTitle = new HBox(new Label("Sales"));
        salesTitle.getStyleClass().add("salesTitle");
        salesTitle.setAlignment(Pos.CENTER);


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

        showSalesBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getShowSalesScene1());
        });


        VBox homeSales = new VBox(returnBtnContainer, salesTitle, salesBtnContainer);

        Scene salesScene = new Scene(homeSales, 300, 600);
        salesScene.getStylesheets().add("gestion/resources/sales.css");


        return salesScene;
    }

    public static Scene addSalesScene() {


        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");

        HBox returnBtnContainer = new HBox(10);
        returnBtnContainer.setAlignment(Pos.TOP_RIGHT);
        returnBtnContainer.setPadding(new Insets(10, 10, 10, 10));
        returnBtnContainer.getChildren().add(returnBtn);


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

        Label idSalesLabel = new Label("Sale ID: ");
        TextField idSalesInput = new TextField();
        idSalesInput.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*")){
                return change;
            }
            return null;
        }));

        Label quantitySales = new Label("Quantity: ");
        TextField quantitySalesInput = new TextField();
        quantitySalesInput.setTextFormatter(new TextFormatter<>(change ->{
            if (change.getControlNewText().matches("\\d*")){
                return change;
            }
            return null;
        }));


        Label dateSales = new Label("Date: ");
        DatePicker dateSalesInput = new DatePicker();
        dateSalesInput.setEditable(false);


        Label comboLabel1 = new Label("Supplier ID: ");
        TextField comboLabelSupplier = new TextField("");
        comboLabelSupplier.setEditable(false);


        Label comboLabel2 = new Label("Product ID: ");
        ComboBox<Products> productsComboBox = new ComboBox<>(ProductsQuery.getProductsID());
        productsComboBox.setId("productsComboBox");
        productsComboBox.setItems(ProductsQuery.getProductsID());
        productsComboBox.valueProperty().addListener((obs, oldProduct, newProduct) -> {
            if (newProduct != null) {
                int supplierID = newProduct.getSupplierId();
                comboLabelSupplier.setText(String.valueOf(supplierID));
            } else {
                comboLabelSupplier.setText("");
            }
        });

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


        addSalesGrid.add(idSalesLabel, 0, 0);
        addSalesGrid.add(idSalesInput, 1, 0);
        addSalesGrid.add(comboLabel2, 0, 1);
        addSalesGrid.add(productsComboBox, 1, 1);
        addSalesGrid.add(comboLabel1, 0, 2);
        addSalesGrid.add(comboLabelSupplier, 1, 2);
        addSalesGrid.add(quantitySales, 0, 3);
        addSalesGrid.add(quantitySalesInput, 1, 3);
        addSalesGrid.add(dateSales, 0, 4);
        addSalesGrid.add(dateSalesInput, 1, 4);

        addSalesGrid.setAlignment(Pos.CENTER);

        addSalesGrid.setPadding(new Insets(40, 0, 0, 0));

        Button submitAddedSalesBtn = new Button("Add");
        submitAddedSalesBtn.getStyleClass().add("submitAddedSalesBtn");
        HBox addSalesContainer = new HBox(10);
        addSalesContainer.getChildren().add(submitAddedSalesBtn);
        addSalesContainer.setAlignment(Pos.CENTER);
        addSalesContainer.setPadding(new Insets(100, 10, 10, 10));

        submitAddedSalesBtn.setOnAction(event -> {
                try {

                    if (idSalesInput.getText().isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle(null);
                        alert.setHeaderText(null);
                        alert.setContentText("Please enter an ID for the sale");

                        DialogPane dialogPane = alert.getDialogPane();
                        dialogPane.setGraphic(null);
                        alert.initStyle(StageStyle.UTILITY);
                        alert.getDialogPane().getStylesheets().add("gestion/resources/sales.css");
                        alert.showAndWait();
                        return;
                    }


                    if (quantitySalesInput.getText().isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle(null);
                        alert.setHeaderText(null);
                        alert.setContentText("Please enter a quantity of products for the sale");
                        DialogPane dialogPane = alert.getDialogPane();
                        dialogPane.setGraphic(null);
                        alert.initStyle(StageStyle.UTILITY);
                        alert.getDialogPane().getStylesheets().add("gestion/resources/sales.css");
                        alert.showAndWait();
                        return;
                    }

                    if (quantitySalesInput.getText().equals("0")) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle(null);
                        alert.setHeaderText(null);
                        alert.setContentText("The quantity cannot be 0");
                        DialogPane dialogPane = alert.getDialogPane();
                        dialogPane.setGraphic(null);
                        alert.initStyle(StageStyle.UTILITY);
                        alert.getDialogPane().getStylesheets().add("gestion/resources/sales.css");
                        alert.showAndWait();
                        return;
                    }

                    int idSales = Integer.parseInt(idSalesInput.getText());
                    Products selectedProduct = productsComboBox.getValue();
                    String selectedSupplier = comboLabelSupplier.getText();
                    int quantity = Integer.parseInt(quantitySalesInput.getText());
                    LocalDate selectedDate = dateSalesInput.getValue();

                    if (selectedSupplier == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle(null);
                        alert.setHeaderText(null);
                        alert.setContentText("Please select a supplier");
                        DialogPane dialogPane = alert.getDialogPane();
                        dialogPane.setGraphic(null);
                        alert.initStyle(StageStyle.UTILITY);
                        alert.getDialogPane().getStylesheets().add("gestion/resources/sales.css");
                        alert.showAndWait();
                        return;
                    }

                    if (selectedProduct == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle(null);
                        alert.setHeaderText(null);
                        alert.setContentText("Please select a supplier");
                        DialogPane dialogPane = alert.getDialogPane();
                        dialogPane.setGraphic(null);
                        alert.initStyle(StageStyle.UTILITY);
                        alert.getDialogPane().getStylesheets().add("gestion/resources/sales.css");
                        alert.showAndWait();
                        return;
                    }

                    if (selectedDate == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle(null);
                        alert.setHeaderText(null);
                        alert.setContentText("Please select a date");
                        DialogPane dialogPane = alert.getDialogPane();
                        dialogPane.setGraphic(null);
                        alert.initStyle(StageStyle.UTILITY);
                        alert.getDialogPane().getStylesheets().add("gestion/resources/sales.css");
                        alert.showAndWait();
                        return;
                    }

                    double price = selectedProduct.getPriceProduct() * quantity;
                    boolean success = SalesService.addSales(idSales, selectedProduct.getIdProduct(), Integer.parseInt(selectedSupplier), quantity, price, String.valueOf(selectedDate));

                    if (success) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle(null);
                        alert.setHeaderText(null);
                        alert.setContentText("Sale added successfully");

                        DialogPane dialogPane = alert.getDialogPane();
                        dialogPane.setGraphic(null);
                        alert.initStyle(StageStyle.UTILITY);
                        alert.getDialogPane().getStylesheets().add("gestion/resources/sales.css");
                        alert.showAndWait();


                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(SceneManager.getSalesHomeScene());
                    }

                } catch (Exception e) {
                        e.printStackTrace();
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle(null);
                        alert.setHeaderText(null);
                        alert.setContentText("Something went wrong");
                        DialogPane dialogPane = alert.getDialogPane();
                        dialogPane.setGraphic(null);
                        alert.initStyle(StageStyle.UTILITY);
                        alert.getDialogPane().getStylesheets().add("gestion/resources/sales.css");
                        alert.showAndWait();
                }
        });

        returnBtn.setOnAction(event -> {
            idSalesInput.clear();
            productsComboBox.valueProperty().set(null);
            comboLabelSupplier.setText("");
            quantitySalesInput.clear();
            dateSalesInput.setValue(null);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getSalesHomeScene());
        });

        VBox addSales = new VBox(returnBtnContainer, addTitle, addTxtContainer, addSalesGrid, addSalesContainer);

        Scene addSalesScene = new Scene(addSales, 300, 600);
        addSalesScene.getStylesheets().add("gestion/resources/sales.css");

        return addSalesScene;
    }

    public static Scene delSalesScene1() {

        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");

        HBox returnBtnContainer = new HBox(10);
        returnBtnContainer.setAlignment(Pos.TOP_RIGHT);
        returnBtnContainer.setPadding(new Insets(10, 10, 10, 10));
        returnBtnContainer.getChildren().add(returnBtn);


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


        Label idSalesLabel = new Label("ID Number: ");
        TextField idSalesInput = new TextField();
        HBox idSalesContainer = new HBox();
        idSalesContainer.getChildren().add(idSalesLabel);
        idSalesContainer.getChildren().add(idSalesInput);
        idSalesContainer.setId("idSalesContainer");
        idSalesContainer.setAlignment(Pos.CENTER);


        Label comboLabel = new Label("ID number : ");
        ComboBox<Sales> salesComboBox = new ComboBox<>(SalesQuery.getSalesById());

        salesComboBox.setId("salesComboBox");
        salesComboBox.setItems(SalesQuery.getSalesById());

        salesComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Sales sales) {
                return sales != null ? String.valueOf(sales.getIdSales()) : "";
            }

            @Override
            public Sales fromString(String s) {
                return null;
            }
        });
        salesComboBox.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Sales sales, boolean empty) {
                super.updateItem(sales, empty);
                setText(empty || sales == null ? null : String.valueOf(sales.getIdSales()));
            }
        });

        HBox boxSuppliersContainer = new HBox();
        boxSuppliersContainer.getStyleClass().add("boxSuppliersContainer");
        boxSuppliersContainer.getChildren().add(comboLabel);
        boxSuppliersContainer.getChildren().add(salesComboBox);
        boxSuppliersContainer.setAlignment(Pos.CENTER);
        boxSuppliersContainer.setSpacing(5);
        boxSuppliersContainer.setPadding(new Insets(20, 0, 0, 0));


        Button submitDeletedSalesBtn1 = new Button("Delete");
        submitDeletedSalesBtn1.getStyleClass().add("submitDeletedSuppliersBtn1");
        HBox delSalesContainer1 = new HBox(10);
        delSalesContainer1.getChildren().add(submitDeletedSalesBtn1);
        delSalesContainer1.setAlignment(Pos.CENTER);
        delSalesContainer1.setPadding(new Insets(250, 10, 10, 10));

        submitDeletedSalesBtn1.setOnAction(event -> {

            Sales selectedSale = salesComboBox.getValue();

            if (selectedSale != null) {

                boolean deleted = SalesService.delSales(selectedSale.getIdSales());

                if (deleted) {
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle(null);
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Sale deleted successfully");
                    salesComboBox.setItems(SalesQuery.getSalesById());

                    DialogPane dialogPane = successAlert.getDialogPane();
                    dialogPane.setGraphic(null);
                    successAlert.initStyle(StageStyle.UTILITY);
                    successAlert.getDialogPane().getStylesheets().add("gestion/resources/sales.css");
                    successAlert.showAndWait();
                }
            } else if (selectedSale == null){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Please choose a sale to delete");

                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setGraphic(null);
                alert.initStyle(StageStyle.UTILITY);
                alert.getDialogPane().getStylesheets().add("gestion/resources/sales.css");
                alert.showAndWait();

            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle(null);
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Something wrong happened..");

                DialogPane dialogPane = errorAlert.getDialogPane();
                dialogPane.setGraphic(null);
                errorAlert.initStyle(StageStyle.UTILITY);
                errorAlert.getDialogPane().getStylesheets().add("gestion/resources/products.css");
                errorAlert.showAndWait();
            }
        });

        returnBtn.setOnAction(event -> {
            salesComboBox.valueProperty().set(null);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getSalesHomeScene());
        });

        VBox delSales1 = new VBox(returnBtnContainer, delTitle1, delTxtContainer1, boxSuppliersContainer, delSalesContainer1);

        Scene delSalesScene1 = new Scene(delSales1, 300, 600);
        delSalesScene1.getStylesheets().add("gestion/resources/suppliers.css");
        return delSalesScene1;
    }


    public static Scene editSalesScene1() {


        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");

        HBox returnBtnContainer = new HBox(10);
        returnBtnContainer.setAlignment(Pos.TOP_RIGHT);
        returnBtnContainer.setPadding(new Insets(10, 10, 10, 10));
        returnBtnContainer.getChildren().add(returnBtn);


        VBox editTitle1 = new VBox(new Label("Edit sales"));
        editTitle1.setAlignment(Pos.CENTER);
        editTitle1.getStyleClass().add("editTitle1");

        Text editTxt1 = new Text();
        editTxt1.setText("Please select the ID number of the sale you want to edit");
        editTxt1.setId("editTxt1");
        editTxt1.setWrappingWidth(280);
        HBox editTxtContainer1 = new HBox();
        editTxtContainer1.getChildren().add(editTxt1);
        editTxtContainer1.setId("delTxtContainer1");
        editTxtContainer1.setAlignment(Pos.CENTER);


        GridPane editSalesGrid = new GridPane();
        editSalesGrid.getStyleClass().add("editSalesGrid");

        editSalesGrid.setVgap(10);
        editSalesGrid.setHgap(10);

        Label comboLabel = new Label("ID Number: ");

        ComboBox<Sales>salesComboBox = new ComboBox<>(SalesQuery.getSalesById());
        salesComboBox.setId("salesComboBox");
        salesComboBox.setItems(SalesQuery.getSalesById());

        salesComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Sales sales) {
                return sales != null ? String.valueOf(sales.getIdSales()) : "";
            }
            @Override
            public Sales fromString(String s) {
                return null;
            }
        });
        salesComboBox.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Sales sales, boolean empty) {
                super.updateItem(sales, empty);
                setText(empty || sales == null ? null : String.valueOf(sales.getIdSales()));
            }
        });

        HBox editSalesContainer = new HBox();
        editSalesContainer.getStyleClass().add("editSalesContainer");
        editSalesContainer.getChildren().add(comboLabel);
        editSalesContainer.getChildren().add(salesComboBox);
        editSalesContainer.setAlignment(Pos.CENTER);
        editSalesContainer.setSpacing(5);
        editSalesContainer.setPadding(new Insets(20, 0, 0, 0));


        Button submitEditedSalesBtn1 = new Button("Next");
        submitEditedSalesBtn1.getStyleClass().add("submitEditedSalesBtn1");
        HBox editSalesContainer1 = new HBox(10);
        editSalesContainer1.getChildren().add(submitEditedSalesBtn1);
        editSalesContainer1.setAlignment(Pos.CENTER);
        editSalesContainer1.setPadding(new Insets(270, 10, 10, 10));

        submitEditedSalesBtn1.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Sales selectedSale = salesComboBox.getValue();
             if (selectedSale != null) {

                 stage.setScene(SceneManager.getEditSalesScene2(
                         selectedSale.getIdSales(),
                         selectedSale.getIdProduct(),
                         selectedSale.getIdSuppliers(),
                         selectedSale.getPriceSales(),
                         selectedSale.getQuantitySales(),
                         selectedSale.getDateSales()
                 ));

             } else if (selectedSale == null){
                 Alert alert = new Alert(Alert.AlertType.WARNING);
                 alert.setTitle(null);
                 alert.setHeaderText(null);
                 alert.setContentText("Please choose a sale to edit");

                 DialogPane dialogPane = alert.getDialogPane();
                 dialogPane.setGraphic(null);
                 alert.initStyle(StageStyle.UTILITY);
                 alert.getDialogPane().getStylesheets().add("gestion/resources/sales.css");
                 alert.showAndWait();
             }
        });

        returnBtn.setOnAction(event -> {
            salesComboBox.valueProperty().set(null);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getSalesHomeScene());
        });

        VBox editSales1 = new VBox(returnBtnContainer, editTitle1, editTxtContainer1, editSalesContainer, editSalesContainer1);

        Scene editSalesScene1 = new Scene(editSales1, 300, 600);
        editSalesScene1.getStylesheets().add("gestion/resources/sales.css");
        return editSalesScene1;
    }

    public static Scene editSalesScene2(int idSalesSql, int idProductSql, int idSupplierSql, double priceSalesSql, int quantitySalesSql, String dateSalesSql) {

        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");

        HBox returnBtnContainer = new HBox(10);
        returnBtnContainer.setAlignment(Pos.TOP_RIGHT);
        returnBtnContainer.setPadding(new Insets(10, 10, 10, 10));
        returnBtnContainer.getChildren().add(returnBtn);


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


        GridPane editSalesGrid = new GridPane();
        editSalesGrid.getStyleClass().add("editSalesGrid");

        editSalesGrid.setVgap(10);
        editSalesGrid.setHgap(10);

        Label idSales = new Label("Sale ID: ");
        Label idSales1 = new Label(" " + idSalesSql);
        idSales.setId("idSalesLabel");

        Label idProduct = new Label("Product ID: ");
        TextField idProductInput = new TextField();
        idProductInput.setText(String.valueOf(idProductSql));

        Label idSupplier = new Label("Supplier ID: ");
        TextField idSupplierInput = new TextField();
        idSupplierInput.setText(String.valueOf(idSupplierSql));

        Label priceSales = new Label("Total price: ");
        TextField priceSalesInput = new TextField();
        priceSalesInput.setText(String.valueOf(priceSalesSql));

        Label quantitySales = new Label("Total quantity: ");
        TextField quantitySalesInput = new TextField();
        quantitySalesInput.setText(String.valueOf(quantitySalesSql));
        quantitySalesInput.setId("quantitySalesInput");

        Label dateSales = new Label("Sale's date: ");
        TextField dateSalesInput = new TextField(); // a l'aide
        dateSalesInput.setText(dateSalesSql);

        editSalesGrid.add(idSales, 0, 0);
        editSalesGrid.add(idSales1, 1, 0);
        editSalesGrid.add(idProduct, 0, 1);
        editSalesGrid.add(idProductInput, 1, 1);
        editSalesGrid.add(idSupplier, 0, 2);
        editSalesGrid.add(idSupplierInput, 1, 2);
        editSalesGrid.add(priceSales, 0, 3);
        editSalesGrid.add(priceSalesInput, 1, 3);
        editSalesGrid.add(quantitySales, 0, 4);
        editSalesGrid.add(quantitySalesInput, 1, 4);
        editSalesGrid.add(dateSales, 0, 5);
        editSalesGrid.add(dateSalesInput, 1, 5);

        editSalesGrid.setAlignment(Pos.CENTER);
        editSalesGrid.setPadding(new Insets(40, 0, 0, 0));

        Button submitEditedSalesBtn2 = new Button("Edit");
        submitEditedSalesBtn2.getStyleClass().add("submitEditedSalesBtn2");
        HBox editSalesContainer2 = new HBox(10);
        editSalesContainer2.getChildren().add(submitEditedSalesBtn2);
        editSalesContainer2.setAlignment(Pos.CENTER);
        editSalesContainer2.setPadding(new Insets(145, 10, 10, 10));

        submitEditedSalesBtn2.setOnAction(event -> {
            try {

                if (idProductInput.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a product for the sale");

                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/sales.css");
                    alert.showAndWait();
                    return;
                }

                if (idSupplierInput.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a supplier for the product");

                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/sales.css");
                    alert.showAndWait();
                    return;
                }

                if (quantitySalesInput.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a quantity for the sale");

                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/sales.css");
                    alert.showAndWait();
                    return;
                }

                if (dateSalesInput == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);

                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/sales.css");
                    alert.showAndWait();
                    return;
                }

                SalesQuery.editSales(idSalesSql, idProductInput, idSupplierInput, priceSalesInput, quantitySalesInput, dateSalesInput);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Sale added successfully!");

                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setGraphic(null);
                alert.initStyle(StageStyle.UTILITY);
                alert.getDialogPane().getStylesheets().add("gestion/resources/sales.css");
                alert.showAndWait();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getSalesHomeScene());

            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Something went wrong");

                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setGraphic(null);
                alert.initStyle(StageStyle.UTILITY);
                alert.getDialogPane().getStylesheets().add("gestion/resources/sales.css");
                alert.showAndWait();
            }
        });

        returnBtn.setOnAction(event -> {
            idProductInput.clear();
            idSupplierInput.clear();
            quantitySalesInput.clear();
            dateSalesInput.clear();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getEditSalesScene1());
        });

        VBox editSales2 = new VBox(returnBtnContainer, editTitle2, editTxtContainer2, editSalesGrid, editSalesContainer2);

        Scene editSalesScene2 = new Scene(editSales2, 300, 600);
        editSalesScene2.getStylesheets().add("gestion/resources/sales.css");
        return editSalesScene2;
    }

    public static Scene showSalesScene() {


        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");

        HBox returnBtnContainer = new HBox(10);
        returnBtnContainer.setAlignment(Pos.TOP_RIGHT);
        returnBtnContainer.setPadding(new Insets(10, 10, 10, 10));
        returnBtnContainer.getChildren().add(returnBtn);


        VBox showTitle = new VBox(new Label("Sales repertory"));
        showTitle.setAlignment(Pos.CENTER);
        showTitle.getStyleClass().add("showTitle");

        Label searchSales = new Label("Search: ");
        searchSales.getStyleClass().add("searchSales");

        TextField searchSalesInput = new TextField();
        searchSalesInput.setPromptText("Search...");
        searchSalesInput.getStyleClass().add("searchSalesInput");

        HBox searchSalesContainer = new HBox();
        searchSalesContainer.getChildren().add(searchSales);
        searchSalesContainer.getChildren().add(searchSalesInput);
        searchSalesContainer.getStyleClass().add("searchSalesContainer");
        searchSalesContainer.setSpacing(10);
        searchSalesContainer.setPadding(new Insets(20, 0, 0, 20));


        TableView salesTable = new TableView<Sales>();
        salesTable.setEditable(true);
        salesTable.setId("salesTable");

        TableColumn<Sales, Integer> idSaleColumn = new TableColumn<>("ID");
        idSaleColumn.setCellValueFactory(new PropertyValueFactory<>("idSales"));
        idSaleColumn.setReorderable(false);

        TableColumn<Sales, Integer> idProductColumn = new TableColumn<>("Product");
        idProductColumn.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
        idProductColumn.setReorderable(false);

        TableColumn<Sales, Integer> idSupplierColumn = new TableColumn<>("Supplier");
        idSupplierColumn.setCellValueFactory(new PropertyValueFactory<>("idSuppliers"));
        idSupplierColumn.setReorderable(false);

        TableColumn<Sales, Integer> quantitySaleColumn = new TableColumn<>("Qty");
        quantitySaleColumn.setCellValueFactory(new PropertyValueFactory<>("quantitySales"));
        quantitySaleColumn.setReorderable(false);

        TableColumn<Sales, Double> priceSaleColumn = new TableColumn<>("Price");
        priceSaleColumn.setCellValueFactory(new PropertyValueFactory<>("priceSales"));
        priceSaleColumn.setResizable(false);
        priceSaleColumn.setReorderable(false);

        TableColumn<Sales, String> dateSaleColumn = new TableColumn<>("Date");
        dateSaleColumn.setCellValueFactory(new PropertyValueFactory<>("dateSales"));
        dateSaleColumn.setResizable(false);
        dateSaleColumn.setReorderable(false);

        idSaleColumn.setPrefWidth(40);
        idProductColumn.setPrefWidth(44);
        idSupplierColumn.setPrefWidth(44);
        quantitySaleColumn.setPrefWidth(40);
        priceSaleColumn.setPrefWidth(40);
        dateSaleColumn.setPrefWidth(70);

        ComboBox<Sales>salesComboBox = new ComboBox<>(SalesQuery.getSalesById());
        salesComboBox.setId("salesComboBox");
        salesComboBox.setItems(SalesQuery.getSalesById());

        salesComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Sales sales) {
                return sales != null ? String.valueOf(sales.getIdSales()) : "";
            }
            @Override
            public Sales fromString(String s) {
                return null;
            }
        });
        salesComboBox.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Sales sales, boolean empty) {
                super.updateItem(sales, empty);
                setText(empty || sales == null ? null : String.valueOf(sales.getIdSales()));
            }
        });

        returnBtn.setOnAction(event -> {
            salesComboBox.getValue();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getSalesHomeScene());
        });

        salesTable.getColumns().addAll(idSaleColumn, idProductColumn, idSupplierColumn, quantitySaleColumn, priceSaleColumn, dateSaleColumn);

        VBox salesTableContainer = new VBox();
        salesTableContainer.setPadding(new Insets(20, 10, 10, 10));
        salesTableContainer.getChildren().add(salesTable);


        ObservableList<Sales> data = SalesQuery.showSales(salesTable);

        FilteredList<Sales> filteredData = new FilteredList<>(data, s -> true);

        searchSalesInput.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(sale -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(sale.getIdSales()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(sale.getPriceSales()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(sale.getIdSuppliers()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(sale.getIdProduct()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(sale.getDateSales()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(sale.getQuantitySales()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                    return false;
                });
            });

            SortedList<Sales> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(salesTable.comparatorProperty());
            salesTable.setItems(sortedData);

            VBox showProducts = new VBox(returnBtnContainer, showTitle, searchSalesContainer, salesTableContainer);

            Scene showProductsScene = new Scene(showProducts, 300, 600);
            showProductsScene.getStylesheets().add("gestion/resources/sales.css");
            return showProductsScene;

        }

    }


