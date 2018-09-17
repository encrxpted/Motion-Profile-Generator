package application;

import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import util.PannableCanvas;

/*
 * This interface instantiates/sets constants
 */

public interface Constants {
	
	// FIELD DIMENSIONS
	public final static double START_X_PX = 5; // inclusive
	public final static double FIELD_X_PX = 556-5;
	public final static double FIELD_Y_PX = 682;
	public final static double FIELD_X_IN = 360;
	public final static double FIELD_Y_IN = 444;
	public final static double IN_TO_PX = FIELD_X_IN / FIELD_X_PX;
	public final static double PX_TO_IN = FIELD_X_PX / FIELD_X_IN;
	
	// ROBOT DIMENSIONS (INCLUDING BUMPERS)
	public final static double ROBOT_WIDTH_IN = 0;
	public final static double ROBOT_LENGTH_IN = 0;
	public final static double ROBOT_DT_WIDTH = 0; // drivetrain width (length between wheels)
	
	// IMAGES
	public InputStream mapIs = Main.class.getResourceAsStream("assets/2018Field.png");
	public ImageView mapImg = new ImageView(new Image(mapIs));
	
	// COLORS
	public final Color white = Color.WHITE;

	// OBJECTS THAT REPRESENT PART OF THE PROGRAM
	public static PannableCanvas canvas = new PannableCanvas();
	public static MenuHelper mh = new MenuHelper();

	// FONTS
	public static Font headerFont = Font.loadFont(Main.class.getResourceAsStream("fonts/futura medium bt.ttf"), 20);
	public static Font defaultFont = Font.loadFont(Main.class.getResourceAsStream("fonts/futura medium bt.ttf"), 15);

}
