// Add your documentation below:

public class CellEntry implements Index2D {
    private final String cellString;

    //Constructor
    public CellEntry(String cellString) {
        this.cellString = cellString;
    }

    public CellEntry(int x, int y) {
        this.cellString = convertX(x) + convertY(y);
    }

    @Override
    public boolean isValid() {
        return cellString.matches("[A-Za-z]\\d{1,2}") && Integer.parseInt(cellString.substring(1)) >= 0 && Integer.parseInt(cellString.substring(1)) <= 99;
    }

    @Override
    public int getX() {
        if (isValid()) {
            if (cellString.charAt(0) == 'A') return 0;
            if (cellString.charAt(0) == 'B') return 1;
            if (cellString.charAt(0) == 'C') return 2;
            if (cellString.charAt(0) == 'D') return 3;
            if (cellString.charAt(0) == 'E') return 4;
            if (cellString.charAt(0) == 'F') return 5;
            if (cellString.charAt(0) == 'G') return 6;
            if (cellString.charAt(0) == 'H') return 7;
            if (cellString.charAt(0) == 'I') return 8;
            if (cellString.charAt(0) == 'J') return 9;
            if (cellString.charAt(0) == 'K') return 10;
            if (cellString.charAt(0) == 'L') return 11;
            if (cellString.charAt(0) == 'M') return 12;
            if (cellString.charAt(0) == 'N') return 13;
            if (cellString.charAt(0) == 'O') return 14;
            if (cellString.charAt(0) == 'P') return 15;
            if (cellString.charAt(0) == 'Q') return 16;
            if (cellString.charAt(0) == 'R') return 17;
            if (cellString.charAt(0) == 'S') return 18;
            if (cellString.charAt(0) == 'T') return 19;
            if (cellString.charAt(0) == 'U') return 20;
            if (cellString.charAt(0) == 'V') return 21;
            if (cellString.charAt(0) == 'W') return 22;
            if (cellString.charAt(0) == 'X') return 23;
            if (cellString.charAt(0) == 'Y') return 24;
            if (cellString.charAt(0) == 'Z') return 25;

            if (cellString.charAt(0) == 'a') return 0;
            if (cellString.charAt(0) == 'b') return 1;
            if (cellString.charAt(0) == 'c') return 2;
            if (cellString.charAt(0) == 'd') return 3;
            if (cellString.charAt(0) == 'e') return 4;
            if (cellString.charAt(0) == 'f') return 5;
            if (cellString.charAt(0) == 'g') return 6;
            if (cellString.charAt(0) == 'h') return 7;
            if (cellString.charAt(0) == 'i') return 8;
            if (cellString.charAt(0) == 'j') return 9;
            if (cellString.charAt(0) == 'k') return 10;
            if (cellString.charAt(0) == 'l') return 11;
            if (cellString.charAt(0) == 'm') return 12;
            if (cellString.charAt(0) == 'n') return 13;
            if (cellString.charAt(0) == 'o') return 14;
            if (cellString.charAt(0) == 'p') return 15;
            if (cellString.charAt(0) == 'q') return 16;
            if (cellString.charAt(0) == 'r') return 17;
            if (cellString.charAt(0) == 's') return 18;
            if (cellString.charAt(0) == 't') return 19;
            if (cellString.charAt(0) == 'u') return 20;
            if (cellString.charAt(0) == 'v') return 21;
            if (cellString.charAt(0) == 'w') return 22;
            if (cellString.charAt(0) == 'x') return 23;
            if (cellString.charAt(0) == 'y') return 24;
            if (cellString.charAt(0) == 'z') return 25;
        }
        return Ex2Utils.ERR;
    }

    @Override
    public int getY() {
        if (isValid()) {
            if (cellString.length() == 2) {
                return Character.getNumericValue(cellString.charAt(1));
            }
            if (cellString.length() == 3) {
                return Integer.parseInt(cellString.substring(1, 3));

            }
        }
        return Ex2Utils.ERR;
    }

    private String convertX(int num) {
        if (num >= 0 && num < 26) {
            return Character.toString((char) ('A' + num)); // Convert 0-25 to 'A'-'Z'
        }

        return null;
    }

    private String convertY(int num) {
        return String.valueOf(num);
    }


    @Override
    public String toString() {
        if (isValid()) {
            return cellString;
        }
        return "Wrong Format";
    }
}
