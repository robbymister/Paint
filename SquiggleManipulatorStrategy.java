package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * A Strategy class for Squiggle. Creates a Squiggle Command 
 * to be able to draw on the canvas based on user mouse events.
 * 
 * @author ilaganr1
 *
 */
public class SquiggleManipulatorStrategy extends ShapeManipulatorStrategy{
	private Squiggle squiggle;
	
	public SquiggleManipulatorStrategy(PaintModel model, PaintPanel panel) {
		super(model, panel);
		
	}

	@Override
	/**
	 * Create a new Squiggle shape that will be stored on the canvas.
	 */
	public void createShape() {
		squiggle = new Squiggle();
		model.addDrawCommand(squiggle);
	}

	@Override
	/**
	 * Show squiggle as the user drags the mouse.
	 */
	public void mouseDragged(MouseEvent e) {
		squiggle.addPoint(new Point((int) e.getX(), (int) e.getY()));
		this.panel.repaint();
	}

	@Override
	/**
	 * Create a new squiggle on mouse press.
	 */
	public void mousePressed(MouseEvent e) {
		createShape();
		this.squiggle.setColour(Color.web(model.getColor()));
		this.squiggle.setThickness(model.getThick());
		
	}

	/**
	 * Once the mouse is released, the current squiggle ends.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		squiggle.addPoint(new Point((int) e.getX(), (int) e.getY()));
		this.panel.repaint();
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
