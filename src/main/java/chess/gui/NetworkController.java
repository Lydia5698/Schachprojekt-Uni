package chess.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * The Controller for the network game. You need to host or join a Game to see the chessboard
 */
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
        getGui().getSettings().getSettingsLanguage().changeLanguage();
        changeToLanguage();
    }

    /**
     * Changes all buttons and text fields to the selected language
     */
    private void changeToLanguage() {
        btnLanguage.setImage(new Image(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource(getGui().getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getSettingsLanguage().getLanguageNumber() + "03")))).toExternalForm())));
        hostGame.setText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getSettingsLanguage().getLanguageNumber() + "95")));
        joinGame.setText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getSettingsLanguage().getLanguageNumber() + "96")));
        ckbxColour.setText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getSettingsLanguage().getLanguageNumber() + "97")));
        txtIpAddress.setText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getSettingsLanguage().getLanguageNumber() + "98")));
        btnSubmit.setText(gui.getSettings().getSettingsLanguage().getLanguage().getDic().get(Integer.parseInt(getGui().getSettings().getSettingsLanguage().getLanguageNumber() + "99")));

    }


    /**
     * Sets the TextFormatter to the ipAddress Textfield
     */
    @FXML
    void ipAddressInput() {
        ipAddress.setTextFormatter(ipAddressTextFormatter());

    }

    /**
     * Gets the ipAddress from the ipAddress field and gets the selected colour. It also sets the ipAddress and the port in the Settings
     */
    @FXML
    void submit() {
        ipAddress.getText();
        getGui().settings.getSettingsNetwork().setBlack(ckbxColour.isSelected());
        gui.settings.getSettingsNetwork().setIp(ipAddress.getText());
        gui.settings.getSettingsNetwork().setPort(4848);
        gui.settings.getSettingsNetwork().setNetwork_active(true);
        if (hostGame.isSelected()){
            gui.settings.getSettingsNetwork().setConnection(gui.settings.getSettingsNetwork().createServer());}
        else if(joinGame.isSelected()){
            gui.settings.getSettingsNetwork().setConnection(gui.settings.getSettingsNetwork().createClient());}
        try { gui.settings.getSettingsNetwork().initCon();}
        catch (Exception e){
            System.out.println ("exception when establishing con"); }

        Stage stage = (Stage) btnSubmit.getScene().getWindow();
        show_FXML("activeGame.fxml", stage, getGui());
    }

    /**
     * Creates the ipAddress Text Formatter
     * @return the Text Formatter for the ipAddress
     */
    public static TextFormatter<TextFormatter.Change> ipAddressTextFormatter() {
        UnaryOperator<TextFormatter.Change> ipAddressFilter = change -> change.getControlNewText().matches(ipAddressRegex) ? change : null;
        return new TextFormatter<>(ipAddressFilter);
    }

    /**
     * Creates the Regex and only allowes the Pattern of an ipAddress
     * @return the Regex for the Text Formatter
     */
    private static String makePartialIPRegex() {
        String partialBlock = "(([01]?[0-9]{0,2})|(2[0-4][0-9])|(25[0-5]))";
        String subsequentPartialBlock = "(\\." + partialBlock + ")";
        String ipAddress = partialBlock + "?" + subsequentPartialBlock + "{0,3}";
        return "^" + ipAddress;
    }

    /**
     * Selects the joinGame Radio Button and sets the Properties
     */
    @FXML
    void hostNetworkGame() {
        joinGame.setSelected(false);
        setProperties();
    }

    /**
     * Selects the hostGame Radio Button and sets the Properties
     */
    @FXML
    void joinNetworkGame() {
        hostGame.setSelected(false);
        setProperties();
    }

    /**
     * It sets the Properties when you click on the Radio Button so that the ip Address is only visible for the
     * Join Game and the colour choice only for the Host Game
     */
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
