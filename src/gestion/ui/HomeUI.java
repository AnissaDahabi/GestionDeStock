package gestion.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;

public class HomeUI extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {

        Scene homeScene = new Scene(createContent(), 300, 600);
        homeScene.getStylesheets().add("gestion/resources/home.css");
        primaryStage.setScene(homeScene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
        primaryStage.setResizable(false);

    }

    public static VBox createContent() {
        Button closeBtn = new Button("Close");
        closeBtn.getStyleClass().add("closeBtn");
        closeBtn.setOnAction(event -> {
            Platform.exit();
        });

        HBox closeBtnContainer = new HBox(10);
        closeBtnContainer.setAlignment(Pos.TOP_RIGHT);
        closeBtnContainer.setPadding(new Insets(10, 10, 10, 10));
        closeBtnContainer.getChildren().add(closeBtn);

        HBox homeTitle = new HBox(new Label("L'Antique"));
        homeTitle.getStyleClass().add("homeTitle");

        homeTitle.setAlignment(Pos.CENTER);

        Image logo = new Image("gestion/resources/logo.png");
        ImageView viewLogo = new ImageView(logo);
        viewLogo.setFitWidth(150);
        viewLogo.setPreserveRatio(true);

        HBox logoContainer = new HBox(10);
        logoContainer.getChildren().add(viewLogo);
        logoContainer.setAlignment(Pos.CENTER);
        logoContainer.setStyle("-fx-padding: 30 0 0 0;");

        return new VBox(20, closeBtnContainer, homeTitle, logoContainer, createHomeButtons());
    }

    private static Node createHomeButtons() {

        Button productsBtn = new Button("Products");
        productsBtn.getStyleClass().add("productsBtn");
        Button suppliersBtn = new Button("Suppliers");
        suppliersBtn.getStyleClass().add("suppliersBtn");
        Button salesBtn = new Button("Sales");
        salesBtn.getStyleClass().add("salesBtn");
        Button reportsBtn = new Button("Reports");
        reportsBtn.getStyleClass().add("reportsBtn");

        GridPane buttonGridHome = new GridPane();
        buttonGridHome.getStyleClass().add("buttonGridHome");

        buttonGridHome.setVgap(20);
        buttonGridHome.setHgap(30);

        buttonGridHome.add(productsBtn, 0, 0);
        buttonGridHome.add(suppliersBtn, 1, 0);
        buttonGridHome.add(salesBtn, 0, 1);
        buttonGridHome.add(reportsBtn, 1, 1);

        buttonGridHome.setAlignment(Pos.CENTER);

        VBox homeButtons = new VBox(20, buttonGridHome);
        homeButtons.setStyle("-fx-padding: 20 0 0 0;");

        homeButtons.setAlignment(Pos.BASELINE_CENTER);

        //Actions des boutons :
        productsBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(gestion.ui.SceneManager.getProductsHomeScene());
        });

        suppliersBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(gestion.ui.SceneManager.getSuppliersHomeScene());
        });

        salesBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(gestion.ui.SceneManager.getSalesHomeScene());
        });

        reportsBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(gestion.ui.SceneManager.getReportsHomeScene());
        });

        return homeButtons;
    }
}
