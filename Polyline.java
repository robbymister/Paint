package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Creates a polyline with a given colour and thickness that extends from the Squiggle class
 * @author burnser2, meiange1
 *
 */
public class Polyline extends Squiggle implements DrawCommand {

	Point previewPoint;
	boolean isPreview;
	private Color colour;
	private double thickness;
	
	public Polyline() {
		this.previewPoint = null;
		this.isPreview = false;
		this.colour = Color.BLACK;
		this.thickness = 1;
	}
	
	/**
	 * Adds the point to the ArrayList of points that keep track of the size of the current PolyLine
	 * @param a point that is on the canvas
	 */
	public void addPoint(Point point) {
		this.points.add(point);
	}
	
	/**
	 * Draws the polyline onto the canvas with the specified thickness and outline color
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
		if (this.previewPoint != null && this.points.size() > 0) {
			g.strokeLine(this.points.get(this.points.size() - 1).getX(), this.points.get(this.points.size() - 1).getY(), this.previewPoint.getX(), this.previewPoint.getY());
		}
	}
}