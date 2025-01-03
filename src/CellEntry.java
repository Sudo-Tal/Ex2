// Add your documentation below:

public class CellEntry  implements Index2D {

    //Constructor make me

    private String CellIndex = "";

    @Override
    public boolean isValid() {
        return false;
    }
    //check if CellIndex is of type "Letter"+"number (A-Z/a-z/ 0-99)

    @Override
    public int getX() {return Ex2Utils.ERR;}

    @Override
    public int getY() {return Ex2Utils.ERR;}
}
