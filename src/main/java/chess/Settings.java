package chess;

public class Settings {
    //felder
    protected boolean ai_active = false;
    protected boolean ai_colour = false;
    //gegen gegner
    //schach anzeigen lassen
    //mehrfach klicken
    //spielfeld mitdrehen


    //constructor
    public Settings() {
    }

    //methoden


    public boolean isAi_active() {
        return ai_active;
    }

    public boolean isAi_colour() {
        return ai_colour;
    }

    public void setAi_active(boolean ai_active) {
        this.ai_active = ai_active;
    }

    public void setAi_colour(boolean ai_colour) {
        this.ai_colour = ai_colour;
    }


}
