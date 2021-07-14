package chess;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SettingsTest {

    @Test
    void LanguageTest(){
        Settings settings = new Settings();
        assertEquals("Chess", settings.getSettingsLanguage().getLanguage().getDic().get(100));
    }
}