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

    private static Scene suppliersScene;
    private static Scene addSuppliersScene;
    private static Scene delSuppliersScene;
    private static Scene delSuppliersScene2;
    private static Scene editSuppliersScene;
    private static Scene editSuppliersScene2;
    private static Scene showSuppliersScene;
    private static Scene showSuppliersScene2;




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

    public static Scene getEditProductScene2(int idProductSql, String nameProductSql, double priceProductSql, int quantityProductSql, String supplierProductSql) {
        if (editProductScene2 == null) {
            editProductScene2 = ProductsUI.editProductScene2(idProductSql, nameProductSql,priceProductSql,quantityProductSql,supplierProductSql);
        }
        return editProductScene2;
    }

    public static Scene getShowProductScene() {
        if (showProductScene == null) {
            showProductScene = ProductsUI.showProductScene();
        }
        return showProductScene;
    }

    // SUPPLIERS PART

    public static Scene getSuppliersHomeScene() {
        if (suppliersScene == null) {
            suppliersScene = SuppliersUI.createContent();
        }
        return suppliersScene;
    }

    public static Scene getAddSuppliersScene() {
        if (addSuppliersScene == null) {
            addSuppliersScene = SuppliersUI.createContent();
        }
        return addSuppliersScene;
    }

    public static Scene getDelSuppliersScene() {
        if (delSuppliersScene == null) {
            delSuppliersScene = SuppliersUI.createContent();
        }
        return delSuppliersScene;
    }

    public static Scene getDelSuppliersScene1() {
        if (delSuppliersScene2 == null) {
            delSuppliersScene2 = SuppliersUI.createContent();
        }
        return delSuppliersScene2;

    }
    //public static Scene getEditSuppliersScene() {}




}

