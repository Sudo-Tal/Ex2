import java.io.IOException;
import java.util.ArrayList;

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
        int col = getColumnIndex(cords);  // Convert letter(s) to column index (X-axis)
        int row = getRowIndex(cords);     // Convert number(s) to row index (Y-axis)

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
        eval();
    }

    @Override
    public void eval() {
        // Step 1: Calculate the depth array for all cells
        int[][] dd = depth();

        // Step 2: Create a list to hold all cells with their coordinates and depth order
        ArrayList<int[]> cellCoordinates = new ArrayList<>();

        // Collect all cells' coordinates and their computed depth order
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                // Get the current cell's depth order from the depth array (dd)
                int order = dd[i][j];
                if (order != -1) {  // Avoid adding cells with invalid order (e.g., cycle detected)
                    cellCoordinates.add(new int[] {i, j, order});
                }
            }
        }

        // Step 3: Sort the cells by their order (depth)
        cellCoordinates.sort((a, b) -> Integer.compare(a[2], b[2]));  // a[2] and b[2] are the order values

        // Step 4: Evaluate the cells in the sorted order
        for (int[] cellCoord : cellCoordinates) {
            int x = cellCoord[0];
            int y = cellCoord[1];

            // Check if the cell type is 3 before proceeding with the evaluation
            if (this.table[x][y].getType() == 3) {

                // Retrieve the data (formula or value) for the cell
                String formula = this.table[x][y].getData();

                // Call the eval(x, y) method to evaluate the formula for this cell
                String result = eval(x, y);

                // Set the evaluated result back into the cell's data (this assumes setData is available in your cell class)
                this.table[x][y].setData(result);  // Update the cell's value with the evaluated result
            }
        }
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

                if (currentCell.getType() != 3) {  // If it's not a formula (i.e., a value cell)
                    ans[i][j] = 0;  // Depth for non-formula cells is 0
                    currentCell.setOrder(0);  // Set order to 0 for non-formula cells
                } else {
                    // If it's a formula, calculate depth recursively
                    int depthValue = calculateDepth(currentCell, visited);
                    if (depthValue != -1) {
                        // Only add +1 if the depth is greater than 0 (i.e., there is a dependency)
                        ans[i][j] = depthValue;
                        currentCell.setOrder(ans[i][j]);
                    } else {
                        ans[i][j] = -1;  // Cycle detected
                        currentCell.setOrder(-1);
                    }
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
        int maxDepth = 0;  // Start with depth 0, meaning no dependencies

        int index = 0;
        boolean hasReferences = false;  // Flag to track if the formula has references

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
                    maxDepth = Math.max(maxDepth, referencedDepth);  // Track the maximum depth
                    hasReferences = true;  // Mark that this formula has references
                }
            } else {
                index++;
            }
        }

        visited.remove(cell);  // Remove the current cell from the visited list

        // If the formula has references, return maxDepth + 1; otherwise, return 0 (indicating no dependencies)
        return hasReferences ? maxDepth + 1 : 0;
    }

    private SCell getCellByReference(String reference) {
        int colIndex = getColumnIndex(reference);  // Now interpreting letters as columns (X-axis)
        int rowIndex = getRowIndex(reference);     // Now interpreting numbers as rows (Y-axis)

        if (rowIndex >= 0 && rowIndex < height() && colIndex >= 0 && colIndex < width()) {
            return (SCell) table[rowIndex][colIndex];
        }
        return null;
    }

    private int getRowIndex(String reference) {
        // Extract the letter part (e.g., "A", "B", "Z", or "a", "b", "z")
        String rowPart = reference.replaceAll("[^A-Za-z]", "").toUpperCase();
        int rowIndex = 0;

        // Convert the letters to a 0-based index
        // A -> 0, B -> 1, Z -> 25, AA -> 26, etc.
        for (int i = 0; i < rowPart.length(); i++) {
            rowIndex = rowIndex * 26 + (rowPart.charAt(i) - 'A');
        }

        return rowIndex;
    }

    private int getColumnIndex(String reference) {
        // Extract the numeric part (e.g., "0", "1", "56", "99")
        String columnPart = reference.replaceAll("[^0-9]", "");

        if (columnPart.isEmpty()) {
            return -1; // Invalid reference
        }

        // Direct conversion to integer
        return Integer.parseInt(columnPart);
    }

    // Helper method to convert from array indices back to spreadsheet coordinates
    public static String toSpreadsheetCoordinate(int row, int col) {
        StringBuilder rowName = new StringBuilder();

        // Convert row number to letter(s)
        int tempRow = row;
        while (tempRow >= 0) {
            rowName.insert(0, (char)('A' + (tempRow % 26)));
            tempRow = (tempRow / 26) - 1;
        }

        // Add the column number
        return rowName.toString() + col;
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
        // Get the current cell's formula
        String formula = get(x, y).getData();

        // Check if the cell has a valid order and depth (not equal to -1)
        int depthValue = depth()[x][y];  // Retrieve the depth from the depth array for this cell
        int orderValue = this.table[x][y].getOrder();  // Get the order of the cell (assuming SCell has getOrder() method)

        // If either the depth or order is -1, set the type to -1 and return an appropriate message
        if (depthValue == -1 || orderValue == -1) {
            this.table[x][y].setType(-1);// Set the cell's type to -1 to mark it as invalid or in a cyclic state
            this.table[x][y].setData(Ex2Utils.ERR_CYCLE);
            return Ex2Utils.ERR_CYCLE;
        }

        // Recursively replace all cell references in the formula with their evaluated values

        String evaluatedFormula;
        try {
            evaluatedFormula = resolveFormula(formula);
        } catch (Exception e) {
            evaluatedFormula = Ex2Utils.ERR_FORM; // Handle error case
            this.table[x][y].setType(-2);// Set type to -2 in case of exception
            return evaluatedFormula;
        }
        // Finally, calculate the numeric result of the formula
        evaluatedFormula = evaluatedFormula.charAt(0) + evaluatedFormula.substring(1).replace("=", "");
        if (evaluatedFormula != null && !evaluatedFormula.isEmpty()) {
        if (evaluatedFormula.charAt(0) != '=') {
            evaluatedFormula = "=" + evaluatedFormula;
        }
        }
        double result = SCell.computeForm(evaluatedFormula);

        // Return the result as a string
        if (result == -1) {
            this.table[x][y].setType(-2);
            return Ex2Utils.ERR_FORM;
        }
        return Double.toString(result);
    }

    // Helper method to recursively resolve cell references in a formula
    private String resolveFormula(String formula) {
        // Keep track of the position to scan the formula
        StringBuilder resolvedFormula = new StringBuilder();
        int i = 0;

        // Iterate through the formula string
        while (i < formula.length()) {
            char currentChar = formula.charAt(i);

            // If we encounter a cell reference (starts with a letter)
            if (Character.isLetter(currentChar)) {
                // Extract the cell reference (e.g., "A1", "B56", "D12")
                StringBuilder cellReference = new StringBuilder();

                // Collect letters for the column part (like "A", "B", "Z")
                while (i < formula.length() && Character.isLetter(formula.charAt(i))) {
                    cellReference.append(formula.charAt(i));
                    i++;
                }

                // Collect digits for the row part (like "1", "56", "99")
                while (i < formula.length() && Character.isDigit(formula.charAt(i))) {
                    cellReference.append(formula.charAt(i));
                    i++;
                }

                // Now we have the complete cell reference (e.g., "A1", "B56")
                String cellRef = cellReference.toString();

                // Get the value from the referenced cell
                SCell referencedCell = (SCell) get(cellRef);  // `get(cellRef)` assumes you have a method to retrieve the cell by its reference.
                String referencedValue = referencedCell.getData();

                // Recursively resolve any other references in the referenced value

                String resolvedValue = resolveFormula(referencedValue);

                // Append the resolved value to the final formula
                resolvedFormula.append(resolvedValue);
            } else {
                // If it's not a cell reference, just append the character to the formula
                resolvedFormula.append(currentChar);
                i++;
            }
        }

        return resolvedFormula.toString();
    }
}
