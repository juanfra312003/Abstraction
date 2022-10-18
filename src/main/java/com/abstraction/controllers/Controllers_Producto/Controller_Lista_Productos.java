package com.abstraction.controllers.Controllers_Producto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.scene.control.TableColumn;

import java.io.IOException;
import java.net.URL;

public class Controller_Lista_Productos {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    @FXML
    private Button botonBuscar;

    @FXML
    private Button botonCerrarSesion;

    @FXML
    private Button botonCotizaciones;

    @FXML
    private Button botonCrearProducto;

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
    private TableColumn<?, ?> columnaActualizarP;

    @FXML
    private TableColumn<?, ?> columnaEliminarP;

    @FXML
    private TableColumn<?, ?> columnaNombre;

    @FXML
    private TableColumn<?, ?> columnaNumeroExistencias;

    @FXML
    private TableColumn<?, ?> columnaPrecio;

    @FXML
    private TableColumn<?, ?> columnaReferencia;

    @FXML
    private TableColumn<?, ?> columnaVerProducto;

    @FXML
    private ComboBox<?> comboBoxFiltrar;

    @FXML
    private Text textFieldBusquedaProducto;

    @FXML
    void OnActionComboBoxFiltrar(ActionEvent event) {

    }

    @FXML
    void OnActionCrearProducto(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        URL fxmlLocation = getClass().getResource("/presentation/View_Productos/MockupCrearProducto.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abstraction");
        stage.setScene(scene);
        Controller_Crear_Producto controller_crear_producto = fxmlLoader.getController();
        controller_crear_producto.setStage(stage);
        stage.show();
        this.stage.close();
    }

    @FXML
    void OnActionLogOut(ActionEvent event) {

    }

    @FXML
    void OnActionPerfil(ActionEvent event) {

    }

    @FXML
    void onActionBuscar(ActionEvent event) {

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
    void onActionProductos(ActionEvent event) {

    }


}
