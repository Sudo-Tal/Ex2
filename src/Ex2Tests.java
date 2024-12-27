import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IsFormTest {

    // Test case: Valid formula with parentheses
    @Test
    void testValidFormula() {
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
        assertTrue(Ex2.IsForm("=3+(-5)*2"));
        assertTrue(Ex2.IsForm("=-(3+5)"));
        assertTrue(Ex2.IsForm("=3.14+5.2+7.0*2/4"));
        assertTrue(Ex2.IsForm("=3.0+5+7/2.0"));


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
        assertFalse(Ex2.IsForm("=+3"));
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
}
