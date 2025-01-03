import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CellEntryTests {

    @Test
    public void testValidCellString() {
        // Test valid cell strings
        CellEntry cell = new CellEntry("A1");
        assertTrue(cell.isValid(), "Cell A1 should be valid");
        assertEquals(0, cell.getX(), "X value for A1 should be 0");
        assertEquals(1, cell.getY(), "Y value for A1 should be 1");
        assertEquals("A1", cell.toString(), "String representation of A1 should be 'A1'");
    }

    @Test
    public void testValidCellStringWithDoubleDigitY() {
        // Test valid cell strings with two digits in Y
        CellEntry cell = new CellEntry("B12");
        assertTrue(cell.isValid(), "Cell B12 should be valid");
        assertEquals(1, cell.getX(), "X value for B12 should be 1");
        assertEquals(12, cell.getY(), "Y value for B12 should be 12");
        assertEquals("B12", cell.toString(), "String representation of B12 should be 'B12'");
    }

    @Test
    public void testInvalidCellStringWithNonAlphabet() {
        // Test invalid cell string with non-alphabet character
        CellEntry cell = new CellEntry("1A1");
        assertFalse(cell.isValid(), "Cell 1A1 should be invalid");
        assertEquals(Ex2Utils.ERR, cell.getX(), "X value for invalid cell should be ERR");
        assertEquals(Ex2Utils.ERR, cell.getY(), "Y value for invalid cell should be ERR");
        assertEquals("Wrong Format", cell.toString(), "String representation for invalid cell should be 'Wrong Format'");
    }

    @Test
    public void testInvalidCellStringWithOutOfBoundsY() {
        // Test cell string with Y value greater than 99
        CellEntry cell = new CellEntry("A100");
        assertFalse(cell.isValid(), "Cell A100 should be invalid");
        assertEquals(Ex2Utils.ERR, cell.getX(), "X value for invalid cell should be ERR");
        assertEquals(Ex2Utils.ERR, cell.getY(), "Y value for invalid cell should be ERR");
        assertEquals("Wrong Format", cell.toString(), "String representation for invalid cell should be 'Wrong Format'");
    }

    @Test
    public void testInvalidCellStringWithShortY() {
        // Test cell string with a missing digit in Y value
        CellEntry cell = new CellEntry("A");
        assertFalse(cell.isValid(), "Cell A should be invalid");
        assertEquals(Ex2Utils.ERR, cell.getX(), "X value for invalid cell should be ERR");
        assertEquals(Ex2Utils.ERR, cell.getY(), "Y value for invalid cell should be ERR");
        assertEquals("Wrong Format", cell.toString(), "String representation for invalid cell should be 'Wrong Format'");
    }

    @Test
    public void testInvalidCellStringWithMultipleDigitsInX() {
        // Test invalid cell string with multiple characters in X
        CellEntry cell = new CellEntry("AA1");
        assertFalse(cell.isValid(), "Cell AA1 should be invalid");
        assertEquals(Ex2Utils.ERR, cell.getX(), "X value for invalid cell should be ERR");
        assertEquals(Ex2Utils.ERR, cell.getY(), "Y value for invalid cell should be ERR");
        assertEquals("Wrong Format", cell.toString(), "String representation for invalid cell should be 'Wrong Format'");
    }

    @Test
    public void testValidCellStringWithLastCharacter() {
        // Test valid cell string with Z and a valid Y
        CellEntry cell = new CellEntry("Z99");
        assertTrue(cell.isValid(), "Cell Z99 should be valid");
        assertEquals(25, cell.getX(), "X value for Z99 should be 25");
        assertEquals(99, cell.getY(), "Y value for Z99 should be 99");
        assertEquals("Z99", cell.toString(), "String representation of Z99 should be 'Z99'");
    }

    @Test
    public void testInvalidCellStringWithOutOfBoundsYLow() {
        // Test cell string with negative Y value
        CellEntry cell = new CellEntry("B-1");
        assertFalse(cell.isValid(), "Cell B-1 should be invalid");
        assertEquals(Ex2Utils.ERR, cell.getX(), "X value for invalid cell should be ERR");
        assertEquals(Ex2Utils.ERR, cell.getY(), "Y value for invalid cell should be ERR");
        assertEquals("Wrong Format", cell.toString(), "String representation for invalid cell should be 'Wrong Format'");
    }
}
