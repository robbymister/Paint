import javafx.event.ActionEvent;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image; 
import javafx.scene.image.ImageView;

/**
 * Class that adds all the buttons and sliders to the side of the Paint window. 
 *
 */
public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

	private View view; // So we can talk to our parent or other components of the view
	private double thick;
	

	private ShapeFactory factory;
	
	public ShapeChooserPanel(View view) {
		this.view = view;
		this.thick = 1;
		String[] buttonLabels = { "Circle", "Rectangle", "Square", "Squiggle", 
				"Polyline", "Redo", "Undo", "Remove" };
		
		
		// Adds the images to the buttons
		Image[] images = new Image[8];
		images[0] = new Image("file:images/circle.png");
		images[1]  = new Image("file:images/rectangle.png");
		images[2] = new Image("file:images/square.png");
		images[3] = new Image("file:images/squiggle.png");
		images[4] = new Image("file:images/polyline.png");
		images[5]= new Image("file:images/Redo.png");
		images[6] = new Image("file:images/Undo.png");
		images[7] = new Image("file:images/Remove.png");
		
		ImageView[] imageViews = new ImageView[8];
		for(int i=0; i<images.length; i++) {
			imageViews[i] = new ImageView();
			imageViews[i].setImage(images[i]);
			imageViews[i].setFitWidth(45);
			imageViews[i].setFitHeight(25);
			imageViews[i].setPreserveRatio(true);
		}
		
		ToggleGroup group = new ToggleGroup();
		for (int i=0; i<buttonLabels.length; i++) {
			ToggleButton button = new ToggleButton(buttonLabels[i], imageViews[i]);
			button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY); // allows the graphic to only show without label
			button.setMaxSize(100, 25);
			button.setToggleGroup(group); // add to group of ToggleButtons
			button.setStyle("-fx-focus-color: transparent;");
			this.add(button, 0, i);
			button.setOnAction(this);
		}
		
		// Add the two specific case buttons, outline and fill pickers
		ToggleButton bouton = new ToggleButton("Pick Outline Colour");
		this.add(bouton, 0, 9);
		bouton.setOnAction(new ColorPickerEventHandler(this.view));
		ToggleButton bouton2 = new ToggleButton("Pick Fill Colour");
		this.add(bouton2, 0, 10);
		bouton2.setOnAction(new FillEventHandler(this.view));
		
		// Creates blank space for the slider
		final Label blank = new Label(" ");
		final Label ThicknessLabel = new Label("Pick Thickness:");
		this.add(blank, 0, 11); this.add(ThicknessLabel, 0, 12);
		
		/**
		 * Adds the slider that chooses the thickness of the shapes. 
		 * @author meiange1
		 */
		final Slider slider = new Slider(0, 100, 0);
		this.add(slider, 0, 13);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(10);
		slider.setBlockIncrement(5);
		slider.setSnapToTicks(true);
		
		/**
		 * Whenever the slider is moved, listeners get notified and changes the value of the thickness
		 * chosen
		 * @author meiange1
		 */
		slider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) { 
			if (new_val.doubleValue() < 1) {
				setThick(1);
			} else {
			setThick(new_val.doubleValue()); }
		}
		});
		
	}
	/**
	 * Sets the line thickness of the current shape
	 * @param i which is the double received from the slider
	 */
	public void setThick(double i) {
		this.view.getPaintModel().setThickness(i);
	}

	/**
	 * Once one of the buttons is pressed, events are chosen and handled accordingly
	 */
	@Override
	public void handle(ActionEvent event) {
		String command = ((ToggleButton) event.getSource()).getText();
		System.out.println(command);
		boolean toggled = ((ToggleButton) event.getSource()).isSelected();

		if(command == "Redo") {
			this.view.getPaintModel().redoCommmand();
		} else if (command == "Undo") {
			this.view.getPaintModel().undoCommand();
		} else if (command == "Remove") {
			this.view.getPaintModel().clearCommand();
		} else {
			if(!toggled) {
				this.factory.createShapeManipulatorStrategy("");
			} else {
				// get the command from the button presses
				this.factory = new ShapeFactory(view);
				this.factory.createShapeManipulatorStrategy(command);
				
			}
		}
	}
}
