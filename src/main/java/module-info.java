/**
 * The main module of the chess application.
 */
module chess {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;
    
    exports chess.gui;
    opens chess.model;
    opens chess.model.figures;
    opens main;
}
