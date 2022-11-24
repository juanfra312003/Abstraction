package com.abstraction.controllers.Controllers_Factura;

import com.abstraction.business.*;
import com.abstraction.controllers.Controllers_Cotizacion.Controller_Lista_Cotizaciones;
import com.abstraction.controllers.Controllers_DashBoard.Controller_DashBoard;
import com.abstraction.controllers.Controllers_Factura.ObservableClasses.CotProdEditFact;
import com.abstraction.controllers.Controllers_IniciarSesion.Controller_Iniciar_Sesion;
import com.abstraction.controllers.Controllers_Pedido.Controller_Lista_Pedidos;
import com.abstraction.controllers.Controllers_Perfil_Aux.Controller_Ver_Perfil;
import com.abstraction.controllers.Controllers_Producto.Controller_Lista_Productos;
import com.abstraction.entities.Cotizacion;
import com.abstraction.entities.CotizacionProducto;
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

public class Controller_Actualizar_Factura {

    public IFactura_facade facade;
    Factura factura;
    int numProds = 0;

    public void initialize(IFactura_facade facade , Factura factura){
        this.facade = facade;
        this.factura = factura;

        //Definir valores iniciales a mostrar (Estáticos)
        numeroDeFacturaText.setText(String.valueOf(factura.getNumero()));
        numeroPedidoText.setText(String.valueOf(factura.getPedidoFactura().getNumero()));
        precioTotalText.setText(String.valueOf(factura.getValorTotal()));
        fechaFacturaText.setText(factura.getFecha().toString());
        abonoRealizadoText.setText("0.0");
        nombreClienteText.setText(factura.getPedidoFactura().getNombreCliente());
        excedenteText.setText("$"+String.valueOf(factura.getValorTotal()-factura.getAbonoTotal()));

        //Mostrar la información de la tabla de acuerdo a cómo corresponda
        actualizarTabla();
    }

    public void actualizarTabla(){
        ArrayList<CotizacionProducto> productos = factura.getPedidoFactura().getCotizacionPedido().getProductos();
        if (productos == null) return;
        numProds = productos.size();

        final ObservableList<CotProdEditFact> data = FXCollections.observableArrayList();

        int i = 0;
        for (CotizacionProducto producto : productos){
            data.add(new CotProdEditFact(
                    producto.getProducto().getReferencia(),
                    producto.getProducto().getNombre(),
                    producto.getProducto().getPrecio(),
                    producto.getCantidad(),
                    producto.getSubtotal()
                    ));
            i++;
        }
        referenciaColumna.setCellValueFactory(new PropertyValueFactory<CotProdEditFact, String>("referencia"));
        nombreProductoColumna.setCellValueFactory(new PropertyValueFactory<CotProdEditFact, String>("nombre"));
        precioUnitarioColumna.setCellValueFactory(new PropertyValueFactory<CotProdEditFact, String>("precioUnitario"));
        numProductosColumna.setCellValueFactory(new PropertyValueFactory<CotProdEditFact, String>("existencias"));
        subTotalColumna.setCellValueFactory(new PropertyValueFactory<CotProdEditFact, String>("subTotal"));

        tableViewActualizarFactura.setItems(data);
    }

    @FXML
    void onActionActualizarFacturaValores(ActionEvent event) {


        //Validaciones sobre los abonos a realizar a través de la actualización del campo.
        if (!abonoRealizadoText.getText().isBlank()){
            String cadenaAlmacenamientoFieldAbono = abonoRealizadoText.getText();
            boolean isNumeric =  cadenaAlmacenamientoFieldAbono.matches("[+-]?\\d*(\\.\\d+)?");

            //Si el dato no es numerico, arroja la alerta de dicha situación
            if (!isNumeric){
                //Arrojar la alerta del espacio no numerico ingresado
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error, valor ingresado no númerico.");
                alert.setTitle("Error en el proceso");
                alert.setContentText("No es posible asignar un abono cómo un valor no númerico para la factura.");
                alert.show();

                //Reestablecer cómo el textField manipulado anteriormente a su valor original en la instancia del objeto
                abonoRealizadoText.setText(String.valueOf(factura.getAbonoTotal()));

                return;
            }
            else{
                float abonoIngresadoText = Float.valueOf(abonoRealizadoText.getText());
                float abonoExistente = factura.getAbonoTotal();

                if (abonoIngresadoText <= 0){
                    //Arrojar la alerta de espacio negativo
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error, abono negativo o en ceros");
                    alert.setTitle("Error en el proceso");
                    alert.setContentText("No es posible asignar un abono cómo valor negativo o cantidad nula.");
                    alert.show();

                    //Reestablecer cómo el textField manipulado anteriormente a su valor original en la instancia del objeto
                    abonoRealizadoText.setText(String.valueOf(factura.getAbonoTotal()));

                    return;
                }
                else if (abonoExistente + abonoIngresadoText > factura.getValorTotal()){
                    //Arrojar la alerta de sobrepaso de los valores que representan el total
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error, con el registro del abono se sobre-excede el total del pedido");
                    alert.setTitle("Error en el proceso");
                    alert.setContentText("No es posible asignar el abono de manera satisfactoria ya que se sobrepasa el valor del pedido pactado en el total.");
                    alert.show();

                    //Reestablecer cómo el textField manipulado anteriormente a su valor original en la instancia del objeto
                    abonoRealizadoText.setText(String.valueOf(factura.getAbonoTotal()));

                    return;
                }
                else{ //Caso exitoso
                    //Realizar la asignación de manera correcta
                    factura.setAbonoTotal(abonoExistente + abonoIngresadoText);
                    excedenteText.setText("$"+String.valueOf(factura.getValorTotal()-factura.getAbonoTotal()));
                }
            }
        }
        else{
            //Arrojar la alerta del espacio en blanco
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error, espacio en blanco.");
            alert.setTitle("Error en el proceso");
            alert.setContentText("No es posible asignar un espacio en blanco cómo abono realizado a la factura.");
            alert.show();

            //Reestablecer cómo el textField manipulado anteriormente a su valor original en la instancia del objeto
            abonoRealizadoText.setText(String.valueOf(factura.getAbonoTotal()));

            return;
        }

        //En caso de que no hubiese retornado la función (Es decir no hubieran errores a lo largo del proceso, se realiza la actualización en el mecanismo de persistencia)
        facade.actualizarFactura(factura.getNumero(), factura);

        //Lanzar mensaje de confirmacion de exito en el proceso
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Factura Actualizada Exitosamente");
        alert.setTitle("Exito en el proceso");
        alert.setContentText("La factura seleccionada se ha actualizado de manera satisfactoria con base a los datos ingresados.");
        alert.show();
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
    private TextField abonoRealizadoText;

    @FXML
    private Button actualizarFacturaValores;

    @FXML
    private TableView<CotProdEditFact> tableViewActualizarFactura;

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
    private Button botonRegresar;

    @FXML
    private Text excedenteText;

    @FXML
    private Text fechaFacturaText;

    @FXML
    private Text precioTotalText;

    @FXML
    private TableColumn<CotProdEditFact, String> nombreProductoColumna;

    @FXML
    private TableColumn<CotProdEditFact, String> numProductosColumna;

    @FXML
    private Text numeroDeFacturaText;

    @FXML
    private Text numeroPedidoText;

    @FXML
    private Text nombreClienteText;

    @FXML
    private TableColumn<CotProdEditFact, String> precioUnitarioColumna;

    @FXML
    private TableColumn<CotProdEditFact, String> referenciaColumna;

    @FXML
    private TableColumn<CotProdEditFact, String>subTotalColumna;
}