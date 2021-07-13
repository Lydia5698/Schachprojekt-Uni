package chess.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.function.UnaryOperator;

public class NetworkController extends MainController {
    private static final String ipAddressRegex = makePartialIPRegex();

    @FXML
    private TextField ipAddress;

    @FXML
    private Label txtIpAddress;

    @FXML
    private CheckBox ckbxColour;

    @FXML
    private Button btnSubmit;

    @FXML
    private RadioButton hostGame;

    @FXML
    private RadioButton joinGame;

    @FXML
    private ImageView btnLanguage;


    @Override
    public void setGui(Gui gui) {
        this.gui = gui;
        ipAddress.setTextFormatter(ipAddressTextFormatter());
        changeToLanguage();
    }

    /**
     * The language is changed in the settings when the Image btnLanguage is pushed
     */
    @FXML
    void changeLanguage() {
        getGui().getSettings().changeLanguage();
        changeToLanguage();
    }

    /**
     * Changes all buttons and text fields to the selected language
     */
    private void changeToLanguage() {
        btnLanguage.setImage(new Image(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource(getGui().getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber() + "03")))).toExternalForm())));
        hostGame.setText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber() + "95")));
        joinGame.setText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber() + "96")));
        ckbxColour.setText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber() + "97")));
        txtIpAddress.setText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber() + "98")));
        btnSubmit.setText(gui.getSettings().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getLanguageNumber() + "99")));

    }


    @FXML
    void ipAddressInput() {
        ipAddress.setTextFormatter(ipAddressTextFormatter());

    }

    @FXML
    void submit() {
        ipAddress.getText();
        getGui().getSettings().setBlack(ckbxColour.isSelected());
        gui.settings.setIp(ipAddress.getText());
        gui.settings.setPort(4848);
        if (hostGame.isSelected()){
            gui.getSettings().setConnection(gui.getSettings().createServer());}
        else if(joinGame.isSelected()){
            gui.getSettings().setConnection(gui.getSettings().createClient());}
        try { gui.getSettings().initCon();}
        catch (Exception e){
            System.out.println ("exception when establishing con"); }

        Stage stage = (Stage) btnSubmit.getScene().getWindow();
        show_FXML("activeGame.fxml", stage, getGui());
    }

    public static TextFormatter<TextFormatter.Change> ipAddressTextFormatter() {
        UnaryOperator<TextFormatter.Change> ipAddressFilter = change -> change.getControlNewText().matches(ipAddressRegex) ? change : null;
        return new TextFormatter<>(ipAddressFilter);
    }

    private static String makePartialIPRegex() {
        String partialBlock = "(([01]?[0-9]{0,2})|(2[0-4][0-9])|(25[0-5]))";
        String subsequentPartialBlock = "(\\." + partialBlock + ")";
        String ipAddress = partialBlock + "?" + subsequentPartialBlock + "{0,3}";
        return "^" + ipAddress;
    }

    @FXML
    void hostNetworkGame() {
        joinGame.setSelected(false);
        setProperties();
    }

    @FXML
    void joinNetworkGame() {
        hostGame.setSelected(false);
        setProperties();
    }

    void setProperties() {
        if (joinGame.isSelected()) {
            ipAddress.setVisible(true);
            txtIpAddress.setVisible(true);
            ckbxColour.setVisible(false);
        } else if (hostGame.isSelected()) {
            ipAddress.setVisible(false);
            txtIpAddress.setVisible(false);
            ckbxColour.setVisible(true);
        }
    }


}
