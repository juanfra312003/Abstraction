package com.abstraction.controllers.Controllers_Cotizacion;

import com.abstraction.business.*;
import com.abstraction.controllers.Controllers_Cotizacion.ObservableClasses.CotProdEditObs;
import com.abstraction.controllers.Controllers_DashBoard.Controller_DashBoard;
import com.abstraction.controllers.Controllers_Factura.Controller_Lista_Facturas;
import com.abstraction.controllers.Controllers_IniciarSesion.Controller_Iniciar_Sesion;
import com.abstraction.controllers.Controllers_Pedido.Controller_Lista_Pedidos;
import com.abstraction.controllers.Controllers_Perfil_Aux.Controller_Ver_Perfil;
import com.abstraction.controllers.Controllers_Producto.Controller_Lista_Productos;
import com.abstraction.entities.Cotizacion;
import com.abstraction.entities.CotizacionProducto;
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

public class Controller_Actualizar_Cotizacion {

    ICotizacion_facade facade;
    Cotizacion cotizacion;
    int numProds;

    public void initialize(ICotizacion_facade facade, Cotizacion cotizacion){
        this.facade = facade;
        this.cotizacion = cotizacion;
        numeroDeCotizacionText.setText(String.valueOf(cotizacion.getNumero()));
        actualizarTabla();
    }

    public void actualizarTabla(){
        nombreCotizacionText.setText(cotizacion.getNombre());
        nombreClienteText.setText(cotizacion.getNombreCliente());

        ArrayList<CotizacionProducto> productos = cotizacion.getProductos();
        if(productos == null) return;
        numProds = productos.size();

        final ObservableList<CotProdEditObs> data = FXCollections.observableArrayList();

        buttonsEliminar = new Button[numProds];
        fieldsExistencias = new TextField[numProds];

        for(int i = 0; i < numProds; i++){
            buttonsEliminar[i] = new Button();
            buttonsEliminar[i].setText("Eliminar");
            buttonsEliminar[i].setOnAction(this::onActionEliminar);

            fieldsExistencias[i] = new TextField();
            fieldsExistencias[i].setText(String.valueOf(productos.get(i).getCantidad()));
        }

        int i = 0;
        for(CotizacionProducto p : productos){
            data.add(new CotProdEditObs(
                    p.getProducto().getReferencia(),
                    p.getProducto().getNombre(),
                    p.getProducto().getPrecio(),
                    fieldsExistencias[i],
                    buttonsEliminar[i]
                    ));
            i++;
        }

        referenciaColumna.setCellValueFactory(new PropertyValueFactory<CotProdEditObs, String>("referencia"));
        nombreProductoColumna.setCellValueFactory(new PropertyValueFactory<CotProdEditObs, String>("nombre"));
        precioColumna.setCellValueFactory(new PropertyValueFactory<CotProdEditObs, String>("precioUnitario"));
        existenciasColumna.setCellValueFactory(new PropertyValueFactory<CotProdEditObs, TextField>("existencias"));
        eliminarColumna.setCellValueFactory(new PropertyValueFactory<CotProdEditObs, Button>("botonEliminar"));

        tableViewActualizarCotizacion.setItems(data);
    }

    @FXML
    void onActionEliminar(ActionEvent event) {
        try {
            for(int i = 0; i < numProds; i++){
                if(event.getSource() == buttonsEliminar[i]){
                    cotizacion.getProductos().remove(cotizacion.getProductos().get(i));
                    actualizarTabla();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @FXML
    void onActionActualizarGeneral(ActionEvent event) {
        boolean errorEnProceso = false;
        String productosFalla = "";
        String productosBlancos = "";

        for (int i = 0; i < numProds; i++) {
            if (!fieldsExistencias[i].getText().isBlank()) {
                int cantidadProducto = Integer.parseInt(fieldsExistencias[i].getText());
                if (cantidadProducto > 0) {
                    cotizacion.getProductos().get(i).setCantidad(cantidadProducto);
                } else {
                    productosFalla = productosFalla + cotizacion.getProductos().get(i).getProducto().getNombre() + " ";
                }
            }
            else{
                productosBlancos = productosBlancos + cotizacion.getProductos().get(i).getProducto().getNombre() + " ";
            }
        }

        /*
             -- ERRORES EN LA EJECUCION
         */

        if (!nombreCotizacionText.getText().isBlank()) {
            cotizacion.setNombre(nombreCotizacionText.getText());
        } else {
            //Arrojar la alerta del espacio en blanco
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error, espacio en blanco.");
            alert.setTitle("Error en el proceso");
            alert.setContentText("No es posible asignar un espacio en blanco cómo nombre de Cotización.");
            alert.show();

            //Anunciar que se presento un error en el proceso mediante la variable booleana
            errorEnProceso = true;

            //Reasignar en el espacio el nombre de la cotizacion anterior.
            nombreCotizacionText.setText(cotizacion.getNombre());
        }

        if (!nombreClienteText.getText().isBlank()) {
            cotizacion.setNombreCliente(nombreClienteText.getText());
        }
        else {
            //Arrojar la alerta del espacio en blanco
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error, espacio en blanco.");
            alert.setTitle("Error en el proceso");
            alert.setContentText("No es posible asignar un espacio en blanco cómo nombre del cliente que realiza la cotización.");
            alert.show();

            //Anunciar que se presento un error en el proceso mediante la variable booleana
            errorEnProceso = true;

            //Reasignar en el espacio el nombre del cliente anterior.
            nombreClienteText.setText(cotizacion.getNombreCliente());
        }

        if (!productosFalla.isEmpty()){
            //Arrojar la alerta de los productos que presentan falla
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error, cantidades negativas o en ceros.");
            alert.setTitle("Error en el proceso");
            alert.setContentText("No es posible asignar cantidades en ceros o negativas para uno o mas productos");
            alert.setContentText("Productos que presentan inconsistencias: " + productosFalla);
            alert.show();
        }

        if (!productosBlancos.isEmpty()){
            //Arrojar la alerta de los productos que presentan falla por estar en blanco
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error, cantidades en blanco.");
            alert.setTitle("Error en el proceso");
            alert.setContentText("No es posible asignar cantidades en blanco para un producto");
            alert.setContentText("Productos que presentan inconsistencias: " + productosFalla);
            alert.show();
        }

        if (!errorEnProceso && productosFalla.isEmpty() && productosBlancos.isEmpty()) {
            //Realizar la actualizacion correspondiente
            facade.actualizarCotizacion(cotizacion);

            //Lanzar mensaje de confirmacion de exito en el proceso
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Cotizacion Actualizada Exitosamente");
            alert.setTitle("Exito en el proceso");
            alert.setContentText("La cotización seleccionada se ha actualizado de manera satisfactoria con base a los datos ingresados.");
            alert.show();
        }
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
    private Button botonActualizarGeneral;

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
    private TableColumn<CotProdEditObs, Button> eliminarColumna;

    @FXML
    private TableColumn<CotProdEditObs, TextField> existenciasColumna;

    @FXML
    private TextField nombreClienteText;

    @FXML
    private TextField nombreCotizacionText;

    @FXML
    private TableColumn<CotProdEditObs, String> nombreProductoColumna;

    @FXML
    private Text numeroDeCotizacionText;

    @FXML
    private TableColumn<CotProdEditObs, String> precioColumna;

    @FXML
    private TableColumn<CotProdEditObs, String> referenciaColumna;

    @FXML
    private TableView<CotProdEditObs> tableViewActualizarCotizacion;

    Button[] buttonsEliminar;
    TextField[] fieldsExistencias;
}
