package com.abstraction.controllers.Controllers_Pedido;

import com.abstraction.business.*;
import com.abstraction.controllers.Controllers_Cotizacion.Controller_Lista_Cotizaciones;
import com.abstraction.controllers.Controllers_Cotizacion.ObservableClasses.CotProductoObservable;
import com.abstraction.controllers.Controllers_DashBoard.Controller_DashBoard;
import com.abstraction.controllers.Controllers_Factura.Controller_Lista_Facturas;
import com.abstraction.controllers.Controllers_Perfil_Aux.Controller_Ver_Perfil;
import com.abstraction.controllers.Controllers_Producto.Controller_Lista_Productos;
import com.abstraction.entities.CotizacionProducto;
import com.abstraction.entities.Factura;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Controller_Ver_Pedido {

    public IPedido_facade facade;
    public Pedido pedido;

    public void initialize(IPedido_facade facade, Pedido pedido){
        this.facade = facade;
        this.pedido = pedido;

        mostrarPedido(pedido);
    }

    public void mostrarPedido(Pedido pedido){
        String pattern = "dd/MM/YYYY";
        DateFormat df = new SimpleDateFormat(pattern);
        String dateToString = df.format(pedido.getFecha());

        nombreClienteText.setText(pedido.getNombreCliente());
        estadoPedidoText.setText(pedido.getEstado());
        numeroDePedidoText.setText(pedido.getNumero().toString());
        precioTotalText.setText(String.valueOf(pedido.getValor()));
        fechaCotizacionText.setText(dateToString);

        actualizarTablaPedidos(pedido);
    }

    public void actualizarTablaPedidos(Pedido pedido){
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

        tableViewVerProducto.setItems(data);
    }


    @FXML
    void onActionCerrarSesion(ActionEvent event) {

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

    /**
     * Getters y Setters
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public ProgressBar getBarraProgresoEstado() {
        return barraProgresoEstado;
    }

    public void setBarraProgresoEstado(ProgressBar barraProgresoEstado) {
        this.barraProgresoEstado = barraProgresoEstado;
    }


    /**
     * FXML Elements
     */
    private Stage stage;

    @FXML
    private ProgressBar barraProgresoEstado;

    @FXML
    private Button botonCerrarSesion;

    @FXML
    private Button botonCotizaciones;

    @FXML
    private Button botonDashBoard;

    @FXML
    private Button botonFacturacion;

    @FXML
    private Button botonGenerarFactura;

    @FXML
    private Button botonPedidos;

    @FXML
    private Button botonPerfil;

    @FXML
    private Button botonProducto;

    @FXML
    private Button botonRegresar;

    @FXML
    private Text estadoPedidoText;

    @FXML
    private Text fechaCotizacionText;

    @FXML
    private Text nombreClienteText;

    @FXML
    private TableColumn<CotProductoObservable, String> nombreProductoColumna;

    @FXML
    private TableColumn<CotProductoObservable, String> numProductosColumna;

    @FXML
    private Text numeroDePedidoText;

    @FXML
    private Text precioTotalText;

    @FXML
    private TableColumn<CotProductoObservable, String> precioUnitarioColumna;

    @FXML
    private TableColumn<CotProductoObservable, String> referenciaColumna;

    @FXML
    private TableColumn<CotProductoObservable, String> subTotalColumna;

    @FXML
    private TableView<CotProductoObservable> tableViewVerProducto;

    public void onActionGenerarFactura(ActionEvent actionEvent) {
        //Crea una factura con la informacion del pedido correspondiente, en donde asigna el mismo numero de factura con relación a su pedido antecesor.
        //Asimismo, realiza la creación para realizar la debida implementacion de la función
        Factura factura = new Factura(
                this.pedido.getNumero(),
                new Date(),
                this.pedido.getValor(),
                0F,
                0,
                this.pedido
        );

        facade.crearFactura(factura);
    }
}
