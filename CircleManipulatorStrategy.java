package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * Strategy class for Circle objects.
 * This allows the client to draw circles on the canvas.
 * 
 * @author ilaganr1
 *
 */
public class CircleManipulatorStrategy extends ShapeManipulatorStrategy {
	private Circle circle;
	private Point centre;
	
	/**
	 * Creates a CircleStrategy object to be able to make a 
	 * Circle object on the canvas
	 * 
	 * @param model
	 * @param e
	 */
	public CircleManipulatorStrategy(PaintModel model, PaintPanel panel) {
		super(model, panel);
		centre = new Point(0,0);
		
		
	}
	
	
	@Override
	/**
	 * Shows the circle as the mouse is being dragged.
	 */
	 public void mouseDragged(MouseEvent e) {
		int radius = (int) Math.sqrt(Math.pow(Math.abs(this.circle.getCentre().getX() - e.getX()), 2) + 
				Math.pow(Math.abs(this.circle.getCentre().getY() - e.getY()), 2));
		this.circle.setRadius(radius);
		this.panel.repaint();
	}

	@Override
	/**
	 * Creates a new circle on the canvas every time the
	 * mouse is pressed on a specific location.
	 */
	public void mousePressed(MouseEvent e) {
		this.createShape();
		centre = new Point((int) e.getX(), (int) e.getY());
		this.circle.setCentre(centre);
		this.circle.setColour(Color.web(model.getColor()));
		this.circle.setFill(Color.web(model.getFill()));
		this.circle.setThickness(model.getThick());
		
		
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

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	/**
	 * Creates a new circle object
	 */
	public void createShape() {
		this.circle = new Circle(centre, 0);
		this.model.addDrawCommand(this.circle);
		
	}
}
