import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
/**
 * Parse a file in Version 1.0 PaintSaveFile format. An instance of this class
 * understands the paint save file format, storing information about
 * its effort to parse a file. After a successful parse, an instance
 * will have an ArrayList of PaintCommand suitable for rendering.
 * If there is an error in the parse, the instance stores information
 * about the error. For more on the format of Version 1.0 of the paint 
 * save file format, see the associated documentation.
 *
 */
public class PaintFileParser {
	private int lineNumber = 0; // the current line being parsed
	private String errorMessage =""; // error encountered during parse
	private PaintModel paintModel; 
	
	/**
	 * Below are Patterns used in parsing 
	 */
	private Pattern pFileStart=Pattern.compile("^PaintSaveFileVersion1.0$"); // state 1
	private Pattern pFileEnd=Pattern.compile("^EndPaintSaveFile$"); // state 15

	private Pattern pCircleStart=Pattern.compile("^Circle$"); // state 2
	private Pattern pRectStart=Pattern.compile("^Rectangle$");
	private Pattern pSquiggleStart=Pattern.compile("^Squiggle$");
	private Pattern pColor=Pattern.compile("^color:(\\d{0,3}),(\\d{0,3}),(\\d{0,3})$"); // state 3
	private Pattern pFill=Pattern.compile("^filled:(true|false)$"); // state 4
	private Pattern pCircleCenter=Pattern.compile("^center:\\((\\d{0,}),(\\d{0,})\\)$"); // state 5
	private Pattern pCircleRadius=Pattern.compile("^radius:(\\d{0,})$"); // state 6
	private Pattern pCircleEnd=Pattern.compile("^EndCircle$"); // state 7
	
	private Pattern pPoint1=Pattern.compile("^p1:\\((\\d{0,4}),(\\d{0,4})\\)$"); // state 8
	private Pattern pPoint2=Pattern.compile("^p2:\\((\\d{0,4}),(\\d{0,4})\\)$"); // state 9
	private Pattern pRectEnd=Pattern.compile("^EndRectangle$"); // state 10
	
	private Pattern pPointStart=Pattern.compile("^points$"); // state 11
	private Pattern pPoints=Pattern.compile("^point:\\((\\d{0,4}),(\\d{0,4})\\)$"); // state 12
	private Pattern pPointEnd=Pattern.compile("endpoints"); // state 13
	private Pattern pSquiggleEnd=Pattern.compile("^EndSquiggle$"); // state 14
	
	/**
	 * Store an appropriate error message in this, including 
	 * lineNumber where the error occurred.
	 * @param mesg
	 */
	private void error(String mesg){
		this.errorMessage = "Error in line "+lineNumber+" "+mesg;
	}
	
	/**
	 * 
	 * @return the error message resulting from an unsuccessful parse
	 */
	public String getErrorMessage(){
		return this.errorMessage;
	}
	
	/**
	 * Parse the inputStream as a Paint Save File Format file.
	 * The result of the parse is stored as an ArrayList of Paint command.
	 * If the parse was not successful, this.errorMessage is appropriately
	 * set, with a useful error message.
	 * 
	 * @param inputStream the open file to parse
	 * @param paintModel the paint model to add the commands to
	 * @return whether the complete file was successfully parsed
	 */
	public boolean parse(BufferedReader inputStream, PaintModel paintModel) {
		this.paintModel = paintModel;
		this.errorMessage="";
		
		// During the parse, we will be building one of the 
		// following commands. As we parse the file, we modify 
		// the appropriate command.
		
		CircleCommand circleCommand = null; 
		RectangleCommand rectangleCommand = null;
		SquiggleCommand squiggleCommand = null;
		String currentShape = null; boolean isFilled = false;
		Color currentColor = null;  float Red = 0; float Blue = 0; float Green = 0; // RGB
		Point centre = null; int radius = 0; Point p1 = null; Point p2 = null; boolean retVal = true;
		String error = "";
		
	
		try {	
			int state=0; Matcher m; String l; Matcher m2; Matcher m3; Matcher m4;
			
			this.lineNumber=0;
			while ((l = inputStream.readLine()) != null) {
				this.lineNumber++;
				l = l.replaceAll("\\s+","");
				System.out.println(lineNumber+" "+l+" "+state);
				switch(state){
					case 0:
						m=pFileStart.matcher(l); 
						if(m.matches()){
							state=1; break;
						} else {
						error=("Expected Start of Paint Save File");
						state=50; break; }
					case 1: // Looking for the start of a new object or end of the save file
						m=pCircleStart.matcher(l);m2=pRectStart.matcher(l); m3=pSquiggleStart.matcher(l);
						m4=pFileEnd.matcher(l);
						if(m.matches()){
							currentShape="Circle";state=2; break; }
						else if (m2.matches()) {
							currentShape="Rectangle";state=2;break;
						}
						else if (m3.matches()) {
							currentShape="Squiggle";state=2;break;
						}
						else if (m4.matches()) {
							state=15;break;
						}else {
						error=("Expected a Type of Shape");
						state=50; break; }
					case 2: // Looking for the color of the given object
						m=pColor.matcher(l);
						if(m.matches()) {
							state=3;
							Red=Integer.parseInt(m.group(1));Green=Integer.parseInt(m.group(2));
							Blue=Integer.parseInt(m.group(3)); 
							currentColor = new Color(Red/255,Green/255,Blue/255,1);
							break;
						} else {
						error=("Expected RGB values");
						state=50; break; }
					case 3: // Looking for fill value
						m=pFill.matcher(l);
						if(m.matches()) {
							isFilled=Boolean.parseBoolean(m.group(1));
							if (currentShape=="Circle") { 
								state=4; break;
								}
							else if (currentShape=="Rectangle") { 
								state=7; break;
								}
							else if (currentShape=="Squiggle") { 
								state=10; break;
								} 
						} 
						else {
							state=50; break;}
					case 4: // Looking for circle center
						m=pCircleCenter.matcher(l); 
						if(m.matches()) {
							state=5;
							centre = new Point(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
							break;
						} else {error=("Expected the Circle point"); state=50;break;} 
					case 5: // Looking for circle radius
						m=pCircleRadius.matcher(l); 
						if(m.matches()) {
							radius = Integer.parseInt(m.group(1)); state=6; break;
						} else { error=("Expected the Radius value"); state = 50;break;}
					case 6: // End of a circle, loops back to initial
						m=pCircleEnd.matcher(l);
						if (m.matches()) {
							state=1;
							circleCommand = new CircleCommand(centre,radius);
							circleCommand.setFill(isFilled); circleCommand.setColor(currentColor);
							paintModel.addCommand(circleCommand); 
							break; 
						} else { error=("Expected the Circle End"); state = 50;break;}
					case 7: // Start of a rectangle, looking for p1
						m=pPoint1.matcher(l);
						if(m.matches()) {
							p1 = new Point(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
							state = 8; break;
						} error=("Expected the Point 1"); state=50; break;
					case 8:
						m=pPoint2.matcher(l);
						if(m.matches()) {
							p2 = new Point(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
							state=9; break;
						} error=("Expected the Point 2"); state=50; break;
					case 9:
						m=pRectEnd.matcher(l);
						if(m.matches()) {
							state = 1;
							rectangleCommand = new RectangleCommand(p1,p2);
							rectangleCommand.setFill(isFilled); 
							rectangleCommand.setColor(currentColor);
							paintModel.addCommand(rectangleCommand);
							break;
						} error=("Expected End Rectangle"); state = 50; break;
					case 10:
						m=pPointStart.matcher(l);
						if(m.matches()) {
							squiggleCommand = new SquiggleCommand();squiggleCommand.setColor(currentColor);
							state = 11; break;
						} error=("Expected Points Start"); state = 50; break; 
					case 11:
						m=pPoints.matcher(l);
						m2=pPointEnd.matcher(l);
						if (m.matches()) {
							squiggleCommand.add(new Point(Integer.parseInt(m.group(1)),Integer.parseInt(m.group(2))));
							state=11; break;
						}
						else if (m2.matches()) {
							state=12;break;
						} else { error=("Expected Points or the End");state=50;break;}
					case 12:
						m=pSquiggleEnd.matcher(l);
						if(m.matches()) {
							state=1; paintModel.addCommand(squiggleCommand);break;
						} else { error=("Expected the End of the Squiggle");state=50;break;}
					case 15:
						break;
					case 50:
						Alert alert = new Alert(AlertType.ERROR, error);
						alert.showAndWait();
						return false;
				}
			}if (state != 15) {
				retVal = false;
			}
		}  catch (Exception e){
			
		}
		System.out.println(retVal);
		return retVal;
	}
}
