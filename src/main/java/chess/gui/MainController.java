package chess.gui;

import chess.model.AI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    private String promoteTo;
    private boolean checkIsVisible = false;
    private boolean rotation = false;
    public AI ai; // = new AI(true); // black ai
    public Gui gui;

    public AI getAi() {
        return ai;
    }

    public void setAi(AI ai) {
        this.ai = ai;
    }

    public boolean isRotation() {
        return rotation;
    }

    public void setRotation(boolean rotation) {
        this.rotation = rotation;
    }


    public boolean isCheckIsVisible() {
        return checkIsVisible;
    }

    public void setCheckIsVisible(boolean checkIsVisible) {
        this.checkIsVisible = checkIsVisible;
    }

    public String getPromoteTo() {
        System.out.println("Flag 2");
        return promoteTo;
    }

    public void setPromoteTo(String promotion) {
        System.out.println("Flag");
        this.promoteTo = promotion;
    }

    public Gui getGui() {
        return gui;
    }
    public void setGui(Gui gui){
        this.gui = gui;
    }

    public static MainController show_FXML(String filename, Stage primaryStage, Gui gui){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Gui.class.getResource(filename));
            Parent root = loader.load();
            Scene gameScene = new Scene(root);
            ((MainController)loader.getController()).setGui(gui); //hier noch oberklasse
            primaryStage.setScene(gameScene);
            primaryStage.show();
            return ((MainController)loader.getController());


        }  catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }



}
