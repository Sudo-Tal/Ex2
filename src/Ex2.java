public class Ex2 {



        public static boolean IsNumber(String Text) {
            boolean Ans = true;

            try {
                double result = Double.parseDouble(Text);
            }
            catch (NumberFormatException e) {
                Ans = false;
            }
            return Ans;
        }

        public static boolean IsText(String Text) {
        boolean Ans = true;
            if (!IsNumber(Text) && !IsForm(Text)) {
        Ans= false;
            }
        return Ans;
        }

    public static boolean IsForm(String Text) {
           boolean Ans= false;

        //Check null
        if (Text==null) {
            return Ans;
        }

        //Check if Starts with =
            if (Text.charAt(0) != '=') {
                Ans = false;
                return Ans;
            }
            //remove "="
            Text = Text.substring(1);

            //check if "(" is ")" same value
            int OpenCounter = 0;
            int CloseCounter = 0;

            for (int i=0; i < Text.length(); i++) {
                char ch = Text.charAt(i);
                if (ch == '(') {
                    OpenCounter++;
                }
                else if (ch == ')') {
                    CloseCounter++;
                }
            }
            if (OpenCounter != CloseCounter) {
                Ans=false;
                return Ans;
        }

            //check if parentheses are aligned
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
            // If balance is wrong, missing opening/closing parentheses
            if (balance != 0) {
                Ans = false;
                return Ans;
            }


            //now the real fun begins...

            //REMOVE ALL PARENTHESES!
        Text = Text.replaceAll("\\(", "");
        Text = Text.replaceAll("\\)", "");

        String operators = "+-*/";

        //Split the formula and check each part
        StringBuilder currentNum = new StringBuilder();
        boolean isNegative = false; // Flag for negative sign


        for (int i =0; i < Text.length(); i++) {
            char ch = Text.charAt(i);

            //if ch is operator check the String before it
            if (operators.indexOf(ch) != -1) {
                if (!IsNumber(currentNum.toString())) {
                    Ans = false;
                    return Ans;
                }
                //Reset Number! collect chars again
                currentNum.setLength(0);
                isNegative = false; // Reset the negative flag

            } else {

                // Check for a negative sign before a number
                if (ch == '-' && (i == 0 || operators.indexOf(Text.charAt(i - 1)) != -1)) {
                    isNegative = true;
                    continue; // Skip this character, as it is part of the next number
                }

                //Build the number string
                currentNum.append(ch);
            }
        }

        //After loop a last number remains to check
        if (IsNumber(currentNum.toString())) {
            Ans=true;
            return Ans;
        }




        return Ans;
    }







    }
