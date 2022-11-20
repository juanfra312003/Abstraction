package com.abstraction.controllers.Controllers_Factura;

import com.abstraction.business.*;
import com.abstraction.controllers.Controllers_Cotizacion.Controller_Lista_Cotizaciones;
import com.abstraction.controllers.Controllers_DashBoard.Controller_DashBoard;
import com.abstraction.controllers.Controllers_Pedido.Controller_Lista_Pedidos;
import com.abstraction.controllers.Controllers_Perfil_Aux.Controller_Ver_Perfil;
import com.abstraction.controllers.Controllers_Producto.Controller_Lista_Productos;
import com.abstraction.entities.CotizacionProducto;
import com.abstraction.entities.Factura;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Controller_Ver_Factura {

    public IFactura_facade facade;

    public void initialize(IFactura_facade facade, Factura factura){
        this.facade = facade;
        this.mostrarFactura(factura);
    }

    public void mostrarFactura (Factura factura){

        //Definir variable para la definición de la fecha de Factura
        String pattern = "dd/MM/YYYY";
        DateFormat df = new SimpleDateFormat(pattern);
        String dateToString = df.format(factura.getFecha());

        //Establecer valores de lectura para la vista, a excepción de la vista de tabla de los productos correspondientes.
        numeroDeFacturaText.setText(factura.getNumero().toString());
        numeroPedidoText.setText(factura.getPedidoFactura().getNumero().toString());
        nombreClienteText.setText(factura.getPedidoFactura().getNombreCliente());
        abonoRealizadoText.setText(String.valueOf(factura.getAbonoTotal()));
        precioTotalText.setText(String.valueOf(factura.getValorTotal()));
        fechaFacturaText.setText(dateToString);

        actualizarTabla(factura);
    }

    public void actualizarTabla (Factura factura){
        //Definir el conjunto de productos que se van a mostrar al interior de la factura en la tabla provista con dicho fin
        ArrayList<CotizacionProducto> productos = factura.getPedidoFactura().getCotizacionPedido().getProductos();

        //Si la lista de productos es nula, retornar.
        if(productos == null) return;

        //Definir los datos para productos observables de tipo <VerFactProductoObservable>
        final ObservableList<VerFactProductoObservable> data = FXCollections.observableArrayList();

        //Añadir a los datos cada una de las columnas de la tabla de acuerdo con su orden especificado en el constructor de dicha clase.
        for(CotizacionProducto p : productos){
            data.add(new VerFactProductoObservable(
                    p.getProducto().getReferencia(),
                    p.getProducto().getNombre(),
                    p.getCantidad(),
                    p.getProducto().getPrecio(),
                    p.getSubtotal()
            ));
        }

        //Realizar la asignación de cada columna de la tabla con su respectivo valor.
        referenciaColumna.setCellValueFactory(new PropertyValueFactory<VerFactProductoObservable, String>("referencia"));
        nombreProductoColumna.setCellValueFactory(new PropertyValueFactory<VerFactProductoObservable, String>("nombre"));
        numProductosColumna.setCellValueFactory(new PropertyValueFactory<VerFactProductoObservable, String>("cantidad"));
        precioUnitarioColumna.setCellValueFactory(new PropertyValueFactory<VerFactProductoObservable, String>("precioUnitario"));
        subTotalColumna.setCellValueFactory(new PropertyValueFactory<VerFactProductoObservable, String>("subtotal"));

        //Realizar la asignación en la correspondiente TableView.
        tableViewVerFactura.setItems(data);
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
        cargarListaFacturas();
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
    private Text abonoRealizadoText;

    @FXML
    private Button botonCerrarSesion;

    @FXML
    private Button botonCotizaciones;

    @FXML
    private TableView<VerFactProductoObservable> tableViewVerFactura;

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
    private Text fechaFacturaText;

    @FXML
    private Text nombreClienteText;

    @FXML
    private TableColumn<VerFactProductoObservable, String> nombreProductoColumna;

    @FXML
    private TableColumn<VerFactProductoObservable, String>numProductosColumna;

    @FXML
    private Text numeroDeFacturaText;

    @FXML
    private Text numeroPedidoText;

    @FXML
    private Text precioTotalText;

    @FXML
    private TableColumn<VerFactProductoObservable, String>precioUnitarioColumna;

    @FXML
    private TableColumn<VerFactProductoObservable, String>referenciaColumna;

    @FXML
    private TableColumn<VerFactProductoObservable, String> subTotalColumna;
}
