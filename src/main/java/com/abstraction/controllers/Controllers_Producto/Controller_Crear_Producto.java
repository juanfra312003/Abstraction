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

public class Controller_Crear_Producto {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    @FXML
    private Button crearProductoBoton;

    @FXML
    private Button regresarBoton;

    @FXML
    private TextField textCantidadesExistentes;

    @FXML
    private TextField textDescripcion;

    @FXML
    private TextField textNombreProducto;

    @FXML
    private TextField textPrecioProducto;

    @FXML
    private TextField textReferenciaProducto;

    @FXML
    void OnActionCrearBoton(ActionEvent event) {

    }

    @FXML
    void OnActionRegresar(ActionEvent event) throws IOException {
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
