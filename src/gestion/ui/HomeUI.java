package gestion.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class HomeUI extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {

        //IPhone border
       /* BorderPane iphone = new BorderPane();
        iphone.getStyleClass().add("iphone-frame");

        Rectangle iphoneFrame = new Rectangle(300, 600);
        iphoneFrame.setArcWidth(40);
        iphoneFrame.setArcHeight(40);
        iphoneFrame.setFill(Color.BLACK);

        Rectangle notch = new Rectangle(100, 20, Color.BLACK);
        notch.setArcWidth(15);
        notch.setArcHeight(15);
        notch.setTranslateY(-270);
        StackPane.setMargin(notch, new Insets(5, 0, 0, 0));

        StackPane root = new StackPane();
        root.getChildren().addAll(iphoneFrame, createContent(), notch); */

        Scene homeScene = new Scene(createContent(), 300, 600);
        homeScene.getStylesheets().add("gestion/resources/home.css");
        primaryStage.setScene(homeScene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
        primaryStage.setResizable(false);

    }

    public static VBox createContent() {
        Button closeBtn = new Button("Close");
        closeBtn.getStyleClass().add("closeBtn"); // crée une classe pour lui donner un style en css
        closeBtn.setOnAction(event -> {
            Platform.exit();
        });

        HBox closeBtnContainer = new HBox(10); // HBox = container horizontal
        closeBtnContainer.setAlignment(Pos.TOP_RIGHT);
        closeBtnContainer.setPadding(new Insets(10, 10, 10, 10)); //équivalent du <style> blabla </style> en html
        closeBtnContainer.getChildren().add(closeBtn); //ajouter un truc a un container

        HBox homeTitle = new HBox(new Label("L'Antique"));
        homeTitle.getStyleClass().add("homeTitle");

        homeTitle.setAlignment(Pos.CENTER); //placer sur la scene

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

        GridPane buttonGridHome = new GridPane(); //créer une grille
        buttonGridHome.getStyleClass().add("buttonGridHome");

        buttonGridHome.setVgap(20);
        buttonGridHome.setHgap(30);

        buttonGridHome.add(productsBtn, 0, 0); // Colonne 0, ligne 0
        buttonGridHome.add(suppliersBtn, 1, 0); // Colonne 1, ligne 0
        buttonGridHome.add(salesBtn, 0, 1); // Colonne 0, ligne 1
        buttonGridHome.add(reportsBtn, 1, 1); // Colonne 1, ligne 1

        buttonGridHome.setAlignment(Pos.CENTER);

        VBox homeButtons = new VBox(20, buttonGridHome); // VBox = container vertical
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

        // Title and txt:
        VBox delTitle1 = new VBox(new Label("Delete a product"));
        delTitle1.setAlignment(Pos.CENTER);
        delTitle1.getStyleClass().add("delTitle");

        Text delTxt = new Text();
        delTxt.setText("Please enter the details of the new product to be added into the inventory.");
        delTxt.setId("delTxt");
        delTxt.setWrappingWidth(300);
        HBox delTxtContainer = new HBox();
        delTxtContainer.getChildren().add(delTxt);
        delTxtContainer.setId("delTxtContainer");
        delTxtContainer.setAlignment(Pos.CENTER);

        return homeButtons;
    }
}
