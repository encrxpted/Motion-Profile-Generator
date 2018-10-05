package application.autos;

import application.Constants;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.StrokeLineCap;

public class LeftSideCurve extends Group implements Constants {
	CubicCurve centerCurve;
	
	public LeftSideCurve(CubicCurve centerCurve) {
		this.centerCurve = centerCurve;
		CubicCurve curve = getCurve();
		
	    curve.setStroke(Color.ALICEBLUE);
	    curve.setStrokeWidth(4);
	    curve.setStrokeLineCap(StrokeLineCap.ROUND);
	    curve.setFill(null);
		curve.setStrokeLineCap(StrokeLineCap.BUTT);
		curve.getStrokeDashArray().setAll(10.0, 5.0);
		
		this.getChildren().add(curve);
	}
	
	private CubicCurve getCurve() {
		CubicCurve curve = new CubicCurve();
		double slope1, slope2;
		double deltaY1, deltaY2, deltaX1, deltaX2;
		double endX, endY, control1X, control1Y, control2X, control2Y, startX, startY;
		Point end, control1, control2, start;
	
		deltaY1 = centerCurve.getEndY() - centerCurve.getControlY2();
		deltaY2 = centerCurve.getStartY() - centerCurve.getControlY1();
		deltaX1 = centerCurve.getEndX() - centerCurve.getControlX2();
		deltaX2 = centerCurve.getStartX() - centerCurve.getControlX1();
		
		// TODO finish me! Make the left/right side curves uwu
		slope1 = getSlope(deltaX1, deltaY1);
		slope2 = getSlope(deltaX2, deltaY2);
		
		double nSlope1 = getNormalSlope(slope1);
		double nSlope2 = getNormalSlope(slope2);
		System.out.println(nSlope1 + " | " +nSlope2);
		
		end = getPointsFromNormal(centerCurve.getEndX(), centerCurve.getEndY(), nSlope1);
		control1 = getPointsFromNormal(centerCurve.getControlX2(), centerCurve.getControlY2(), nSlope1);
		control2 = getPointsFromNormal(centerCurve.getControlX1(), centerCurve.getControlY1(), nSlope2);
		start = getPointsFromNormal(centerCurve.getStartX(), centerCurve.getStartY(), nSlope2);

		curve.setEndX(end.x);
		curve.setEndY(end.y);
		curve.setControlX2(control1.x);
		curve.setControlY2(control1.y);
		curve.setControlX1(control2.x);
		curve.setControlY1(control2.y);
		curve.setStartX(start.x);
		curve.setStartY(start.y);
		
//		curve.setEndY(centerCurve.getEndY());
//		curve.setEndX(centerCurve.getEndX() - ROBOT_WIDTH_PX/2);
		
		
		return curve;
	}
	
	private Point getPointsFromNormal(double x, double y, double nSlope) {
		double deltaX, deltaY;
		double xVal, yVal;
		
		if(nSlope == 0) {
			deltaX = -ROBOT_DT_WIDTH_PX/2.0;
			deltaY = 0;
		}
		else if(nSlope == Double.POSITIVE_INFINITY) {
			deltaX = 0;
			deltaY = ROBOT_DT_WIDTH_PX/2;
		}
		else if(nSlope > 0) {
			deltaX = 1.0 * (ROBOT_DT_WIDTH_PX/2) / Math.sqrt(1 + nSlope * nSlope); // Derived from x^2 + (m*x)^2 = width^2 (pythag)
			deltaY = deltaX * nSlope;
		}
		else {
			deltaX = -1.0 * (ROBOT_DT_WIDTH_PX/2) / Math.sqrt(1 + nSlope * nSlope); // Derived from x^2 + (m*x)^2 = width^2 (pythag)
			deltaY = deltaX * nSlope;
		}
		
		xVal = x + deltaX;
		yVal = y + deltaY;
		Point point = new Point();
		point.x = xVal;
		point.y = yVal;
		return point;
	}
	
	// Returns the normal (perpendicular) slope
	private double getNormalSlope(double slope) {
		double nSlope;
		if(slope == Double.POSITIVE_INFINITY)  nSlope = 0;
		else if(slope == 0) nSlope = Double.POSITIVE_INFINITY;
		else nSlope = -1.0/slope;
		
		return nSlope;
	}
	
	// Returns the slope from deltaX and deltaY. Returns positive infinity if vertical
	private double getSlope(double deltaX, double deltaY) {
		double slope;
		if(deltaY == 0) slope = 0;
		else if(deltaX == 0) slope = Double.POSITIVE_INFINITY;
		else {
			slope = deltaY / deltaX;
		}
		return slope;
	}
	
	class Point {
		double x, y;
	}

}
