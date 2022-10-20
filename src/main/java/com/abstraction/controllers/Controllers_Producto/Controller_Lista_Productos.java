package com.abstraction.controllers.Controllers_Producto;

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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn<ProductoObservable, String> columnaActualizarP;

    @FXML
    private TableColumn<ProductoObservable, String> columnaEliminarP;

    @FXML
    private TableColumn<ProductoObservable, String> columnaNombre;

    @FXML
    private TableColumn<ProductoObservable, String> columnaNumeroExistencias;

    @FXML
    private TableColumn<ProductoObservable,String> columnaPrecio;

    @FXML
    private TableColumn<ProductoObservable,String> columnaReferencia;

    @FXML
    private TableColumn<ProductoObservable,String> columnaVerProducto;

    @FXML
    private ComboBox<?> comboBoxFiltrar;

    @FXML
    private Text textFieldBusquedaProducto;


    @FXML
    private TableView<ProductoObservable> tableViewListarProductos;

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

    @FXML
    void onActionVer(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        URL fxmlLocation = getClass().getResource("/presentation/View_Productos/mockupVerProducto.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abstraction");
        stage.setScene(scene);
        Controller_Ver_Producto controller_ver_producto = fxmlLoader.getController();
        controller_ver_producto.setStage(stage);
        stage.show();
        this.stage.close();
    }

    @FXML
    void onActionActualizar(ActionEvent event) throws IOException {

    }

    @FXML
    void onActionBorrar(ActionEvent event) throws IOException {
    }
    public void actualizarTabla()  throws  IOException {
        ArrayList<Producto> productos = facade.listarProductos();
        final ObservableList<ProductoObservable> data = FXCollections.observableArrayList();
        if (productos == null) return;
        for(Producto producto : productos) {
            Button buttonVer = new Button();
            buttonVer.setText("Ver");
            //buttonVer.setOnAction(this::onActionVer);
            Button buttonActualizar = new Button();
            buttonActualizar.setText("Acualizar");
            //buttonActualizar.setOnAction(this::onActionActualizar);
            Button buttonBorrar = new Button();
            buttonBorrar.setText("Borrar");
            //buttonBorrar.setOnAction(this::onActionBorrar);
            data.add(new ProductoObservable(
                    producto.getReferencia(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    producto.getExistencias(),
                    buttonVer,
                    buttonActualizar,
                    buttonBorrar
            ));

            columnaReferencia.setCellValueFactory(new PropertyValueFactory<ProductoObservable, String>("referencia"));
            columnaNombre.setCellValueFactory(new PropertyValueFactory<ProductoObservable, String>("nombre"));
            columnaPrecio.setCellValueFactory(new PropertyValueFactory<ProductoObservable, String>("precio"));
            columnaNumeroExistencias.setCellValueFactory(new PropertyValueFactory<ProductoObservable, String>("existencias"));
            columnaVerProducto.setCellValueFactory(new PropertyValueFactory<ProductoObservable, String>("botonVer"));
            columnaActualizarP.setCellValueFactory(new PropertyValueFactory<ProductoObservable, String>("botonActualizar"));
            columnaEliminarP.setCellValueFactory(new PropertyValueFactory<ProductoObservable, String>("botonBorrar"));

            tableViewListarProductos.setItems(data);
        }
    }
}
