package com.abstraction.controllers.Controllers_Cotizacion;

import com.abstraction.business.*;
import com.abstraction.controllers.Controllers_DashBoard.Controller_DashBoard;
import com.abstraction.controllers.Controllers_Factura.Controller_Lista_Facturas;
import com.abstraction.controllers.Controllers_Pedido.Controller_Lista_Pedidos;
import com.abstraction.controllers.Controllers_Perfil_Aux.Controller_Ver_Perfil;
import com.abstraction.controllers.Controllers_Producto.Controller_Lista_Productos;
import com.abstraction.controllers.Controllers_Producto.ProductoObservable;
import com.abstraction.entities.Cotizacion;
import com.abstraction.entities.CotizacionProducto;
import com.abstraction.entities.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class Controller_Crear_Cotizacion {

    public ICotizacion_facade facade;
    int numProds;
    float costoTotal;

    public void initialize(ICotizacion_facade facade){
        this.facade = facade;
        numeroDeCotizacionText.setText(String.valueOf(facade.nextCotId()));
        numProds = 0;
        costoTotal = 0;
        precioCotizadoText.setText(String.valueOf(costoTotal));
        actualizarTabla();
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

    @FXML
    void onActionCrearCotizacion(ActionEvent event) {
        Cotizacion  cotizacion = new Cotizacion(
                facade.nextCotId(),
                nombreCotizacionText.getText(),
                new Date(),
                parseFloat(precioCotizadoText.getText()),
                nombreClienteText.getText(),
                0);
        cotizacion.setProductos(productosCotizados);
        facade.crearCotizacion(cotizacion);
    }

    @FXML
    public void onActionAgregar(ActionEvent event) {
        for (int i = 0; i < numProds; i++) {
            if (event.getSource() == buttonsAdd[i]) {
                productosCotizados.add(new CotizacionProducto(productos.get(i), parseInt(textFieldsConfirmar[i].getText())));
                costoTotal += productos.get(i).getPrecio()*parseInt(textFieldsConfirmar[i].getText());
                precioCotizadoText.setText(String.valueOf(costoTotal));
            }
        }
    }

    @FXML
    void actualizarTabla() {
        IProducto_facade facadeProd = new FacadeGeneral();
        productos = facadeProd.listarProductos();
        final ObservableList<ProductoObservable2> data = FXCollections.observableArrayList();

        numProds = productos.size();
        buttonsAdd = new Button[numProds];
        textFieldsConfirmar = new TextField[numProds];

        for (int i = 0; i < numProds; i++) {
            buttonsAdd[i] = new Button();
            buttonsAdd[i].setText("Agregar");
            buttonsAdd[i].setOnAction(this::onActionAgregar);

            textFieldsConfirmar[i] = new TextField();
        }

        int i = 0;
        for (Producto producto : productos) {
            data.add(new ProductoObservable2(
                    producto.getReferencia(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    producto.getExistencias(),
                    textFieldsConfirmar[i],
                    buttonsAdd[i]
            ));
            i++;
        }
        referenciaColumna.setCellValueFactory(new PropertyValueFactory<ProductoObservable2, String>("referencia"));
        nombreProductoColumna.setCellValueFactory(new PropertyValueFactory<ProductoObservable2, String>("nombre"));
        precioColumna.setCellValueFactory(new PropertyValueFactory<ProductoObservable2, String>("precio"));
        existenciasColumna.setCellValueFactory(new PropertyValueFactory<ProductoObservable2, String>("cantidades"));
        addColumna.setCellValueFactory(new PropertyValueFactory<ProductoObservable2, TextField>("agregarTexto"));
        confirmarColumna.setCellValueFactory(new PropertyValueFactory<ProductoObservable2, Button>("buttonConfirmarAgregar"));
        tableViewCrearCotizacion.setItems(data);

    }

    @FXML
    void onActionCerrarSesion(ActionEvent event) {

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
        cargarListaCotizaciones();
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

    public TextField getNombreClienteText() {
        return nombreClienteText;
    }

    public void setNombreClienteText(TextField nombreClienteText) {
        this.nombreClienteText = nombreClienteText;
    }

    public TextField getNombreCotizacionText() {
        return nombreCotizacionText;
    }

    public void setNombreCotizacionText(TextField nombreCotizacionText) {
        this.nombreCotizacionText = nombreCotizacionText;
    }


    /**
     * FXML Elements
     */

    private Stage stage;

    @FXML
    private TableColumn<ProductoObservable2, TextField> addColumna;

    @FXML
    private Button botonCerrarSesion;

    @FXML
    private Button botonCotizaciones;

    @FXML
    private Button botonCrearCotizacion;

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
    private TableColumn<ProductoObservable2, Button> confirmarColumna;

    @FXML
    private TableColumn<ProductoObservable2, String> existenciasColumna;

    @FXML
    private TextField nombreClienteText;

    @FXML
    private TextField nombreCotizacionText;

    @FXML
    private TableColumn<ProductoObservable2, String> nombreProductoColumna;

    @FXML
    private Text numeroDeCotizacionText;

    @FXML
    private TableColumn<ProductoObservable2, String> precioColumna;

    @FXML
    private Text precioCotizadoText;


    @FXML
    private TableColumn<ProductoObservable2, String> referenciaColumna;

    @FXML
    private TableView<ProductoObservable2> tableViewCrearCotizacion;

    ArrayList<Producto> productos;
    ArrayList<CotizacionProducto> productosCotizados = new ArrayList<>();
    Button[] buttonsAdd;
    TextField[] textFieldsConfirmar;
}
