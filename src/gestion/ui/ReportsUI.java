package gestion.ui;

import gestion.dao.ReportsQuery;
import gestion.model.Reports;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.util.StringConverter;


public class ReportsUI {
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
        HBox reportsTitle = new HBox(new Label("Reports"));
        reportsTitle.getStyleClass().add("reportsTitle");
        reportsTitle.setAlignment(Pos.CENTER);

        //Buttons

        Button generateReportsBtn = new Button("Generate");
        generateReportsBtn.getStyleClass().add("generateBtn");

        VBox reportsBtnContainer = new VBox(40);
        reportsBtnContainer.getStyleClass().add("reportsBtnContainer");
        reportsBtnContainer.setAlignment(Pos.CENTER);
        reportsBtnContainer.getChildren().add(generateReportsBtn);

        //Fil déroulants

        Label comboLabel = new Label("Supplier : ");
        ComboBox<Reports> reportsComboBox = new ComboBox(ReportsQuery.getReports());
        reportsComboBox.getStyleClass().add("reportsComboBox");
        reportsComboBox.setItems(FXCollections.observableArrayList(ReportsQuery.getReports()));


        VBox homeReports = new VBox(returnBtnContainer, reportsTitle, reportsBtnContainer);

        Scene reportsScene = new Scene(homeReports, 300, 600);
        reportsScene.getStylesheets().add("gestion/resources/reports.css");

        return reportsScene;
    }



}
