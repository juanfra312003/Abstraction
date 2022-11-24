package com.abstraction.controllers.Controllers_Factura;

import com.abstraction.business.*;
import com.abstraction.controllers.Controllers_Cotizacion.Controller_Actualizar_Cotizacion;
import com.abstraction.controllers.Controllers_Cotizacion.Controller_Lista_Cotizaciones;
import com.abstraction.controllers.Controllers_Cotizacion.Controller_Ver_Cotizacion;
import com.abstraction.controllers.Controllers_Cotizacion.ObservableClasses.CotizacionObservable;
import com.abstraction.controllers.Controllers_DashBoard.Controller_DashBoard;
import com.abstraction.controllers.Controllers_Factura.ObservableClasses.FacturaObservable;
import com.abstraction.controllers.Controllers_IniciarSesion.Controller_Iniciar_Sesion;
import com.abstraction.controllers.Controllers_Pedido.Controller_Lista_Pedidos;
import com.abstraction.controllers.Controllers_Perfil_Aux.Controller_Ver_Perfil;
import com.abstraction.controllers.Controllers_Producto.Controller_Lista_Productos;
import com.abstraction.entities.Cotizacion;
import com.abstraction.entities.Factura;
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
import java.util.Date;

public class Controller_Lista_Facturas {

    public IFactura_facade facade;

    int numFacts = 0;

    public void initialize(IFactura_facade facade){
        this.facade = facade;
        this.actualizarTabla();
    }


    public void actualizarTabla (){
        facturas = facade.listarFacturas();
        if (facturas == null) return;
        final ObservableList<FacturaObservable> data = FXCollections.observableArrayList();
        numFacts = facturas.size();
        buttonsVer = new Button[numFacts];
        buttonsAct = new Button[numFacts];
        buttonsArch = new Button[numFacts];

        for(int i = 0; i < numFacts; i++){
            buttonsVer[i] = new Button();
            buttonsVer[i].setText("Ver");
            buttonsVer[i].setOnAction(this::onActionVer);

            buttonsAct[i] = new Button();
            buttonsAct[i].setText("Actualizar");
            buttonsAct[i].setOnAction(this::onActionActualizar);

            buttonsArch[i] = new Button();
            buttonsArch[i].setText("Archivar");
            buttonsArch[i].setOnAction(this::onActionArch);
        }

        int i = 0;
        for(Factura factura : facturas){
            if (factura.getArchivado() == 0) {
                data.add(new FacturaObservable(
                        factura.getNumero(),
                        factura.getPedidoFactura().getNumero(),
                        factura.getPedidoFactura().getNombreCliente(),
                        factura.getFecha(),
                        factura.getValorTotal(),
                        buttonsVer[i],
                        buttonsAct[i],
                        buttonsArch[i]
                ));
            }
            i++;
        }

        numeroFacturaColumna.setCellValueFactory(new PropertyValueFactory<FacturaObservable, String>("numeroFactura"));
        numeroPedidoColumna.setCellValueFactory(new PropertyValueFactory<FacturaObservable, String>("numeroPedido"));
        nombreClienteColumna.setCellValueFactory(new PropertyValueFactory<FacturaObservable, String>("nombreCliente"));
        fechaColumna.setCellValueFactory(new PropertyValueFactory<FacturaObservable, String>("fecha"));
        totalColumna.setCellValueFactory(new PropertyValueFactory<FacturaObservable, String>("precio"));
        verColumna.setCellValueFactory(new PropertyValueFactory<FacturaObservable, Button>("botonVer"));
        actualizarColumna.setCellValueFactory(new PropertyValueFactory<FacturaObservable, Button>("botonActualizar"));
        archivarColumna.setCellValueFactory(new PropertyValueFactory<FacturaObservable, Button>("botonArchivar"));
        tableViewListaFacturas.setItems(data);
    }

    public void actualizarTablaBusqueda (String numeroBusqueda){
        facturas = facade.listarFacturas();
        if (facturas == null) return;
        final ObservableList<FacturaObservable> data = FXCollections.observableArrayList();
        numFacts = facturas.size();
        buttonsVer = new Button[numFacts];
        buttonsAct = new Button[numFacts];
        buttonsArch = new Button[numFacts];

        for(int i = 0; i < numFacts; i++){
            buttonsVer[i] = new Button();
            buttonsVer[i].setText("Ver");
            buttonsVer[i].setOnAction(this::onActionVer);

            buttonsAct[i] = new Button();
            buttonsAct[i].setText("Actualizar");
            buttonsAct[i].setOnAction(this::onActionActualizar);

            buttonsArch[i] = new Button();
            buttonsArch[i].setText("Archivar");
            buttonsArch[i].setOnAction(this::onActionArch);
        }

        int i = 0;
        for(Factura factura : facturas){
            if (factura.getArchivado() == 0 && String.valueOf(factura.getNumero()).contains(numeroBusqueda)) {
                data.add(new FacturaObservable(
                        factura.getNumero(),
                        factura.getPedidoFactura().getNumero(),
                        factura.getPedidoFactura().getNombreCliente(),
                        factura.getFecha(),
                        factura.getValorTotal(),
                        buttonsVer[i],
                        buttonsAct[i],
                        buttonsArch[i]
                ));
            }
            i++;
        }

        numeroFacturaColumna.setCellValueFactory(new PropertyValueFactory<FacturaObservable, String>("numeroFactura"));
        numeroPedidoColumna.setCellValueFactory(new PropertyValueFactory<FacturaObservable, String>("numeroPedido"));
        nombreClienteColumna.setCellValueFactory(new PropertyValueFactory<FacturaObservable, String>("nombreCliente"));
        fechaColumna.setCellValueFactory(new PropertyValueFactory<FacturaObservable, String>("fecha"));
        totalColumna.setCellValueFactory(new PropertyValueFactory<FacturaObservable, String>("precio"));
        verColumna.setCellValueFactory(new PropertyValueFactory<FacturaObservable, Button>("botonVer"));
        actualizarColumna.setCellValueFactory(new PropertyValueFactory<FacturaObservable, Button>("botonActualizar"));
        archivarColumna.setCellValueFactory(new PropertyValueFactory<FacturaObservable, Button>("botonArchivar"));
        tableViewListaFacturas.setItems(data);
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
        //Facturacion -> Modelado Equivocacion
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
        Date date = new Date(dateSeleccionar.getValue().toEpochDay());
        actualizarTablaPorFecha(date);
    }

    public void actualizarTablaPorFecha(Date date){
        facturas = facade.listarFacturas();
        if (facturas == null) return;
        final ObservableList<FacturaObservable> data = FXCollections.observableArrayList();
        numFacts = facturas.size();
        buttonsVer = new Button[numFacts];
        buttonsAct = new Button[numFacts];
        buttonsArch = new Button[numFacts];

        for(int i = 0; i < numFacts; i++){
            buttonsVer[i] = new Button();
            buttonsVer[i].setText("Ver");
            buttonsVer[i].setOnAction(this::onActionVer);

            buttonsAct[i] = new Button();
            buttonsAct[i].setText("Actualizar");
            buttonsAct[i].setOnAction(this::onActionActualizar);

            buttonsArch[i] = new Button();
            buttonsArch[i].setText("Archivar");
            buttonsArch[i].setOnAction(this::onActionArch);
        }

        int i = 0;
        for(Factura factura : facturas){
            if (factura.getArchivado() == 0 && factura.getFecha().equals(date)) {
                data.add(new FacturaObservable(
                        factura.getNumero(),
                        factura.getPedidoFactura().getNumero(),
                        factura.getPedidoFactura().getNombreCliente(),
                        factura.getFecha(),
                        factura.getValorTotal(),
                        buttonsVer[i],
                        buttonsAct[i],
                        buttonsArch[i]
                ));
            }
            i++;
        }

        numeroFacturaColumna.setCellValueFactory(new PropertyValueFactory<FacturaObservable, String>("numeroFactura"));
        numeroPedidoColumna.setCellValueFactory(new PropertyValueFactory<FacturaObservable, String>("numeroPedido"));
        nombreClienteColumna.setCellValueFactory(new PropertyValueFactory<FacturaObservable, String>("nombreCliente"));
        fechaColumna.setCellValueFactory(new PropertyValueFactory<FacturaObservable, String>("fecha"));
        totalColumna.setCellValueFactory(new PropertyValueFactory<FacturaObservable, String>("precio"));
        verColumna.setCellValueFactory(new PropertyValueFactory<FacturaObservable, Button>("botonVer"));
        actualizarColumna.setCellValueFactory(new PropertyValueFactory<FacturaObservable, Button>("botonActualizar"));
        archivarColumna.setCellValueFactory(new PropertyValueFactory<FacturaObservable, Button>("botonArchivar"));
        tableViewListaFacturas.setItems(data);
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
            for(int i = 0; i < numFacts; i++){
                if(event.getSource() == buttonsVer[i]){
                    cargarVer(facturas.get(i));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cargarVer(Factura factura) throws IOException {
        Stage stage = new Stage();
        URL fxmlLocation = getClass().getResource("/presentation/View_Facturas/mockupVerFactura.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abstraction");
        stage.setScene(scene);
        Controller_Ver_Factura controller_ver_factura = fxmlLoader.getController();
        controller_ver_factura.setStage(stage);
        controller_ver_factura.initialize((IFactura_facade) this.facade, factura);
        stage.show();
        this.stage.close();
    }

    @FXML
    public void onActionArch(ActionEvent event) {
        try {
            for(int i = 0; i < numFacts; i++){
                if(event.getSource() == buttonsArch[i]){
                    if (facade.archivarFactura(facturas.get(i).getNumero())) {

                        //Actualizar tabla con los datos de manera respectiva.
                        this.actualizarTabla();

                        //Arrojar la confirmación del proceso.
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText("Factura Archivada Exitosamente");
                        alert.setTitle("Exito en el proceso");
                        alert.setContentText("La factura seleccionada se ha archivado de manera satisfactoria.");
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
            for(int i = 0; i < numFacts; i++){
                if(event.getSource() == buttonsAct[i]){
                    cargarActualizar(facturas.get(i));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cargarActualizar (Factura factura) throws IOException{
        Stage stage = new Stage();
        URL fxmlLocation = getClass().getResource("/presentation/View_Cotizaciones/mockupActualizarFactura.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abstraction");
        stage.setScene(scene);
        Controller_Actualizar_Factura controller_actualizar_factura = fxmlLoader.getController();
        controller_actualizar_factura.setStage(stage);
        controller_actualizar_factura.initialize((IFactura_facade) this.facade, factura);
        stage.show();
        this.stage.close();
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
    private TableColumn<FacturaObservable, Button> actualizarColumna;

    @FXML
    private TableColumn<FacturaObservable, Button> archivarColumna;

    @FXML
    private Button botonBuscar;

    @FXML
    private TableView<FacturaObservable> tableViewListaFacturas;

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
    private DatePicker dateSeleccionar;

    @FXML
    private TextField textoBusqueda;

    @FXML
    private TableColumn<FacturaObservable, String> fechaColumna;

    @FXML
    private TableColumn<FacturaObservable, String> nombreClienteColumna;

    @FXML
    private TableColumn<FacturaObservable, String> numeroFacturaColumna;



    @FXML
    private TableColumn<FacturaObservable, String> numeroPedidoColumna;

    @FXML
    private TableColumn<FacturaObservable, String> totalColumna;

    @FXML
    private TableColumn<FacturaObservable, Button> verColumna;


    ArrayList<Factura> facturas;
    Button[] buttonsVer;
    Button[] buttonsAct;
    Button[] buttonsArch;
}
