import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import static org.junit.Assert.*;

public class WestminsterShoppingManagerTest {

    @Test
    public void testAddNewProduct() {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        Clothing clothing = new Clothing("123", "Shirt", 10, 19.99, "Medium", "Blue");

        shoppingManager.addNewProduct(clothing);
        assertEquals(1, shoppingManager.getProductList().size());
    }

    @Test
    public void testDeleteProduct() {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        Clothing clothing = new Clothing("123", "Shirt", 10, 19.99, "Medium", "Blue");

        shoppingManager.addNewProduct(clothing);
        shoppingManager.deleteProduct("123");
        assertEquals(0, shoppingManager.getProductList().size());
    }

    @Test
    public void testPrintProduct() {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        Clothing clothing = new Clothing("123", "Shirt", 10, 19.99, "Medium", "Blue");

        shoppingManager.addNewProduct(clothing);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        shoppingManager.printProduct();

        String expectedOutput = "\nProduct ID: 123\nProduct Name: Shirt\nAvailable: 10\nPrice: $19.99\nType: Clothing\nSize: Medium\nColour: Blue\n-------------------------------------\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testFile() {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        Clothing clothing = new Clothing("123", "Shirt", 10, 19.99, "Medium", "Blue");

        shoppingManager.addNewProduct(clothing);
        shoppingManager.file();

    }

    @Test
    public void testLoadTextFile() {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        shoppingManager.loadTextFile();


    }

    @Test
    public void testGUI() {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        shoppingManager.GUI();

    }
}

