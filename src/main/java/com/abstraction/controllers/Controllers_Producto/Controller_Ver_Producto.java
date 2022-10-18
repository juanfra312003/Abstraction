package com.abstraction.controllers.Controllers_Producto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Controller_Ver_Producto {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    @FXML
    private Button botonCerrarSesion;

    @FXML
    private Button botonCotizaciones;

    @FXML
    private Button botonDashBoard;

    @FXML
    private Button botonFacturacion;

    @FXML
    private Button botonPedidos;

    @FXML
    private Button botonPerfil;

    @FXML
    private Button botonProductos;

    @FXML
    private Button botonRegresar;

    @FXML
    private TextField cantidadesExisText;

    @FXML
    private TextField nombreProductoText;

    @FXML
    private TextField precioUnitarioText;

    @FXML
    private TextField referenciaProductoText;

    @FXML
    void onActionCerrarSesion(ActionEvent event) {

    }

    @FXML
    void onActionCotizaciones(ActionEvent event) {

    }

    @FXML
    void onActionDashBoard(ActionEvent event) {

    }

    @FXML
    void onActionFacturacion(ActionEvent event) {

    }

    @FXML
    void onActionPedidos(ActionEvent event) {

    }

    @FXML
    void onActionPerfil(ActionEvent event) {

    }

    @FXML
    void onActionProductos(ActionEvent event) {

    }

    @FXML
    void onActionRegresar(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        URL fxmlLocation = getClass().getResource("/presentation/View_Productos/mockupProductos.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abstraction");
        stage.setScene(scene);
        Controller_Lista_Productos controller_lista_productos = fxmlLoader.getController();
        controller_lista_productos.setStage(stage);
        stage.show();
        this.stage.close();
    }

}
