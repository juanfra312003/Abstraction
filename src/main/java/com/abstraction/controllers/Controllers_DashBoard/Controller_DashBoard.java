package com.abstraction.controllers.Controllers_DashBoard;

import com.abstraction.business.*;
import com.abstraction.controllers.Controllers_Cotizacion.Controller_Lista_Cotizaciones;
import com.abstraction.controllers.Controllers_DashBoard.ObservableClasses.ObservableLeads;
import com.abstraction.controllers.Controllers_Factura.Controller_Lista_Facturas;
import com.abstraction.controllers.Controllers_IniciarSesion.Controller_Iniciar_Sesion;
import com.abstraction.controllers.Controllers_Pedido.Controller_Lista_Pedidos;
import com.abstraction.controllers.Controllers_Perfil_Aux.Controller_Ver_Perfil;
import com.abstraction.controllers.Controllers_Producto.Controller_Lista_Productos;
import com.abstraction.entities.Factura;
import com.abstraction.persistence.IFacturaDAO;
import com.abstraction.persistence.impl.FacturaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.chart.NumberAxis;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class Controller_DashBoard {

    public IDashboard_facade facade;

    public void initialize(IDashboard_facade facade){
        this.facade = new FacadeGeneral();
        choiceBoxYearIngresosTiempo.getItems().addAll("2021","2022");
        choiceBoxPeriodoIngresosTiempo.getItems().addAll("Periodo 1", "Periodo 2");
        actualizarTextCreditos();
    }

    public void actualizarTextCreditos (){
        creditosOtorgadosText.setText("$" + String.valueOf(facade.verCreditos()));
    }

    @FXML
    void onActionCalcularIngresos(ActionEvent event) {
        ingresosPeriodoTiempoText.setText("$"+String.valueOf(facade.verIngresos(choiceBoxYearIngresosTiempo.getValue(), choiceBoxPeriodoIngresosTiempo.getValue())));
    }

    @FXML
    void onActionCalcularLeads(ActionEvent event) {
        actualizarTablaLeads();
    }

    void actualizarTablaLeads(){
        IFacturaDAO facturaDAO = new FacturaDAO();
        ArrayList<Factura> facturas = facturaDAO.findAll();

        if (facturas.isEmpty()) return;

        final ObservableList<ObservableLeads> data = FXCollections.observableArrayList();

        for (int i = 0; i < facturas.size(); i++){
            data.add(new ObservableLeads(
                    facturas.get(i).getNumero(),
                    facturas.get(i).getPedidoFactura().getCotizacionPedido().getNumero(),
                    facturas.get(i).getValorTotal()
            ));
        }
        numeroCotizacionColumna.setCellValueFactory(new PropertyValueFactory<ObservableLeads, String>("numeroCotizacion"));
        numeroFacturaColumna.setCellValueFactory(new PropertyValueFactory<ObservableLeads, String>("numeroFactura"));
        valorFacturacionColumna.setCellValueFactory(new PropertyValueFactory<ObservableLeads, String>("total"));
        tablaConversionLeads.setItems(data);
    }
    @FXML
    void onActionCalcularRendimiento(ActionEvent event) {

    }

    @FXML
    void onActionCerrarSesion(ActionEvent event) {
        cargarCerrarSesion();
    }

    @FXML
    void onActionChoiceBoxPeriodoRendProducto(MouseEvent event) {

    }

    @FXML
    void onActionChoiceBoxSelectProduct(MouseEvent event) {

    }

    @FXML
    void onActionCotizaciones(ActionEvent event) throws IOException {
        cargarListaCotizaciones();
    }

    @FXML
    void onActionCrecVentas(ActionEvent event) {

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
    void onActionIngresosPeriodoTiempoText(ActionEvent event) {

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
    void onActionPeriodoCrecVentas(MouseEvent event) {

    }

    @FXML
    void onActionProductos(ActionEvent event) throws IOException {
        cargarListaProductos();
    }

    @FXML
    void onActionValorTransaccionPromedio(ActionEvent event) {

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
    private Button botonCalcularIngresos;

    @FXML
    private Button botonCalcularLeads;

    @FXML
    private Button botonCalcularRendimiento;

    @FXML
    private Button botonCerrarSesion;

    @FXML
    private Button botonCotizaciones;

    @FXML
    private Button botonCrecVentas;

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
    private ChoiceBox<?> choiceBoxPeriodoAnalisisRendProduc;

    @FXML
    private ChoiceBox<?> choiceBoxPeriodoCrecVentas;

    @FXML
    private ChoiceBox<String> choiceBoxPeriodoIngresosTiempo;

    @FXML
    private ChoiceBox<?> choiceBoxPeriodoValorPromedio;

    @FXML
    private ChoiceBox<?> choiceBoxProductoAnalisisRendProduc;

    @FXML
    private ChoiceBox<String> choiceBoxYearIngresosTiempo;

    @FXML
    private ChoiceBox<?> choiceBoxYearValorPromedio;

    @FXML
    private LineChart<?, ?> graficaCrecimientoVentas;

    @FXML
    private LineChart<?, ?> graficaRendimientoProducto;

    @FXML
    private Text ingresosPeriodoTiempoText;

    @FXML
    private Text creditosOtorgadosText;

    @FXML
    private TableColumn<ObservableLeads, String> numeroCotizacionColumna;

    @FXML
    private TableColumn<ObservableLeads, String> numeroFacturaColumna;

    @FXML
    private TitledPane panelConversionLeads;

    @FXML
    private TitledPane panelValTransProm;

    @FXML
    private TitledPane panelVerIngresos;

    @FXML
    private TableView<ObservableLeads> tablaConversionLeads;

    @FXML
    private TableColumn<ObservableLeads, String> valorFacturacionColumna;

    @FXML
    private Text valorTransaccionPromedioText;
}
