package oxgame.app;

import java.util.HashMap;
import java.util.Map;

import static oxgame.app.ConsoleColor.*;

public class OldBoard {
    private Map<Coordinates,Symbol> coordinatesSymbolMap;
    private int width;
    private int height;
    private int size;

    public static OldBoard newBoard(int width, int height) {
        OldBoard board = new OldBoard(width,height);
        for (int i = 1; i <= board.size; i++) {
            board.coordinatesSymbolMap.put(Coordinates.apply(i), null);
        }
        return board;
    }
    private OldBoard(int width, int height) {
        this.coordinatesSymbolMap = new HashMap<>();
        this.width = width;
        this.height = height;
        this.size = width * height;
    }

    public void placeSymbol(Coordinates coordinates, Symbol symbol) {
        coordinatesSymbolMap.put(coordinates,symbol);
    }

    public void showBoard() {
        int columnCounter = 0;
        for (int i = 1; i <= size; i++) {
            if (columnCounter == width) {
                System.out.println();
                columnCounter = 0;
            }
            Symbol receivedSymbol = coordinatesSymbolMap.get(Coordinates.apply(i));
            changePrintMode(receivedSymbol,i);
            columnCounter++;
        }
        System.out.println();
    }

    private void changePrintMode(Symbol receivedSymbol, int iterator) {
        String consoleColor;
        if (receivedSymbol == null) {
            System.out.printf(RED+"["+ GRAY +"%1$-"+String.valueOf(size).length()+"s"+RED+"]"+RESET,iterator);
            return;
        } else if (receivedSymbol.equals(Symbol.X)) {
            consoleColor = BLUE.toString();
        } else {
            consoleColor = PURPLE.toString();
        }
        System.out.printf(RED+"["+consoleColor+"%1$-"+String.valueOf(size).length()+"s"+RED+"]"+RESET,receivedSymbol);
    }
    public int size() {
        return size;
    }
    public Symbol getSymbol(Coordinates coordinates) {
        return coordinatesSymbolMap.get(coordinates);
    }
}