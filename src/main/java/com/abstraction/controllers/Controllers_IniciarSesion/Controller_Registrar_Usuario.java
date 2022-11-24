package com.abstraction.controllers.Controllers_IniciarSesion;

import com.abstraction.business.FacadeGeneral;
import com.abstraction.business.IUsuario_facade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    private ChoiceBox<?> choiceBoxRol;

    @FXML
    private TextField textoUsuario;

    public void initialize(FacadeGeneral facade){
        this.facade=(IUsuario_facade) facade;
    }

    @FXML
    void onActionRegistrar(ActionEvent event) {

    }

    @FXML
    void onActionVolverIngreso(ActionEvent event) {
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
