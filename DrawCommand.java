package ca.utoronto.utm.paint;
import javafx.scene.canvas.GraphicsContext;

/**
 * Interface that allows the shapes to draw themselves
 * @author burnser2
 *
 */
public interface DrawCommand {
	public abstract void execute(GraphicsContext g); 
}

