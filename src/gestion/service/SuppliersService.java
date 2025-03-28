package gestion.service;

import gestion.dao.ProductsQuery;
import gestion.dao.SuppliersQuery;
import gestion.model.Suppliers;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class SuppliersService {

    private static SuppliersQuery suppliersQuery;
    private final ProductsQuery productsQuery;

    public SuppliersService(SuppliersQuery suppliersQuery, ProductsQuery productsQuery) {
        this.suppliersQuery = suppliersQuery;
        this.productsQuery = productsQuery;
    }


    public static boolean addSuppliers(int idSuppliers, String name, String phone, String address, String email) {

        try {
            if(name.isEmpty() || phone.isEmpty() || address.isEmpty() || email.isEmpty()) {
                throw new IllegalArgumentException("Please fill all the required fields");
            }
            if (idSuppliers==0) {
                throw new IllegalArgumentException("Please fill all the required fields");
            }
            if (!email.contains("@")) {
                throw new IllegalArgumentException("Please fill a valid email address");
            }

            Suppliers suppliers = new Suppliers(idSuppliers, name, phone, address, email);

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
        try {
            return SuppliersQuery.getSuppliersId(idSupplier);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return null;
        }
    }

    public static boolean delSuppliers(int idSupplier) {
        try {
            return SuppliersQuery.delSuppliers(idSupplier);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return false;
        }

    }

   // public static boolean showEditedSuppliers(TextField idSuppliersInput, int idSuppliers) {}

  /*  public static boolean editSuppliers(int idSuppliers, String name, String phone, String address, String email, int idProduct) {
        Suppliers suppliers = new Suppliers(idSuppliers, name, phone, address, email, idProduct);
        return SuppliersQuery.editSuppliers(suppliers);
    }
*/

}







