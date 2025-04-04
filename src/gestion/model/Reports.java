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

}

