package util;


import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;


public class SceneGestures {

    private static final double MAX_SCALE = 10d;
    private static final double MIN_SCALE = 0.5d;
    private int newX;
    private int newY;

    private DragContext sceneDragContext = new DragContext();

    PannableCanvas canvas;

    public SceneGestures(PannableCanvas canvas) {
        this.canvas = canvas;
    }

    public EventHandler<MouseEvent> getOnMousePressedEventHandler() {
        return onMousePressedEventHandler;
    }

    public EventHandler<MouseEvent> getOnMouseDraggedEventHandler() {
        return onMouseDraggedEventHandler;
    }

    public EventHandler<ScrollEvent> getOnScrollEventHandler() {
        return onScrollEventHandler;
    }

    private EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

        public void handle(MouseEvent event) {
            if(event.isSecondaryButtonDown())
                return;

            sceneDragContext.mouseAnchorX = event.getSceneX();
            sceneDragContext.mouseAnchorY = event.getSceneY();

            sceneDragContext.translateAnchorX = canvas.getTranslateX();
            sceneDragContext.translateAnchorY = canvas.getTranslateY();

        }

    };

    private EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {

            if(event.isSecondaryButtonDown())
                return;

            canvas.setTranslateX(sceneDragContext.translateAnchorX + event.getSceneX() - sceneDragContext.mouseAnchorX);
            canvas.setTranslateY(sceneDragContext.translateAnchorY + event.getSceneY() - sceneDragContext.mouseAnchorY);

            event.consume();
        }
    };

    /**
     * Mouse wheel handler: zoom to pivot point
     */
    private EventHandler<ScrollEvent> onScrollEventHandler = new EventHandler<ScrollEvent>() {

        @Override
        public void handle(ScrollEvent event) {

        	  double delta = 1.2;

              double scale = canvas.getScale(); // currently we only use Y, same value is used for X
              double oldScale = scale;

              if (event.getDeltaY() < 0)
                  scale /= delta;
              else
                  scale *= delta;

              scale = clamp( scale, MIN_SCALE, MAX_SCALE);

              double f = (scale / oldScale)-1;

              double dx = (event.getSceneX() - (canvas.getBoundsInParent().getWidth()/2 + canvas.getBoundsInParent().getMinX()));
              double dy = (event.getSceneY() - (canvas.getBoundsInParent().getHeight()/2 + canvas.getBoundsInParent().getMinY()));

              canvas.setScale( scale);

              // note: pivot value must be untransformed, i. e. without scaling
              canvas.setPivot(f*dx, f*dy);

              event.consume();

        }
    };
    
    public void zoom(double factor, double x, double y) {
        double oldScale = canvas.getScale();
        double scale = oldScale * factor;
        if (scale < 0.05) scale = 0.05;
        if (scale > 50)  scale = 50;
        canvas.setScale(scale);

        double  f = (scale / oldScale)-1;
        Bounds bounds = canvas.localToScene(canvas.getBoundsInLocal());
        double dx = (x - (bounds.getWidth()/2 + bounds.getMinX()));
        double dy = (y - (bounds.getHeight()/2 + bounds.getMinY()));

        canvas.setPivot(f*dx, f*dy);
    }


    public static double clamp( double value, double min, double max) {

        if( Double.compare(value, min) < 0)
            return min;

        if( Double.compare(value, max) > 0)
            return max;

        return value;
    }
    
}


