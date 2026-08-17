import org.junit.Test;
import static org.junit.Assert.*;

public class ClothingTest {

    @Test
    public void testGetSize() {
        Clothing clothing = new Clothing("123", "Shirt", 10, 19.99, "Medium", "Blue");
        assertEquals("Medium", clothing.getSize());
    }

    @Test
    public void testGetColour() {
        Clothing clothing = new Clothing("456", "Jeans", 5, 29.99, "Large", "Black");
        assertEquals("Black", clothing.getColour());
    }

    @Test
    public void testSetSize() {
        Clothing clothing = new Clothing("789", "Dress", 15, 49.99, "Small", "Red");
        clothing.setSize("X-Small");
        assertEquals("X-Small", clothing.getSize());
    }

    @Test
    public void testSetColour() {
        Clothing clothing = new Clothing("101", "Jacket", 8, 79.99, "Large", "Green");
        clothing.setColour("Navy");
        assertEquals("Navy", clothing.getColour());
    }

    @Test
    public void testInheritedFields() {
        Clothing clothing = new Clothing("202", "Socks", 3, 9.99, "One Size", "White");
        assertEquals("202", clothing.getProductID());
        assertEquals("Socks", clothing.getProductName());
        assertEquals(3, clothing.getNumberOfItems());
        assertEquals(9.99, clothing.getPrice(), 0.001);
    }
}
