package gestion.service;

import gestion.dao.ProductsQuery;
import gestion.dao.SuppliersQuery;
import gestion.model.Suppliers;
import javafx.scene.control.Alert;

public class SuppliersService {
    public static boolean delSuppliers;
    private static SuppliersQuery suppliersQuery;
    private final ProductsQuery productsQuery;

    public SuppliersService(SuppliersQuery suppliersQuery, ProductsQuery productsQuery) {
        this.suppliersQuery = suppliersQuery;
        this.productsQuery = productsQuery;
    }


    public static boolean addSuppliers(int idSuppliers, String name, String phone, String address, String email, int idProduct) {

        try {
            if(name.isEmpty() || phone.isEmpty() || address.isEmpty() || email.isEmpty()) {
                throw new IllegalArgumentException("Please fill all the required fields");
            }
            if (idSuppliers==0 || idProduct==0) {
                throw new IllegalArgumentException("Please fill all the required fields");
            }
            if (!email.contains("@")) {
                throw new IllegalArgumentException("Please fill a valid email address");
            }

            Suppliers suppliers = new Suppliers(idSuppliers, name, phone, address, email, idProduct);

            return SuppliersQuery.addSuppliers(suppliers);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return false;
        }

    }

/*    public static boolean delSuppliers(int idSuppliers, String name, String phone, String address, String email, int idProduct) {

        try {
            Suppliers suppliers = new Suppliers(idSuppliers, name, phone, address, email, idProduct);
            return SuppliersQuery.delSuppliers(suppliers);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return false;
        }


    }


 */

    public static Suppliers getSuppliers(int idSupplier) {
        return suppliersQuery.getSuppliersId(idSupplier);
    }

    public static boolean delSuppliers(int suppliersId) {

        return SuppliersQuery.delSuppliers(suppliersId);

    }




}







