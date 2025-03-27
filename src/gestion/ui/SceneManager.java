package gestion.ui;

import javafx.scene.Scene;

public class SceneManager {

    private static Scene homeScene;
    private static Scene productsScene;
    private static Scene addProductsScene;
    private static Scene delProductsScene;
    private static Scene editProductsScene1;
    private static Scene editProductsScene2;
    private static Scene showProductsScene;

    private static Scene suppliersScene;
    private static Scene addSuppliersScene;
    private static Scene delSuppliersScene1;
    private static Scene editSuppliersScene;
    private static Scene editSuppliersScene2;
    private static Scene showSuppliersScene1;
    private static Scene showSuppliersScene2;
    private static Scene showSuppliersScene3;

    private static Scene salesScene;
    private static Scene addSalesScene;
    private static Scene delSalesScene1;
    private static Scene delSalesScene2;
    private static Scene editSalesScene1;
    private static Scene editSalesScene2;
    private static Scene showSalesScene1;
    private static Scene showSalesScene2;
    private static Scene showSalesScene3;

    private static Scene reportsScene;
    private static Scene showReportsScene;
    private static Scene showReportsScene2;


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

    public static Scene getAddProductsScene() {
        if (addProductsScene == null) {
            addProductsScene = ProductsUI.addProductsScene();
        }
        return addProductsScene;
    }

    public static Scene getDelProductsScene() {
        if (delProductsScene == null) {
            delProductsScene = ProductsUI.delProductsScene();
        }
        return delProductsScene;
    }

    public static Scene getEditProductsScene1() {
        if (editProductsScene1 == null) {
            editProductsScene1 = ProductsUI.editProductsScene1();
        }
        return editProductsScene1;
    }

    public static Scene getEditProductsScene2(int idProductsSql, String nameProductsSql, double priceProductsSql, int quantityProductsSql, int supplierIdSql) {
        if (editProductsScene2 == null) {
            editProductsScene2 = ProductsUI.editProductsScene2(idProductsSql, nameProductsSql, priceProductsSql, quantityProductsSql, supplierIdSql);
        }
        return editProductsScene2;
    }

    public static Scene getShowProductsScene() {
        if (showProductsScene == null) {
            showProductsScene = ProductsUI.showProductsScene();
        }
        return showProductsScene;
    }

    // SUPPLIERS SCENES

    public static Scene getSuppliersHomeScene() {
        if (suppliersScene == null) {
            suppliersScene = SuppliersUI.createContent();
        }
        return suppliersScene;
    }

    public static Scene getAddSuppliersScene() {
        if (addSuppliersScene == null) {
            addSuppliersScene = SuppliersUI.addSuppliersScene();
        }
        return addSuppliersScene;
    }

    public static Scene getDelSuppliersScene1() {
        if (delSuppliersScene1 == null) {
            delSuppliersScene1 = SuppliersUI.delSuppliersScene1();
        }
        return delSuppliersScene1;
    }


    public static Scene getEditSuppliersScene1() {
        if (editSuppliersScene == null) {
            editSuppliersScene = SuppliersUI.editSuppliersScene1();
        }
        return editSuppliersScene;
    }

    public static Scene getEditSuppliersScene2(int idSuppliersSql, String nameSuppliersSql, String phoneSuppliersSql, String addressSuppliersSql, String emailSuppliersSql) {
        if (editSuppliersScene2 == null) {
            editSuppliersScene2 = SuppliersUI.editSuppliersScene2(idSuppliersSql, nameSuppliersSql, phoneSuppliersSql, addressSuppliersSql, emailSuppliersSql);
        }
        return editSuppliersScene2;
    }

    public static Scene getShowSuppliersScene1() {
        if (showSuppliersScene1 == null) {
            showSuppliersScene1 = SuppliersUI.showSuppliersScene1();
        }
        return showSuppliersScene1;
    }

  /*  public static Scene showSuppliersScene2() {
        if (showSuppliersScene2 == null) {
            showSuppliersScene2 = SuppliersUI.showSuppliersScene2();
        }
        return showSuppliersScene2;
    } */

    // SALES SCENES


    public static Scene getSalesHomeScene() {
        if (salesScene == null) {
            salesScene = SalesUI.createContent();
        }
        return salesScene;
    }

    public static Scene getAddSalesScene() {
        if (addSalesScene == null) {
            addSalesScene = SalesUI.addSalesScene();
        }
        return addSalesScene;
    }

    public static Scene getDelSalesScene1() {
        if (delSalesScene1 == null) {
            delSalesScene1 = SalesUI.delSalesScene1();
        }
        return delSalesScene1;
    }

    public static Scene getDelSalesScene2(int idSalesSql, String nameSalesSuppliersSql, String nameSalesProductSql, String dateSalesSql) {
        if (delSalesScene2 == null) {
            delSalesScene2 = SalesUI.delSalesScene2(idSalesSql, nameSalesSuppliersSql, nameSalesProductSql);
        }
        return delSalesScene2;
    }

    public static Scene getEditSalesScene1() {
        if (editSalesScene1 == null) {
            editSalesScene1 = SalesUI.editSalesScene1();
        }
        return editSalesScene1;
    }

    public static Scene getEditSalesScene2(int idSalesSql, String nameSalesSuppliersSql, String nameSalesProductSql, int quantitySalesProductSql, String priceSalesSql, String dateSalesSql) {
        if (editSalesScene2 == null) {
            editSalesScene2 = SalesUI.editSalesScene2(idSalesSql, nameSalesSuppliersSql, nameSalesProductSql, quantitySalesProductSql, priceSalesSql, dateSalesSql);
        }
        return editSalesScene2;
    }

    public static Scene getShowSalesScene1() {
        if (showSalesScene1 == null) {
            showSalesScene1 = SalesUI.showSalesScene1();
        }
        return showSalesScene1;
    }

    // REPORTS SCENES

    public static Scene getReportsHomeScene() {
        if (reportsScene == null) {
            reportsScene = ReportsUI.createContent();
        }
        return reportsScene;
    }

    public static Scene getGenerateScene1() {
        if (showReportsScene == null) {
            showReportsScene = ReportsUI.createContent();
        }
        return showReportsScene;
    }

    public static Scene getReportsScene2() {
        if (showReportsScene2 == null) {
            showReportsScene2 = ReportsUI.createContent();
        } return showReportsScene2;
    }
}







