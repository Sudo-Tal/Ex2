import java.io.IOException;
import java.util.ArrayList;

// Add your documentation below:
public class Ex2Sheet implements Sheet {
    private Cell[][] table;

    // ///////////////////
    public Ex2Sheet(int x, int y) {
        table = new SCell[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                table[i][j] = new SCell("");  // Initialize with empty cells
            }
        }
        eval();  // Initial evaluation
    }

    public Ex2Sheet() {
        this(Ex2Utils.WIDTH, Ex2Utils.HEIGHT);  // Default width and height from Ex2Utils
    }

    @Override
    public String value(int x, int y) {
        String ans = Ex2Utils.EMPTY_CELL;  // Default if the cell is empty
        Cell c = get(x, y);
        if (c != null) {
            ans = c.toString();  // Return the value of the cell as a string
        }
        return ans;
    }

    @Override
    public Cell get(int x, int y) {
        return table[x][y];
    }

    @Override
    public Cell get(String cords) {
        // Extract the column letter(s) and row number from the string coordinates
        int col = getColumnIndex(cords);  // Convert letter(s) to column index
        int row = getRowIndex(cords);     // Convert number to row index

        // Check if the coordinates are within bounds
        if (isIn(row, col)) {
            return table[row][col];  // Return the cell at the specified position
        }
        return null;  // Return null if the coordinates are out of bounds
    }

    @Override
    public int width() {
        return table.length;
    }

    @Override
    public int height() {
        return table[0].length;
    }

    @Override
    public void set(int x, int y, String s) {
        table[x][y] = new SCell(s);  // Set the cell at (x, y) to the new value (s)
        eval();  // Re-evaluate the sheet to handle dependencies
    }

    @Override
    public void eval() {
        int[][] dd = depth();
        // This method will evaluate formulas based on depth
        // You may need to implement specific logic to recalculate or update cell values based on dependencies
    }

    @Override
    public boolean isIn(int xx, int yy) {
        return xx >= 0 && yy >= 0 && xx < width() && yy < height();
    }

    @Override
    public int[][] depth() {
        int[][] ans = new int[width()][height()];
        ArrayList<SCell> visited = new ArrayList<>();

        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                SCell currentCell = (SCell) table[i][j];

                if (currentCell.getType() != 3) {  // If it's not a formula
                    ans[i][j] = 0;
                    currentCell.setOrder(0);  // Depth is 0 for non-formula cells
                } else {
                    // If it's a formula, calculate depth recursively
                    int depthValue = calculateDepth(currentCell, visited);
                    ans[i][j] = depthValue;
                    currentCell.setOrder(depthValue);
                }
            }
        }

        return ans;
    }

    // Helper function to recursively calculate the depth of a formula cell
    private int calculateDepth(SCell cell, ArrayList<SCell> visited) {
        if (visited.contains(cell)) {
            return -1;  // Cycle detected, return -1
        }

        visited.add(cell);
        String formula = cell.getData();
        int maxDepth = 0;
        int index = 0;

        while (index < formula.length()) {
            if (Character.isLetter(formula.charAt(index))) {
                StringBuilder reference = new StringBuilder();
                while (index < formula.length() && (Character.isLetter(formula.charAt(index)) || Character.isDigit(formula.charAt(index)))) {
                    reference.append(formula.charAt(index));
                    index++;
                }

                SCell referencedCell = getCellByReference(reference.toString());
                if (referencedCell != null) {
                    int referencedDepth = calculateDepth(referencedCell, visited);
                    if (referencedDepth == -1) {
                        return -1;  // Cycle detected
                    }
                    maxDepth = Math.max(maxDepth, referencedDepth);
                }
            } else {
                index++;
            }
        }

        visited.remove(cell);  // Remove the current cell from the visited list
        return maxDepth + 1;  // Return max depth + 1
    }

    private SCell getCellByReference(String reference) {
        int colIndex = getColumnIndex(reference);
        int rowIndex = getRowIndex(reference);

        if (rowIndex >= 0 && rowIndex < height() && colIndex >= 0 && colIndex < width()) {
            return (SCell) table[rowIndex][colIndex];
        }
        return null;
    }

    private int getColumnIndex(String reference) {
        String columnPart = reference.replaceAll("[^A-Za-z]", "");
        int columnIndex = 0;
        for (int i = 0; i < columnPart.length(); i++) {
            columnIndex = columnIndex * 26 + (columnPart.charAt(i) - 'A');
        }
        return columnIndex;  // Return the column index
    }

    private int getRowIndex(String reference) {
        String rowPart = reference.replaceAll("[^0-9]", "");
        return Integer.parseInt(rowPart) - 1;  // Convert to 0-based index
    }

    @Override
    public void load(String fileName) throws IOException {
        // Implement file loading logic here
        // You can use BufferedReader or FileReader to read the file contents
        // and populate the `table` based on the data in the file
    }

    @Override
    public void save(String fileName) throws IOException {
        // Implement file saving logic here
        // You can use BufferedWriter or FileWriter to save the current sheet data to a file
    }

    @Override
    public String eval(int x, int y) {
        String ans = null;
        if (get(x, y) != null) {
            ans = get(x, y).toString();
        }
        return ans;
    }
}
