package gestion.ui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class ReportsUI {

    public static Scene createContent() {

        //Return button:
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

        //Title:
        HBox reportsTitle = new HBox(new Label("Reports"));
        reportsTitle.getStyleClass().add("reportsTitle");
        reportsTitle.setAlignment(Pos.CENTER);

        //Buttons
        Button generatePdfReportsBtn = new Button("Generate PDF");
        generatePdfReportsBtn.getStyleClass().add("generateBtn");

        Button generateExcelReportBtn = new Button("Generate Excel");
        generateExcelReportBtn.getStyleClass().add("generateBtn");

        VBox reportsBtnContainer = new VBox(10);
        reportsBtnContainer.getStyleClass().add("reportsBtnContainer");
        reportsBtnContainer.setAlignment(Pos.CENTER);
        reportsBtnContainer.getChildren().add(generatePdfReportsBtn);
        reportsBtnContainer.getChildren().add(generateExcelReportBtn);

        //Labels:
        Label startDateLabel = new Label("Start Date : ");
        Label endDateLabel = new Label("End Date : ");
        Label displayLabel = new Label("Display : ");

        //Fil déroulant:
        DatePicker startDatePicker = new DatePicker();
        startDatePicker.getStyleClass().add("startDatePicker");
        startDatePicker.setEditable(false);
        startDatePicker.setPrefWidth(100);
        reportsBtnContainer.getChildren().add(startDatePicker);

        DatePicker endDatePicker = new DatePicker();
        endDatePicker.getStyleClass().add("endDatePicker");
        endDatePicker.setEditable(false);
        reportsBtnContainer.getChildren().add(endDatePicker);

        //Radiobuttons:
        RadioButton productCheck = new RadioButton("Products");
        RadioButton supplierCheck = new RadioButton("Suppliers");
        RadioButton salesCheck = new RadioButton("Sales");

        ToggleGroup toggleGroup = new ToggleGroup();
        productCheck.setToggleGroup(toggleGroup);
        supplierCheck.setToggleGroup(toggleGroup);
        salesCheck.setToggleGroup(toggleGroup);

        GridPane reportsGrid = new GridPane();
        reportsGrid.getStyleClass().add("reportsGrid");
        reportsGrid.setAlignment(Pos.CENTER);
        reportsGrid.setPadding(new Insets(60, 0, 70, 0));

        reportsGrid.setVgap(20);
        reportsGrid.setHgap(20);
        reportsGrid.add(startDateLabel, 0, 0);
        reportsGrid.add(startDatePicker, 1, 0);
        reportsGrid.add(endDateLabel, 0, 1);
        reportsGrid.add(endDatePicker, 1, 1);
        reportsGrid.add(displayLabel, 0, 2);
        reportsGrid.add(productCheck, 1, 2);
        reportsGrid.add(supplierCheck, 1, 3);
        reportsGrid.add(salesCheck, 1, 4);

        generatePdfReportsBtn.setOnAction(event -> {

            LocalDate startDate = startDatePicker.getValue();
            LocalDate endDate = endDatePicker.getValue();
            boolean product = productCheck.isSelected();
            boolean supplier = supplierCheck.isSelected();
            boolean sales = salesCheck.isSelected();


            StringBuilder query = new StringBuilder();
            if (sales) {
                query.append("SELECT s.id_sales, sup.id_supplier, p.id_product, sup.name_supplier, p.name_product, p.price_product, s.quantity_sales, ROUND(s.price_sales, 2) AS price_sales, DATE_FORMAT(s.date_sales, '%d-%m-%Y') AS date_sales FROM Sales s JOIN Suppliers sup ON s.id_supplier = sup.id_supplier JOIN Products p ON s.id_product = p.id_product WHERE s.date_sales BETWEEN ? AND ?");
            } else if (product) {
                query.append("SELECT * FROM Products");
            } else if (supplier) {
                query.append("SELECT * FROM Suppliers");
            }
            Document document = new Document();

            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");

                if (sales) {

                    PdfWriter.getInstance(document, new FileOutputStream("reportSales.pdf"));
                    document.open();
                    PreparedStatement stmt = con.prepareStatement(query.toString());

                    stmt.setDate(1, java.sql.Date.valueOf(startDate));
                    stmt.setDate(2, java.sql.Date.valueOf(endDate));

                    ResultSet rs = stmt.executeQuery();

                    PdfPTable table = new PdfPTable(9);
                    table.setWidthPercentage(100);
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

                    PdfWriter.getInstance(document, new FileOutputStream("reportProduct.pdf"));
                    document.open();
                    PreparedStatement stmt = con.prepareStatement(query.toString());
                    ResultSet rs = stmt.executeQuery();
                    PdfPTable table = new PdfPTable(5);
                    table.setWidthPercentage(100);
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

                    PdfWriter.getInstance(document, new FileOutputStream("reportSupplier.pdf"));
                    document.open();
                    PreparedStatement stmt = con.prepareStatement(query.toString());
                    ResultSet rs = stmt.executeQuery();
                    PdfPTable table = new PdfPTable(5);
                    table.setWidthPercentage(100);
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
                if (sales) {
                    File pdfFile = new File("reportSales.pdf");
                    if (Desktop.isDesktopSupported()) {
                        Desktop desktop = Desktop.getDesktop();
                        try {
                            desktop.open(pdfFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle(null);
                        alert.setHeaderText(null);
                        alert.setContentText("Desktop is not supported");
                        DialogPane dialogPane = alert.getDialogPane();
                        dialogPane.setGraphic(null);
                        alert.initStyle(StageStyle.UTILITY);
                        alert.getDialogPane().getStylesheets().add("gestion/resources/suppliers.css");
                        alert.showAndWait();
                    }

                    document.close();
                    con.close();

                } if (product) {
                    File pdfFile = new File("reportProduct.pdf");
                    if (Desktop.isDesktopSupported()) {
                        Desktop desktop = Desktop.getDesktop();
                        try {
                            desktop.open(pdfFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle(null);
                        alert.setHeaderText(null);
                        alert.setContentText("Desktop is not supported");
                        DialogPane dialogPane = alert.getDialogPane();
                        dialogPane.setGraphic(null);
                        alert.initStyle(StageStyle.UTILITY);
                        alert.getDialogPane().getStylesheets().add("gestion/resources/reports.css");
                        alert.showAndWait();
                    }

                    document.close();
                    con.close();
                } if (supplier) {
                    File pdfFile = new File("reportSupplier.pdf");
                    if (Desktop.isDesktopSupported()) {
                        Desktop desktop = Desktop.getDesktop();
                        try {
                            desktop.open(pdfFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle(null);
                        alert.setHeaderText(null);
                        alert.setContentText("Desktop is not supported");
                        DialogPane dialogPane = alert.getDialogPane();
                        dialogPane.setGraphic(null);
                        alert.initStyle(StageStyle.UTILITY);
                        alert.getDialogPane().getStylesheets().add("gestion/resources/reports.css");
                        alert.showAndWait();
                    }

                    document.close();
                    con.close();
                }

            } catch (SQLException  | RuntimeException | FileNotFoundException | DocumentException e){
                throw new RuntimeException(e);
            }
        });

        generateExcelReportBtn.setOnAction(event -> {

            LocalDate startDate = startDatePicker.getValue();
            LocalDate endDate = endDatePicker.getValue();
            boolean product = productCheck.isSelected();
            boolean supplier = supplierCheck.isSelected();
            boolean sales = salesCheck.isSelected();

            StringBuilder query = new StringBuilder();
            if (sales) {
                query.append("SELECT s.id_sales, sup.id_supplier, p.id_product, sup.name_supplier, p.name_product, p.price_product, s.quantity_sales, ROUND(s.price_sales, 2) AS price_sales, DATE_FORMAT(s.date_sales, '%d-%m-%Y') AS date_sales FROM Sales s JOIN Suppliers sup ON s.id_supplier = sup.id_supplier JOIN Products p ON s.id_product = p.id_product WHERE s.date_sales BETWEEN ? AND ?");
            } else if (product) {
                query.append("SELECT * FROM Products");
            } else if (supplier) {
                query.append("SELECT * FROM Suppliers");
            }

            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetjava", "root", "root");
                PreparedStatement stmt = con.prepareStatement(query.toString());

                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Reports");
                if (sales) {
                    stmt.setDate(1, Date.valueOf(startDate));
                    stmt.setDate(2, Date.valueOf(endDate));
                }
                ResultSet rs = stmt.executeQuery();
                Row header = sheet.createRow(0);
                if (sales) {
                    header.createCell(0).setCellValue("Sales_ID");
                    header.createCell(1).setCellValue("Supplier_ID");
                    header.createCell(2).setCellValue("Product_ID");
                    header.createCell(3).setCellValue("Supplier_Name");
                    header.createCell(4).setCellValue("Product_Name");
                    header.createCell(5).setCellValue("Unit_Price");
                    header.createCell(6).setCellValue("Quantity");
                    header.createCell(7).setCellValue("Sales_Price");
                    header.createCell(8).setCellValue("Date");

                    for (int i = 0; i < 9; i++) {
                        sheet.autoSizeColumn(i);
                    }

                } else if (product) {
                    header.createCell(0).setCellValue("Id_Product");
                    header.createCell(1).setCellValue("Id_Supplier");
                    header.createCell(2).setCellValue("Product_Name");
                    header.createCell(3).setCellValue("Product_Price");
                    header.createCell(4).setCellValue("Product_Quantity");

                    for (int i = 0; i < 5; i++) {
                        sheet.autoSizeColumn(i);
                    }

                } else if (supplier) {
                    header.createCell(0).setCellValue("Id_Supplier");
                    header.createCell(1).setCellValue("Supplier_Name");
                    header.createCell(2).setCellValue("Supplier_Phone");
                    header.createCell(3).setCellValue("Supplier_Address");
                    header.createCell(4).setCellValue("Supplier_Email");

                    for (int i = 0; i < 5; i++) {
                        sheet.autoSizeColumn(i);
                    }
                }
                int rowIndex = 1;
                while (rs.next()) {
                    Row row = sheet.createRow(rowIndex);
                    if (sales) {
                        row.createCell(0).setCellValue(rs.getString("id_sales"));
                        row.createCell(1).setCellValue(rs.getString("id_supplier"));
                        row.createCell(2).setCellValue(rs.getString("id_product"));
                        row.createCell(3).setCellValue(rs.getString("name_supplier"));
                        row.createCell(4).setCellValue(rs.getString("name_product"));
                        row.createCell(5).setCellValue(rs.getString("price_product"));
                        row.createCell(6).setCellValue(rs.getString("quantity_sales"));
                        row.createCell(7).setCellValue(rs.getString("price_sales"));
                        row.createCell(8).setCellValue(rs.getString("date_sales"));

                        rowIndex++;
                        for (int i = 0; i < 9; i++) {
                            sheet.autoSizeColumn(i);
                        }
                    } else if (product) {
                        row.createCell(0).setCellValue(rs.getString("id_product"));
                        row.createCell(1).setCellValue(rs.getString("id_supplier"));
                        row.createCell(2).setCellValue(rs.getString("name_product"));
                        row.createCell(3).setCellValue(rs.getString("price_product"));
                        row.createCell(4).setCellValue(rs.getString("quantity_product"));

                        rowIndex++;
                        for (int i = 0; i < 5; i++) {
                            sheet.autoSizeColumn(i);
                        }
                    } else if (supplier) {
                        row.createCell(0).setCellValue(rs.getString("id_supplier"));
                        row.createCell(1).setCellValue(rs.getString("name_supplier"));
                        row.createCell(2).setCellValue(rs.getString("phone_supplier"));
                        row.createCell(3).setCellValue(rs.getString("address_supplier"));
                        row.createCell(4).setCellValue(rs.getString("email_supplier"));

                        rowIndex++;
                        for (int i = 0; i < 5; i++) {
                            sheet.autoSizeColumn(i);
                        }
                    }
                }
                try (FileOutputStream fileOutSales = new FileOutputStream("reportSales.xlsx")) {
                    FileOutputStream fileOutProduct = new FileOutputStream("reportProduct.xlsx");
                    FileOutputStream fileOutSupplier = new FileOutputStream("reportSupplier.xlsx");
                    workbook.write(fileOutSales);
                    workbook.write(fileOutProduct);
                    workbook.write(fileOutSupplier);
                }
                workbook.close();
                rs.close();

                if (sales) {
                    File excelFile = new File("reportSales.xlsx");
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(excelFile);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle(null);
                        alert.setHeaderText(null);
                        alert.setContentText("Desktop is not supported");

                        DialogPane dialogPane = alert.getDialogPane();
                        dialogPane.setGraphic(null);
                        alert.initStyle(StageStyle.UTILITY);
                        alert.getDialogPane().getStylesheets().add("gestion/resources/reports.css");
                        alert.showAndWait();
                    }
                } else if (product) {
                    File excelFile = new File("reportProduct.xlsx");
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(excelFile);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle(null);
                        alert.setHeaderText(null);
                        alert.setContentText("Desktop is not supported");

                        DialogPane dialogPane = alert.getDialogPane();
                        dialogPane.setGraphic(null);
                        alert.initStyle(StageStyle.UTILITY);
                        alert.getDialogPane().getStylesheets().add("gestion/resources/reports.css");
                        alert.showAndWait();
                    }
                } else if (supplier) {
                    File excelFile = new File("reportSupplier.xlsx");
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(excelFile);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle(null);
                        alert.setHeaderText(null);
                        alert.setContentText("Desktop is not supported");

                        DialogPane dialogPane = alert.getDialogPane();
                        dialogPane.setGraphic(null);
                        alert.initStyle(StageStyle.UTILITY);
                        alert.getDialogPane().getStylesheets().add("gestion/resources/reports.css");
                        alert.showAndWait();
                    }
                }

            } catch (SQLException | IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });

        VBox homeReports = new VBox(returnBtnContainer, reportsTitle, reportsGrid, reportsBtnContainer);

        Scene reportsScene = new Scene(homeReports, 300, 600);
        reportsScene.getStylesheets().add("gestion/resources/reports.css");

        return reportsScene;
    }
}

