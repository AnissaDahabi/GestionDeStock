package gestion.model;

public class Reports {


    private int report_id;
    private String start_date;
    private String end_date;
    private double total_sales;
    private int total_quantity;
    private int id_supplier;
    private int id_product;


    public Reports() {
       this.report_id = 0;
       this.start_date = "";
       this.end_date = "";
       this.total_sales = 0;
       this.total_quantity = 0;
       this.id_supplier = 0;
       this.id_product = 0;
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public double getTotal_sales() {
        return total_sales;
    }

    public void setTotal_sales(double total_sales) {
        this.total_sales = total_sales;
    }

    public int getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(int total_quantity) {
        this.total_quantity = total_quantity;
    }

    public int getId_supplier() {
        return id_supplier;
    }

    public void setId_supplier(int id_supplier) {
        this.id_supplier = id_supplier;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }
}

