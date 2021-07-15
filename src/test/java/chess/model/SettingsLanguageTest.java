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
    void ChangeLanguageToGerman(){
        Settings settings = new Settings();
        settings.getSettingsLanguage().changeLanguage();
        assertTrue(settings.getSettingsLanguage().isLanguageGerman());
        assertFalse(settings.getSettingsLanguage().isLanguageKlingon());
        assertFalse(settings.getSettingsLanguage().isLanguageEnglish());
    }

    @Test
    void ChangeLanguageToEnglish(){
        Settings settings = new Settings();
        settings.getSettingsLanguage().changeLanguage();
        settings.getSettingsLanguage().changeLanguage();
        settings.getSettingsLanguage().changeLanguage();
        assertFalse(settings.getSettingsLanguage().isLanguageGerman());
        assertFalse(settings.getSettingsLanguage().isLanguageKlingon());
        assertTrue(settings.getSettingsLanguage().isLanguageEnglish());
    }

    @Test
    void ChangeLanguageToKlingon(){
        Settings settings = new Settings();
        settings.getSettingsLanguage().changeLanguage();
        settings.getSettingsLanguage().changeLanguage();
        assertFalse(settings.getSettingsLanguage().isLanguageGerman());
        assertTrue(settings.getSettingsLanguage().isLanguageKlingon());
        assertFalse(settings.getSettingsLanguage().isLanguageEnglish());
    }
}