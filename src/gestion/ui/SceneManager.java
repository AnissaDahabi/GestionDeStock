package gestion.ui;

import javafx.scene.Scene;

public class SceneManager {

    private static Scene homeScene;
    private static Scene productsScene;
    private static Scene addProductScene;

    public static Scene getHomeScene() {
        if (homeScene == null) {
            homeScene = new Scene(HomeUI.createContent(), 300, 600);
            homeScene.getStylesheets().add("gestion/resources/style.css");
        }
        return homeScene;
    }

    // PRODUCTS SCENES
    public static Scene getProductsHomeScene() {
        if (productsScene == null) {
            productsScene = ProductsUI.createContent();
        }
        return productsScene;
    }

    public static Scene getAddProductScene() {
        if (addProductScene == null) {
            addProductScene = ProductsUI.addProductScene();
        }
        return addProductScene;
    }
}

