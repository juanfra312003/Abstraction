package com.abstraction.demo;

import com.abstraction.business.FacadeGeneral;
import com.abstraction.controllers.Controllers_Producto.Controller_Lista_Productos;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import javafx.application.Application;


import java.net.URL;
public class DemoApplication extends Application{
	public static Parent rootNode;
	public static Stage stage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		try {
			URL fxmlLocation = getClass().getResource("/presentation/View_Productos/mockupProductos.fxml");
			System.out.println(fxmlLocation);
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
			Scene scene = new Scene(fxmlLoader.load());
			stage.setTitle("Abstraction");
			stage.setScene(scene);
			Controller_Lista_Productos controladorListaProductos = fxmlLoader.getController();
			controladorListaProductos.setStage(stage);
			controladorListaProductos.facade = new FacadeGeneral();
			controladorListaProductos.facade.listarProductos();
			stage.show();
		}
		catch (Exception e){
			System.out.println("y tho");
			e.printStackTrace();
		}
	}
}
