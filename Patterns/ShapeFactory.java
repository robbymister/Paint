import java.util.ArrayList;

/**
 * This factory is responsible for creating specific ShapeManipulatorStrategy classes
 * depending on the button that was pressed on the panel.
 * Implements the Factory design pattern.
 *
 */
public class ShapeFactory {
	private PaintPanel panel;
	private PaintModel model;
	private ArrayList<ShapeManipulatorStrategy> strategies;
	private CircleManipulatorStrategy circleStrategy;
	private RectangleManipulatorStrategy rectangleStrategy;
	private SquiggleManipulatorStrategy squiggleStrategy;
	private PolylineManipulatorStrategy polylineStrategy;
	private View view;
	
	/**
	 * Creates the ShapeFactory instance and
	 * makes a new Arraylist for ShapeManipulatorStrategy classes.
	 */
	//public ShapeFactory(PaintModel model, PaintPanel panel) {
	public ShapeFactory(View view) {
		this.view = view;
		this.model = this.view.getPaintModel();
		this.panel = this.view.getPaintPanel();
		this.strategies = new ArrayList<ShapeManipulatorStrategy>();
	}
	
	/**
	 * Creates specific ShapeManipulatorStrategy depending 
	 * on the button that was pressed.
	 * A noob-implementation.
	 * 
	 * @param strategyType
	 */
	public void createShapeManipulatorStrategy(String strategyType) {
		if(strategyType.equals("Circle")) {
			circleStrategy = new CircleManipulatorStrategy(model, panel);
			strategies.add(circleStrategy);
			panel.setStrategy(circleStrategy);
		} else if(strategyType.equals("Rectangle")) {
			rectangleStrategy = new RectangleManipulatorStrategy(model, panel);
			strategies.add(rectangleStrategy);
			panel.setStrategy(rectangleStrategy);
		} else if(strategyType.equals("Squiggle")) {
			squiggleStrategy = new SquiggleManipulatorStrategy(model, panel);
			strategies.add(squiggleStrategy);
			panel.setStrategy(squiggleStrategy);
		} else if(strategyType.equals("Polyline")) {
			polylineStrategy = new PolylineManipulatorStrategy(model, panel);
			strategies.add(polylineStrategy);
			panel.setStrategy(polylineStrategy);
		} else if(strategyType.equals("Square")) {
			
		} else if(strategyType.equals("")) {
			System.out.println("Select a Shape");
			panel.setStrategy(null);
		} 
	}
	
	/**
	 * 
	 * @return list of strategies
	 */
	public ArrayList<ShapeManipulatorStrategy> getStrategies() {
		return strategies;
	}
}
