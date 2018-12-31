package ca.utoronto.utm.paint;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Creates a squiggle that is composed of squares that follow the mouse cursor with a given colour and 
 * thickness
 * @author burnser2, meiange1
 *
 */
public class Squiggle extends Shape implements DrawCommand{
	
	ArrayList<Point> points;
	private Color colour;
	private double thickness;
	
	public Squiggle() {
		this.points = new ArrayList<Point>();
		this.colour = Color.BLACK;
		this.thickness = 1;
	}
	
	/**
	 * Draws the Squiggle onto the canvas with the specified thickness and outline color
	 * @param g which is the access to the canvas
	 */
	@Override
	public void execute(GraphicsContext g) {
		for (int i = 0; i < this.points.size() - 1; i++) {
			Point p1 = this.points.get(i);
			Point p2 = this.points.get(i + 1);
			g.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
			g.setLineWidth(this.getThickness());
			g.setStroke(this.getColour());
		}
	}
	
	/**
	 * Adds the point to the ArrayList of points that keep track of the size of the current Squiggle
	 * @param a point that is on the canvas
	 */
	public void addPoint(Point point) {
		this.points.add(point);
	}

}

