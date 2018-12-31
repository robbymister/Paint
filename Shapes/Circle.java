import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The Circle class inherits from the Shape class and implements the DrawCommand class. It has a 
 * specific thickness, color, fill color, centre and radius.
 * 
 */
public class Circle extends Shape implements DrawCommand{
	
	private Point centre;
	private int radius;
	private Color colour;
	private Color fill;
	private double thickness;
	
	/**
	 * Creates a circle object with a specific centre and radius.
	 * @param centre
	 * @param radius
	 */
	public Circle(Point centre, int radius) {
		this.centre = centre;
		this.radius = radius;
		this.colour = Color.BLACK;
		this.fill = Color.TRANSPARENT;
		this.thickness = 1;
	}
	
	@Override
	/**
	 * Draws the circle onto the canvas with the
	 * specified radius, centre, thickness, color, and fill color.
	 * @param g
	 */
	public void execute(GraphicsContext g) {
		int x = this.getCentre().getX();
		int y = this.getCentre().getY();
		int radius = this.getRadius();
		g.setLineWidth(this.getThickness());
		g.setStroke(this.getColour());
		g.setFill(this.getFill());
		g.strokeOval(x - radius, y - radius, radius * 2, radius * 2);
		g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
	}
	
	/**
	 * Getter for the centre of this circle
	 * @return the centre of this circle instance
	 */
	public Point getCentre() {
		return centre;
	}
	
	/**
	 * Set the centre of this circle instance
	 * @param centre of the circle
	 */
	public void setCentre(Point centre) {
		this.centre = centre;
	}
	
	/**
	 * Getter for the radius of the circle
	 * @return the radius of the circle
	 */
	public int getRadius() {
		return radius;
	}
	
	/**
	 * Set the radius of this circle
	 * @param radius
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}


}
