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

        Label idSuppliers = new Label("ID Number: ");
        TextField idSuppliersInput = new TextField();

        Label nameSuppliers = new Label("Name: ");
        TextField nameSuppliersInput = new TextField();

        Label phoneSuppliers = new Label("Phone: ");
        TextField phoneSuppliersInput = new TextField();

        Label addressSuppliers = new Label("Address: ");
        TextField addressSuppliersInput = new TextField();

        Label emailSuppliers = new Label("Email: ");
        TextField emailSuppliersInput = new TextField();


        addSuppliersGrid.add(idSuppliers, 0, 0);
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
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            gestion.dao.SuppliersQuery.addSuppliers(idSuppliersInput, nameSuppliersInput, phoneSuppliersInput, addressSuppliersInput, emailSuppliersInput);
            idSuppliersInput.clear();
            nameSuppliersInput.clear();
            phoneSuppliersInput.clear();
            addressSuppliersInput.clear();
            emailSuppliersInput.clear();
            stage.setScene(SceneManager.getSuppliersHomeScene());
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

        returnBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getSuppliersHomeScene());
        });

        HBox returnBtnContainer = new HBox(10);
        returnBtnContainer.setAlignment(Pos.TOP_RIGHT);
        returnBtnContainer.setPadding(new Insets(10, 10, 10, 10));
        returnBtnContainer.getChildren().add(returnBtn);

        // Title and txt:
        VBox delTitle1 = new VBox(new Label("Delete a supplier"));
        delTitle1.setAlignment(Pos.CENTER);
        delTitle1.getStyleClass().add("delTitle1");

        Text delTxt1 = new Text();
        delTxt1.setText("Please enter the ID number of the supplier you want to delete from the database");
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


        //Next button:
        Button submitDeletedSuppliersBtn1 = new Button("Next");
        submitDeletedSuppliersBtn1.getStyleClass().add("submitDeletedSuppliersBtn1");
        HBox delSuppliersContainer1 = new HBox(10);
        delSuppliersContainer1.getChildren().add(submitDeletedSuppliersBtn1);
        delSuppliersContainer1.setAlignment(Pos.CENTER);
        delSuppliersContainer1.setPadding(new Insets(250, 10, 10, 10));

        submitDeletedSuppliersBtn1.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            gestion.dao.SuppliersQuery.showDelSuppliers(idSuppliersInput, stage);
            idSuppliersInput.clear();
        });

        VBox delSuppliers1 = new VBox(returnBtnContainer, delTitle1, delTxtContainer1, idSuppliersContainer, delSuppliersContainer1);

        Scene delSuppliersScene1 = new Scene(delSuppliers1, 300, 600);
        delSuppliersScene1.getStylesheets().add("gestion/resources/suppliers.css");
        return delSuppliersScene1;
    }

    // DELETE SUPPLIERS SCENE 2
    public static Scene delSuppliersScene2(int idSuppliersSql, String nameSuppliersSql) {

        // Return button:
        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");

        returnBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getDelSuppliersScene1());
        });

        HBox returnBtnContainer = new HBox(10);
        returnBtnContainer.setAlignment(Pos.TOP_RIGHT);
        returnBtnContainer.setPadding(new Insets(10, 10, 10, 10));
        returnBtnContainer.getChildren().add(returnBtn);

        // Title and txt:
        VBox delTitle2 = new VBox(new Label("Delete a supplier"));
        delTitle2.setAlignment(Pos.CENTER);
        delTitle2.getStyleClass().add("delTitle2");

        Text delTxt2 = new Text();
        delTxt2.setText("Is this the supplier you want to delete from the database ?");
        delTxt2.setId("delTxt2");
        delTxt2.setWrappingWidth(280);
        HBox delTxtContainer2 = new HBox();
        delTxtContainer2.getChildren().add(delTxt2);
        delTxtContainer2.setId("delTxtContainer2");
        delTxtContainer2.setAlignment(Pos.CENTER);

        // Id and name of supplier selected by user
        Label idSuppliers = new Label("ID Number: " + idSuppliersSql);
        Label nameSuppliers = new Label("Name: " + nameSuppliersSql);

        VBox inputResult = new VBox(10, idSuppliers, nameSuppliers);
        inputResult.setId("inputResult");
        inputResult.setAlignment(Pos.CENTER);
        inputResult.setPadding(new Insets(50, 10, 10, 10));

        Button submitDeletedSuppliersBtn2 = new Button("Confirm");
        submitDeletedSuppliersBtn2.getStyleClass().add("submitDeletedSuppliersBtn2");
        HBox delSuppliersContainer2 = new HBox(10);
        delSuppliersContainer2.getChildren().add(submitDeletedSuppliersBtn2);
        delSuppliersContainer2.setAlignment(Pos.CENTER);
        delSuppliersContainer2.setPadding(new Insets(130, 10, 10, 10));

        submitDeletedSuppliersBtn2.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            gestion.dao.SuppliersQuery.delSuppliers();
            stage.setScene(SceneManager.getSuppliersHomeScene());
        });

        VBox delSuppliers2 = new VBox(returnBtnContainer, delTitle2, delTxtContainer2, inputResult,delSuppliersContainer2);

        Scene delSuppliersScene2 = new Scene(delSuppliers2, 300, 600);
        delSuppliersScene2.getStylesheets().add("gestion/resources/suppliers.css");
        return delSuppliersScene2;
    }

    //EDIT SUPPLIERS SCENE 1
    public static Scene editSuppliersScene1() {

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
        VBox editTitle1 = new VBox(new Label("Edit suppliers"));
        editTitle1.setAlignment(Pos.CENTER);
        editTitle1.getStyleClass().add("editTitle1");

        Text editTxt1 = new Text();
        editTxt1.setText("Please enter the ID number of the supplier you want to edit");
        editTxt1.setId("editTxt1");
        editTxt1.setWrappingWidth(280);
        HBox editTxtContainer1 = new HBox();
        editTxtContainer1.getChildren().add(editTxt1);
        editTxtContainer1.setId("delTxtContainer1");
        editTxtContainer1.setAlignment(Pos.CENTER);

        //User input
        Label idSuppliers = new Label("ID Number: ");
        TextField idSuppliersInput = new TextField();
        HBox idSuppliersContainer = new HBox();
        idSuppliersContainer.getChildren().add(idSuppliers);
        idSuppliersContainer.getChildren().add(idSuppliersInput);
        idSuppliersContainer.setId("idSuppliersContainer");
        idSuppliersContainer.setAlignment(Pos.CENTER);

        //Next button:
        Button submitEditedSuppliersBtn1 = new Button("Next");
        submitEditedSuppliersBtn1.getStyleClass().add("submitEditedSuppliersBtn1");
        HBox editSuppliersContainer1 = new HBox(10);
        editSuppliersContainer1.getChildren().add(submitEditedSuppliersBtn1);
        editSuppliersContainer1.setAlignment(Pos.CENTER);
        editSuppliersContainer1.setPadding(new Insets(270, 10, 10, 10));

        submitEditedSuppliersBtn1.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            gestion.dao.SuppliersQuery.showEditedSuppliers(idSuppliersInput, stage);
            idSuppliersInput.clear();
        });

        VBox editSuppliers1 = new VBox(returnBtnContainer, editTitle1, editTxtContainer1, idSuppliersContainer, editSuppliersContainer1);

        Scene editSuppliersScene1 = new Scene(editSuppliers1, 300, 600);
        editSuppliersScene1.getStylesheets().add("gestion/resources/suppliers.css");
        return editSuppliersScene1;
    }

    public static Scene editSuppliersScene2(int idSuppliersSql, String nameSuppliersSql, int phoneSuppliersSql, String addressSuppliersSql, String emailSuppliersSql) {

        // Return button:
        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");

        returnBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getEditSuppliersScene1());
        });

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

        Label idSuppliers = new Label("ID Number: " + idSuppliersSql );
        idSuppliers.setId("idSuppliersLabel");

        Label nameSuppliers = new Label("Name: ");
        TextField nameSuppliersInput = new TextField();
        nameSuppliersInput.setPromptText(nameSuppliersSql);
        nameSuppliersInput.setId("nameSuppliersInput");

        Label phoneSuppliers = new Label("Phone: ");
        TextField phoneSuppliersInput = new TextField();
        phoneSuppliersInput.setPromptText(String.valueOf(phoneSuppliersSql));
        phoneSuppliersInput.setId("phoneSuppliersInput");

        Label addressSuppliers = new Label("Address: ");
        TextField addressSuppliersInput = new TextField();
        addressSuppliersInput.setPromptText(String.valueOf(addressSuppliersSql));
        addressSuppliersInput.setId("addressSuppliersInput");

        Label emailSuppliers = new Label("Email: ");
        TextField emailSuppliersInput = new TextField();
        emailSuppliersInput.setPromptText(emailSuppliersSql);
        emailSuppliersInput.setId("emailSuppliersInput");

        editSuppliersGrid.add(idSuppliers, 0, 0);
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

        submitEditedSuppliersBtn2.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            gestion.dao.SuppliersQuery.editSuppliers(nameSuppliersInput, phoneSuppliersInput, addressSuppliersInput, emailSuppliersInput);
            stage.setScene(SceneManager.getSuppliersHomeScene());
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

        returnBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getSuppliersHomeScene());
        });

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

        HBox sortFilterContainer = new HBox();
        sortFilterContainer.getChildren().add(sortSuppliers);
        sortFilterContainer.getChildren().add(sortFilter);
        sortFilterContainer.getStyleClass().add("sortFilterContainer");


        VBox showSuppliers = new VBox(returnBtnContainer, showTitle, sortFilterContainer);

        Scene showSuppliersScene = new Scene(showSuppliers, 300, 600);
        showSuppliersScene.getStylesheets().add("gestion/resources/suppliers.css");
        return showSuppliersScene;
    }




}
