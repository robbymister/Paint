import javafx.scene.canvas.GraphicsContext;

/**
 * Interface that allows the shapes to draw themselves
 *
 */
public interface DrawCommand {
	public abstract void execute(GraphicsContext g); 
}

