package com.abstraction.controllers.Controllers_Producto;

import com.abstraction.business.FacadeGeneral;
import com.abstraction.business.IProducto_facade;
import com.abstraction.controllers.Controllers_Cotizacion.Controller_Lista_Cotizaciones;
import com.abstraction.controllers.Controllers_DashBoard.Controller_DashBoard;
import com.abstraction.controllers.Controllers_Factura.Controller_Lista_Facturas;
import com.abstraction.controllers.Controllers_Pedido.Controller_Lista_Pedidos;
import com.abstraction.controllers.Controllers_Perfil_Aux.Controller_Ver_Perfil;
import com.abstraction.entities.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.ArrayList;

public class Controller_Lista_Productos {

    private Stage stage;
    public IProducto_facade facade;

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
        controller_crear_producto.facade = this.facade;
        stage.show();
        this.stage.close();
    }

    @FXML
    void OnActionLogOut(ActionEvent event) {

    }

    @FXML
    void OnActionPerfil(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        URL fxmlLocation = getClass().getResource("/presentation/View_Perfil_Aux/mockupVerPerfil.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abstraction");
        stage.setScene(scene);
        Controller_Ver_Perfil controller_ver_perfil = fxmlLoader.getController();
        controller_ver_perfil.setStage(stage);
        stage.show();
        this.stage.close();
    }

    @FXML
    void onActionBuscar(ActionEvent event) {

    }

    @FXML
    void onActionCotizaciones(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        URL fxmlLocation = getClass().getResource("/presentation/View_Cotizaciones/mockupListaCotizaciones.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abstraction");
        stage.setScene(scene);
        Controller_Lista_Cotizaciones controller_lista_cotizaciones = fxmlLoader.getController();
        controller_lista_cotizaciones.setStage(stage);
        stage.show();
        this.stage.close();
    }

    public Button getBotonBuscar() {
        return botonBuscar;
    }

    public void setBotonBuscar(Button botonBuscar) {
        this.botonBuscar = botonBuscar;
    }

    public Text getTextFieldBusquedaProducto() {
        return textFieldBusquedaProducto;
    }

    public void setTextFieldBusquedaProducto(Text textFieldBusquedaProducto) {
        this.textFieldBusquedaProducto = textFieldBusquedaProducto;
    }

    @FXML
    void onActionDashBoard(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        URL fxmlLocation = getClass().getResource("/presentation/View_DashBoard/MockupDASHBOARD.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abstraction");
        stage.setScene(scene);
        Controller_DashBoard controller_dashBoard = fxmlLoader.getController();
        controller_dashBoard.setStage(stage);
        stage.show();
        this.stage.close();
    }

    @FXML
    void onActionFacturacion(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        URL fxmlLocation = getClass().getResource("/presentation/View_Facturas/mockupListaFacturas.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abstraction");
        stage.setScene(scene);
        Controller_Lista_Facturas controller_lista_facturas = fxmlLoader.getController();
        controller_lista_facturas.setStage(stage);
        stage.show();
        this.stage.close();
    }

    @FXML
    void onActionPedidos(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        URL fxmlLocation = getClass().getResource("/presentation/View_Pedidos/mockupListaPedidos.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abstraction");
        stage.setScene(scene);
        Controller_Lista_Pedidos controller_lista_pedidos = fxmlLoader.getController();
        controller_lista_pedidos.setStage(stage);
        stage.show();
        this.stage.close();
    }

    @FXML
    void onActionProductos(ActionEvent event) throws IOException {
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

    void actualizarTabla(){
        ArrayList<Producto> productos = facade.listarProductos();
        final ObservableList<Producto> data = FXCollections.observableArrayList();
    }
}
