import javafx.scene.paint.Color;

/**
 * A unifying class for all shapes that will be on the canvas.
 *
 */
public abstract class Shape {
	Color colour;
	Color fill;
	double thickness;
	
	/**
	 * @return the color of this shape
	 */
	public Color getColour() {
		return colour;
	}
	
	/**
	 * Set the colour of this shape
	 * 
	 * @param colour
	 */
	public void setColour(Color colour) {
		this.colour = colour;
	}
	
	/**
	 * 
	 * @return the color for filling the shape
	 */
	public Color getFill() {
		return fill;
	}
	
	/**
	 * Set the fill color of the shape
	 * 
	 * @param fill
	 */
	public void setFill(Color fill) {
		this.fill = fill;
	}
	
	/**
	 * 
	 * @return the thickness of the shape
	 */
	public double getThickness() {
		return thickness;
	}
	
	/**
	 * Set the thickness of the shape
	 * 
	 * @param thickness 
	 */
	public void setThickness(double thickness) {
		this.thickness = thickness;
	}
	
	

}
