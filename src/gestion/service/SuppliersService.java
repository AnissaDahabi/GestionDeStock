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


    public static boolean addSuppliers(Suppliers suppliers, Products products) {


        if (suppliers.getNameSupplier() == null || suppliers.getNameSupplier().isEmpty() || suppliers.getAddressSupplier() == null || suppliers.getAddressSupplier().isEmpty() || suppliers.getEmailSupplier() == null || !suppliers.getEmailSupplier().contains("@" + ".")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please, fill all the fields correctly.");
            alert.showAndWait();
            return false;
        }
        return SuppliersQuery.addSuppliers(suppliers, products);
    }

    //public boolean delSuppliers(Suppliers suppliers) {
    //}





}







