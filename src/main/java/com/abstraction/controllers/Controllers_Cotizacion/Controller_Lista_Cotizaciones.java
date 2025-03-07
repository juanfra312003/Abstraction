package com.abstraction.controllers.Controllers_Cotizacion;
import com.abstraction.business.*;
import com.abstraction.controllers.Controllers_Cotizacion.ObservableClasses.CotizacionObservable;
import com.abstraction.controllers.Controllers_DashBoard.Controller_DashBoard;
import com.abstraction.controllers.Controllers_Factura.Controller_Lista_Facturas;
import com.abstraction.controllers.Controllers_IniciarSesion.Controller_Iniciar_Sesion;
import com.abstraction.controllers.Controllers_Pedido.Controller_Lista_Pedidos;
import com.abstraction.controllers.Controllers_Perfil_Aux.Controller_Ver_Perfil;
import com.abstraction.controllers.Controllers_Producto.Controller_Lista_Productos;
import com.abstraction.entities.Cotizacion;
import com.abstraction.entities.CotizacionProducto;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Controller_Lista_Cotizaciones {

    public ICotizacion_facade facade;
    int numCots = 0;

    public void initialize(ICotizacion_facade facade){
        this.facade = new FacadeGeneral();
        this.actualizarTabla();
    }

    public void actualizarTabla(){
        cotizaciones = facade.listarCotizaciones();
        if(cotizaciones == null) return;
        final ObservableList<CotizacionObservable> data = FXCollections.observableArrayList();
        numCots = cotizaciones.size();
        buttonsVer = new Button[numCots];
        buttonsAct = new Button[numCots];
        buttonsArch = new Button[numCots];
        buttonsGenerar = new Button[numCots];

        for(int i = 0; i < numCots; i++){
            buttonsVer[i] = new Button();
            buttonsVer[i].setText("Ver");
            buttonsVer[i].setOnAction(this::onActionVer);

            buttonsAct[i] = new Button();
            buttonsAct[i].setText("Actualizar");
            buttonsAct[i].setOnAction(this::onActionActualizar);

            buttonsArch[i] = new Button();
            buttonsArch[i].setText("Archivar");
            buttonsArch[i].setOnAction(this::onActionElim);

            buttonsGenerar[i] = new Button();
            buttonsGenerar[i].setText("Generar");
            buttonsGenerar[i].setOnAction(this::onActionGenerar);
        }

        int i = 0;
        for(Cotizacion cotizacion : cotizaciones){
            if (cotizacion.getArchivado() == 0) {
                data.add(new CotizacionObservable(
                        cotizacion.getNumero(),
                        cotizacion.getNombre(),
                        cotizacion.getFecha(),
                        cotizacion.getPrecio(),
                        buttonsVer[i],
                        buttonsAct[i],
                        buttonsGenerar[i],
                        buttonsArch[i]
                ));
            }
            i++;
        }
        numeroColumna.setCellValueFactory(new PropertyValueFactory<CotizacionObservable, String>("numero"));
        nombreColumna.setCellValueFactory(new PropertyValueFactory<CotizacionObservable, String>("nombre"));
        fechaColumna.setCellValueFactory(new PropertyValueFactory<CotizacionObservable, String>("fecha"));
        precioColumna.setCellValueFactory(new PropertyValueFactory<CotizacionObservable, String>("precio"));
        verColumna.setCellValueFactory(new PropertyValueFactory<CotizacionObservable, Button>("botonVer"));
        actualizarColumna.setCellValueFactory(new PropertyValueFactory<CotizacionObservable, Button>("botonActualizar"));
        generarColumna.setCellValueFactory(new PropertyValueFactory<CotizacionObservable, Button>("botonGenerarPedido"));
        archivarColumna.setCellValueFactory(new PropertyValueFactory<CotizacionObservable, Button>("botonArchivar"));
        tableViewListaCotizaciones.setItems(data);
    }

    @FXML
    void onActionBuscar(ActionEvent event) {
        String bufferBusqueda = textoBusqueda.getText();
        actualizarTablaBusqueda(bufferBusqueda);
    }

    void actualizarTablaBusqueda (String referenciaBusqueda){
        cotizaciones = facade.listarCotizaciones();
        if(cotizaciones == null) return;
        final ObservableList<CotizacionObservable> data = FXCollections.observableArrayList();
        numCots = cotizaciones.size();
        buttonsVer = new Button[numCots];
        buttonsAct = new Button[numCots];
        buttonsArch = new Button[numCots];
        buttonsGenerar = new Button[numCots];

        for(int i = 0; i < numCots; i++){
            buttonsVer[i] = new Button();
            buttonsVer[i].setText("Ver");
            buttonsVer[i].setOnAction(this::onActionVer);

            buttonsAct[i] = new Button();
            buttonsAct[i].setText("Actualizar");
            buttonsAct[i].setOnAction(this::onActionActualizar);

            buttonsArch[i] = new Button();
            buttonsArch[i].setText("Archivar");
            buttonsArch[i].setOnAction(this::onActionElim);

            buttonsGenerar[i] = new Button();
            buttonsGenerar[i].setText("Generar");
            buttonsGenerar[i].setOnAction(this::onActionGenerar);
        }

        int i = 0;
        for(Cotizacion cotizacion : cotizaciones){
            if ((cotizacion.getArchivado() == 0) && (String.valueOf(cotizacion.getNumero())).contains(referenciaBusqueda)) {
                data.add(new CotizacionObservable(
                        cotizacion.getNumero(),
                        cotizacion.getNombre(),
                        cotizacion.getFecha(),
                        cotizacion.getPrecio(),
                        buttonsVer[i],
                        buttonsAct[i],
                        buttonsGenerar[i],
                        buttonsArch[i]
                ));
            }
            i++;
        }
        numeroColumna.setCellValueFactory(new PropertyValueFactory<CotizacionObservable, String>("numero"));
        nombreColumna.setCellValueFactory(new PropertyValueFactory<CotizacionObservable, String>("nombre"));
        fechaColumna.setCellValueFactory(new PropertyValueFactory<CotizacionObservable, String>("fecha"));
        precioColumna.setCellValueFactory(new PropertyValueFactory<CotizacionObservable, String>("precio"));
        verColumna.setCellValueFactory(new PropertyValueFactory<CotizacionObservable, Button>("botonVer"));
        actualizarColumna.setCellValueFactory(new PropertyValueFactory<CotizacionObservable, Button>("botonActualizar"));
        generarColumna.setCellValueFactory(new PropertyValueFactory<CotizacionObservable, Button>("botonGenerarPedido"));
        archivarColumna.setCellValueFactory(new PropertyValueFactory<CotizacionObservable, Button>("botonArchivar"));
        tableViewListaCotizaciones.setItems(data);
    }

    @FXML
    void onActionCerrarSesion(ActionEvent event) {
        cargarCerrarSesion();
    }

    @FXML
    void onActionCotizacion(ActionEvent event) throws IOException {
        //Acá es Facturacion, error de digitación al hacer Mockups.
        //onActionFacturacion.
        cargarListaFacturas();
    }

    @FXML
    void onActionCotizaciones(ActionEvent event) throws IOException {
        cargarListaCotizaciones();
    }

    @FXML
    void onActionCrearCotizacion(ActionEvent event) throws IOException {
        cargarCrear();
    }

    @FXML
    void onActionDashBoard(ActionEvent event) throws IOException {
        cargarDashboard();
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
    public void onActionVer(ActionEvent event) {
        try {
            for(int i = 0; i < numCots; i++){
                if(event.getSource() == buttonsVer[i]){
                    cargarVer(cotizaciones.get(i));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void onActionElim(ActionEvent event) {
        try {
            for(int i = 0; i < numCots; i++){
                if(event.getSource() == buttonsArch[i]){
                    if (facade.archivarCotizacion(cotizaciones.get(i).getNumero())) {

                        //Actualizar tabla con los datos de manera respectiva.
                        actualizarTabla();

                        //Arrojar la confirmación del proceso.
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText("Cotizacion Archivada Exitosamente");
                        alert.setTitle("Exito en el proceso");
                        alert.setContentText("La cotización seleccionada se ha archivado de manera satisfactoria.");
                        alert.show();

                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void onActionActualizar(ActionEvent event) {
        try {
            for(int i = 0; i < numCots; i++){
                if(event.getSource() == buttonsAct[i]){
                    cargarActualizar(cotizaciones.get(i));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void onActionGenerar(ActionEvent event) {
        for(int i = 0; i < numCots; i++){
            Cotizacion cotizacion = cotizaciones.get(i);
            if(event.getSource() == buttonsGenerar[i]){
                ArrayList<CotizacionProducto> productos = cotizacion.getProductos();
                Producto producto;
                boolean cantidadesSuficientes = true;
                String faltantes = "No hay cantidades suficientes de:";
                for(CotizacionProducto productoCot : productos){
                    producto = productoCot.getProducto();
                    if(producto.getExistencias()-productoCot.getCantidad() < 0){
                        cantidadesSuficientes = false;
                        faltantes = faltantes + " " + producto.getNombre();
                    }
                }
                if(!cantidadesSuficientes){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Fallo en el proceso");
                    alert.setTitle("No se pudo generar el pedido");
                    alert.setContentText(faltantes);
                    alert.show();
                }
                else if(facade.crearPedido(cotizacion)){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Exito en el proceso");
                    alert.setTitle("Pedido generado correctamente");
                    alert.setContentText("Se ha generado un pedido a partir de la cotizacion dada");
                    alert.show();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Fallo en el proceso");
                    alert.setTitle("No se pudo generar el pedido");
                    alert.setContentText("Error desconocido.");
                    alert.show();
                }
            }
        }
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

    public void cargarVer(Cotizacion cotizacion) throws IOException {
        Stage stage = new Stage();
        URL fxmlLocation = getClass().getResource("/presentation/View_Cotizaciones/mockupVerCotizacion.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abstraction");
        stage.setScene(scene);
        Controller_Ver_Cotizacion controller_ver_cotizacion = fxmlLoader.getController();
        controller_ver_cotizacion.setStage(stage);
        controller_ver_cotizacion.initialize((ICotizacion_facade) this.facade, cotizacion);
        stage.show();
        this.stage.close();
    }

    public void cargarActualizar (Cotizacion cotizacion) throws IOException{
        Stage stage = new Stage();
        URL fxmlLocation = getClass().getResource("/presentation/View_Cotizaciones/mockupActualizarCotizacion.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abstraction");
        stage.setScene(scene);
        Controller_Actualizar_Cotizacion controller_actualizar_cotizacion = fxmlLoader.getController();
        controller_actualizar_cotizacion.setStage(stage);
        controller_actualizar_cotizacion.initialize((ICotizacion_facade) this.facade, cotizacion);
        stage.show();
        this.stage.close();
    }

    public void cargarCrear() throws IOException {
        Stage stage = new Stage();
        URL fxmlLocation = getClass().getResource("/presentation/View_Cotizaciones/mockupCrearCotizacion.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abstraction");
        stage.setScene(scene);
        Controller_Crear_Cotizacion controller_crear_cotizacion = fxmlLoader.getController();
        controller_crear_cotizacion.initialize((ICotizacion_facade) this.facade);
        controller_crear_cotizacion.setStage(stage);
        stage.show();
        this.stage.close();
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

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
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
    private TextField textoBusqueda;

    @FXML
    private TableColumn<CotizacionObservable, Button> actualizarColumna;

    @FXML
    private TableColumn<CotizacionObservable, Button> archivarColumna;

    @FXML
    private TableColumn<CotizacionObservable, String> numeroColumna;

    @FXML
    private TableColumn<CotizacionObservable, String> fechaColumna;

    @FXML
    private TableColumn<CotizacionObservable, Button> generarColumna;

    @FXML
    private TableColumn<CotizacionObservable, String> nombreColumna;

    @FXML
    private TableColumn<CotizacionObservable, String> precioColumna;

    @FXML
    private TableColumn<CotizacionObservable, Button> verColumna;

    @FXML
    private TableView<CotizacionObservable> tableViewListaCotizaciones;

    ArrayList<Cotizacion> cotizaciones;
    Button[] buttonsVer;
    Button[] buttonsAct;
    Button[] buttonsGenerar;
    Button[] buttonsArch;

}
