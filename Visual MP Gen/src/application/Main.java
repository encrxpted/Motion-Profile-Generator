package application;

import util.SceneGestures;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import application.autos.FirstCurve;
import application.autos.MovableCurves;
import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import util.NodeGestures;
import util.PannableCanvas;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class Main extends Application implements Constants {
	public static GridPane rightMenu;
	
	@Override
	public void start(Stage primaryStage) {
		MovableCurves curve = new MovableCurves();
		FirstCurve curve1 = new FirstCurve("Left");
		canvas.getChildren().addAll(mapImg, curve, curve1);

		rightMenu = MenuHelper.addGridPane(); // Adds the menu on the right
		
		mapImg.setSmooth(false);
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(canvas);
		borderPane.setRight(rightMenu);
		
		StackPane sp = new StackPane();
		sp.getChildren().add(borderPane);
		sp.setStyle("-fx-background-color: rgba(1,1,1,1);");
		sp.setAlignment(Pos.TOP_LEFT);
		sp.setPadding(new Insets(12, 12, 12, 12));
		
		NodeGestures nodeGestures = new NodeGestures(canvas);

		// Instantiates the scene and adds events to allow it to be pannable
		Scene scene = new Scene(sp, 980, 560, false, SceneAntialiasing.DISABLED);
		SceneGestures sceneGestures = new SceneGestures(canvas);
		canvas.addEventFilter(MouseEvent.MOUSE_PRESSED, sceneGestures.getOnMousePressedEventHandler());
		canvas.addEventFilter(MouseEvent.MOUSE_DRAGGED, sceneGestures.getOnMouseDraggedEventHandler());
		canvas.addEventFilter(ScrollEvent.ANY, sceneGestures.getOnScrollEventHandler());
				
		primaryStage.setTitle("This better work");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static GridPane getMenu() {
		return rightMenu;
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public PannableCanvas getCanvas() {
		return canvas;
	}
	
	public static MenuHelper getPaneHelper() {
		return mh;
	}
}
