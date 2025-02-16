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

        VBox homeReports = new VBox(returnBtnContainer, reportsTitle);

        Scene reportsScene = new Scene(homeReports, 300, 600);
        reportsScene.getStylesheets().add("gestion/resources/reports.css");

        return reportsScene;
    }


}
