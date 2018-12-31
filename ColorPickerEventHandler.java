package ca.utoronto.utm.paint;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

/**
 * Class that implements the EventHandler for the ColorPicker button on the ShapeChooserPanel.
 * This class opens up a new window where the user can select the colour of the outline they want
 * from a ColourPanel.
 * 
 * @author evansro3
 *
 */
public class ColorPickerEventHandler implements EventHandler<ActionEvent>{
	private View view;
    public ColorPickerEventHandler(View view1) {
    	this.view = view1;
    }

    /**
     * When the ColorPicker button is pressed, a new window is created here and the ColourPicker is created
     * with some text displaying what colour is currently picked. The colour defaults to red. Once a colour 
     * is picked, it changes the value in PaintModel so that the outline is constantly updated.
     */
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		Stage stage = new Stage();
		Scene scene = new Scene(new HBox(20), 400, 100);
		HBox box = (HBox) scene.getRoot();
		final ColorPicker colorPicker = new ColorPicker();
		colorPicker.setValue(Color.RED);
		this.view.getPaintModel().setColor(Color.RED);

		final Text text = new Text("Chosen colour.");
		text.setFill(colorPicker.getValue());

		colorPicker.setOnAction((ActionEvent t) -> {
		text.setFill(colorPicker.getValue());
		Color colour = colorPicker.getValue(); //WHERE THE COLOUR GETS SAVED
		this.view.getPaintModel().setColor(colour);
		});

		box.getChildren().addAll(colorPicker, text);

		stage.setScene(scene);
		stage.show();
	}
}