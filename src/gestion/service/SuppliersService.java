package gestion.service;

import gestion.dao.ProductsQuery;
import gestion.dao.SuppliersQuery;
import gestion.model.Suppliers;
import javafx.scene.control.Alert;

public class SuppliersService {

    private static SuppliersQuery suppliersQuery;
    private final ProductsQuery productsQuery;

    public SuppliersService(SuppliersQuery suppliersQuery, ProductsQuery productsQuery) {
        this.suppliersQuery = suppliersQuery;
        this.productsQuery = productsQuery;
    }


    public static boolean addSuppliers(int idSuppliers, String name, String phone, String address, String email) {

        try {
            Suppliers suppliers = new Suppliers(idSuppliers, name, phone, address, email);

            if(name.isEmpty() || phone.isEmpty() || address.isEmpty() || email.isEmpty()) {
                throw new IllegalArgumentException("Please fill all the required fields");
            }
            if (idSuppliers==0) {
                throw new IllegalArgumentException("Please fill all the required fields");
            }
            if (!email.contains("@")) {
                throw new IllegalArgumentException("Please fill a valid email address");
            }
            if (!isValidName(suppliers.getNameSupplier())) {
                throw new IllegalArgumentException("Name not valid");
            }
            if (!isValidPhone(suppliers.getPhoneSupplier())) {
                throw new IllegalArgumentException("Phone not valid");
            }
            if (!isValidEmail(suppliers.getEmailSupplier())) {
                throw new IllegalArgumentException("Email not valid");
            }


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

    public static boolean isValidName(String name) {
        return name.matches("[a-zA-Z\\s\\-']+");
    }

    public static boolean isValidPhone(String phone) {
        return phone.matches("\\d*");
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }


}







