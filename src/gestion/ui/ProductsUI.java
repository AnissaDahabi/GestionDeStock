package gestion.ui;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProductsUI {

    public static Scene homeProducts() {
        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");
        returnBtn.setOnAction(event -> {
             //pas fonctionnel :(
        });

        Scene productsScene = new Scene(createContent(), 300, 600);

        return productsScene;
    }

    private static Region createContent() {
        HBox productsTitle = new HBox(new Label("Products"));

        return productsTitle;
    }
}
