// Add your documentation below:

import java.util.ArrayList;

public class SCell implements Cell {
    String OriginalLine;
    private String line;
    private int type;
    private int order;

    public SCell(String s) {
        OriginalLine = s;
        line = s;
        order = 0;


        //set type
        if (!s.isEmpty() && s.charAt(0) == '=') {
            if (!IsForm(s)) {
                type = -2;
            }
            if (IsForm(s)) {
                type = 3;
            }
            if (order == -1) {
                type = -1;
            }
        }
        if (!s.isEmpty() && s.charAt(0) != '=') {

            if (IsText(s)) {
                type = 1;
            }
            if (IsNumber(s)) {
                type = 2;
            }
        }
        setData(s);

    }

    //PreCode
    public static boolean IsNumber(String Text) {
        boolean Ans = true;
        if (Text == null || Text.isEmpty()) {
            return false;  // Return false if the string is empty or null
        }
        if (Text.charAt(0) == '=') {
            return false;
        }

        try {
            Double.parseDouble(Text);
        } catch (NumberFormatException e) {
            Ans = false;
        }
        return Ans;
    }

    public static boolean IsText(String Text) {
        if (Text == null || Text.isEmpty()) {
            return false;  // Return false if the string is empty or null
        }
        if (Text.charAt(0) == '=') {
            return false;
        }

        boolean Ans = !IsNumber(Text) && !IsForm(Text);
        return Ans;
    }

    public static boolean IsForm(String Text) {
        boolean Ans = false;

        // Check for null
        if (Text == null) {
            return Ans;
        }

        // Check for empty
        if (Text.isEmpty()) {
            return Ans;
        }

        // Check if starts with "="
        if (Text.charAt(0) != '=') {
            Ans = false;
            return Ans;
        }

        // Check for numbers adjacent to letters in invalid order (e.g., 1A, but allow A1)
        for (int i = 0; i < Text.length() - 1; i++) {
            char currChar = Text.charAt(i);
            char nextChar = Text.charAt(i + 1);

            // Check if a digit is immediately followed by a letter (invalid: 1A)
            if (Character.isDigit(currChar) && Character.isLetter(nextChar)) {
                return false; // Invalid: number adjacent to cell reference in wrong order
            }
        }

        // StringBuilder to build the output string
        StringBuilder result = new StringBuilder();  // To build the output string
        int length = Text.length();  // Get the length of the input string

        // Iterate through each character of the input string
        for (int i = 0; i < length; i++) {
            char currentChar = Text.charAt(i);

            // If the character is a letter (A-Z or a-z), we may have a cell reference
            if (Character.isLetter(currentChar)) {
                // Start building the cell reference (it will start with the letter)
                StringBuilder cellReference = new StringBuilder();
                cellReference.append(currentChar);

                // Check if the next character is a digit and build the number part
                if (i + 1 < length && Character.isDigit(Text.charAt(i + 1))) {
                    StringBuilder numberPart = new StringBuilder();
                    i++;  // Move to the next character to collect digits

                    // Collect all digits (0-99)
                    while (i < length && Character.isDigit(Text.charAt(i))) {
                        numberPart.append(Text.charAt(i));
                        i++;
                    }

                    // If the number is between 0 and 99, replace it with "1"
                    int number = Integer.parseInt(numberPart.toString());
                    if (number >= 0 && number <= 99) {
                        result.append("1");  // Replace valid reference with "1"
                    } else {
                        // If the number is out of the range (not between 0-99), keep the reference as is
                        result.append(cellReference);
                        result.append(numberPart);
                    }
                } else {
                    // If it's just a letter without a valid number, add it as is
                    result.append(cellReference);
                }

                // After processing the cell reference, we need to make sure
                // we are at the next valid character, so we don't skip anything.
                if (i < length && !Character.isLetter(Text.charAt(i)) && !Character.isDigit(Text.charAt(i))) {
                    result.append(Text.charAt(i));
                }
            } else {
                // If it's not a letter (i.e., part of a number or some other text), add to the result
                result.append(currentChar);
            }
        }

        // Convert the StringBuilder to a String and return the result
        Text = result.toString();


        // check for consecutive operators
        String regex = "[\\+\\-\\*/]{2}";
        if (Text.matches(".*" + regex + ".*")) {
            Ans = false;
            return Ans;
        }

        //check if numbers are adjacent to parentheses
        for (int i = 0; i < Text.length(); i++) {
            char curr = Text.charAt(i);

            if (Character.isDigit(curr)) {
                // Check if number is directly adjacent to '(' or ')'
                if ((i > 0 && Text.charAt(i - 1) == ')') || (i < Text.length() - 1 && Text.charAt(i + 1) == '(')) {
                    return false;  // Invalid: number adjacent to '(' or ')'
                }
            }
        }

        // Remove "="
        Text = Text.substring(1);

        // Check if "(" and ")" have the same number
        int OpenCounter = 0;
        int CloseCounter = 0;

        for (int i = 0; i < Text.length(); i++) {
            char ch = Text.charAt(i);
            if (ch == '(') {
                OpenCounter++;
            } else if (ch == ')') {
                CloseCounter++;
            }
        }
        if (OpenCounter != CloseCounter) {
            Ans = false;
            return Ans;
        }

        // Check if parentheses are aligned
        int balance = 0;
        for (int i = 0; i < Text.length(); i++) {
            char c = Text.charAt(i);
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                balance--;
            }
            if (balance < 0) {
                return false;
            }
        }
        if (balance != 0) {
            Ans = false;
            return Ans;
        }

        // Remove parentheses
        Text = Text.replaceAll("\\(", "");
        Text = Text.replaceAll("\\)", "");

        //check if empty
        if (Text.isEmpty()) {
            Ans = false;
            return Ans;
        }

        // Check if the first character is an operator
        if ((Text.charAt(0) == '+') || (Text.charAt(0) == '*') || (Text.charAt(0) == '/')) {
            Ans = false;
            return Ans;
        }

        boolean isNegative = Text.charAt(0) == '-';

        String operators = "+-*/";
        StringBuilder currentNum = new StringBuilder();

        for (int i = 0; i < Text.length(); i++) {
            char ch = Text.charAt(i);

            // If ch is an operator, check the number before it
            if (operators.indexOf(ch) != -1) {
                // If there's a current number, check if it's valid
                if (!currentNum.isEmpty() && !IsNumber(currentNum.toString())) {
                    Ans = false;  // Invalid number found
                    return Ans;
                }

                // Reset current number and negative flag
                currentNum.setLength(0);
                isNegative = false; // Reset the negative flag
            } else {
                // Check for a negative sign before a number
                if (ch == '-' && (currentNum.charAt(0) == '-' || operators.indexOf(Text.charAt(i - 1)) != -1)) {
                    isNegative = true;
                    currentNum.append(ch); // Add the negative sign to the number
                    continue; // Skip this character, as it's part of the next number
                }

                // Add character to the current number
                currentNum.append(ch);
            }
        }

        // After the loop, check the last part of the number
        Ans = currentNum.length() < 0 || IsNumber(currentNum.toString());

        return Ans;
    }

    // Helper method to perform the operation based on the operator
    private static double performOperation(double left, double right, char operator) {
        return switch (operator) {
            case '+' -> left + right;
            case '-' -> left - right;
            case '*' -> left * right;
            case '/' -> {
                if (right == 0) throw new ArithmeticException("Division by zero");
                yield left / right;
            }
            default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
        };
    }

    public static int findLastOperator(String Form) {
        int Ans = -1;

        //check if any operators exist
        if (!(Form.contains("+") || Form.contains("*") || Form.contains("-") || Form.contains("/"))) {
            return Ans;
        }


        // Remove "="
        if (Form.charAt(0) == '=') {
            Form = Form.substring(1);
        }
        double ParantCounter = 0;
        double smallValue = 0.25;
        double largeValue = 0.5;
        ArrayList<Integer> indexes = new ArrayList<>();
        ArrayList<Double> values = new ArrayList<>();


        for (int i = 0; i < Form.length(); i++) {
            char currentChar = Form.charAt(i);

            // Check for opening parenthesis
            if (currentChar == '(') {
                ParantCounter++;
            }
            // Check for closing parenthesis
            else if (currentChar == ')') {
                ParantCounter--;
            }

            // Check for operators (+, -, *, /)
            else if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/') {
                // Add the current index to the indices ArrayList
                indexes.add(i);

                // Calculate the value to be added to the values ArrayList
                double value;
                if (currentChar == '+' || currentChar == '-') {
                    value = ParantCounter + smallValue;
                } else {
                    value = ParantCounter + largeValue;
                }

                // Add the calculated value to the values ArrayList
                values.add(value);


            }
        }

        // FIND INDEX OF SMALLEST VALUE:

        // Initialize the index of the smallest value
        int smallestIndex = -1;

        // Initialize a variable to track the smallest value (start with a very large number)
        double smallestValue = Double.MAX_VALUE;

        // Loop through the values ArrayList
        int i = 0;
        int j = 0;
        for (i = 0; i < values.size(); i++) {
            double currentValue = values.get(i);

            // If a smaller value is found, or it's equal but later, update the smallest
            if (currentValue <= smallestValue) {
                smallestValue = currentValue;
                smallestIndex = i;
                j = i;
            }
        }

        Ans = indexes.get(j);
        return Ans;
    }

    public static Double computeForm(String form) {
        if (!IsForm(form)) {
            return -1.0;
        }

        // Check if starts with "="
        if (form.charAt(0) == '=') {
            form = form.substring(1);
        }

        //check if expression is parentheses
        if (form.charAt(0) == '(' && form.charAt(form.length() - 1) == ')') {
            // Remove the first and last characters (parentheses)
            //check if result is valid
            if (IsForm('=' + form.substring(1, form.length() - 1))) {
                form = form.substring(1, form.length() - 1);
            }
        }

        // Stopping point: if the formula is just a number, parse and return it
        if (IsNumber(form)) {
            return Double.parseDouble(form);
        }

        // Find the last operator in the formula
        int operatorIndex = findLastOperator(form);

        //split formula
        String leftFormula = form.substring(0, operatorIndex);
        String rightFormula = form.substring(operatorIndex + 1);

        //append "="
        leftFormula = '=' + leftFormula;
        rightFormula = '=' + rightFormula;

        // Recursively calculate the left and right formulas
        double leftValue = computeForm(leftFormula);
        double rightValue = computeForm(rightFormula);

        char operator = form.charAt(operatorIndex);
        // Calculate the result of this operator
        double result = performOperation(leftValue, rightValue, operator);
        return result;
    }

    @Override
    public int getOrder() {
        return order;  // Return the actual value of the 'order' field
    }

    @Override
    public void setOrder(int t) {
        order = t;

    }

    //@Override
    @Override
    public String toString() {
        if (type == Ex2Utils.ERR_FORM_FORMAT) {
            return Ex2Utils.ERR_FORM;  // Error: Invalid Formula Format
        } else if (type == Ex2Utils.ERR_CYCLE_FORM) {
            return Ex2Utils.ERR_CYCLE;  // Error: Circular Dependency
        } else if (type == -1) {
            return Ex2Utils.ERR_FORM;  // For any other error types
        }
        return line;  // Default to the cell's data if no error
    }

    @Override
    public String getData() {
        return OriginalLine;
    }

    @Override
    public void setData(String s) {
        line = s;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int t) {
        type = t;
    }


}
