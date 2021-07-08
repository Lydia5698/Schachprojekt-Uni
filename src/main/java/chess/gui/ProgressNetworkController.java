package chess.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

public class ProgressNetworkController {
    static double ii = 0;

    @FXML
    private ProgressIndicator progressBar;

    @FXML
    private Label title;

    EventHandler<ActionEvent> event = new EventHandler<>() {
        public void handle(ActionEvent e)
        {
            // set progress to different level of progressindicator
            ii += 0.1;
            progressBar.setProgress(ii);
        }

    }; //

}
