package chess.gui;

import chess.model.AI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The MainController
 */
public class MainController {
    private String promoteTo;
    public Gui gui;

    public String getPromoteTo() {
        return promoteTo;
    }

    public void setPromoteTo(String promotion) {
        this.promoteTo = promotion;
    }

    public Gui getGui() {
        return gui;
    }
    public void setGui(Gui gui){
        this.gui = gui;
    }

    /**
     * Loads the fxml files
     * @param filename The filename of the fxml file
     * @param primaryStage The initial root stage of the application.
     * @param gui The initial root gui of the application.
     * @return the Controller for the fxml
     */
    public static MainController show_FXML(String filename, Stage primaryStage, Gui gui){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Gui.class.getResource(filename));
            Parent root = loader.load();
            Scene gameScene = new Scene(root);
            ((MainController)loader.getController()).setGui(gui); //hier noch oberklasse
            primaryStage.setScene(gameScene);
            primaryStage.show();
            return loader.getController();


        }  catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }



}
