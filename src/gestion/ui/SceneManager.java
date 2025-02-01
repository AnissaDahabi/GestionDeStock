package gestion.ui;

import javafx.scene.Scene;

public class SceneManager {

    private static Scene homeScene;
    private static Scene productsScene;
    private static Scene addProductScene;
    private static Scene delProductScene1;
    private static Scene delProductScene2;
    private static Scene editProductScene1;
    private static Scene editProductScene2;
    private static Scene showProductScene;



    public static Scene getHomeScene() {
        if (homeScene == null) {
            homeScene = new Scene(HomeUI.createContent(), 300, 600);
            homeScene.getStylesheets().add("gestion/resources/home.css");
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

    public static Scene getDelProductScene1() {
        if (delProductScene1 == null) {
            delProductScene1 = ProductsUI.delProductScene1();
        }
        return delProductScene1;
    }

    public static Scene getDelProductScene2(int idProductSql, String nameProductSql) {
        if (delProductScene2 == null) {
            delProductScene2 = ProductsUI.delProductScene2(idProductSql, nameProductSql);
        }
        return delProductScene2;
    }

    public static Scene getEditProductScene1() {
        if (editProductScene1 == null) {
            editProductScene1 = ProductsUI.editProductScene1();
        }
        return editProductScene1;
    }

    public static Scene getEditProductScene2() {
        if (editProductScene2 == null) {
            editProductScene2 = ProductsUI.editProductScene2();
        }
        return editProductScene2;
    }

    public static Scene getShowProductScene() {
        if (showProductScene == null) {
            showProductScene = ProductsUI.showProductScene();
        }
        return showProductScene;
    }
}

