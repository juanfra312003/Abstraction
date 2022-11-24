package com.abstraction.controllers.Controllers_Pedido;

import com.abstraction.business.*;
import com.abstraction.controllers.Controllers_Cotizacion.Controller_Lista_Cotizaciones;
import com.abstraction.controllers.Controllers_Cotizacion.ObservableClasses.CotProductoObservable;
import com.abstraction.controllers.Controllers_DashBoard.Controller_DashBoard;
import com.abstraction.controllers.Controllers_Factura.Controller_Lista_Facturas;
import com.abstraction.controllers.Controllers_IniciarSesion.Controller_Iniciar_Sesion;
import com.abstraction.controllers.Controllers_Perfil_Aux.Controller_Ver_Perfil;
import com.abstraction.controllers.Controllers_Producto.Controller_Lista_Productos;
import com.abstraction.entities.CotizacionProducto;
import com.abstraction.entities.Pedido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Controller_Actualizar_Pedido {

    public IPedido_facade facade;
    Pedido pedido;

    public void initialize(IPedido_facade facade, Pedido pedido){
        this.facade = facade;
        this.pedido = pedido;
        actualizarTabla(pedido);
        numeroDePedidoText.setText(String.valueOf(pedido.getNumero()));
        nombreClienteText.setText(pedido.getNombreCliente());
        choiceBoxEstadoPedido.getItems().addAll("En espera", "En preparacion", "En empaque", "En espera de envio", "Enviado", "Entregado");
    }

    public void actualizarTabla(Pedido pedido){
        ArrayList<CotizacionProducto> productos = pedido.getCotizacionPedido().getProductos();
        if(productos == null) return;
        final ObservableList<CotProductoObservable> data = FXCollections.observableArrayList();

        for(CotizacionProducto p : productos){
            data.add(new CotProductoObservable(
                    p.getProducto().getReferencia(),
                    p.getProducto().getNombre(),
                    p.getCantidad(),
                    p.getProducto().getPrecio(),
                    p.getSubtotal()
            ));
        }

        referenciaColumna.setCellValueFactory(new PropertyValueFactory<CotProductoObservable, String>("referencia"));
        nombreProductoColumna.setCellValueFactory(new PropertyValueFactory<CotProductoObservable, String>("nombre"));
        numProductosColumna.setCellValueFactory(new PropertyValueFactory<CotProductoObservable, String>("cantidad"));
        precioUnitarioColumna.setCellValueFactory(new PropertyValueFactory<CotProductoObservable, String>("precioUnitario"));
        subTotalColumna.setCellValueFactory(new PropertyValueFactory<CotProductoObservable, String>("subtotal"));

        tableViewActPedido.setItems(data);
    }
    @FXML
    void onActionActualizarGeneral(ActionEvent event) {
        if(nombreClienteText.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Datos incorrectos");
            alert.setTitle("Error actualizando pedido");
            alert.setContentText("El espacio de nombre de cliente no puede estar vacio.");
            alert.show();
        }
        else if(choiceBoxEstadoPedido.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Datos incorrectos");
            alert.setTitle("Error actualizando pedido");
            alert.setContentText("Elija un valor para el estado del pedido");
            alert.show();
        }
        else {
            pedido.setNombreCliente(nombreClienteText.getText());
            pedido.setEstado(choiceBoxEstadoPedido.getValue());
            if(facade.actualizarPedido(pedido)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Proceso exitoso");
                alert.setTitle("Pedido actualizado");
                alert.setContentText("El pedido ha sido actualizado exitosamente");
                alert.show();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error actualizando el pedido");
                alert.setTitle("Error actualizando pedido");
                alert.setContentText("Error desconocido");
                alert.show();
            }
        }
    }

    @FXML
    void onActionActualizarNombreCliente(ActionEvent event) {

    }

    @FXML
    void onActionCerrarSesion(ActionEvent event) {
        cargarCerrarSesion();
    }

    @FXML
    void onActionCotizaciones(ActionEvent event) throws IOException {
        cargarListaCotizaciones();
    }

    @FXML
    void onActionDashBoard(ActionEvent event) throws IOException {
        cargarDashboard();
    }

    @FXML
    void onActionFacturacion(ActionEvent event) throws IOException {
        cargarListaFacturas();
    }

    @FXML
    void onActionPedidos(ActionEvent event) throws IOException {
        cargarListaPedidos();
    }

    @FXML
    void onActionPerfil(ActionEvent event) throws IOException {
        cargarPerfil();
    }

    @FXML
    void onActionProducto(ActionEvent event) throws IOException {
        cargarListaProductos();
    }

    @FXML
    void onActionRegresar(ActionEvent event) throws IOException {
        cargarListaPedidos();
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
    //cargar cerrar sesion
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
    /**
     * Getters y Setters
     */

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * FXML Elements
     */

    private Stage stage;

    @FXML
    private Button botonActualizarGeneral;

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
    private Button botonProducto;

    @FXML
    private Button botonRegresar;

    @FXML
    private TextField nombreClienteText;

    @FXML
    private Text numeroDePedidoText;

    @FXML
    private TableColumn<CotProductoObservable, String> nombreProductoColumna;

    @FXML
    private TableColumn<CotProductoObservable, String> numProductosColumna;

    @FXML
    private TableColumn<CotProductoObservable, String> precioUnitarioColumna;

    @FXML
    private TableColumn<CotProductoObservable, String> referenciaColumna;

    @FXML
    private TableColumn<CotProductoObservable, String> subTotalColumna;

    @FXML
    private TableView<CotProductoObservable> tableViewActPedido;

    @FXML
    private ChoiceBox<String> choiceBoxEstadoPedido;

}
