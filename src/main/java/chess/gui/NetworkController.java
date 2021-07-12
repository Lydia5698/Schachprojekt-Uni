package chess.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.function.UnaryOperator;

public class NetworkController extends MainController {
    private static final String ipAddressRegex = makePartialIPRegex();

    @FXML
    private TextField ipAdress;

    @FXML
    private Label txtIpAdress;

    @FXML
    private CheckBox colour;

    @FXML
    private Button submit;

    @FXML
    private RadioButton hostGame;

    @FXML
    private RadioButton joinGame;


    @Override
    public void setGui(Gui gui) {
        this.gui = gui;
        ipAdress.setTextFormatter(ipAddressTextFormatter());
    }

    @FXML
    void ipAdressInput(ActionEvent event) {
        ipAdress.setTextFormatter(ipAddressTextFormatter());

    }



    @FXML
    void submit(ActionEvent event) { //TODO wahl zwischen host game and join Game
        ipAdress.getText();
        gui.settings.setIp(ipAdress.getText());
        gui.settings.setPort(4848); // von hier zu settings ins Network? Was ist mit der Farbwahl?
        if (hostGame.isSelected()){
            gui.getSettings().setConnection(gui.getSettings().createServer());}
        else if(joinGame.isSelected()){
            gui.getSettings().setConnection(gui.getSettings().createClient());}
        try { gui.getSettings().initCon();}
        catch (Exception e){
            System.out.println ("exception when establishing con"); }

        Stage stage = (Stage) submit.getScene().getWindow();
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

    UnaryOperator<TextFormatter.Change> filter = t -> {

        if (t.isReplaced())
            if (t.getText().matches("[^0-9]"))
                t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));


        if (t.isAdded()) {
            if (t.getControlText().contains(".")) {
                if (t.getText().matches("[^0-9]")) {
                    t.setText("");
                }
            } else if (t.getText().matches("[^0-9.]")) {
                t.setText("");
            }
        }

        return t;
    };


    @FXML
    void hostNetworkGame(ActionEvent event) {
        joinGame.setSelected(false);
        setProperties();
    }

    @FXML
    void joinNetworkGame(ActionEvent event) {
        hostGame.setSelected(false);
        setProperties();
    }

    void setProperties() {
        if (joinGame.isSelected()) {
            ipAdress.setVisible(true);
            txtIpAdress.setVisible(true);
        } else if (hostGame.isSelected()) {
            ipAdress.setVisible(false);
            txtIpAdress.setVisible(false);
        }
    }


}
