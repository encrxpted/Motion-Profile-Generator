package application.autos;

import application.Constants;
import javafx.scene.Group;
import javafx.scene.shape.CubicCurve;

public class LeftSideCurve extends Group implements Constants {
	CubicCurve centerCurve;
	
	public LeftSideCurve(CubicCurve centerCurve) {
		this.centerCurve = centerCurve;
	}
	
	private CubicCurve getCurve() {
		CubicCurve curve = new CubicCurve();
		double slope1, slope2;
		double deltaY1, deltaY2, deltaX1, deltaX2;
		deltaY1 = centerCurve.getEndY() - centerCurve.getControlY2();
		deltaY2 = centerCurve.getStartY() - centerCurve.getControlY1();
		deltaX1 = centerCurve.getEndX() - centerCurve.getControlX2();
		deltaX2 = centerCurve.getStartX() - centerCurve.getControlX1();
		
		
		// TODO finish me! Make the left/right side curves uwu
		if(deltaY1 != 0 && deltaX1 != 0) 
			slope1 = deltaY1 - deltaX1;
		else {
			if(deltaY1 == 0) {
				
			}
			else slope1 = 0;
		}
		
		
		curve.setEndY(centerCurve.getEndY());
		curve.setEndX(centerCurve.getEndX() - ROBOT_WIDTH_PX/2);
		
		
		return curve;
	}

}
