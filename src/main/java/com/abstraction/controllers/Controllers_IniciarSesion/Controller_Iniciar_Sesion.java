package com.abstraction.controllers.Controllers_IniciarSesion;

import com.abstraction.business.*;
import com.abstraction.controllers.Controllers_Producto.Controller_Lista_Productos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import com.abstraction.business.FacadeGeneral;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class Controller_Iniciar_Sesion {
    FacadeGeneral facade;
    private Stage stage;

    public void initialize(FacadeGeneral facade){
        this.facade=facade;
    }

    /**
     * Getters y Setters
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    /**
     * FXML Elements
     */

    @FXML
    private TextField PasswordText;

    @FXML
    private Button botonIngresar;

    @FXML
    private TextField textoUsuario;

    @FXML
    void onActionIngresar(ActionEvent event) {
        try {
            Stage stage = new Stage();
            URL fxmlLocation = getClass().getResource("/presentation/View_Productos/mockupProductos.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Abstraction");
            stage.setScene(scene);
            Controller_Lista_Productos controller_lista_productos = fxmlLoader.getController();
            controller_lista_productos.initialize((IProducto_facade) this.facade);
            controller_lista_productos.setStage(stage);
            stage.show();
            this.stage.close();
        } catch (IOException e) {
            System.out.printf(e.getMessage());
        }
    }
}
