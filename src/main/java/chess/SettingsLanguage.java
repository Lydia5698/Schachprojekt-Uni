package chess;

/**
 * The settingsLanguage is the settings class for the language
 *
 *  @author Lydia Günther
 */
public class SettingsLanguage {
    protected boolean languageGerman = false;
    protected boolean languageEnglish = true;
    protected boolean languageKlingon = false;
    String languageNumber = "1";
    Language language = new Language();


    /**
     * Changes the booleans for the Language and updates the Language number so the right Language is load from the Dictionary
     */
    public void changeLanguage() {
        if(isLanguageEnglish()) {
            setLanguageEnglish(false);
            setLanguageGerman(true);
            setLanguageKlingon(false);
            setLanguageNumber("2");
        }
        else if(isLanguageGerman()){
            setLanguageEnglish(false);
            setLanguageGerman(false);
            setLanguageKlingon(true);
            setLanguageNumber("3");
        }
        else {
            setLanguageEnglish(true);
            setLanguageGerman(false);
            setLanguageKlingon(false);
            setLanguageNumber("1");
        }
    }

    public Language getLanguage() {
        return language;
    }

    public boolean isLanguageGerman() {
        return languageGerman;
    }

    public void setLanguageGerman(boolean languageGerman) {
        this.languageGerman = languageGerman;
    }

    public boolean isLanguageEnglish() {
        return languageEnglish;
    }

    public void setLanguageEnglish(boolean languageEnglish) {
        this.languageEnglish = languageEnglish;
    }

    public String getLanguageNumber() {
        return languageNumber;
    }

    public void setLanguageNumber(String languageNumber) {
        this.languageNumber = languageNumber;
    }
    public boolean isLanguageKlingon() {
        return languageKlingon;
    }

    public void setLanguageKlingon(boolean languageKlingon) {
        this.languageKlingon = languageKlingon;
    }

}
