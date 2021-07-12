package chess;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SettingsTest {

    @Test
    void LanguageTest(){
        Settings settings = new Settings();
        assertTrue(settings.getLanguage().getDic().get(100).equals("Chess"));
    }
}