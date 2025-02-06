module GestionDeStock {
    requires javafx.controls;

    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.base;
    requires mysql.connector.j;
    requires org.controlsfx.controls;
    requires java.datatransfer;


    opens gestion.dao;
    opens gestion.model;
    opens gestion.resources;
    opens gestion.service;
    opens gestion.ui;
    opens gestion.utils;
}