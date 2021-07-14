package chess;

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
}