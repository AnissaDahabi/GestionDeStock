package gestion.service;

import gestion.dao.ProductsQuery;
import gestion.dao.SuppliersQuery;
import gestion.model.Suppliers;
import gestion.model.Products;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.util.function.Supplier;

public class SuppliersService {
    private final SuppliersQuery suppliersQuery;
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

    //public boolean delSuppliers(Suppliers suppliers) {
    //}





}







