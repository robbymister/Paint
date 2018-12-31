
package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The Rectangle class is a Shape object that has a specific thickness, color and fill color.
 * @author burnser2
 *
 */
public class Rectangle extends Shape implements DrawCommand{

	Point vertex1;
	Point vertex2;
	private Color colour;
	private Color fill;
	private double thickness;
	
	/**
	 * Creates a Rectangle object that has 2 vertices, color,
	 * fill color and thickness.
	 * @param vertex1
	 * @param vertex2
	 */
	public Rectangle(Point vertex1, Point vertex2) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.colour = Color.BLACK;
		this.fill = Color.TRANSPARENT;
		this.thickness = 1;
	}
	
	@Override
	/**
	 * Creates a Rectangle object on the canvas
	 */
	public void execute(GraphicsContext g) {
		int topLeftX = this.vertex1.getX() - Math.abs(this.vertex1.getX()-this.minVertices()[0]);
		int topLeftY = this.vertex1.getY() - Math.abs(this.vertex1.getY()-this.minVertices()[1]);
		
		g.setStroke(this.getColour());
		g.setFill(this.getFill());
		g.setLineWidth(this.getThickness());
		g.strokeRect(topLeftX, topLeftY, this.getWidth(), this.getHeight());
		g.fillRect(topLeftX, topLeftY, this.getWidth(), this.getHeight());
	}

	/**
	 * Getter for the width of the current rectangle
	 * @return the width of this rectangle
	 */
	public int getWidth() {
		return Math.abs(this.vertex2.getX() - this.vertex1.getX());
	}
	
	/**
	 * Getter for the height of the current rectangle
	 * @return the height of this rectangle
	 */
	public int getHeight() {
		return Math.abs(this.vertex2.getY() - this.vertex1.getY());
	}
	
	/**
	 * Set the 1st vertex of this rectangle
	 * @param vertex1
	 */
	public void setVertex1(Point vertex1) {
		this.vertex1 = vertex1;
	}

	
	/**
	 * Set the 2nd vertex of this rectangle
	 * @param vertex2
	 */
	public void setVertex2(Point vertex2) {
		this.vertex2 = vertex2;
	}
	

	/**
	 * Function to identify which vertice is the smallest.
	 * @return which of the vertices is the smaller one.
	 */
	public int [] minVertices() {
		int minX = Math.min(this.vertex1.getX(), this.vertex2.getX());
		int minY = Math.min(this.vertex1.getY(), this.vertex2.getY());

		
		int [] minimums = {minX, minY};
		return minimums;
	}
}

