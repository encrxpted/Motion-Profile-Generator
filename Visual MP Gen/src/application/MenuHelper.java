package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
 * This class handles the right menu
 */

public class MenuHelper implements Constants {

	private static GridPane gridPane = new GridPane();
	
	private static Label currentAutoLbl = new Label("CURRENT AUTO");
	private static Label autoNameLbl = new Label("Auto name: ");
	private static Text autoName = new Text();
	private static Label newLbl = new Label("[ NEW ]");
	private static Label openLbl = new Label("[ OPEN ]");
	private static Label saveLbl = new Label("[ SAVE ]");
	private static Label saveAsLbl = new Label("[ SAVE AS ]");
	private static Label startPosLbl = new Label("Start Pos: ");
	private static Text startPos = new Text();
	
	private static Label currentPathLbl = new Label("CURRENT PATH");
	private static Label currentPathNumLbl = new Label("Current path: ");
	
	private static Label spacer = new Label(" ");
	

	public static GridPane addGridPane() {
		gridPane.setAlignment(Pos.TOP_LEFT);
		gridPane.setVgap(5);
		setStyles();
		gridPane.setHgap(5);
	    gridPane.setStyle("-fx-background-color: rgba(0, 2, 20, 0.6);");
	    gridPane.setPadding(new Insets(18, 15, 12, 15));
		
	    gridPane.add(currentAutoLbl, 0, 0, 2, 1);
	    
	    gridPane.add(autoNameLbl, 0, 1);
	    gridPane.add(newLbl, 0, 2);
	    gridPane.add(openLbl, 0, 3, 2, 1);
	    gridPane.add(saveLbl, 0, 4, 2, 1);
	    gridPane.add(saveAsLbl, 0, 5, 2, 1);
	    gridPane.add(startPosLbl, 0, 6);
	    
	    gridPane.add(spacer, 0, 7, 2,1);
	    
		gridPane.add(currentPathLbl, 0, 8, 2, 1);
		gridPane.add(currentPathNumLbl, 0, 9);
		
		
		return gridPane;
	}

    // Sets fonts, colors, and hover events
	public static void setStyles() {
		currentPathLbl.setTextFill(white);
		currentPathLbl.setFont(headerFont);
		autoNameLbl.setTextFill(white);
		autoNameLbl.setFont(defaultFont);
		openLbl.setTextFill(white);
		openLbl.setFont(defaultFont);
		saveLbl.setFont(defaultFont);
		saveLbl.setTextFill(white);
		saveAsLbl.setTextFill(white);
		saveAsLbl.setFont(defaultFont);
		startPosLbl.setTextFill(white);
		startPosLbl.setFont(defaultFont);
		setHoverEvents(openLbl);
		setHoverEvents(saveLbl);
		setHoverEvents(saveAsLbl);
		autoName.setFill(white);
		autoName.setFont(defaultFont);
		startPos.setFont(defaultFont);
		startPos.setFill(white);
		newLbl.setTextFill(white);
		newLbl.setFont(defaultFont);
		setHoverEvents(newLbl);
		
		currentAutoLbl.setFont(headerFont);
		currentAutoLbl.setTextFill(white);
		currentPathNumLbl.setFont(defaultFont);
		currentPathNumLbl.setTextFill(white);
	}
	
	private static EventHandler<MouseEvent> onNewClicked = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			// Makes the window appear
            final Stage stage = new Stage();
            stage.setTitle("New Auto");
            stage.initModality(Modality.APPLICATION_MODAL);
            
            Label nameLbl = new Label("Name: ");
            TextField nameField = new TextField();
            Label startPosLbl = new Label("Start Pos: ");
            ObservableList<String> options = 
            	    FXCollections.observableArrayList(
            	        "Option 1",
            	        "Option 2",
            	        "Option 3"
            	    );
            final ComboBox startPosMenu = new ComboBox(options);
            

            
            GridPane gp = new GridPane();
		}
	};

	
	// Sets a shadow effect upon hovering over the node in the parameter
	public static void setHoverEvents(Node node) {
		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(5.0);
		dropShadow.setOffsetX(3.0);
		dropShadow.setOffsetY(3.0);
		dropShadow.setColor(Color.color(0, 0, 0));
		node.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				DropShadow dropShadow = new DropShadow();
				dropShadow.setRadius(5.0);
				dropShadow.setOffsetX(3.0);
				dropShadow.setOffsetY(3.0);
				dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
				node.setEffect(dropShadow);
			}
		});
		node.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				node.setEffect(null);
			}
		});
	}
	
}
