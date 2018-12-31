package ca.utoronto.utm.paint;

import java.util.ArrayList;
import java.util.Observable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The model object that contains the DrawCommands and executes them.
 * @author ilaganr1
 *
 */
public class PaintModel extends Observable {
	private ArrayList<DrawCommand> commands;
	private ArrayList<DrawCommand> undoCommands;
	private ArrayList<DrawCommand> clearCommands;
	private DrawCommand lastCommand;
	
	private String colour;
	private String fill;
	private double thickness;
	/**
	 * Creates a new model object that can store DrawCommands
	 */
	public PaintModel() {
		commands = new ArrayList<DrawCommand>();
		undoCommands = new ArrayList<DrawCommand>();
		clearCommands = new ArrayList<DrawCommand>();
		this.colour = "0x000000ff"; // sets default to white
		this.fill = "00000000";
		this.thickness = 1;
	}
	
	/**
	 * Executes all the commands that are stored currently
	 * @param g, GraphicsContent component for panel.
	 */
	public void execute(GraphicsContext g) {
		for(DrawCommand command: commands) {
			command.execute(g);
		}
	}
	
	/**
	 * Adds the commands to the stack.
	 */
	public void addDrawCommand(DrawCommand command) {
		this.commands.add(command);
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Reverses the last command that was performed, if there was any.
	 */
	public void undoCommand() {
		if(commands.size()>0) {
			lastCommand = commands.remove(commands.size()-1);
			undoCommands.add(lastCommand);
		} else if(clearCommands.size()>0) {
			while(clearCommands.size()>0) {
				lastCommand = clearCommands.remove(0);
				commands.add(lastCommand);
			}
			
		} else {
			System.out.println("Nothing to undo");
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Undoes the last undo that was done, if there was any.
	 * 
	 */
	public void redoCommmand() {
		if(undoCommands.size()>0) {
			lastCommand = undoCommands.remove(undoCommands.size()-1);
			commands.add(lastCommand);
		} else {
			System.out.println("Nothing to redo");
		}
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Clear the panel of all the drawing commands, if there is any.
	 * 
	 */
	public void clearCommand() {
		if(commands.size()>0) {
			clearCommands.addAll(commands);
			commands.removeAll(commands);
		} else {
			System.out.println("Nothing to clear");
			}
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Sets the current outline colour chosen with the parameter
	 * @param color is the colour grabbed from ColorPickerEventHandler
	 */
	public void setColor(Color color) {
		this.colour = color.toString();
	}
	
	/**
	 * Sets the current fill colour chosen with the parameter
	 * @param c is the colour grabbed from FillEventHandler
	 */
	public void setFill2(Color c) {
		this.fill = c.toString();
	}
	
	/**
	 * Sets the thickness of the current line to the parameter
	 * @param i is the value of the Slider from ShapeChooserPanel
	 */
	public void setThickness(double i) {
		this.thickness = i;
	}
	
	/**
	 * Gets the current outline colour
	 * @return the current colour of the outline
	 */
	public String getColor() {
		return colour;
	}
	
	/**
	 * Gets the current fill colour
	 * @return the current colour of the fill
	 */
	public String getFill() {
		return fill;
	}
	
	/**
	 * Gets the current thickness value
	 * @return the current double value of the thickness
	 */
	public double getThick() {
		return thickness;
	}
}

