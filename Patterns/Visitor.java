import javafx.scene.canvas.GraphicsContext;

/**
 * An object that visits objects and notes down certain information
 *
 */
public interface Visitor{
	  public void visit(PaintCommand paintCommand);
	  public void visit(GraphicsContext graphicsContext2D);
}
