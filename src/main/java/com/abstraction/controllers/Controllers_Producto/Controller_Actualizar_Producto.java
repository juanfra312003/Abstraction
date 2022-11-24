package com.abstraction.controllers.Controllers_Producto;

import com.abstraction.business.*;
import com.abstraction.controllers.Controllers_Cotizacion.Controller_Lista_Cotizaciones;
import com.abstraction.controllers.Controllers_Cotizacion.ObservableClasses.CotProdEditObs;
import com.abstraction.controllers.Controllers_DashBoard.Controller_DashBoard;
import com.abstraction.controllers.Controllers_Factura.Controller_Lista_Facturas;
import com.abstraction.controllers.Controllers_IniciarSesion.Controller_Iniciar_Sesion;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javafx.scene.control.Alert;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class Controller_Actualizar_Producto {

    public IProducto_facade facade;
    Producto producto;
    int numProductos;
    public void initialize(IProducto_facade facade, Producto producto){
        this.facade = facade;
        this.producto=producto;
        this.cantidadesExistentesText.setText(String.valueOf(producto.getExistencias()));
        this.nombreProductoText.setText(String.valueOf(producto.getNombre()));
        this.referenciaProductoText.setText(String.valueOf(producto.getExistencias()));
        this.precioProductoText.setText(String.valueOf(producto.getPrecio()));
        this.descripcionProductoText.setText(producto.getDescripcion());
    }

    @FXML
    void onActionActualizar(ActionEvent event) {
        String nombreActualizar;
        Float precio;
        int cantExistentes;
        String descripcionProducto;
        String productosFalla = "";

        if(!nombreProductoText.getText().isBlank()&&!precioProductoText.getText().isBlank()&&!cantidadesExistentesText.getText().isBlank()&&!descripcionProductoText.getText().isBlank()){
            nombreActualizar=String.valueOf(nombreProductoText.getText());
            System.out.println(nombreActualizar);
            producto.setNombre(nombreActualizar);


            precio=Float.parseFloat(precioProductoText.getText());
            producto.setPrecio(precio);


            descripcionProducto=descripcionProductoText.getText();
            producto.setDescripcion(descripcionProducto);

            cantExistentes=Integer.parseInt(cantidadesExistentesText.getText());

            if(cantExistentes>0){
                producto.setExistencias(cantExistentes);
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Producto Actualizado");
            alert.setTitle("Exito en el proceso");
            alert.setContentText("El producto fue creado exitosamente.");
            alert.show();
        }
        else {
             /*
             -- ERRORES EN LA EJECUCION
             */

            if(nombreProductoText.getText().isBlank()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Producto No Actualizado");
                alert.setTitle("GUAGUI en el proceso");
                alert.setContentText("El producto no fue creado exitosamente, indique el nombre del producto a actualizar.");
                alert.show();
            }
            else if(precioProductoText.getText().isBlank()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Producto No Actualizado");
                alert.setTitle("Fallo en el proceso");
                alert.setContentText("El producto no fue creado exitosamente, indique el precio del producto a actualizar.");
                alert.show();
            }
            else if(cantidadesExistentesText.getText().isBlank()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Producto No Actualizado");
                alert.setTitle("Fallo en el proceso");
                alert.setContentText("El producto no fue creado exitosamente, indique las cantidades del producto a actualizar.");
                alert.show();
            }
            else if(descripcionProductoText.getText().isBlank()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Producto No Actualizado");
                alert.setTitle("Fallo en el proceso");
                alert.setContentText("El producto no fue creado exitosamente, indique la descripcion del producto a actualizar.");
                alert.show();
            }


        }
        facade.actualizarProducto(producto);

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
    void onActionRegresar(ActionEvent event){
        cargarListaProductos();
    }

    @FXML
    void onActionProductos(ActionEvent event){
        cargarListaProductos();
    }


    /**
     *
     * Cambios de pantalla
     *
     */

    // Cambio a Lista_productos
    void cargarListaProductos (){
        try{
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
        }
        catch(IOException e){
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
    void cargarListaPedidos(){
        try{
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
        }
        catch(IOException e){
            System.out.printf(e.getMessage());
        }
    }

    //Cargar Lista_facturas
    void cargarListaFacturas(){
        try{
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
        }
        catch(IOException e){
            System.out.printf(e.getMessage());
        }
    }

    //Cargar Dashboard
    void cargarDashboard(){
        try{
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
        }
        catch(IOException e){
            System.out.printf(e.getMessage());
        }
    }

    //Cargar Lista_cotizaciones
    void cargarListaCotizaciones(){
        try{
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
        }
        catch(IOException e){
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
    private Button botonActualizarProducto;
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
    private Button botonProductos;

    @FXML
    private Button botonRegresar;

    @FXML
    private TextField cantidadesExistentesText;

    @FXML
    private TextField descripcionProductoText;

    @FXML
    private TextField nombreProductoText;

    @FXML
    private TextField precioProductoText;

    @FXML
    private TextField referenciaProductoText;

    Button[] buttonsVer;
    Button[] buttonsAct;
    Button[] buttonsBorrar;

    ArrayList<Producto> productos;
}
