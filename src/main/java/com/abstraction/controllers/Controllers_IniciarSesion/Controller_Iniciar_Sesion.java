package com.abstraction.controllers.Controllers_IniciarSesion;

import com.abstraction.controllers.Controllers_Cotizacion.Controller_Lista_Cotizaciones;
import com.abstraction.controllers.Controllers_DashBoard.Controller_DashBoard;
import com.abstraction.controllers.Controllers_Factura.Controller_Lista_Facturas;
import com.abstraction.controllers.Controllers_Pedido.Controller_Lista_Pedidos;
import com.abstraction.controllers.Controllers_Perfil_Aux.Controller_Ver_Perfil;
import com.abstraction.controllers.Controllers_Producto.Controller_Actualizar_Producto;
import com.abstraction.entities.Producto;
import com.abstraction.entities.Usuario;
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
    IUsuario_facade facade;
    private Stage stage;

    public void initialize(FacadeGeneral facade){
        this.facade=(IUsuario_facade) facade;
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
        textoUsuario.getText();
        String passwordWrote="";
        passwordWrote=PasswordField.getText();
        System.out.println(passwordWrote);
        String username=textoUsuario.getText();
        Usuario usuario = facade.validar(new Usuario(username, passwordWrote, "superusuario"));

        if (usuario != null) {
            PasswordField.clear();
            textoUsuario.clear();
            if(usuario.getRol().equals("superusuario")) cargarListaProductos();
            else if(usuario.getRol().equals("vendedor")) cargarListaCotizaciones();
            else if(usuario.getRol().equals("bodeguero")) cargarListaProductos();
            else cargarDashboard();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Datos incorrectos");
            alert.setTitle("Error en el usuario y la contraseña");
            alert.setContentText("Ingrese nuevamente el usuario y la contraseña.");
            alert.show();
            PasswordField.clear();
        }
        textoUsuario.clear();
    }

    /**
     * Cambios de pantalla
     */

    // Cambio a Lista_productos
    void cargarListaProductos() {
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
    void cargarActualizar(Producto producto) {
        try {
            Stage stage = new Stage();
            URL fxmlLocation = getClass().getResource("/presentation/View_Productos/mockupActualizarPrdocuto.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Abstraction");
            stage.setScene(scene);
            Controller_Actualizar_Producto controller_actualizar_producto= fxmlLoader.getController();
            controller_actualizar_producto.initialize((FacadeGeneral) this.facade,producto);
            controller_actualizar_producto.setStage(stage);
            stage.show();
            this.stage.close();
        } catch (IOException e) {
            System.out.printf(e.getMessage());
        }
    }

    //Cambio a perfil
    void cargarPerfil() {
        try {
            Stage stage = new Stage();
            URL fxmlLocation = getClass().getResource("/presentation/View_Perfil_Aux/mockupVerPerfil.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Abstraction");
            stage.setScene(scene);
            Controller_Ver_Perfil controller_ver_perfil = fxmlLoader.getController();
            controller_ver_perfil.initialize((FacadeGeneral) this.facade);
            controller_ver_perfil.setStage(stage);
            stage.show();
            this.stage.close();
        } catch (IOException e) {
            System.out.printf(e.getMessage());
        }
    }
    //cambio a Cerrar Sesion
    void cargarCerrarSesion() {
        try {
            URL fxmlLocation = getClass().getResource("/presentation/View_IniciarSesion/mockupIniciarSesion.fxml");
            System.out.println(fxmlLocation);
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Abstraction");
            stage.setScene(scene);
            Controller_Iniciar_Sesion controladorIniciarSesion= fxmlLoader.getController();
            controladorIniciarSesion.setStage(stage);
            controladorIniciarSesion.initialize(new FacadeGeneral());
            stage.show();
        }
        catch (Exception e){
            System.out.println("y tho");
            e.printStackTrace();
        }
    }


    //Cambio a Lista_pedidos
    void cargarListaPedidos() {
        try {
            Stage stage = new Stage();
            URL fxmlLocation = getClass().getResource("/presentation/View_Pedidos/mockupListaPedidos.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Abstraction");
            stage.setScene(scene);
            Controller_Lista_Pedidos controller_lista_pedidos = fxmlLoader.getController();
            controller_lista_pedidos.initialize((IPedido_facade) this.facade);
            controller_lista_pedidos.setStage(stage);
            stage.show();
            this.stage.close();
        } catch (IOException e) {
            System.out.printf(e.getMessage());
        }
    }

    //Cargar Lista_facturas
    void cargarListaFacturas() {
        try {
            Stage stage = new Stage();
            URL fxmlLocation = getClass().getResource("/presentation/View_Facturas/mockupListaFacturas.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Abstraction");
            stage.setScene(scene);
            Controller_Lista_Facturas controller_lista_facturas = fxmlLoader.getController();
            controller_lista_facturas.initialize((IFactura_facade) this.facade);
            controller_lista_facturas.setStage(stage);
            stage.show();
            this.stage.close();
        } catch (IOException e) {
            System.out.printf(e.getMessage());
        }
    }

    //Cargar Dashboard
    void cargarDashboard() {
        try {
            Stage stage = new Stage();
            URL fxmlLocation = getClass().getResource("/presentation/View_DashBoard/MockupDASHBOARD.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Abstraction");
            stage.setScene(scene);
            Controller_DashBoard controller_dashBoard = fxmlLoader.getController();
            controller_dashBoard.initialize((IDashboard_facade) this.facade);
            controller_dashBoard.setStage(stage);
            stage.show();
            this.stage.close();
        } catch (IOException e) {
            System.out.printf(e.getMessage());
        }
    }

    //Cargar Lista_cotizaciones
    void cargarListaCotizaciones() {
        try {
            Stage stage = new Stage();
            URL fxmlLocation = getClass().getResource("/presentation/View_Cotizaciones/mockupListaCotizaciones.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Abstraction");
            stage.setScene(scene);
            Controller_Lista_Cotizaciones controller_lista_cotizaciones = fxmlLoader.getController();
            controller_lista_cotizaciones.initialize((ICotizacion_facade) this.facade);
            controller_lista_cotizaciones.setStage(stage);
            stage.show();
            this.stage.close();
        } catch (IOException e) {
            System.out.printf(e.getMessage());
        }
    }
}
