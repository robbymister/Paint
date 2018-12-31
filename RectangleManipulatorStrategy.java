package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * RectangleStrategy is a subclass of ShapeManipulatorStrategy and allows
 * it to make Rectangle objects on the canvas.
 * 
 * @author ilaganr1
 *
 */
public class RectangleManipulatorStrategy extends ShapeManipulatorStrategy {
	private Rectangle rectangle;
	private Point vertex1;
	private Point vertex2;
	
	/**
	 * Creates a RectangleStrategy that also creates
	 * a Rectangle object
	 * 
	 * @param model, the model for the canvas
	 * @param e, the mouse events
	 */
	public RectangleManipulatorStrategy(PaintModel model, PaintPanel panel) {
		super(model, panel);
		this.vertex1 = new Point(0,0);
		this.vertex2 = new Point(0,0);
		this.createShape();
	}

	@Override
	/**
	 * Create a new Rectangle with 0 vertices
	 */
	public void createShape() {
		this.rectangle = new Rectangle(vertex1, vertex2);
		this.model.addDrawCommand(this.rectangle);
		
	}

	@Override
	/**
	 * Show the rectangle as the mouse is being dragged.
	 */
	public void mouseDragged(MouseEvent e) {
		if (this.rectangle != null) {
			Point vertex2 = new Point((int) e.getX(), (int) e.getY());
			this.rectangle.setVertex2(vertex2);
			this.panel.repaint();
		}
	}

	@Override
	/**
	 * Create a rectangle as soon as the mouse button is clicked.
	 */
	public void mousePressed(MouseEvent e) {
		Point vertex1 = new Point((int) e.getX(), (int) e.getY());
		createShape();
		this.rectangle.setVertex1(vertex1);
		this.rectangle.setColour(Color.web(model.getColor()));
		this.rectangle.setFill(Color.web(model.getFill()));
		this.rectangle.setThickness(model.getThick());
		
	}
	
	@Override
	/**
	 * Add the rectangle instance to the canvas after releasing the mouse click
	 * and then, delete the old one.
	 */
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
