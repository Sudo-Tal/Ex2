import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalcTests {

    private Ex2Sheet sheet;

    @BeforeEach
    public void setUp() {
        // Initialize the Ex2Sheet with a 3x3 grid for the tests
        sheet = new Ex2Sheet(3, 3);
    }

    @Test
    public void testEvalBasicValue() {
        // Test for simple value
        sheet.set(0, 0, "5");  // Cell A0 = "5"

        String result = sheet.eval(0, 0);

        assertEquals("5.0", result, "Expected the result to be 5.0");
    }

    @Test
    public void testEvalSimpleAddition() {
        // Test for simple addition formula
        sheet.set(0, 0, "5");  // Cell A0 = "5"
        sheet.set(0, 1, "3");  // Cell A1 = "3"
        sheet.set(2, 0, "A0+A1");  // Cell A2 = "A0 + A1"

        String result = sheet.eval(2, 0);

        assertEquals("8.0", result, "Expected the result to be 8.0");
    }

    @Test
    public void testTemp() {
        // Test for simple addition formula
        sheet.set(0, 0, "banana");  // Cell A0 = "5"
        sheet.set(1, 0, "=a0+5");  // Cell B1 = "3"

        String result = sheet.eval(1, 0);

        assertEquals("ERR_FORM!", result, "Expected the result to be ERROR");
    }

    @Test
    public void testSmall() {
        // Test for simple addition formula
        sheet.set(0, 0, "=b0");  // Cell A0 = "5"
        sheet.set(1, 0, "");  // Cell B1 = ""

        String result = sheet.eval(0, 0);

        assertEquals("ERR_FORM!", result, "Expected the result to be ERROR");
    }

    @Test
    public void testEvalComplexFormula() {
        // Test for nested formulas
        sheet.set(0, 0, "5");  // Cell A0 = "5"
        sheet.set(0, 1, "3");  // Cell A1 = "3"
        sheet.set(1, 0, "10"); // Cell B0 = "10"
        sheet.set(1, 1, "2");  // Cell B1 = "2"
        sheet.set(2, 0, "=A0+A1");  // Cell c0 = "A0 + A1" -> 5 + 3 = 8
        sheet.set(2, 1, "=B0+B1");  // Cell c1 = "B0 + B1" -> 10 + 2 = 12
        sheet.set(2, 2, "=C0+c1");  // Cell c2 = "A2 + B2" -> 8 + 12 = 20

        String result = sheet.eval(2, 2);

        assertEquals("20.0", result, "Expected the result to be 20.0");
    }

    @Test
    public void testEvalCycle() {
        // Test for simple addition formula
        sheet.set(0, 0, "=a0");  // Cell A0 = "5"
        sheet.set(0, 1, "3");  // Cell A1 = "3"
        sheet.set(2, 0, "A0+A1");  // Cell A2 = "A0 + A1"

        String result = sheet.eval(0, 0);

        assertEquals("ERR_CYCLE!", result, "Expected the result to be cycle");
    }

    @Test
    public void testDivisionByZero() {
        //test a division by zero
        sheet.set(0,0, "=5/0");
        String result = sheet.eval(0,0);

        assertEquals("Infinity",result, "Expected the result to be Infinity");

    }

    @Test
    public void returnDouble() {
    sheet.set(0,0, "5");
    String result = sheet.eval(0,0);
    assertEquals("5.0", result);
    }


    @Test
    public void testMinusCell() {
    //test a minus to a cell

    sheet.set (0,0,"=25+30");
    sheet.set (0,1 , "=-A0");
    String result = sheet.eval(0,1);

    assertEquals("-55.0", result, "Expected a Minus 55");
    }

    @Test
    public void testEvalCycleComplexReset() {
        // Test for simple addition formula
        sheet.set(0, 0, "=5");
        sheet.set(0, 1, "=a0");
        sheet.set(0, 2, "=a1");

        String result = sheet.eval(0, 2);

        assertEquals("5.0", result, "Expected the result to be 5");

        sheet.set(0,0, "=a2"); //set cycle
        assertEquals("ERR_CYCLE!", sheet.eval(0, 0), "Expected the result to be Cycle");
        assertEquals("ERR_CYCLE!", sheet.eval(0, 1), "Expected the result to be Cycle");
        assertEquals("ERR_CYCLE!", sheet.eval(0, 2), "Expected the result to be Cycle");

    }


    @Test
    public void test1A1Issue() {
        // Test for simple addition formula
        sheet.set(0, 0, "=1A1");  // Cell A0 = "1A1"
        sheet.set(0, 1, "5");  // Cell A1 = "5"
        if (!SCell.IsForm("1A1")) {
            System.out.println("IT WORK");
        };

        String result = sheet.eval(0, 0);

        assertEquals("ERR_FORM!", result, "Expected the result to be ERROR");
    }

}
