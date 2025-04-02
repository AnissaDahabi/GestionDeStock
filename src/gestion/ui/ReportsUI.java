package gestion.ui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import gestion.dao.ReportsQuery;
import gestion.model.Reports;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;


public class ReportsUI {
    public static Scene createContent() {

        // Return button:
        Button returnBtn = new Button("Return");
        returnBtn.getStyleClass().add("returnBtn");

        returnBtn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getHomeScene());
        });

        HBox returnBtnContainer = new HBox(10);
        returnBtnContainer.setAlignment(Pos.TOP_RIGHT);
        returnBtnContainer.setPadding(new Insets(10, 10, 10, 10));
        returnBtnContainer.getChildren().add(returnBtn);

        // Title:
        HBox reportsTitle = new HBox(new Label("Reports"));
        reportsTitle.getStyleClass().add("reportsTitle");
        reportsTitle.setAlignment(Pos.CENTER);

        //Buttons

        Button generateReportsBtn = new Button("Generate");
        generateReportsBtn.getStyleClass().add("generateBtn");

        VBox reportsBtnContainer = new VBox(40);
        reportsBtnContainer.getStyleClass().add("reportsBtnContainer");
        reportsBtnContainer.setAlignment(Pos.CENTER);
        reportsBtnContainer.getChildren().add(generateReportsBtn);



        //Titre tu connais

        Label startDateLabel = new Label("Start Date : ");
        Label endDateLabel = new Label("End Date : ");
        Label displayLabel = new Label("Display : ");
        HBox labelContainer = new HBox();
        labelContainer.getStyleClass().add("labelContainer");
        labelContainer.getChildren().add(startDateLabel);
        labelContainer.getChildren().add(endDateLabel);
        labelContainer.getChildren().add(displayLabel);
        labelContainer.setAlignment(Pos.CENTER);


        //Fil déroulants

        DatePicker startDatePicker = new DatePicker();
        startDatePicker.getStyleClass().add("startDatePicker");
        reportsBtnContainer.getChildren().add(startDatePicker);

        DatePicker endDatePicker = new DatePicker();
        endDatePicker.getStyleClass().add("endDatePicker");
        reportsBtnContainer.getChildren().add(endDatePicker);

        //Radiobuttons

        RadioButton productCheck = new RadioButton("Products : ");
        RadioButton supplierCheck = new RadioButton("Suppliers : ");
        RadioButton salesCheck = new RadioButton("Sales : ");

        ToggleGroup toggleGroup = new ToggleGroup();
        productCheck.setToggleGroup(toggleGroup);
        supplierCheck.setToggleGroup(toggleGroup);
        salesCheck.setToggleGroup(toggleGroup);

        reportsBtnContainer.getChildren().add(productCheck);
        reportsBtnContainer.getChildren().add(supplierCheck);
        reportsBtnContainer.getChildren().add(salesCheck);





        generateReportsBtn.setOnAction(event -> {

            LocalDate startDate = startDatePicker.getValue();
            LocalDate endDate = endDatePicker.getValue();
            boolean product = productCheck.isSelected();
            boolean supplier = supplierCheck.isSelected();
            boolean sales = salesCheck.isSelected();


            StringBuilder query = new StringBuilder();
            if (sales) {
                query.append("SELECT s.id_sales, sup.id_supplier, p.id_product, sup.name_supplier, p.name_product, p.price_product, s.quantity_sales, s.price_sales, s.date_sales FROM Sales s JOIN Suppliers sup ON s.id_supplier = sup.id_supplier JOIN Products p ON s.id_product = p.id_product WHERE s.date_sales BETWEEN ? AND ?");
            } else if (product) {
                query.append("SELECT * FROM Products");
            } else if (supplier) {
                query.append("SELECT * FROM Suppliers");
            }

            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream("reports.pdf"));
                document.open();

                if (sales) {
                    PreparedStatement stmt = con.prepareStatement(query.toString());

                    stmt.setDate(1, java.sql.Date.valueOf(startDate));
                    stmt.setDate(2, java.sql.Date.valueOf(endDate));

                    ResultSet rs = stmt.executeQuery();

                    PdfPTable table = new PdfPTable(9);
                    table.addCell(new Phrase("Sales_ID"));
                    table.addCell(new Phrase("Supplier_ID"));
                    table.addCell(new Phrase("Product_ID"));
                    table.addCell(new Phrase("Supplier_Name"));
                    table.addCell(new Phrase("Product_Name"));
                    table.addCell(new Phrase("Unit_Price"));
                    table.addCell(new Phrase("Quantity"));
                    table.addCell(new Phrase("Sales_Price"));
                    table.addCell(new Phrase("Date"));

                    while (rs.next()) {
                        table.addCell(new Phrase(rs.getString("id_sales")));
                        table.addCell(new Phrase(rs.getString("id_supplier")));
                        table.addCell(new Phrase(rs.getString("id_product")));
                        table.addCell(new Phrase(rs.getString("name_sales")));
                        table.addCell(new Phrase(rs.getString("name_supplier")));
                        table.addCell(new Phrase(rs.getString("name_product")));
                        table.addCell(new Phrase(rs.getString("price_product")));
                        table.addCell(new Phrase(rs.getString("quantity_sales")));
                        table.addCell(new Phrase(rs.getString("price_sales")));
                        table.addCell(new Phrase(rs.getString("date_sales")));
                    }

                    stmt.close();
                    document.add(table);

                } else if (product) {
                    PreparedStatement stmt = con.prepareStatement(query.toString());
                    ResultSet rs = stmt.executeQuery();
                    PdfPTable table = new PdfPTable(5);
                    table.addCell(new Phrase("Id_Product"));
                    table.addCell(new Phrase("Id_Supplier"));
                    table.addCell(new Phrase("Product_Name"));
                    table.addCell(new Phrase("Product_Price"));
                    table.addCell(new Phrase("Product_Quantity"));

                    while (rs.next()) {
                        table.addCell(new Phrase(rs.getString("id_product")));
                        table.addCell(new Phrase(rs.getString("id_supplier")));
                        table.addCell(new Phrase(rs.getString("name_product")));
                        table.addCell(new Phrase(rs.getString("price_product")));
                        table.addCell(new Phrase(rs.getString("quantity_product")));
                    }
                    stmt.close();
                    document.add(table);

                } else if (supplier) {
                    PreparedStatement stmt = con.prepareStatement(query.toString());
                    ResultSet rs = stmt.executeQuery();
                    PdfPTable table = new PdfPTable(5);
                    table.addCell(new Phrase("Id_Supplier"));
                    table.addCell(new Phrase("Supplier_Name"));
                    table.addCell(new Phrase("Supplier_Phone"));
                    table.addCell(new Phrase("Suppler_Address"));
                    table.addCell(new Phrase("Supplier_Email"));

                    while (rs.next()) {
                        table.addCell(new Phrase(rs.getString("id_supplier")));
                        table.addCell(new Phrase(rs.getString("name_supplier")));
                        table.addCell(new Phrase(rs.getString("phone_supplier")));
                        table.addCell(new Phrase(rs.getString("address_supplier")));
                        table.addCell(new Phrase(rs.getString("email_supplier")));
                    }
                    stmt.close();
                    document.add(table);

                }
                File pdfFile = new File("reports.pdf");
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        desktop.open(pdfFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Desktop is not supported");
                    alert.showAndWait();
                }

                document.close();
                con.close();

            } catch (SQLException  | RuntimeException | FileNotFoundException | DocumentException e){
                throw new RuntimeException(e);
            }
        });

        VBox homeReports = new VBox(returnBtnContainer, reportsTitle, reportsBtnContainer, labelContainer);

        Scene reportsScene = new Scene(homeReports, 300, 600);
        reportsScene.getStylesheets().add("gestion/resources/reports.css");

        return reportsScene;
    }



}
