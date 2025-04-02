module GestionDeStock {
    requires javafx.controls;

    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.base;
    requires mysql.connector.j;
    requires org.controlsfx.controls;
    requires com.almasb.fxgl.scene;
    requires itextpdf;
    requires java.desktop;


    opens gestion.dao;
    opens gestion.model;
    opens gestion.resources;
    opens gestion.service;
    opens gestion.ui;
    opens gestion.utils;
}