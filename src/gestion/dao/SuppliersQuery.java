package gestion.dao;

import gestion.model.Products;
import gestion.model.Suppliers;
import gestion.ui.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.sql.*;

public class SuppliersQuery {


    private static int currentSuppliersId;
    //private static int currentPhoneSuppliers;

    /*

    public static void addSuppliers(TextField idSupplierInput, TextField nameSupplierInput, TextField phoneSupplierInput, TextField addressSupplierInput, TextField emailSupplierInput, TextField productInput) {
        try {
            Connection con =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query = "INSERT INTO Supplier (id_supplier, name_supplier, phone_supplier, address_supplier, email_supplier, id_product) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);

            //Récupération des données saisies par l'utilisateur
            int idSupplier = Integer.parseInt(idSupplierInput.getText());
            String nameSupplier = nameSupplierInput.getText();
            int phoneSupplier = Integer.parseInt(phoneSupplierInput.getText());
            String addressSupplier = addressSupplierInput.getText();
            String emailSupplier = emailSupplierInput.getText();
            int productId = Integer.parseInt(productInput.getText());

            //Remplissage des paramètres de la requête SQL
            pstmt.setInt(1, idSupplier);
            pstmt.setString(2, nameSupplier);
            pstmt.setInt(3, phoneSupplier);
            pstmt.setString(4, addressSupplier);
            pstmt.setString(5, emailSupplier);
            pstmt.setInt(6, productId);

            //Exécution de la requête SQL
            int rowsAffected = pstmt.executeUpdate();

            //Fermeture de la connexion et du PreparedStatement
            pstmt.close();
            con.close();

            //Message de succès
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success !");
            alert.setHeaderText(null);
            alert.setContentText("Supplier added successfully");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error" + e.getMessage());
            alert.showAndWait();
        }
    }
*/
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




    public static void showEditedSuppliers(TextField idSuppliersInput,  Stage stage) {
        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query = "SELECT id_supplier, name_supplier, phone_supplier, address_supplier, email_supplier FROM Suppliers WHERE id_supplier = ?";
            PreparedStatement pstmt = con.prepareStatement(query);

            try {
                currentSuppliersId  = Integer.parseInt(idSuppliersInput.getText());
                pstmt.setInt(1, currentSuppliersId );

            } catch (NumberFormatException ex) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Supplier ID, please enter an integer");
                alert.setContentText("Error: " + ex.getMessage());
                alert.showAndWait();
                return;
            }

            try (ResultSet resultSet = pstmt.executeQuery()) {

                if (resultSet.next()) {
                    int idSuppliersSql = resultSet.getInt("id_supplier");
                    String nameSuppliersSql = resultSet.getString("name_supplier");
                    int phoneSuppliersSql = resultSet.getInt("phone_supplier");
                    String addressSuppliersSql = resultSet.getString("address_supplier");
                    String emailSuppliersSql = resultSet.getString("email_supplier");

                    stage.setScene(SceneManager.getEditSuppliersScene2(idSuppliersSql, nameSuppliersSql, phoneSuppliersSql, addressSuppliersSql, emailSuppliersSql));

                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Supplier not found");
                    alert.setContentText("No supplier found with this ID");
                    alert.showAndWait();
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error encountered");
            alert.setContentText("Error: " + ex.getMessage());
            alert.showAndWait();
        }
    }

 /*   public static boolean editSuppliers(Suppliers suppliers) {
        String query = "UPDATE Suppliers SET name_supplier = ?, phone_supplier = ?, address_supplier = ?, email_supplier = ? WHERE id_supplier = ?";

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, suppliers.getNameSupplier());
            pstmt.setString(2, suppliers.getPhoneSupplier());
            pstmt.setString(3, suppliers.getAddressSupplier());
            pstmt.setString(4, suppliers.getEmailSupplier());
            pstmt.setInt(5, suppliers.getIdSupplier());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


  */
    public static void editSuppliers(TextField nameSuppliersInput, TextField phoneSuppliersInput, TextField addressSuppliersInput, TextField emailSuppliersInput) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

            String query = "UPDATE Suppliers SET name_supplier = ?, phone_supplier = ?, address_supplier = ?, email_supplier = ? WHERE id_supplier = ?";
            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setString(1, nameSuppliersInput.getText());

            try {
                pstmt.setInt(2, Integer.parseInt(phoneSuppliersInput.getText()));
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid input");
                alert.setContentText("Please enter a valid phone number");
                return;
            }

            pstmt.setString(3, addressSuppliersInput.getText());
            pstmt.setString(4, emailSuppliersInput.getText());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText(null);
                alert.setContentText("Supplier edited successfully");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("No Changes");
                alert.setHeaderText(null);
                alert.setContentText("No changes were made.");
                alert.showAndWait();
            }

            pstmt.close();
            con.close();

        } catch (SQLException ex) {
            // Gestion des erreurs SQL
            ex.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error encountered while editing the supplier");
            alert.setContentText("Error: " + ex.getMessage());
            alert.showAndWait();
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



}



