import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * PolylineManipulatorStrategy is a subclass of ShapeManipulatorStrategy and allows
 * it to make Polyline objects on the canvas.
 * 
 * @author ilaganr1
 *
 */
public class PolylineManipulatorStrategy extends ShapeManipulatorStrategy {
	private Polyline polyline;
	
	/**
	 * Creates a PolylineStrategy that also creates
	 * a Squiggle object
	 * 
	 * @param model, the model for the canvas
	 * @param e, the mouse events
	 */
	public PolylineManipulatorStrategy(PaintModel model, PaintPanel panel) {
		super(model, panel);
		createShape();
	}

	@Override
	/**
	 * Create a new Rectangle with 0 vertices
	 */
	public void createShape() {
		this.polyline = new Polyline();
		this.model.addDrawCommand(this.polyline);
		
	}

	@Override
	/**
	 * Show the rectangle as the mouse is being dragged.
	 */
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	/**
	 * Create a rectangle as soon as the mouse button is clicked.
	 */
	public void mousePressed(MouseEvent e) {
		this.polyline.setColour(Color.web(model.getColor()));
		this.polyline.setThickness(model.getThick());
	}
	
	@Override
	/**
	 * Add the rectangle instance to the canvas after releasing the mouse click
	 * and then, delete the old one.
	 */
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * Shows a preview of the current PolyLine leading to where the mouse cursor is on the canvas.
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		if (this.polyline.points.size() != 0) {
			this.polyline.previewPoint = new Point((int) e.getX(), (int) e.getY());
			this.panel.repaint();
		} 
		
	}

	/**
	 * Once the mouse is clicked onto the canvas, the endpoint of the current PolyLine gets added and a 
	 * new PolyLine is created from that point.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		Point clickedPoint = new Point((int) e.getX(), (int) e.getY());
		this.polyline.addPoint(clickedPoint);
		if (e.getClickCount() == 2) {
			createShape();
		}
		else {
			this.panel.repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
