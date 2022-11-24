package com.abstraction.controllers.Controllers_IniciarSesion;

import com.abstraction.business.*;
import com.abstraction.controllers.Controllers_Producto.Controller_Lista_Productos;
import com.sun.javafx.scene.control.behavior.PasswordFieldBehavior;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.TextFieldSkin;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import com.abstraction.business.FacadeGeneral;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.Objects;

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
    private Button botonIngresar;

    @FXML
    private TextField textoUsuario;

    @FXML
    private PasswordField PasswordField;

    @FXML
    void onActionIngresar(ActionEvent event) {
        try {
            textoUsuario.getText();


            String password="hi";

            String passwordWrote="";

            passwordWrote=PasswordField.getText();
            System.out.println(passwordWrote);

            String username=textoUsuario.getText();
            String usernameFind="usuariogenial";

            if (Objects.equals(passwordWrote, password)&&(Objects.equals(username, usernameFind))) {
                PasswordField.clear();
                textoUsuario.clear();
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

            } else {
                if(!Objects.equals(username, usernameFind)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Usuario incorrecto");
                    alert.setTitle("Error en el usuario");
                    alert.setContentText("Ingrese nuevamente el usuario.");
                    alert.show();
                    PasswordField.clear();
                }
                else if(!Objects.equals(passwordWrote, password)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Contraseña incorrecta");
                    alert.setTitle("Error en la contraseña");
                    alert.setContentText("Ingrese nuevamente la contraseña.");
                    alert.show();
                }
                else if(!Objects.equals(username, usernameFind)&&!Objects.equals(passwordWrote, password)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Datos incorrectos");
                    alert.setTitle("Error en el usuario y la contraseña");
                    alert.setContentText("Ingrese nuevamente el usuario y la contraseña.");
                    alert.show();
                    PasswordField.clear();
                }
            }
            textoUsuario.clear();

        } catch (IOException e) {
            System.out.printf(e.getMessage());
        }
    }
}
