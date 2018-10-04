package application.autos;

import application.Constants;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;

/* 
 * Credits for most of the stuff in here go to the guy on this stack overflow response:
 * https://stackoverflow.com/questions/13056795/cubiccurve-javafx?lq=1
 */

public class FirstCurve extends Group implements Constants {
	String startPos;
	double startX;
	
	public FirstCurve(String startPos) {
		this.startPos = startPos;
		if(startPos.equalsIgnoreCase("Left")) startX = LEFT_START_POS_X;
		else if(startPos.equalsIgnoreCase("Center")) startX = CENTER_START_POS_X;
		else if(startPos.equalsIgnoreCase("Right")) startX = RIGHT_START_POS_X;

		CubicCurve curve = createStartingCurve();

		Line controlLine1 = new BoundLine(curve.controlX1Property(), curve.controlY1Property(), curve.startXProperty(),
				curve.startYProperty());
		Line controlLine2 = new BoundLine(curve.controlX2Property(), curve.controlY2Property(), curve.endXProperty(),
				curve.endYProperty());

		Anchor start = new Anchor(Color.PALEGREEN, curve.startXProperty(), curve.startYProperty());
		Anchor control1 = new Anchor(Color.GOLD, curve.controlX1Property(), curve.controlY1Property());
		FirstBoundAnchor control2 = new FirstBoundAnchor(Color.GOLDENROD, curve.controlX2Property(), curve.controlY2Property());
		FirstAnchor end = new FirstAnchor(Color.TOMATO, curve.endXProperty(), curve.endYProperty());
		end.setCenterX(startX);
		end.setCenterY(START_POS_Y);
		control2.setCenterX(startX);
		
		this.getChildren().addAll(curve, controlLine1, controlLine2, start, control1, control2, end);
	}

	  private CubicCurve createStartingCurve() {
	    CubicCurve curve = new CubicCurve();
	    curve.setStartX(100);
	    curve.setStartY(100);
	    curve.setControlX1(150);
	    curve.setControlY1(50);
	    curve.setControlX2(250);
	    curve.setControlY2(150);
	    curve.setEndX(300);
	    curve.setEndY(100);
	    curve.setStroke(Color.FORESTGREEN);
	    curve.setStrokeWidth(4);
	    curve.setStrokeLineCap(StrokeLineCap.ROUND);
	    curve.setFill(null);
	    return curve;
	  }
	
	class BoundLine extends Line {
		BoundLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY) {
			startXProperty().bind(startX);
			startYProperty().bind(startY);
			endXProperty().bind(endX);
			endYProperty().bind(endY);
			setStrokeWidth(2);
			setStroke(Color.ANTIQUEWHITE);
			setStrokeLineCap(StrokeLineCap.BUTT);
			getStrokeDashArray().setAll(10.0, 5.0);
		}
	}
	
	class FirstAnchor extends Circle {
		FirstAnchor(Color color, DoubleProperty x, DoubleProperty y) {
			super(x.get(), y.get(), 10);
			setFill(color.deriveColor(1, 1, 1, 0.5));
			setStroke(color);
			setStrokeWidth(2);
			setStrokeType(StrokeType.OUTSIDE);

			x.bind(centerXProperty());
			y.bind(centerYProperty());
		}
	}
	
	class FirstBoundAnchor extends Circle {
		FirstBoundAnchor(Color color, DoubleProperty x, DoubleProperty y) {
			super(x.get(), y.get(), 10);
			setFill(color.deriveColor(1, 1, 1, 0.5));
			setStroke(color);
			setStrokeWidth(2);
			setStrokeType(StrokeType.OUTSIDE);

			x.bind(centerXProperty());
			y.bind(centerYProperty());
			enableDrag();
		}

		// make a node movable by dragging it around with the mouse.
		private void enableDrag() {
			final Delta dragDelta = new Delta();
			setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					// record a delta distance for the drag and drop operation.
					//dragDelta.x = getCenterX() - mouseEvent.getX();
					dragDelta.y = getCenterY() - mouseEvent.getY();
					getScene().setCursor(Cursor.MOVE);
				}
			});
			setOnMouseReleased(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					getScene().setCursor(Cursor.HAND);
				}
			});
			setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
//					double newX = mouseEvent.getX() + dragDelta.x;
//					if (newX > 0 && newX < getScene().getWidth()) {
//						setCenterX(newX);
//					}
					double newY = mouseEvent.getY() + dragDelta.y;
					if (newY > 0 && newY < getScene().getHeight()) {
						setCenterY(newY);
					}
				}
			});
			setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					if (!mouseEvent.isPrimaryButtonDown()) {
						getScene().setCursor(Cursor.HAND);
					}
				}
			});
			setOnMouseExited(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					if (!mouseEvent.isPrimaryButtonDown()) {
						getScene().setCursor(Cursor.DEFAULT);
					}
				}
			});
		}

		// records relative x and y co-ordinates.
		private class Delta {
			double x, y;
		}
		
	}

	// a draggable anchor displayed around a point.
	class Anchor extends Circle {
		Anchor(Color color, DoubleProperty x, DoubleProperty y) {
			super(x.get(), y.get(), 10);
			setFill(color.deriveColor(1, 1, 1, 0.5));
			setStroke(color);
			setStrokeWidth(2);
			setStrokeType(StrokeType.OUTSIDE);

			x.bind(centerXProperty());
			y.bind(centerYProperty());
			enableDrag();
		}

		// make a node movable by dragging it around with the mouse.
		private void enableDrag() {
			final Delta dragDelta = new Delta();
			setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					// record a delta distance for the drag and drop operation.
					dragDelta.x = getCenterX() - mouseEvent.getX();
					dragDelta.y = getCenterY() - mouseEvent.getY();
					getScene().setCursor(Cursor.MOVE);
				}
			});
			setOnMouseReleased(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					getScene().setCursor(Cursor.HAND);
				}
			});
			setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					double newX = mouseEvent.getX() + dragDelta.x;
					if (newX > 0 && newX < getScene().getWidth()) {
						setCenterX(newX);
					}
					double newY = mouseEvent.getY() + dragDelta.y;
					if (newY > 0 && newY < getScene().getHeight()) {
						setCenterY(newY);
					}
				}
			});
			setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					if (!mouseEvent.isPrimaryButtonDown()) {
						getScene().setCursor(Cursor.HAND);
					}
				}
			});
			setOnMouseExited(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					if (!mouseEvent.isPrimaryButtonDown()) {
						getScene().setCursor(Cursor.DEFAULT);
					}
				}
			});
		}

		// records relative x and y co-ordinates.
		private class Delta {
			double x, y;
		}
	}

}
