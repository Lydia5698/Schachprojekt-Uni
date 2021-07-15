package chess.model;

import chess.Settings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the settings
 */
class SettingsTest {

    @Test
    void setSettingsGui(){
        Settings settings = new Settings();
        settings.setGui_active(true);
        settings.setDoubleClick(true);
        settings.setRotateBoard(true);
        settings.setCheckVisible(true);
        settings.setHighlightPossibleMoves(true);

        assertTrue(settings.isGui_active());
        assertTrue(settings.isDoubleClick());
        assertTrue(settings.isRotateBoard());
        assertTrue(settings.isCheckVisible());
        assertTrue(settings.isHighlightPossibleMoves());

    }

    @Test
    void setSettings(){
        Settings settings = new Settings();
        settings.setGameEnd(true);
        settings.setAi_active(true);
        settings.setAi_colour(true);
        settings.setPlayerInCheck(true);

        assertTrue(settings.isGameEnd());
        assertTrue(settings.isAi_active());
        assertTrue(settings.isAi_colour());
        assertTrue(settings.isPlayerInCheck());
    }


}