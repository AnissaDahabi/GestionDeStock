module GestionDeStock {
    requires javafx.controls;

    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.base;
    requires java.desktop;


    opens gestion.dao;
    opens gestion.model;
    opens gestion.resources;
    opens gestion.service;
    opens gestion.ui;
    opens gestion.utils;
}