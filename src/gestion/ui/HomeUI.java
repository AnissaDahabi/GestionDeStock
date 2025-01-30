package gestion.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.FileInputStream;

public class HomeUI extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createContent(), 300, 600);
        scene.getStylesheets().add("gestion/resources/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    private Region createContent() {
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


        return new VBox(20, closeBtnContainer, homeTitle, logoContainer, createHomeButtons());
    }

    private Node createHomeButtons() {

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

        buttonGridHome.add(productsBtn, 0, 0); // Colonne 0, ligne 0
        buttonGridHome.add(suppliersBtn, 1, 0); // Colonne 1, ligne 0
        buttonGridHome.add(salesBtn, 0, 1); // Colonne 0, ligne 1
        buttonGridHome.add(reportsBtn, 1, 1); // Colonne 1, ligne 1

        buttonGridHome.setAlignment(Pos.CENTER);

        VBox homeButtons = new VBox(20, buttonGridHome);
        homeButtons.setStyle("-fx-padding: 80 0 0 0;");

        homeButtons.setAlignment(Pos.BASELINE_CENTER);

        return homeButtons;
    }
}
