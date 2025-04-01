package gestion.dao;

import gestion.model.Reports;
import gestion.ui.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.*;


public class ReportsQuery {

    public static ObservableList<Reports> getReports() {
        ObservableList<Reports> reportsList = FXCollections.observableArrayList();

        String query = "SELECT * FROM Sales";

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {

                String nameSupplier = rs.getString("name_supplier");
                String nameProduct = rs.getString("name_product");
                double priceSales = rs.getDouble("price_sales");
                int quantitySales= rs.getInt("quantity_sales");
                String dateSales = rs.getString("date_sales");



                Reports reports = new Reports(nameSupplier, nameProduct, priceSales, quantitySales,dateSales);
                reportsList.add(reports);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportsList;

    }



}
