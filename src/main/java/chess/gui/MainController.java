package chess.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The MainController has the methode to show the FXML and sets the Gui
 *  @author Lydia Günther
 */
public class MainController {
    private String promoteTo;
    public Gui gui;
    //LoadSaveController loadSaveController = new LoadSaveController(gui);

    public String getPromoteTo() {
        return promoteTo;
    }

    public void setPromoteTo(String promotion) {
        this.promoteTo = promotion;
    }

    public Gui getGui() {
        return gui;
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }


    /**
     * Loads the fxml files
     *
     * @param filename     The filename of the fxml file
     * @param primaryStage The initial root stage of the application.
     * @param gui          The initial root gui of the application.
     * @return the Controller for the fxml
     */
    public static MainController show_FXML(String filename, Stage primaryStage, Gui gui) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Gui.class.getResource(filename));
            Parent root = loader.load();
            Scene gameScene = new Scene(root);
            ((MainController) loader.getController()).setGui(gui);
            primaryStage.setScene(gameScene);
            primaryStage.show();
            return loader.getController();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }


}
