package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	
	private static Label currentLbl = new Label("CURRENT PATH");

	public static GridPane addGridPane() {
		gridPane.setAlignment(Pos.TOP_LEFT);
		gridPane.setVgap(5);
		setStyles();
		gridPane.setHgap(5);
	    gridPane.setStyle("-fx-background-color: rgba(0, 2, 20, 0.6);");
	    gridPane.setPadding(new Insets(18, 15, 12, 15));
		

		gridPane.add(currentLbl, 0, 0);
		
		return gridPane;
	}

    // Sets fonts, colors, and hover events
	public static void setStyles() {
		currentLbl.setTextFill(white);
		currentLbl.setFont(headerFont);
	}


	
}
