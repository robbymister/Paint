import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * This class is the unifying abstract class for different strategies
 * of making shapes on the canvas. The instance vars are non-modified 
 * so the subclasses can inherit from them.
 * 
 */
public abstract class ShapeManipulatorStrategy {
	PaintModel model;
	PaintPanel panel;
	Color colour;
	Color fill;
	double thickness;
	
	/**
	 * Creates a ShapeManipulatorStrategy object that the client
	 * can use to find out the correct strategy for different shapes.
	 * 
	 * @param model
	 * @param e
	 */
	public ShapeManipulatorStrategy(PaintModel model, PaintPanel panel) {
		this.model = model;
		this.panel = panel;
	}
	
	public abstract void createShape();
	public abstract void mouseDragged(MouseEvent e);
	public abstract void mousePressed(MouseEvent e);
	public abstract void mouseReleased(MouseEvent e);
	public abstract void mouseMoved(MouseEvent e);
	public abstract void mouseClicked(MouseEvent e);
	public abstract void mouseEntered(MouseEvent e);
	public abstract void mouseExited(MouseEvent e);
}
