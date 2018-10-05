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
	
	// CONVERSIONS
	public final static double IN_TO_PX = FIELD_X_IN / FIELD_X_PX;
	public final static double PX_TO_IN = FIELD_X_PX / FIELD_X_IN;
	
	// ROBOT DIMENSIONS (INCLUDING BUMPERS)
	public final static double ROBOT_WIDTH_IN = 32; // GET THE REAL VALUE
	public final static double ROBOT_WIDTH_PX = ROBOT_WIDTH_IN * PX_TO_IN;
	public final static double ROBOT_LENGTH_IN = 0;
	public final static double ROBOT_DT_WIDTH_IN = 24; // Drivetrain width (length between wheels) TODO real value
	public final static double ROBOT_DT_WIDTH_PX = ROBOT_DT_WIDTH_IN * PX_TO_IN;
	
	// STARTING POSITIONS
	public final static double START_POS_Y = 497;
	public final static double LEFT_START_POS_X = 485 - ROBOT_WIDTH_IN * PX_TO_IN/2;
	public final static double CENTER_START_POS_X = 0;
	public final static double RIGHT_START_POS_X = 0;
	
	// SPEED/ACCELERATION
	public final static double RAMP_RATE_IN_PER_SECSQ = 0; // 
	public final static double CRUISE_VELO_IN_PER_SEC = 0;
	
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
