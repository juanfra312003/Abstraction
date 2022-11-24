package com.abstraction.controllers.Controllers_IniciarSesion;

import com.abstraction.business.FacadeGeneral;
import com.abstraction.business.IUsuario_facade;
import com.abstraction.entities.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;

public class Controller_Registrar_Usuario {
    IUsuario_facade facade;
    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button botonRegistrar;

    @FXML
    private Button botonVolverIngreso;

    @FXML
    private ChoiceBox<String> choiceBoxRol;

    @FXML
    private TextField textoUsuario;

    public void initialize(FacadeGeneral facade){
        this.facade=(IUsuario_facade) facade;
        choiceBoxRol.getItems().setAll("superusuario","vendedor","bodeguero","contador");
    }

    @FXML
    void onActionRegistrar(ActionEvent event) {
        if(textoUsuario.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Datos incorrectos");
            alert.setTitle("Error creando el usuario");
            alert.setContentText("El campo de usuario no puede estar vacio");
            alert.show();
            textoUsuario.clear();
        }
        else if (PasswordField.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Datos incorrectos");
            alert.setTitle("Error creando el usuario");
            alert.setContentText("El campo de contrasena no puede estar vacio");
            alert.show();
            PasswordField.clear();
        }
        else if(choiceBoxRol.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Datos incorrectos");
            alert.setTitle("Error creando el usuario");
            alert.setContentText("Elija un rol para el usuario");
            alert.show();
            textoUsuario.clear();
        }
        else{
            if(facade.create(new Usuario(textoUsuario.getText(), PasswordField.getText(), choiceBoxRol.getValue()))) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Usuario creado");
                alert.setTitle("Usuario creado correctamente");
                alert.setContentText("El usuario ha sido creado correctamente");
                alert.showAndWait();
                cargarIngreso();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setTitle("Error creando el usuario");
                alert.setContentText("Error desconocido");
                alert.show();
                textoUsuario.clear();
            }
        }
    }

    @FXML
    void onActionVolverIngreso(ActionEvent event) {
        cargarIngreso();
    }
    void cargarIngreso(){
        try {
            URL fxmlLocation = getClass().getResource("/presentation/View_IniciarSesion/mockupIniciarSesion.fxml");
            System.out.println(fxmlLocation);
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Abstraction");
            stage.setScene(scene);
            Controller_Iniciar_Sesion controller_iniciar_sesion= fxmlLoader.getController();
            controller_iniciar_sesion.setStage(stage);
            controller_iniciar_sesion.initialize(new FacadeGeneral());
            stage.show();
        }
        catch (Exception e){
            System.out.println("y tho");
            e.printStackTrace();
        }
    }
}
