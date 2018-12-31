package ca.utoronto.utm.paint;

/**
 * Class that is a point at a certain x and y
 * @author burnser2
 *
 */
public class Point {
	int x, y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Getter for the X value of this point
	 * @return x value of this point
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the value of X for the current point
	 * @param x 
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Getter for the Y value of this point
	 * @return y value of this point
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the value of Y for the current point
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

}
