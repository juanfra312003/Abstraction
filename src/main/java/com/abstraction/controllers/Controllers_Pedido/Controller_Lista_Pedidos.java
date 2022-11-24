package com.abstraction.controllers.Controllers_Pedido;

import com.abstraction.business.*;
import com.abstraction.controllers.Controllers_Cotizacion.Controller_Actualizar_Cotizacion;
import com.abstraction.controllers.Controllers_Cotizacion.Controller_Lista_Cotizaciones;
import com.abstraction.controllers.Controllers_Cotizacion.Controller_Ver_Cotizacion;
import com.abstraction.controllers.Controllers_DashBoard.Controller_DashBoard;
import com.abstraction.controllers.Controllers_Factura.Controller_Lista_Facturas;
import com.abstraction.controllers.Controllers_IniciarSesion.Controller_Iniciar_Sesion;
import com.abstraction.controllers.Controllers_Pedido.ObservableClasses.PedidoObservable;
import com.abstraction.controllers.Controllers_Perfil_Aux.Controller_Ver_Perfil;
import com.abstraction.controllers.Controllers_Producto.Controller_Lista_Productos;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Controller_Lista_Pedidos {

    public IPedido_facade facade;
    int numPedidos = 0;

    public void initialize(IPedido_facade facade){
        this.facade = new FacadeGeneral();
        actualizarTabla();
    }

    public void actualizarTabla(){
        pedidos = facade.listarPedidos();
        if(pedidos == null) return;
        final ObservableList<PedidoObservable> data = FXCollections.observableArrayList();
        numPedidos = pedidos.size();
        buttonsVer = new Button[numPedidos];
        buttonsAct = new Button[numPedidos];
        buttonsArch = new Button[numPedidos];
        buttonsGenerar = new Button[numPedidos];

        for(int i = 0; i < numPedidos; i++){
            buttonsVer[i] = new Button();
            buttonsVer[i].setText("Ver");
            buttonsVer[i].setOnAction(this::onActionVer);

            buttonsAct[i] = new Button();
            buttonsAct[i].setText("Actualizar");
            buttonsAct[i].setOnAction(this::onActionActualizar);

            buttonsArch[i] = new Button();
            buttonsArch[i].setText("Archivar");
            buttonsArch[i].setOnAction(this::onActionElim);
        }

        int i = 0;
        for(Pedido p : pedidos){
            if (p.getArchivado() == 0) {
                data.add(new PedidoObservable(
                        p.getNumero(),
                        p.getNombre(),
                        p.getFecha(),
                        p.getValor(),
                        buttonsVer[i],
                        buttonsAct[i],
                        buttonsArch[i]
                ));
            }
            i++;
        }
        numeroColumna.setCellValueFactory(new PropertyValueFactory<PedidoObservable, String>("numero"));
        nombreColumna.setCellValueFactory(new PropertyValueFactory<PedidoObservable, String>("nombre"));
        fechaColumna.setCellValueFactory(new PropertyValueFactory<PedidoObservable, String>("fecha"));
        totalColumna.setCellValueFactory(new PropertyValueFactory<PedidoObservable, String>("valor"));
        verColumna.setCellValueFactory(new PropertyValueFactory<PedidoObservable, Button>("botonVer"));
        actualizarColumna.setCellValueFactory(new PropertyValueFactory<PedidoObservable, Button>("botonActualizar"));
        archivarColumna.setCellValueFactory(new PropertyValueFactory<PedidoObservable, Button>("botonArchivar"));
        tableViewListaPedidos.setItems(data);
    }

    public void actualizarTablaBusqueda(String busquedaPedido){
        pedidos = facade.listarPedidos();
        if(pedidos == null) return;
        final ObservableList<PedidoObservable> data = FXCollections.observableArrayList();
        numPedidos = pedidos.size();
        buttonsVer = new Button[numPedidos];
        buttonsAct = new Button[numPedidos];
        buttonsArch = new Button[numPedidos];
        buttonsGenerar = new Button[numPedidos];

        for(int i = 0; i < numPedidos; i++){
            buttonsVer[i] = new Button();
            buttonsVer[i].setText("Ver");
            buttonsVer[i].setOnAction(this::onActionVer);

            buttonsAct[i] = new Button();
            buttonsAct[i].setText("Actualizar");
            buttonsAct[i].setOnAction(this::onActionActualizar);

            buttonsArch[i] = new Button();
            buttonsArch[i].setText("Archivar");
            buttonsArch[i].setOnAction(this::onActionElim);
        }

        int i = 0;
        for(Pedido p : pedidos){
            if (p.getArchivado() == 0 && String.valueOf(p.getNumero()).contains(busquedaPedido)) {
                data.add(new PedidoObservable(
                        p.getNumero(),
                        p.getNombre(),
                        p.getFecha(),
                        p.getValor(),
                        buttonsVer[i],
                        buttonsAct[i],
                        buttonsArch[i]
                ));
            }
            i++;
        }
        numeroColumna.setCellValueFactory(new PropertyValueFactory<PedidoObservable, String>("numero"));
        nombreColumna.setCellValueFactory(new PropertyValueFactory<PedidoObservable, String>("nombre"));
        fechaColumna.setCellValueFactory(new PropertyValueFactory<PedidoObservable, String>("fecha"));
        totalColumna.setCellValueFactory(new PropertyValueFactory<PedidoObservable, String>("valor"));
        verColumna.setCellValueFactory(new PropertyValueFactory<PedidoObservable, Button>("botonVer"));
        actualizarColumna.setCellValueFactory(new PropertyValueFactory<PedidoObservable, Button>("botonActualizar"));
        archivarColumna.setCellValueFactory(new PropertyValueFactory<PedidoObservable, Button>("botonArchivar"));
        tableViewListaPedidos.setItems(data);
    }

    public void onActionVer(ActionEvent event){
        try {
            for(int i = 0; i < numPedidos; i++){
                if(event.getSource() == buttonsVer[i]){
                    cargarVer(pedidos.get(i));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void onActionElim(ActionEvent event){
        try {
            for(int i = 0; i < numPedidos; i++){
                if(event.getSource() == buttonsArch[i]){
                    if (facade.eliminarPedido(pedidos.get(i).getNumero())) {

                        //Actualizar tabla con los datos de manera respectiva.
                        actualizarTabla();

                        //Arrojar la confirmación del proceso.
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText("Pedido Archivado Exitosamente");
                        alert.setTitle("Exito en el proceso");
                        alert.setContentText("El pedido seleccionado se ha archivado de manera satisfactoria.");
                        alert.show();

                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void onActionActualizar(ActionEvent event){
        try {
            for(int i = 0; i < numPedidos; i++){
                if(event.getSource() == buttonsAct[i]){
                    cargarActualizar(pedidos.get(i));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cargarActualizar(Pedido pedido) throws IOException {
        Stage stage = new Stage();
        URL fxmlLocation = getClass().getResource("/presentation/View_Pedidos/mockupActualizarPedido.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abstraction");
        stage.setScene(scene);
        Controller_Actualizar_Pedido controller_actualizar_pedido = fxmlLoader.getController();
        controller_actualizar_pedido.setStage(stage);
        controller_actualizar_pedido.initialize((IPedido_facade) this.facade, pedido);
        stage.show();
        this.stage.close();
    }

    public void cargarVer(Pedido pedido) throws IOException {
        Stage stage = new Stage();
        URL fxmlLocation = getClass().getResource("/presentation/View_Pedidos/mockupVerPedido.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abstraction");
        stage.setScene(scene);
        Controller_Ver_Pedido controller_ver_pedido = fxmlLoader.getController();
        controller_ver_pedido.setStage(stage);
        controller_ver_pedido.initialize((IPedido_facade) this.facade, pedido);
        stage.show();
        this.stage.close();
    }

    @FXML
    void onActionBuscar(ActionEvent event) {
        String bufferBusqueda = textoBusqueda.getText();
        actualizarTablaBusqueda(bufferBusqueda);
    }

    @FXML
    void onActionCerrarSesion(ActionEvent event) {
        cargarCerrarSesion();
    }

    @FXML
    void onActionCotizacion(ActionEvent event) throws IOException {
        //onActionFacturacion --> Error en el SceneBuilder
        cargarListaFacturas();
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
    void onActionDateSeleccionar(ActionEvent event) {
        try {
            String sdate = dateSeleccionar.getValue().toString();
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sdate);
            actualizarTablaPorFecha(date);
        }
        catch(ParseException e){
            System.out.println(e.getMessage());
        }
    }

    public void actualizarTablaPorFecha(Date date){
        pedidos = facade.listarPedidos();
        if(pedidos == null) return;
        final ObservableList<PedidoObservable> data = FXCollections.observableArrayList();
        numPedidos = pedidos.size();
        buttonsVer = new Button[numPedidos];
        buttonsAct = new Button[numPedidos];
        buttonsArch = new Button[numPedidos];
        buttonsGenerar = new Button[numPedidos];

        for(int i = 0; i < numPedidos; i++){
            buttonsVer[i] = new Button();
            buttonsVer[i].setText("Ver");
            buttonsVer[i].setOnAction(this::onActionVer);

            buttonsAct[i] = new Button();
            buttonsAct[i].setText("Actualizar");
            buttonsAct[i].setOnAction(this::onActionActualizar);

            buttonsArch[i] = new Button();
            buttonsArch[i].setText("Archivar");
            buttonsArch[i].setOnAction(this::onActionElim);
        }

        System.out.println(date);
        int i = 0;
        for(Pedido p : pedidos){
            System.out.println(date);
            System.out.println(p.getFecha());
            if (p.getArchivado() == 0 && p.getFecha().equals(date)) {
                data.add(new PedidoObservable(
                        p.getNumero(),
                        p.getNombre(),
                        p.getFecha(),
                        p.getValor(),
                        buttonsVer[i],
                        buttonsAct[i],
                        buttonsArch[i]
                ));
            }
            i++;
        }
        numeroColumna.setCellValueFactory(new PropertyValueFactory<PedidoObservable, String>("numero"));
        nombreColumna.setCellValueFactory(new PropertyValueFactory<PedidoObservable, String>("nombre"));
        fechaColumna.setCellValueFactory(new PropertyValueFactory<PedidoObservable, String>("fecha"));
        totalColumna.setCellValueFactory(new PropertyValueFactory<PedidoObservable, String>("valor"));
        verColumna.setCellValueFactory(new PropertyValueFactory<PedidoObservable, Button>("botonVer"));
        actualizarColumna.setCellValueFactory(new PropertyValueFactory<PedidoObservable, Button>("botonActualizar"));
        archivarColumna.setCellValueFactory(new PropertyValueFactory<PedidoObservable, Button>("botonArchivar"));
        tableViewListaPedidos.setItems(data);
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
    private TableColumn<PedidoObservable, Button> actualizarColumna;

    @FXML
    private TableColumn<PedidoObservable, Button> archivarColumna;

    @FXML
    private Button botonBuscar;

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
    private TextField textoBusqueda;

    @FXML
    private DatePicker dateSeleccionar;

    @FXML
    private TableColumn<PedidoObservable, String> fechaColumna;


    @FXML
    private TableColumn<PedidoObservable, String> nombreColumna;

    @FXML
    private TableColumn<PedidoObservable, String> numeroColumna;

    @FXML
    private TableColumn<PedidoObservable, String> totalColumna;

    @FXML
    private TableColumn<PedidoObservable, Button> verColumna;

    @FXML
    private TableView<PedidoObservable> tableViewListaPedidos;

    ArrayList<Pedido> pedidos;
    Button[] buttonsVer;
    Button[] buttonsAct;
    Button[] buttonsGenerar;
    Button[] buttonsArch;
}
