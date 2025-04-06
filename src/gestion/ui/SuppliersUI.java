package gestion.ui;

import gestion.dao.ProductsQuery;
import gestion.dao.SuppliersQuery;
import gestion.model.Products;
import gestion.model.Suppliers;
import gestion.service.SuppliersService;
import javafx.collections.FXCollections;
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


public class SuppliersUI {


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
        HBox suppliersTitle = new HBox(new Label("Suppliers"));
        suppliersTitle.getStyleClass().add("suppliersTitle");
        suppliersTitle.setAlignment(Pos.CENTER);

        // Buttons:
        Button addSuppliersBtn = new Button("Add a supplier");
        addSuppliersBtn.getStyleClass().add("addSuppliersBtn");
        Button delSuppliersBtn = new Button("Delete a supplier");
        delSuppliersBtn.getStyleClass().add("delSuppliersBtn");
        Button editSuppliersBtn = new Button("Edit a supplier");
        editSuppliersBtn.getStyleClass().add("editSuppliersBtn");
        Button showSuppliersBtn = new Button("Show all suppliers");
        showSuppliersBtn.getStyleClass().add("showSuppliersBtn");

        VBox SuppliersBtnContainer = new VBox(40);
        SuppliersBtnContainer.getStyleClass().add("suppliersBtnContainer");
        SuppliersBtnContainer.setAlignment(Pos.CENTER);
        SuppliersBtnContainer.getChildren().add(addSuppliersBtn);
        SuppliersBtnContainer.getChildren().add(delSuppliersBtn);
        SuppliersBtnContainer.getChildren().add(editSuppliersBtn);
        SuppliersBtnContainer.getChildren().add(showSuppliersBtn);

        addSuppliersBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getAddSuppliersScene());
        });
        delSuppliersBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getDelSuppliersScene1());
        });
        editSuppliersBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getEditSuppliersScene1());
        });

        showSuppliersBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getShowSuppliersScene1());
        });

        VBox homeSuppliers = new VBox(returnBtnContainer, suppliersTitle, SuppliersBtnContainer);

        Scene suppliersScene = new Scene(homeSuppliers, 300, 600);
        suppliersScene.getStylesheets().add("gestion/resources/suppliers.css");

        return suppliersScene;
    }

    //Add Suppliers Scene
    public static Scene addSuppliersScene() {

        // Return button:
        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");

        returnBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getSuppliersHomeScene());
        });

        HBox returnBtnContainer = new HBox(10);
        returnBtnContainer.setAlignment(Pos.TOP_RIGHT);
        returnBtnContainer.setPadding(new Insets(10, 10, 10, 10));
        returnBtnContainer.getChildren().add(returnBtn);

        // Title and txt:
        VBox addTitle = new VBox(new Label("Add a supplier"));
        addTitle.setAlignment(Pos.CENTER);
        addTitle.getStyleClass().add("addTitle");

        Text addTxt = new Text();
        addTxt.setText("Please enter the details of the new supplier to be added into the database");
        addTxt.setId("addTxt");
        addTxt.setWrappingWidth(280);
        HBox addTxtContainer = new HBox();
        addTxtContainer.getChildren().add(addTxt);
        addTxtContainer.setId("addTxtContainer");
        addTxtContainer.setAlignment(Pos.CENTER);

        // User input:
        GridPane addSuppliersGrid = new GridPane();
        addSuppliersGrid.getStyleClass().add("addSuppliersGrid");

        addSuppliersGrid.setVgap(10);
        addSuppliersGrid.setHgap(10);

        Label idSupplier = new Label("ID Number: ");
        TextField idSuppliersInput = new TextField();
        idSuppliersInput.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d{0,4}")) {
                return change;
            }
            return null;
        }));

        Label nameSuppliers = new Label("Name: ");
        TextField nameSuppliersInput = new TextField();
        nameSuppliersInput.setTextFormatter(new TextFormatter<>(change -> {
            String input = change.getControlNewText();
            if (input.isEmpty() || input.matches("[\\p{L}0-9\\s\\-']+")) {
                return change;
            }
            return null;
        }));

        Label phoneSuppliers = new Label("Phone: ");
        TextField phoneSuppliersInput = new TextField();
        phoneSuppliersInput.setTextFormatter(new TextFormatter<>(change -> {
            String input = change.getControlNewText();
            if (input.isEmpty() || input.matches("^[0-9]*")) {
                return change;
            }
            return null;
        }));

        Label addressSuppliers = new Label("Address: ");
        TextField addressSuppliersInput = new TextField();
        addressSuppliersInput.setTextFormatter(new TextFormatter<>(change -> {
            String input = change.getControlNewText();
            if (input.isEmpty() || input.matches("[\\p{L}0-9\\s\\-']+")) {
                return change;
            }
            return null;
        }));


        Label emailSuppliers = new Label("Email: ");
        TextField emailSuppliersInput = new TextField();
        emailSuppliersInput.setTextFormatter(new TextFormatter<>(change -> {
            String input = change.getControlNewText();
            if (input.isEmpty() || input.matches("[a-zA-Z0-9@\\.\\-_]+")) {
                return change;
            }
            return null;
        }));


        addSuppliersGrid.add(idSupplier, 0, 0);
        addSuppliersGrid.add(idSuppliersInput, 1, 0);
        addSuppliersGrid.add(nameSuppliers, 0, 1);
        addSuppliersGrid.add(nameSuppliersInput, 1, 1);
        addSuppliersGrid.add(phoneSuppliers, 0, 2);
        addSuppliersGrid.add(phoneSuppliersInput, 1, 2);
        addSuppliersGrid.add(addressSuppliers, 0, 3);
        addSuppliersGrid.add(addressSuppliersInput, 1, 3);
        addSuppliersGrid.add(emailSuppliers, 0, 4);
        addSuppliersGrid.add(emailSuppliersInput, 1, 4);

        addSuppliersGrid.setAlignment(Pos.CENTER);

        addSuppliersGrid.setPadding(new Insets(40, 0, 0, 0));

        Button submitAddedSuppliersBtn = new Button("Add");
        submitAddedSuppliersBtn.getStyleClass().add("submitAddedSuppliersBtn");
        HBox addSuppliersContainer = new HBox(10);
        addSuppliersContainer.getChildren().add(submitAddedSuppliersBtn);
        addSuppliersContainer.setAlignment(Pos.CENTER);
        addSuppliersContainer.setPadding(new Insets(100, 10, 10, 10));

        submitAddedSuppliersBtn.setOnAction(event -> {
            try {

                if (idSuppliersInput.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter an ID for the supplier");
                    //Alert's design
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/suppliers.css");
                    alert.showAndWait();
                    return;
                }

                if (nameSuppliersInput.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a name for the supplier");
                    //Alert's design
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/suppliers.css");
                    alert.showAndWait();
                    return;
                }

                if (phoneSuppliersInput.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a phone number for the supplier");
                    //Alert's design
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/suppliers.css");
                    alert.showAndWait();
                    return;
                }

                if (addressSuppliersInput.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter an address for the supplier");
                    //Alert's design
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/suppliers.css");
                    alert.showAndWait();
                    return;
                }

                if (emailSuppliersInput.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter an email address for the supplier");
                    //Alert's design
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/suppliers.css");
                    alert.showAndWait();
                    return;
                }

                int idSuppliers = Integer.parseInt(idSuppliersInput.getText());
                String name = nameSuppliersInput.getText();
                String phone = phoneSuppliersInput.getText();
                String address = addressSuppliersInput.getText();
                String email = emailSuppliersInput.getText();

                boolean success = SuppliersService.addSuppliers(idSuppliers, name, phone, address, email);

                if (success) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Supplier added successfully");

                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/suppliers.css");
                    alert.showAndWait();

                    idSuppliersInput.clear();
                    nameSuppliersInput.clear();
                    phoneSuppliersInput.clear();
                    addressSuppliersInput.clear();
                    emailSuppliersInput.clear();

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(SceneManager.getSuppliersHomeScene());
                }

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Something went wrong");
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setGraphic(null);
                alert.initStyle(StageStyle.UTILITY);
                alert.getDialogPane().getStylesheets().add("gestion/resources/suppliers.css");
                alert.showAndWait();
            }
        });

        VBox addSuppliers = new VBox(returnBtnContainer, addTitle, addTxtContainer, addSuppliersGrid,addSuppliersContainer);

        Scene addSuppliersScene = new Scene(addSuppliers, 300, 600);
        addSuppliersScene.getStylesheets().add("gestion/resources/suppliers.css");
        return addSuppliersScene;
    }

    public static Scene delSuppliersScene1() {

        // Return button:
        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");



        HBox returnBtnContainer = new HBox(10);
        returnBtnContainer.setAlignment(Pos.TOP_RIGHT);
        returnBtnContainer.setPadding(new Insets(10, 10, 10, 10));
        returnBtnContainer.getChildren().add(returnBtn);

        // Title and txt:
        VBox delTitle1 = new VBox(new Label("Delete a supplier"));
        delTitle1.setAlignment(Pos.CENTER);
        delTitle1.getStyleClass().add("delTitle1");

        Text delTxt1 = new Text();
        delTxt1.setText("Please select the ID number of the supplier you want to delete from the inventory");
        delTxt1.setId("delTxt1");
        delTxt1.setWrappingWidth(280);
        HBox delTxtContainer1 = new HBox();
        delTxtContainer1.getChildren().add(delTxt1);
        delTxtContainer1.setId("delTxtContainer1");
        delTxtContainer1.setAlignment(Pos.CENTER);

        //User input
       Label idSuppliersLabel = new Label("ID Number: ");
        TextField idSuppliersInput = new TextField();
        HBox idSuppliersContainer = new HBox();
        idSuppliersContainer.getChildren().add(idSuppliersLabel);
        idSuppliersContainer.getChildren().add(idSuppliersInput);
        idSuppliersContainer.setId("idSuppliersContainer");
        idSuppliersContainer.setAlignment(Pos.CENTER);

        //Fil déroulant
        Label comboLabel = new Label("ID number : ");
        ComboBox<Suppliers> suppliersComboBox = new ComboBox<>(SuppliersQuery.getSuppliersID());
        //suppliersComboBox.setPromptText("Choose supplier");
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

        HBox boxSuppliersContainer = new HBox();
        boxSuppliersContainer.getStyleClass().add("boxSuppliersContainer");
        boxSuppliersContainer.getChildren().add(comboLabel);
        boxSuppliersContainer.getChildren().add(suppliersComboBox);
        boxSuppliersContainer.setAlignment(Pos.CENTER);
        boxSuppliersContainer.setSpacing(5);
        boxSuppliersContainer.setPadding(new Insets(20, 0, 0, 0));


        //Next button:
        Button submitDeletedSuppliersBtn1 = new Button("Delete");
        submitDeletedSuppliersBtn1.getStyleClass().add("submitDeletedSuppliersBtn1");
        HBox delSuppliersContainer1 = new HBox(10);
        delSuppliersContainer1.getChildren().add(submitDeletedSuppliersBtn1);
        delSuppliersContainer1.setAlignment(Pos.CENTER);
        delSuppliersContainer1.setPadding(new Insets(250, 10, 10, 10));

        returnBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getSuppliersHomeScene());
            suppliersComboBox.setItems(SuppliersQuery.getSuppliersID());
        });

        submitDeletedSuppliersBtn1.setOnAction(event -> {

            Suppliers selectedSupplier = suppliersComboBox.getValue();

            if (selectedSupplier != null) {

                boolean deleted = SuppliersService.delSuppliers(selectedSupplier.getIdSupplier());

                if (deleted) {
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle(null);
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Supplier deleted successfully");

                    DialogPane dialogPane = successAlert.getDialogPane();
                    dialogPane.setGraphic(null);
                    successAlert.initStyle(StageStyle.UTILITY);
                    successAlert.getDialogPane().getStylesheets().add("gestion/resources/suppliers.css");
                    successAlert.showAndWait();
                    suppliersComboBox.setItems(SuppliersQuery.getSuppliersID());
                }
            } else if (selectedSupplier == null){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Please choose a supplier to delete");
                    //Alert's design
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/suppliers.css");
                    alert.showAndWait();
            } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle(null);
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Something wrong happened..");
                    DialogPane dialogPane = errorAlert.getDialogPane();
                    dialogPane.setGraphic(null);
                    errorAlert.initStyle(StageStyle.UTILITY);
                    errorAlert.getDialogPane().getStylesheets().add("gestion/resources/suppliers.css");
                    errorAlert.showAndWait();
            }
        });

        VBox delSuppliers1 = new VBox(returnBtnContainer, delTitle1, delTxtContainer1, boxSuppliersContainer, delSuppliersContainer1);

        Scene delSuppliersScene1 = new Scene(delSuppliers1, 300, 600);
        delSuppliersScene1.getStylesheets().add("gestion/resources/suppliers.css");
        return delSuppliersScene1;
    }

    //EDIT SUPPLIERS SCENE 1
    public static Scene editSuppliersScene1() {

        // Return button:
        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");

        HBox returnBtnContainer = new HBox(10);
        returnBtnContainer.setAlignment(Pos.TOP_RIGHT);
        returnBtnContainer.setPadding(new Insets(10, 10, 10, 10));
        returnBtnContainer.getChildren().add(returnBtn);

        // Title and txt:
        VBox editTitle1 = new VBox(new Label("Edit suppliers"));
        editTitle1.setAlignment(Pos.CENTER);
        editTitle1.getStyleClass().add("editTitle1");

        Text editTxt1 = new Text();
        editTxt1.setText("Please select the ID number of the supplier you want to edit");
        editTxt1.setId("editTxt1");
        editTxt1.setWrappingWidth(280);
        HBox editTxtContainer1 = new HBox();
        editTxtContainer1.getChildren().add(editTxt1);
        editTxtContainer1.setId("delTxtContainer1");
        editTxtContainer1.setAlignment(Pos.CENTER);

        //User input
        Label comboLabel = new Label("ID Number: ");

        ComboBox<Suppliers> suppliersComboBox = new ComboBox<>(SuppliersQuery.getSuppliersID());
        //suppliersComboBox.setPromptText("Choose supplier");
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

        HBox boxSuppliersContainer = new HBox();
        boxSuppliersContainer.getStyleClass().add("boxSuppliersContainer");
        boxSuppliersContainer.getChildren().add(comboLabel);
        boxSuppliersContainer.getChildren().add(suppliersComboBox);
        boxSuppliersContainer.setAlignment(Pos.CENTER);
        boxSuppliersContainer.setSpacing(5);
        boxSuppliersContainer.setPadding(new Insets(20, 0, 0, 0));

        //Next button:
        Button submitEditedSuppliersBtn1 = new Button("Next");
        submitEditedSuppliersBtn1.getStyleClass().add("submitEditedSuppliersBtn1");
        HBox editSuppliersContainer1 = new HBox(10);
        editSuppliersContainer1.getChildren().add(submitEditedSuppliersBtn1);
        editSuppliersContainer1.setAlignment(Pos.CENTER);
        editSuppliersContainer1.setPadding(new Insets(270, 10, 10, 10));

        returnBtn.setOnAction(event -> {
            suppliersComboBox.setItems(SuppliersQuery.getSuppliersID());
            suppliersComboBox.getSelectionModel().clearSelection();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getSuppliersHomeScene());
        });

        submitEditedSuppliersBtn1.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Suppliers selectedSupplier = suppliersComboBox.getValue();
            if (selectedSupplier != null) {

                stage.setScene(SceneManager.getEditSuppliersScene2(
                        selectedSupplier.getIdSupplier(),
                        selectedSupplier.getNameSupplier(),
                        selectedSupplier.getPhoneSupplier(),
                        selectedSupplier.getAddressSupplier(),
                        selectedSupplier.getEmailSupplier()
                ));

            } else if (selectedSupplier == null){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Please choose a supplier to edit.");
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setGraphic(null);
                alert.initStyle(StageStyle.UTILITY);
                alert.getDialogPane().getStylesheets().add("gestion/resources/suppliers.css");
                alert.showAndWait();
            }
        });

        VBox editSuppliers1 = new VBox(returnBtnContainer, editTitle1, editTxtContainer1, boxSuppliersContainer, editSuppliersContainer1);

        Scene editSuppliersScene1 = new Scene(editSuppliers1, 300, 600);
        editSuppliersScene1.getStylesheets().add("gestion/resources/suppliers.css");
        return editSuppliersScene1;
    }

    public static Scene editSuppliersScene2(int idSuppliersSql, String nameSuppliersSql, String phoneSuppliersSql, String addressSuppliersSql, String emailSuppliersSql) {

        // Return button:
        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");

        HBox returnBtnContainer = new HBox(10);
        returnBtnContainer.setAlignment(Pos.TOP_RIGHT);
        returnBtnContainer.setPadding(new Insets(10, 10, 10, 10));
        returnBtnContainer.getChildren().add(returnBtn);

        // Title and txt:
        VBox editTitle2 = new VBox(new Label("Edit supplier"));
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
        GridPane editSuppliersGrid = new GridPane();
        editSuppliersGrid.getStyleClass().add("editSuppliersGrid");

        editSuppliersGrid.setVgap(10);
        editSuppliersGrid.setHgap(10);

        Label idSuppliers = new Label("Supplier ID: ");
        Label idSuppliersInput = new Label(" " + idSuppliersSql );
        idSuppliers.setId("idSuppliersLabel");

        Label nameSuppliers = new Label("Name: ");
        TextField nameSuppliersInput = new TextField();
        nameSuppliersInput.setText(nameSuppliersSql);
        nameSuppliersInput.setId("nameSuppliersInput");
        nameSuppliersInput.setTextFormatter(new TextFormatter<>(change -> {
            String input = change.getControlNewText();
            if (input.isEmpty() || input.matches("[\\p{L}0-9\\s\\-']+")) {
                return change;
            }
            return null;
        }));

        Label phoneSuppliers = new Label("Phone: ");
        TextField phoneSuppliersInput = new TextField();
        phoneSuppliersInput.setText(String.valueOf(phoneSuppliersSql));
        phoneSuppliersInput.setId("phoneSuppliersInput");
        phoneSuppliersInput.setTextFormatter(new TextFormatter<>(change -> {
            String input = change.getControlNewText();
            if (input.isEmpty() || input.matches("^[0-9]*")) {
                return change;
            }
            return null;
        }));


        //ComboBox for the refresh
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


        Label addressSuppliers = new Label("Address: ");
        TextField addressSuppliersInput = new TextField();
        addressSuppliersInput.setText(String.valueOf(addressSuppliersSql));
        addressSuppliersInput.setId("addressSuppliersInput");
        addressSuppliersInput.setTextFormatter(new TextFormatter<>(change -> {
            String input = change.getControlNewText();
            if (input.isEmpty() || input.matches("[\\p{L}0-9\\s\\-']+")) {
                return change;
            }
            return null;
        }));

        Label emailSuppliers = new Label("Email: ");
        TextField emailSuppliersInput = new TextField();
        emailSuppliersInput.setText(emailSuppliersSql);
        emailSuppliersInput.setId("emailSuppliersInput");
        emailSuppliersInput.setTextFormatter(new TextFormatter<>(change -> {
            String input = change.getControlNewText();
            if (input.isEmpty() || input.matches("[a-zA-Z0-9@\\.\\-_]+")) {
                return change;
            }
            return null;
        }));

        editSuppliersGrid.add(idSuppliers, 0, 0);
        editSuppliersGrid.add(idSuppliersInput, 1, 0);
        editSuppliersGrid.add(nameSuppliers, 0, 1);
        editSuppliersGrid.add(nameSuppliersInput, 1, 1);
        editSuppliersGrid.add(phoneSuppliers, 0, 2);
        editSuppliersGrid.add(phoneSuppliersInput, 1, 2);
        editSuppliersGrid.add(addressSuppliers, 0, 3);
        editSuppliersGrid.add(addressSuppliersInput, 1, 3);
        editSuppliersGrid.add(emailSuppliers, 0, 4);
        editSuppliersGrid.add(emailSuppliersInput, 1, 4);

        editSuppliersGrid.setAlignment(Pos.CENTER);
        editSuppliersGrid.setPadding(new Insets(40, 0, 0, 0));

        Button submitEditedSuppliersBtn2 = new Button("Edit");
        submitEditedSuppliersBtn2.getStyleClass().add("submitEditedSuppliersBtn2");
        HBox editSuppliersContainer2 = new HBox(10);
        editSuppliersContainer2.getChildren().add(submitEditedSuppliersBtn2);
        editSuppliersContainer2.setAlignment(Pos.CENTER);
        editSuppliersContainer2.setPadding(new Insets(145, 10, 10, 10));

        returnBtn.setOnAction(event -> {
            suppliersComboBox.setItems(SuppliersQuery.getSuppliersID());
            suppliersComboBox.getSelectionModel().clearSelection();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getEditSuppliersScene1());
        });

        submitEditedSuppliersBtn2.setOnAction(event -> {
            try {

                if (nameSuppliersInput.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Please enter a name for the supplier");
                //Alert's design
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setGraphic(null);
                alert.initStyle(StageStyle.UTILITY);
                alert.getDialogPane().getStylesheets().add("gestion/resources/suppliers.css");
                alert.showAndWait();
                return;
            }

                if (phoneSuppliersInput.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a phone number for the supplier");
                    //Alert's design
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/suppliers.css");
                    alert.showAndWait();
                    return;
                }

                if (addressSuppliersInput.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter an address for the supplier");
                    //Alert's design
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/suppliers.css");
                    alert.showAndWait();
                    return;
                }

                if (emailSuppliersInput.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter an email address for the supplier");
                    //Alert's design
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setGraphic(null);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.getDialogPane().getStylesheets().add("gestion/resources/suppliers.css");
                    alert.showAndWait();
                    return;
                }

                SuppliersQuery.editSuppliers(idSuppliersSql ,nameSuppliersInput, phoneSuppliersInput, addressSuppliersInput, emailSuppliersInput);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Supplier added successfully!");
                //Alert's design
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setGraphic(null);
                alert.initStyle(StageStyle.UTILITY);
                alert.getDialogPane().getStylesheets().add("gestion/resources/suppliers.css");
                alert.showAndWait();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(SceneManager.getSuppliersHomeScene());

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
                alert.getDialogPane().getStylesheets().add("gestion/resources/suppliers.css");
                alert.showAndWait();
            }

        });

        VBox editSuppliers2 = new VBox(returnBtnContainer, editTitle2, editTxtContainer2, editSuppliersGrid,editSuppliersContainer2);

        Scene editSuppliersScene2 = new Scene(editSuppliers2, 300, 600);
        editSuppliersScene2.getStylesheets().add("gestion/resources/suppliers.css");
        return editSuppliersScene2;
    }

    //SHOW SUPPLIERS SCENE
    public static Scene showSuppliersScene1() {

        // Return button:
        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");

        HBox returnBtnContainer = new HBox(10);
        returnBtnContainer.setAlignment(Pos.TOP_RIGHT);
        returnBtnContainer.setPadding(new Insets(10, 10, 10, 10));
        returnBtnContainer.getChildren().add(returnBtn);

        // Title and txt:
        VBox showTitle = new VBox(new Label("Suppliers repertory"));
        showTitle.setAlignment(Pos.CENTER);
        showTitle.getStyleClass().add("showTitle");

        Label sortSuppliers = new Label("Sort Suppliers: ");
        sortSuppliers.getStyleClass().add("sortSuppliers");

        ObservableList<String> sortOptions =
                FXCollections.observableArrayList(
                        "alphabetical order",
                        "ascending order",
                        "descending order"
                );
        final ComboBox sortFilter = new ComboBox(sortOptions);
        sortFilter.setId("sortFilter");

        Label searchSuppliers = new Label("Search by ID: ");
        searchSuppliers.getStyleClass().add("searchSuppliers");
        TextField searchSuppliersInput = new TextField();
        searchSuppliersInput.setPromptText("Search...");
        searchSuppliersInput.getStyleClass().add("searchSuppliersInput");

        HBox searchSuppliersContainer = new HBox();
        searchSuppliersContainer.getChildren().add(searchSuppliers);
        searchSuppliersContainer.getChildren().add(searchSuppliersInput);
        searchSuppliersContainer.getStyleClass().add("searchSuppliersContainer");
        searchSuppliersContainer.setSpacing(10);

        TableView suppliersTable = new TableView<Suppliers>();
        suppliersTable.setEditable(true);
        suppliersTable.setId("suppliersTable");

        TableColumn idSuppliersColumn = new TableColumn<Suppliers, Integer>("ID");
        idSuppliersColumn.setCellValueFactory(new PropertyValueFactory<Suppliers, Integer>("idSupplier"));
        idSuppliersColumn.setReorderable(false);

        TableColumn nameSuppliersColumn = new TableColumn<Suppliers, String>("Name");
        nameSuppliersColumn.setCellValueFactory(new PropertyValueFactory<Suppliers, String>("nameSupplier"));
        nameSuppliersColumn.setReorderable(false);

        TableColumn phoneSuppliersColumn = new TableColumn<Suppliers, String>("Phone");
        phoneSuppliersColumn.setCellValueFactory(new PropertyValueFactory<Suppliers, String>("phoneSupplier"));
        phoneSuppliersColumn.setReorderable(false);

        TableColumn addressSuppliersColumn = new TableColumn<Suppliers, String>("Address");
        addressSuppliersColumn.setCellValueFactory(new PropertyValueFactory<Suppliers, String>("addressSupplier"));
        addressSuppliersColumn.setReorderable(false);

        TableColumn emailSuppliersColumn = new TableColumn<Suppliers, String>("Email");
        emailSuppliersColumn.setCellValueFactory(new PropertyValueFactory<Suppliers, String>("emailSupplier"));
        emailSuppliersColumn.setReorderable(false);

        suppliersTable.getColumns().addAll(idSuppliersColumn, nameSuppliersColumn, phoneSuppliersColumn, addressSuppliersColumn, emailSuppliersColumn);

        suppliersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        SuppliersQuery.showSuppliers(suppliersTable);

        // Search filter:
        ObservableList<Suppliers> data = SuppliersQuery.searchSuppliers(suppliersTable);

        FilteredList<Suppliers> filteredData = new FilteredList<>(data, p -> true);

        searchSuppliersInput.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(supplier -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(supplier.getIdSupplier()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (supplier.getNameSupplier().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (supplier.getPhoneSupplier().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (supplier.getAddressSupplier().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (supplier.getEmailSupplier().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }

                return false;
            });
        });

        SortedList<Suppliers> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(suppliersTable.comparatorProperty());
        suppliersTable.setItems(sortedData);

        returnBtn.setOnAction(event -> {
            suppliersTable.refresh();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getSuppliersHomeScene());
        });

        VBox suppliersTableContainer = new VBox();
        suppliersTableContainer.setPadding(new Insets(10, 10, 10, 10));
        suppliersTableContainer.getChildren().add(suppliersTable);


        VBox showSuppliers = new VBox(returnBtnContainer, showTitle, searchSuppliersContainer, suppliersTableContainer);

        Scene showSuppliersScene = new Scene(showSuppliers, 300, 600);
        showSuppliersScene.getStylesheets().add("gestion/resources/suppliers.css");

        return showSuppliersScene;
    }




}
