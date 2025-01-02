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
        assertTrue(Ex2.IsText("123"));
        assertTrue(Ex2.IsText("-123.45"));
        assertTrue(Ex2.IsText("=2*3-4/5"));
        assertTrue(Ex2.IsText("=-1+(2*3)"));
    }

    @Test
    public void testIsText_InvalidText() {
        assertFalse(Ex2.IsText("abc"));
        assertFalse(Ex2.IsText("123abc"));
        assertFalse(Ex2.IsText("++123"));
        assertFalse(Ex2.IsText("123--"));
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
        }



