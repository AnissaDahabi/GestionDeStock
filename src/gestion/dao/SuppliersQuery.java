package gestion.dao;

import gestion.model.Products;
import gestion.model.Suppliers;
import gestion.ui.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.*;

public class SuppliersQuery {


    public static boolean addSuppliers(Suppliers suppliers) {

        try {
            Connection con =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");
            String query = "INSERT INTO Suppliers (id_supplier, name_supplier, phone_supplier, address_supplier, email_supplier) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setInt(1, suppliers.getIdSupplier());
            pstmt.setString(2, suppliers.getNameSupplier());
            pstmt.setString(3, suppliers.getPhoneSupplier());
            pstmt.setString(4, suppliers.getAddressSupplier());
            pstmt.setString(5, suppliers.getEmailSupplier());


            return pstmt.executeUpdate() > 0; //en gros ça return true parce que y'a eu une affectation
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delSuppliers(int suppliersId) {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");
            String query = "DELETE FROM Suppliers WHERE id_supplier = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, suppliersId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting supplier", e.getCause());
        }

    }

    public static ObservableList<Suppliers> getSuppliersID() {
        ObservableList<Suppliers> suppliersList = FXCollections.observableArrayList();

        String query = "SELECT * FROM Suppliers";

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                       int id = rs.getInt("id_supplier");
                       String name = rs.getString("name_supplier");
                       String phone = rs.getString("phone_supplier");
                       String address = rs.getString("address_supplier");
                       String mail = rs.getString("email_supplier");

                        Suppliers suppliers = new Suppliers(id, name, phone, address, mail);
                        suppliersList.add(suppliers);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliersList;
    }


    public static Suppliers getSuppliersId(int idSupplier) {
        String query = "SELECT * FROM Suppliers WHERE id_supplier = ?";

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");
            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setInt(1, idSupplier);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Suppliers(
                        rs.getInt("id_supplier"),
                        rs.getString("name_supplier"),
                        rs.getString("phone_supplier"),
                        rs.getString("address_supplier"),
                        rs.getString("email_supplier")
                );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return null;
    }



    public static void editSuppliers(int idSuppliersInput , TextField nameSuppliersInput, TextField phoneSuppliersInput, TextField addressSuppliersInput, TextField emailSuppliersInput) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query1 = "SELECT id_supplier, name_supplier, phone_supplier, address_supplier, email_supplier FROM Suppliers WHERE id_supplier = ?";
            PreparedStatement pstmt1 = con.prepareStatement(query1);
            pstmt1.setInt(1, idSuppliersInput);

            ResultSet rs = pstmt1.executeQuery();

            if (!rs.next()) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("No supplier found with this ID");
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setGraphic(null);
                alert.initStyle(StageStyle.UTILITY);
                alert.getDialogPane().getStylesheets().add("gestion/resources/suppliers.css");
                alert.showAndWait();

                rs.close();
                pstmt1.close();
                con.close();
                return;
            }

            String currentName = rs.getString("name_supplier");
            String currentPhone = rs.getString("phone_supplier");
            String currentAddress = rs.getString("address_supplier");
            String currentEmail = rs.getString("email_supplier");

            rs.close();
            pstmt1.close();

            String newName = nameSuppliersInput.getText();
            String newPhone = phoneSuppliersInput.getText();
            String newAddress = addressSuppliersInput.getText();
            String newEmail = emailSuppliersInput.getText();

            boolean noChanges = currentName.equals(newName) &&
                    currentPhone.equals(newPhone) &&
                    currentAddress.equals(newAddress) &&
                    currentEmail.equals(newEmail);

            if (noChanges) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("No changes were made.");
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setGraphic(null);
                alert.initStyle(StageStyle.UTILITY);
                alert.getDialogPane().getStylesheets().add("gestion/resources/suppliers.css");
                alert.showAndWait();
            } else {
                String query = "UPDATE Suppliers SET name_supplier = ?, phone_supplier = ?, address_supplier = ?, email_supplier = ? WHERE id_supplier = ?";
                PreparedStatement pstmt = con.prepareStatement(query);

                pstmt.setString(1, nameSuppliersInput.getText());
                pstmt.setString(2, phoneSuppliersInput.getText());
                pstmt.setString(3, addressSuppliersInput.getText());
                pstmt.setString(4, emailSuppliersInput.getText());

                pstmt.executeUpdate();

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Supplier edited successfully");
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setGraphic(null);
                alert.initStyle(StageStyle.UTILITY);
                alert.getDialogPane().getStylesheets().add("gestion/resources/suppliers.css");
                alert.showAndWait();

                pstmt.close();
            }

            con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }




    public static boolean showSuppliers(TableView<Suppliers> suppliersTable) {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query = "SELECT id_supplier, name_supplier, phone_supplier, address_supplier, email_supplier FROM Suppliers";
            PreparedStatement pstmt = con.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                Suppliers suppliers = new Suppliers(
                        rs.getInt("id_supplier"),
                        rs.getString("name_supplier"),
                        rs.getString("phone_supplier"),
                        rs.getString("address_supplier"),
                        rs.getString("email_supplier")
                );
                suppliersTable.getItems().add(suppliers);
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database error: " + e.getMessage());        }
        return false;
    }

    public static ObservableList<Suppliers> searchSuppliers(TableView<Suppliers> suppliersTable) {
        ObservableList<Suppliers> suppliersList = FXCollections.observableArrayList();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query = "SELECT id_supplier, name_supplier, phone_supplier, address_supplier, email_supplier FROM Suppliers";
            PreparedStatement pstmt = con.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                Suppliers suppliers = new Suppliers(
                        rs.getInt("id_supplier"),
                        rs.getString("name_supplier"),
                        rs.getString("phone_supplier"),
                        rs.getString("address_supplier"),
                        rs.getString("email_supplier")
                );
                suppliersList.add(suppliers);
            }
            if (suppliersTable != null) {
                suppliersTable.setItems(suppliersList);
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database error: " + e.getMessage());
        }
        return suppliersList;
    }

}



