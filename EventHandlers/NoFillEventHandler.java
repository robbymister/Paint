import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;

/**
 * Class that implements the EventHandler for the NoFill button on the window for the FillPicker.
 * This class adds another button to the FillPicker window which sets the colour of the fill to Transparent.
 *
 */
public class NoFillEventHandler implements EventHandler<ActionEvent>{
		private View view;
		
	    public NoFillEventHandler(View view) {
	    	this.view = view;
	    }
	    
	    /**
	     * Changes the fill color to Transparent if this button is clicked.
	     */
		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			this.view.getPaintModel().setFill2(Color.TRANSPARENT);
		}
}
