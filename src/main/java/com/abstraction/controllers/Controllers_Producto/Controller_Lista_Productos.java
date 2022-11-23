package com.abstraction.controllers.Controllers_Producto;

import com.abstraction.business.*;
import com.abstraction.controllers.Controllers_Cotizacion.Controller_Lista_Cotizaciones;
import com.abstraction.controllers.Controllers_DashBoard.Controller_DashBoard;
import com.abstraction.controllers.Controllers_Factura.Controller_Lista_Facturas;
import com.abstraction.controllers.Controllers_Pedido.Controller_Lista_Pedidos;
import com.abstraction.controllers.Controllers_Perfil_Aux.Controller_Ver_Perfil;
import com.abstraction.controllers.Controllers_Producto.ObservableClasses.ProductoObservable;
import com.abstraction.entities.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;




public class Controller_Lista_Productos {

    public IProducto_facade facade;
    int numProds = 0;

    //Intialize inicia todos los valores y tablas requeridos para el despliegue de la pantalla
    public void initialize(IProducto_facade facade) {
        this.facade = facade;
        this.actualizarTabla();
    }

    //Actualiza la tabla de la pantalla
    public void actualizarTabla() {
        productos = facade.listarProductos();
        if(productos == null) return;
        final ObservableList<ProductoObservable> data = FXCollections.observableArrayList();
        numProds = productos.size();
        buttonsVer = new Button[productos.size()];
        buttonsAct = new Button[productos.size()];
        buttonsBorrar = new Button[productos.size()];

        for (int i = 0; i < productos.size(); i++) {
            buttonsVer[i] = new Button();
            buttonsVer[i].setText("Ver");
            buttonsVer[i].setOnAction(this::onActionVer);

            buttonsAct[i] = new Button();
            buttonsAct[i].setText("Actualizar");
            buttonsAct[i].setOnAction(this::onActionActualizar);

            buttonsBorrar[i] = new Button();
            buttonsBorrar[i].setText("Eliminar");
            buttonsBorrar[i].setOnAction(this::onActionBorrar);
        }

        int i = 0;
        for (Producto producto : productos) {
            if(producto.getArchivado()==0){
                data.add(new ProductoObservable(
                        producto.getReferencia(),
                        producto.getNombre(),
                        producto.getPrecio(),
                        producto.getExistencias(),
                        buttonsVer[i],
                        buttonsAct[i],
                        buttonsBorrar[i]
                ));
            }
            i++;
        }
        columnaReferencia.setCellValueFactory(new PropertyValueFactory<ProductoObservable, String>("referencia"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<ProductoObservable, String>("nombre"));
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<ProductoObservable, String>("precio"));
        columnaNumeroExistencias.setCellValueFactory(new PropertyValueFactory<ProductoObservable, String>("existencias"));
        columnaVerProducto.setCellValueFactory(new PropertyValueFactory<ProductoObservable, String>("botonVer"));
        columnaActualizarP.setCellValueFactory(new PropertyValueFactory<ProductoObservable, String>("botonActualizar"));
        columnaEliminarP.setCellValueFactory(new PropertyValueFactory<ProductoObservable, String>("botonBorrar"));
        tableViewListarProductos.setItems(data);
    }


    /**
     * ON ACTION:
     * Acciones a realizar con cada boton presionado
     */
    @FXML
    void OnActionComboBoxFiltrar(ActionEvent event) {

    }

    @FXML
    void onActionBuscar(ActionEvent event) {

    }

    @FXML
    public void onActionVer(ActionEvent event) {
        for (int i = 0; i < numProds; i++) {
            if (event.getSource() == buttonsVer[i]) {
                cargarVer(productos.get(i));
            }
        }
    }

    @FXML
    public void onActionActualizar(ActionEvent event) {
       for (int i = 0; i < numProds; i++) {
            if (event.getSource() == buttonsAct[i]) {
                cargarActualizar(productos.get(i));
            }
        }
    }

    @FXML
    public void onActionBorrar(ActionEvent event) {
        try{
            for(int i=0;i<productos.size();i++){
                if(event.getSource()==buttonsBorrar[i]){
                    if(facade.archivarProducto(productos.get(i).getReferencia())){

                        actualizarTabla();
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText("Producto Eliminado Exitosamente");
                        alert.setTitle("Exito en el proceso");
                        alert.setContentText("El producto seleccionado se ha eliminado de manera satisfactoria.");
                        alert.show();

                    }
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void OnActionLogOut(ActionEvent event) {

    }

    @FXML
    void OnActionCrearProducto(ActionEvent event) throws IOException {
        cargarCrearProducto();
    }

    @FXML
    void OnActionPerfil(ActionEvent event) throws IOException {
        cargarPerfil();
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
    void onActionProductos(ActionEvent event) throws IOException {
        cargarListaProductos();
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

    public void cargarCrearProducto(){
        try {
            Stage stage = new Stage();
            URL fxmlLocation = getClass().getResource("/presentation/View_Productos/MockupCrearProducto.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Abstraction");
            stage.setScene(scene);
            Controller_Crear_Producto controller_crear_producto = fxmlLoader.getController();
            controller_crear_producto.setStage(stage);
            controller_crear_producto.initialize(this.facade);
            stage.show();
            this.stage.close();
        } catch (IOException e) {
            System.out.printf(e.getMessage());
        }
    }

    public void cargarVer(Producto producto) {
        try {
            Stage stage = new Stage();
            URL fxmlLocation = getClass().getResource("/presentation/View_Productos/mockupVerProducto.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Abstraction");
            stage.setScene(scene);
            Controller_Ver_Producto controller_ver_producto = fxmlLoader.getController();
            controller_ver_producto.setStage(stage);
            controller_ver_producto.initialize(this.facade, producto);
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

    /**
     * FXML Elements
     */

    private Stage stage;

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
    private TableColumn<ProductoObservable, String> columnaPrecio;

    @FXML
    private TableColumn<ProductoObservable, String> columnaReferencia;

    @FXML
    private TableColumn<ProductoObservable, String> columnaVerProducto;

    @FXML
    private ComboBox<?> comboBoxFiltrar;

    @FXML
    private Text textFieldBusquedaProducto;


    @FXML
    private TableView<ProductoObservable> tableViewListarProductos;

    Button[] buttonsVer;
    Button[] buttonsAct;
    Button[] buttonsBorrar;

    ArrayList<Producto> productos;

}
