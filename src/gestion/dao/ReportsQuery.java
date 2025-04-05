package gestion.dao;

import gestion.model.Reports;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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



                Reports reports = new Reports();
                reportsList.add(reports);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportsList;

    }



}
