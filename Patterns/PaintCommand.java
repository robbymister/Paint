import java.util.Observable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class PaintCommand extends Observable implements Visitable {
	private Color color;
	private boolean fill;
	private String shape;

public String toString(){
		double r = this.color.getRed(); int r2 = (int) (r*255) ;
		double g = this.color.getGreen(); int g2 = (int) (g*255);
		double b = this.color.getBlue(); int b2 = (int) (b*255);
		String s="";
		s+="\tcolor:"+r2+","+g2+","+b2+"\n";
		s+="\tfilled:"+this.fill+"\n";
		return s;
	}
	
	public abstract void execute(GraphicsContext g);
	public abstract String saveString();
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
  
  public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public boolean isFill() {
		return fill;
	}
	public void setFill(boolean fill) {
		this.fill = fill;
	}
}
