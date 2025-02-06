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
    private static Scene delSuppliersScene1;
    private static Scene delSuppliersScene2;
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

    public static Scene getDelSuppliersScene2(int idSuppliersSql, String nameSuppliersSql) {
        if (delSuppliersScene2 == null) {
            delSuppliersScene2 = SuppliersUI.delSuppliersScene2(idSuppliersSql, nameSuppliersSql);
        }
        return delSuppliersScene2;
    }

    public static Scene getEditSuppliersScene1() {
        if (editSuppliersScene == null) {
            editSuppliersScene = SuppliersUI.editSuppliersScene1();
        }
        return editSuppliersScene;
    }

    public static Scene getEditSuppliersScene2(int idSuppliersSql, String nameSuppliersSql, int phoneSuppliersSql, String addressSuppliersSql, String emailSuppliersSql) {
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

    public static Scene getDelSalesScene2(int idSalesSql, String nameSalesSuppliersSql, String nameSalesProductSql) {
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

    public static Scene getEditSalesScene2(int idSalesSql, String nameSalesSuppliersSql, String nameSalesProductSql, int quantitySalesProductSql, String priceSalesSql) {
        if (editSalesScene2 == null) {
            editSalesScene2 = SalesUI.editSalesScene2(idSalesSql, nameSalesSuppliersSql, nameSalesProductSql, quantitySalesProductSql, priceSalesSql);
        }
        return editSalesScene2;
    }



}







