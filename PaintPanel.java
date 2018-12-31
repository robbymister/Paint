package ca.utoronto.utm.paint;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

class PaintPanel extends StackPane implements Observer, EventHandler<MouseEvent> {
	private PaintModel model; // slight departure from MVC, because of the way painting works
	private View view; // So we can talk to our parent or other components of the view

	private String mode; // modifies how we interpret input (could be better?)
	
	private ArrayList<ShapeManipulatorStrategy> strategies = new ArrayList<ShapeManipulatorStrategy>();
	
	private Canvas canvas;
	private GraphicsContext g;

	public PaintPanel(PaintModel model, View view) {

		this.canvas = new Canvas(300, 300);

		this.canvas = new Canvas(1000, 1000);
		this.getChildren().add(this.canvas);
		
		// The canvas is transparent, so the background color of the
		// containing pane serves as the background color of the canvas.
		this.setStyle("-fx-background-color: white");

		this.addEventHandler(MouseEvent.ANY, this);

		this.model = model;
		
		this.g = this.canvas.getGraphicsContext2D();
		this.model.addObserver(this);
		
		this.view = view;
	}
	/**
	 * This function updates the canvas whenever an Observer is notified
	 */
	public void repaint () {

		// Clear the canvas
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		this.model.execute(g);
		
	}
	
	/**
	 * Function that calls repaint to be updated once it's observers are notified
	 */
	@Override
	public void update(Observable o, Object arg) {

		// Not exactly how MVC works, but similar.
		this.repaint();
	}
	
	/**
	 * Set the strategy that will be used to determine what 
	 * shape will be drawn on the canvas.
	 * 
	 * @param strategy, the strategy to be used on the panel
	 * @author ilaganr1
	 */
	public void setStrategy(ShapeManipulatorStrategy strategy) {
		this.strategies.add(strategy);
	}
	
	/**
	 * 
	 * @return the last strategy that was clicked from the panel.
	 * @author ilaganr1
	 */
	public ShapeManipulatorStrategy getStrategy() {
		int latestStrategy = strategies.size() - 1;
		return strategies.get(latestStrategy);
	}
	
	/**
	 * Function that handles the types of mouse events possible. Uses a different strategy for each type
	 * of MouseEvent. If there is no current strategies selected, the system will print out "Select  a 
	 * shape"
	 */
	@Override
	public void handle(MouseEvent event) {
		if(strategies.isEmpty() || getStrategy()==null) {
			System.out.println("Select a Shape");
		} 
		else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			getStrategy().mouseDragged(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
			getStrategy().mousePressed(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_MOVED) {
			getStrategy().mouseMoved(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			getStrategy().mouseClicked(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
			getStrategy().mouseReleased(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			getStrategy().mouseEntered(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
			getStrategy().mouseExited(event);
		}
		
	}
	
}


