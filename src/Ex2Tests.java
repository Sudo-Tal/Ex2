import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Ex2Tests {

    // -------------------- Test IsNumber method --------------------
    @Test
    public void testIsNumber_ValidNumbers() {
        assertTrue(Ex2.IsNumber("123"));
        assertTrue(Ex2.IsNumber("0"));
        assertTrue(Ex2.IsNumber("-123"));
        assertTrue(Ex2.IsNumber("123.45"));
        assertTrue(Ex2.IsNumber("-123.45"));
        assertTrue(Ex2.IsNumber("0.0001"));
        assertTrue(Ex2.IsNumber("3.14"));
        assertTrue(Ex2.IsNumber("1e3"));
        assertTrue(Ex2.IsNumber("+1e3"));

    }

    @Test
    public void testIsNumber_InvalidNumbers() {
        assertFalse(Ex2.IsNumber("abc"));
        assertFalse(Ex2.IsNumber("123abc"));
        assertFalse(Ex2.IsNumber("1.2.3"));
        assertFalse(Ex2.IsNumber("1e"));
        assertFalse(Ex2.IsNumber("++123"));
        assertFalse(Ex2.IsNumber("123--"));
    }

    @Test
    public void testIsNumber_EmptyOrNull() {
        assertFalse(Ex2.IsNumber(""));
        assertFalse(Ex2.IsNumber(null));
    }

    // -------------------- Test IsText method --------------------
    @Test
    public void testIsText_ValidText() {
        assertFalse(Ex2.IsText("123"));
        assertFalse(Ex2.IsText("-123.45"));
        assertTrue(Ex2.IsText("2*3-4/5"));
        assertTrue(Ex2.IsText("-1+(2*3)"));
    }

    @Test
    public void testIsText_InvalidText() {
        assertFalse(Ex2.IsText("=2*3-4/5"));
        assertFalse(Ex2.IsText("=-1+(2*3)"));
        assertTrue(Ex2.IsText("abc"));
        assertTrue(Ex2.IsText("123abc"));
        assertTrue(Ex2.IsText("++123"));
        assertTrue(Ex2.IsText("123--"));
        assertFalse(Ex2.IsText("=++123"));
        assertFalse(Ex2.IsText("=123++"));
    }

    // -------------------- Test IsForm method --------------------
    @Test
    public void testIsForm_ValidForms() {
        assertTrue(Ex2.IsForm("=2*3-4/5"));
        assertTrue(Ex2.IsForm("=-1+(2*3)"));
        assertTrue(Ex2.IsForm("=2*(3+4)"));
        assertTrue(Ex2.IsForm("=5*(6/7)+8"));
        assertTrue(Ex2.IsForm("=1+2*3/(4-5)"));
        assertTrue(Ex2.IsForm("=(5+3)"));
        assertTrue(Ex2.IsForm("=1"));
        assertTrue(Ex2.IsForm("=1+2*2"));
        assertTrue(Ex2.IsForm("=(2)"));
        assertTrue(Ex2.IsForm("=(1+2)*2"));
        assertTrue(Ex2.IsForm("=3+5"));
        assertTrue(Ex2.IsForm("=3+5*7/2"));
        assertTrue(Ex2.IsForm("=3.5+5.5-7.5*2/2"));
        assertTrue(Ex2.IsForm("=(3+5)*2"));
        assertTrue(Ex2.IsForm("=(3+5)*(2+4)"));
        assertTrue(Ex2.IsForm("=((3+5)*2)+4"));
        assertTrue(Ex2.IsForm("=-3+5"));
        assertTrue(Ex2.IsForm("=5*(-3)"));
        assertTrue(Ex2.IsForm("=3+(-5)*2"));
        assertTrue(Ex2.IsForm("=-(3+5)"));
        assertTrue(Ex2.IsForm("=3.14+5.2+7.0*2/4"));
        assertTrue(Ex2.IsForm("=3.0+5+7/2.0"));
        assertTrue(Ex2.IsForm("=2.0"));
    }

    @Test
    public void testIsForm_InvalidForms() {
        assertFalse(Ex2.IsForm("=++123"));
        assertFalse(Ex2.IsForm("=123++"));
        assertFalse(Ex2.IsForm("=123/"));
        assertFalse(Ex2.IsForm("=+/123"));
        assertFalse(Ex2.IsForm("=123--"));
        assertFalse(Ex2.IsForm("=3+*4"));
        assertFalse(Ex2.IsForm("=3/4-"));
        assertFalse(Ex2.IsForm("=3+(4-5"));
        assertFalse(Ex2.IsForm("=3+4)"));
        assertFalse(Ex2.IsForm("=(3+4"));
        assertFalse(Ex2.IsForm("=3.14.15"));
        assertFalse(Ex2.IsForm("=()"));
        assertFalse(Ex2.IsForm(" =3+5"));
        assertFalse(Ex2.IsForm(null));
        assertFalse(Ex2.IsForm("3+5"));
        assertFalse(Ex2.IsForm("3*5"));
        assertFalse(Ex2.IsForm("=3+5)"));
        assertFalse(Ex2.IsForm("=3+(5"));
        assertFalse(Ex2.IsForm("=3+)(5"));
        assertFalse(Ex2.IsForm("=3+5)"));
        assertFalse(Ex2.IsForm("=3+(5"));
        assertFalse(Ex2.IsForm("=(3+5"));
        assertFalse(Ex2.IsForm("=)3+5("));
        assertFalse(Ex2.IsForm(""));
        assertFalse(Ex2.IsForm("=(3+5"));
        assertFalse(Ex2.IsForm("=(3+5))"));
        assertFalse(Ex2.IsForm("=*3"));
        assertFalse(Ex2.IsForm("=3+"));
        assertFalse(Ex2.IsForm("=3++5"));
        assertFalse(Ex2.IsForm("=3**5"));
        assertFalse(Ex2.IsForm("=3+5a"));
        assertFalse(Ex2.IsForm("=3+5b*2"));
        assertFalse(Ex2.IsForm("=3+5#2"));
        assertFalse(Ex2.IsForm("=3+5!2"));
        assertFalse(Ex2.IsForm("=3+5.5.5"));
        assertFalse(Ex2.IsForm("=3+5.5*2+5..5"));
        assertFalse(Ex2.IsForm("5"));

    }

    @Test
    public void testIsForm_EmptyOrNull() {
        assertFalse(Ex2.IsForm(""));
        assertFalse(Ex2.IsForm(null));
    }

    @Test
    public void testIsForm_ParenthesesBalance() {
        assertTrue(Ex2.IsForm("=3+(4-5)"));
        assertFalse(Ex2.IsForm("=3+(4-5"));
        assertFalse(Ex2.IsForm("=3+4)"));
        assertTrue(Ex2.IsForm("=((3+4)*5)/2"));
        assertFalse(Ex2.IsForm("=(((3+4)*5)/2"));
        assertFalse(Ex2.IsForm("=()"));
    }

    @Test
    public void testIsForm_ConsecutiveOperators() {
        assertFalse(Ex2.IsForm("=3++4"));
        assertFalse(Ex2.IsForm("=3--4"));
        assertFalse(Ex2.IsForm("=3**4"));
        assertFalse(Ex2.IsForm("=3//4"));
        assertFalse(Ex2.IsForm("=3//4"));
        assertTrue(Ex2.IsForm("=3+4"));
        assertTrue(Ex2.IsForm("=3-4"));
    }

    @Test
    public void testIsForm_OperatorAtStart() {
        assertFalse(Ex2.IsForm("=+123"));
        assertTrue(Ex2.IsForm("=-123"));
        assertTrue(Ex2.IsForm("=123"));
    }

    @Test
    public void testIsForm_LeadingTrailingSpaces() {
        assertTrue(Ex2.IsForm("=  123  "));
        assertTrue(Ex2.IsForm("=  (3+4) * 5  "));
        assertFalse(Ex2.IsForm("  =3+4 "));
        assertFalse(Ex2.IsForm(" =3+4"));
    }

    @Test
    public void testIsForm_MultipleParentheses() {
        assertTrue(Ex2.IsForm("=3+(4*(5+6))"));
        assertFalse(Ex2.IsForm("=3+(4*(5+6)"));
        assertFalse(Ex2.IsForm("=3+((4*5)"));
        assertTrue(Ex2.IsForm("=((2+3)*5)/7"));
    }



    // -------------------- Test FindLastOperator method --------------------
    @Test
    public void FindLastOpTest() {
   assertEquals(1 , Ex2.findLastOperator("=5-3"));
   assertEquals(5 , Ex2.findLastOperator("=(5+3)-(2*8)"));

                // Test case 1: Simple expression with operators
                String expression1 = "=3+5*2-8";
                assertEquals(5, Ex2.findLastOperator(expression1));

                // Test case 2: Expression with parentheses
                String expression2 = "(3+5)*2-8";
                assertEquals(7, Ex2.findLastOperator(expression2));

                // Test case 3: Expression starting with "=" character (should be ignored)
                String expression3 = "=3+5*2-8";
                assertEquals(5, Ex2.findLastOperator(expression3));

                // Test case 4: Expression with no operators
                String expression4 = "42";
                assertEquals(-1, Ex2.findLastOperator(expression4)); // No operator should return -1

                // Test case 5: Expression with multiple parentheses and different operator precedence
                String expression5 = "(3+5)*2-(8/4)";
                assertEquals(7, Ex2.findLastOperator(expression5)); // The last operator should be '-'

                // Test case 6: Expression with nested parentheses
                String expression6 = "((3+5)*2)-8";
                assertEquals(9, Ex2.findLastOperator(expression6)); // The last operator is '-'

                // Test case 7: Expression with only a single operator at the end
                String expression7 = "2+2";
                assertEquals(1, Ex2.findLastOperator(expression7)); // The last operator should be '+'

                // Test case 8: Empty string (edge case)
                String expression8 = "";
                assertEquals(-1, Ex2.findLastOperator(expression8)); // No operator, should return -1

            }


    // -------------------- Test FindLastOperator method --------------------
        @Test
        public void computeFormTest() {
        assertEquals(7, Ex2.computeForm("=5+2"));

                    // Test case 1: Simple formula with addition
                    String formula1 = "=3+5";
                    double result1 = Ex2.computeForm(formula1);
                    assertEquals(8.0, result1, "Expected result for 3+5 is 8");

                    // Test case 2: Formula with subtraction
                    String formula2 = "=10-2";
                    double result2 = Ex2.computeForm(formula2);
                    assertEquals(8.0, result2, "Expected result for 10-2 is 8");

                    // Test case 3: Formula with multiplication
                    String formula3 = "=4*3";
                    double result3 = Ex2.computeForm(formula3);
                    assertEquals(12.0, result3, "Expected result for 4*3 is 12");

                    // Test case 4: Formula with division
                    String formula4 = "=8/4";
                    double result4 = Ex2.computeForm(formula4);
                    assertEquals(2.0, result4, "Expected result for 8/4 is 2");

                    // Test case 5: Formula with multiple operators, respecting operator precedence
                    String formula5 = "=3+5*2";
                    double result5 = Ex2.computeForm(formula5);
                    assertEquals(13.0, result5, "Expected result for 3+5*2 is 13");

                    // Test case 6: Formula with parentheses, ensuring correct order of operations
                    String formula6 = "=(3+5)*2";
                    double result6 = Ex2.computeForm(formula6);
                    assertEquals(16.0, result6, "Expected result for (3+5)*2 is 16");

                    // Test case 7: Formula with a mix of operators
                    String formula7 = "=3+5*2-8/4";
                    double result7 = Ex2.computeForm(formula7);
                    assertEquals(11.0, result7, "Expected result for 3+5*2-8/4 is 11");

                    // Test case 8: Edge case: Division by zero
                    String formula8 = "=3/0";
                    assertThrows(ArithmeticException.class, () -> Ex2.computeForm(formula8),
                            "Expected ArithmeticException for division by zero");

                    // Test case 9: Formula with no operators, just a number
                    String formula9 = "=42";
                    double result9 = Ex2.computeForm(formula9);
                    assertEquals(42.0, result9, "Expected result for single number 42 is 42");

                    // Test case 10: Invalid formula (e.g., no operators but has characters)
                    String formula10 = "=3++5";
                    assertEquals(-1, Ex2.computeForm(formula10));

                    // Test case 11: A long formula with multiple operations
                    String formula11 = "=2+3*4-6/2+5*3";
                    double result11 = Ex2.computeForm(formula11);
                    assertEquals(26.0, result11, "Expected result for 2+3*4-6/2+5*3 is 21");

                    // Test case 12: Nested parentheses
                    String formula12 = "=((2+3)*4)-(6/2)";
                    double result12 = Ex2.computeForm(formula12);
                    assertEquals(17.0, result12, "Expected result for ((2+3)*4)-(6/2) is 14");

                    // Test case 13: Formula with negative numbers
                    String formula13 = "=-3+5";
                    double result13 = Ex2.computeForm(formula13);
                    assertEquals(2.0, result13, "Expected result for -3+5 is 2");

                    // Test case 14: Formula with floating-point numbers
                    String formula14 = "=3.5+2.5";
                    double result14 = Ex2.computeForm(formula14);
                    assertEquals(6.0, result14, "Expected result for 3.5+2.5 is 6");
                }
            }






