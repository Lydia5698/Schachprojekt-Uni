package chess;

public class Settings {
    private boolean pvp = true;
    private boolean black = false;

    public void options(int id, boolean change){ //options im switchcase_modalis
        switch(id){
            case 1: pvp = change;
                break;
            case 2: black = change;
                break;
        }
    }
}
