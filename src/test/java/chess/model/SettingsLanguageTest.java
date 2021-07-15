package chess.model;

import chess.Settings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * tests the SettingsLanguage class. Sets the Dictionary and checks if the entry is the right one
 */
class SettingsLanguageTest {

    @Test
    void LanguageTest(){
        Settings settings = new Settings();
        assertEquals("Chess", settings.getSettingsLanguage().getLanguage().getDic().get(100));
    }

    @Test
    void ChangeLanguage(){
        Settings settings = new Settings();
        settings.getSettingsLanguage().changeLanguage();
        assertTrue(settings.getSettingsLanguage().isLanguageGerman());
        assertFalse(settings.getSettingsLanguage().isLanguageKlingon());
        assertFalse(settings.getSettingsLanguage().isLanguageEnglish());
    }
}