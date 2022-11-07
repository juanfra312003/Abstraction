package com.abstraction.controllers.Controllers_Cotizacion;
import com.abstraction.business.FacadeGeneral;
import com.abstraction.business.ICotizacion_facade;
import com.abstraction.controllers.Controllers_DashBoard.Controller_DashBoard;
import com.abstraction.controllers.Controllers_Factura.Controller_Lista_Facturas;
import com.abstraction.controllers.Controllers_Pedido.Controller_Lista_Pedidos;
import com.abstraction.controllers.Controllers_Perfil_Aux.Controller_Ver_Perfil;
import com.abstraction.controllers.Controllers_Producto.Controller_Lista_Productos;
import com.abstraction.controllers.Controllers_Producto.Controller_Ver_Producto;
import com.abstraction.controllers.Controllers_Producto.ProductoObservable;
import com.abstraction.entities.Cotizacion;
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
        this.facade = facade;
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
            //buttonsAct[i].setOnAction(this::onActionActualizar);

            buttonsArch[i] = new Button();
            buttonsArch[i].setText("Archivar");
            //buttonsArch[i].setOnAction(this::onActionArch);

            buttonsGenerar[i] = new Button();
            buttonsGenerar[i].setText("Generar");
            buttonsGenerar[i].setOnAction(this::onActionGenerar);
        }

        int i = 0;
        for(Cotizacion cotizacion : cotizaciones){
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

    }

    @FXML
    void onActionCerrarSesion(ActionEvent event) {

    }

    @FXML
    void onActionCotizacion(ActionEvent event) throws IOException {
        //Acá es Facturacion, error de digitación al hacer Mockups.
        //onActionFacturacion.
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
    void onActionCrearCotizacion(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        URL fxmlLocation = getClass().getResource("/presentation/View_Cotizaciones/mockupCrearCotizacion.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abstraction");
        stage.setScene(scene);
        Controller_Crear_Cotizacion controller_crear_cotizacion = fxmlLoader.getController();
        controller_crear_cotizacion.facade = this.facade;
        controller_crear_cotizacion.actualizarTabla();
        controller_crear_cotizacion.setStage(stage);
        stage.show();
        this.stage.close();
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
    void onActionPerfil(ActionEvent event) throws IOException {
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
    void onActionProducto(ActionEvent event) throws IOException {
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

    public void cargarVer(Cotizacion cotizacion) throws IOException {
        Stage stage = new Stage();
        URL fxmlLocation = getClass().getResource("/presentation/View_Cotizaciones/mockupVerCotizacion.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abstraction");
        stage.setScene(scene);
        Controller_Ver_Cotizacion controller_ver_cotizacion = fxmlLoader.getController();
        controller_ver_cotizacion.setStage(stage);
        controller_ver_cotizacion.facade = this.facade;
        controller_ver_cotizacion.mostrarCotizacion(cotizacion);
        stage.show();
        this.stage.close();
    }

    @FXML
    public void onActionGenerar(ActionEvent event) {
        try {
            for(int i = 0; i < numCots; i++){
                if(event.getSource() == buttonsVer[i]){
                    cargarGenerar(cotizaciones.get(i));
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cargarGenerar(Cotizacion cotizacion) throws IOException {
        /*Stage stage = new Stage();
        URL fxmlLocation = getClass().getResource("/presentation/View_Productos/mockupVerProducto.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Abstraction");
        stage.setScene(scene);
        Controller_Ver_Producto controller_ver_producto = fxmlLoader.getController();
        controller_ver_producto.setStage(stage);
        //controller_ver_producto.facade = this.facade;
        //controller_ver_producto.mostrarProducto(producto);
        stage.show();
        this.stage.close();*/
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
    private Text cotizacionBuscar;

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
