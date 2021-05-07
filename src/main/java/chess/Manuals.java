package chess;

import chess.model.Position;

import java.util.ArrayList;
public class Manuals {
    private Game game;
    public Manuals(Game game) {
        this.game = game;
    }

    public ArrayList<Position> updateValidPositions(Position origin, boolean black, char minion_type) {
        switch (minion_type) {
            case 'K':
                int[][] directionsKing = {{1, 1}, {-1, -1}, {1, -1}, {-1, 1}, {0, 1}, {-1, 0}, {0, -1}, {1, 0}};
                return calcValidMovesSpecial(origin, black, directionsKing);
            case 'Q':
                int[][] directionsQueen = {{1, 1}, {-1, -1}, {1, -1}, {-1, 1}, {0, 1}, {-1, 0}, {0, -1}, {1, 0}};
                return calcValidMovesLine(origin, black, directionsQueen);
            case 'R':
                int[][] directionsRook = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
                return calcValidMovesLine(origin, black, directionsRook);
            case 'B':
                int[][] directionsBishop = {{1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
                return calcValidMovesLine(origin, black, directionsBishop);
            case 'N':
                int[][] directionsNight = {{1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}};
                return calcValidMovesSpecial(origin, black, directionsNight);
            case 'P':
                int[][] directionsPawn = {{1, 1}, {-1, 1}};
                return calcValidMovesPawn(origin, black, directionsPawn);
            default:
                return new ArrayList<>();
        }
    }

    private ArrayList<Position> calcValidMovesSpecial(Position origin, boolean black, int[][] directionsKing) { //king / knight move
        int currHorizont = origin.getHorizont();
        int currVertical = origin.getVertical();
        ArrayList<Position> validPositions = new ArrayList<>();

        for (int[] direction : directionsKing) {
            Position validPos = new Position(-1, -1);
            validPos.setHorizont(currHorizont + direction[1]);
            validPos.setVertical(currVertical + direction[0]);
            if (validPos.getVertical() < 8 && validPos.getVertical() >= 0 && validPos.getHorizont() < 8 && validPos.getHorizont() >= 0) {
                if (game.checkPosition(validPos)) {
                    if (game.getBoard()[validPos.getHorizont()][validPos.getVertical()].isBlack() != black) {
                        validPositions.add(validPos);
                    }
                } else {
                    validPositions.add(validPos);
                }
            }
        }
        return validPositions;
    }

    private ArrayList<Position> calcValidMovesPawn(Position origin, boolean black, int[][] directionsPawn) { //pawns move
        int currHorizont = origin.getHorizont();
        int currVertical = origin.getVertical();
        ArrayList<Position> validPositions = new ArrayList<>();
        int alignment = 1; //ausrichtung

        if (!black) {
            alignment = -1;
        }

        for (int i = 1; i < 3; i++) { //iteration
            Position validPos = new Position(-1, -1);
            validPos.setVertical(currVertical);

            if ((currHorizont == 1 && black) || (currHorizont == 6 && !black)) {
                validPos.setHorizont(currHorizont + alignment * i);
            } else {
                validPos.setHorizont(currHorizont + alignment);
            }

            if (!game.checkPosition(validPos)) {
                validPositions.add(validPos);
            }
        }

        for (int[] direction : directionsPawn) {
            Position validPos = new Position(-1, -1);
            validPos.setVertical(currVertical + direction[0]);
            validPos.setHorizont(currHorizont + direction[1] * alignment);

            if (game.checkPosition(validPos)) {
                if (game.getBoard()[validPos.getHorizont()][validPos.getVertical()].isBlack() != black) {
                    validPositions.add(validPos);
                }
            }
        }
        return validPositions;
    }

    public ArrayList<Position> calcValidMovesLine(Position origin, boolean black, int[][] directions) {
        int currHorizont = origin.getHorizont();
        int currVertical = origin.getVertical();
        ArrayList<Position> validPositions = new ArrayList<>();

        for (int[] direction : directions) {
            for (int n = 1; n < 9; n++) { //n vector calc
                Position validPos = new Position(-1, -1);
                validPos.setHorizont(currHorizont + n * direction[1]);
                validPos.setVertical(currVertical + n * direction[0]);
                if (validPos.getHorizont() < 8 && validPos.getHorizont() >= 0 && validPos.getVertical() < 8 && validPos.getVertical() >= 0) {
                    if (game.checkPosition(validPos)) {
                        if (game.getBoard()[validPos.getHorizont()][validPos.getVertical()].isBlack() != black) {
                            validPositions.add(validPos);
                        }
                        break;
                    } else {
                        validPositions.add(validPos);
                    }
                } else {
                    break;
                }
            }
        }
        return validPositions;
    }
}