import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

//    private final SILab2 testedClassObj = new SILab2();

    @Test
    public void Every_Statement_Test(){

        //Nodes: 1, 2
        RuntimeException ex1 = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(null, "0123456789012345");
        });
        assertTrue(ex1.getMessage().contains("allItems list can't be null"));


        //Nodes: 3-5, 8-11, 13-17, 21
        Item item2 = new Item("P1", 5, 350, 0.1);
        List<Item> items2 = List.of(item2);
        String cardNumber2 = "1234567890123456";
        assertEquals(-30 + 350 * (1 - 0.1) * 5, SILab2.checkCart(items2, cardNumber2));


        //Nodes: 3-7
//        Item item3 = new Item("", 1, 100, 0);
        Item item3 = new Item(null, 1, 100, 0);
        List<Item> items3 = List.of(item3);
        RuntimeException ex3 = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(items3, "0123456789012345");
        });
        assertTrue(ex3.getMessage().contains("Invalid item"));


        //Nodes: 3-5, 12, 20
        Item item4 = new Item("P2", 3, 200, 0);
        List<Item> items4 = List.of(item4);
        RuntimeException ex4 = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(items4, "123");
        });
        assertTrue(ex4.getMessage().contains("Invalid card number"));


        //Nodes: 3-5, 8-11, 13-19
//        Item item4 = new Item("Item4", 1, 100, 0);
        Item item5 = new Item("P3", 3, 200, 0);
        List<Item> items5 = List.of(item5);
        RuntimeException ex5 = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(items5, "AB34567890123456");
        });
        assertTrue(ex5.getMessage().contains("Invalid character in card number"));


        //ako treba da se vrati / proveri vrednosta na cenata koga nema discount, togash bi imale 6 testa
        //Nodes: 3-5, 8-10, 12-17, 21
//        Item item6 = new Item("Item2", 3, 200, 0);
//        List<Item> items6 = List.of(item6);
//        String cardNumber6 = "0000111122223333";
//        double expected = 200 * 3;
//        assertEquals(expected, SILab2.checkCart(items6, cardNumber6));

    }

    //...

    private double returnValue(String name, int quantity, int price, double discount) {
        Item item = new Item(name, quantity, price, discount);
        return SILab2.checkCart(List.of(item), "1234567890123456");
    }

    //TC1: F F F -> price <= 300, discount == 0, quantity <= 10 -> edinstven sluchaj koga nema odzemanje so 30
    @Test
    public void caseFFF() {
        double result = returnValue("P1", 9, 299, 0);
        assertEquals(299 * 9, result);
    }

    //TC2: T F F -> samo quantity > 10 e T
    @Test
    public void caseTFF() {
        double result = returnValue("P2", 11, 299, 0);
        assertEquals(-30 + 299 * 11, result);
    }

    //TC3: F T F -> samo price > 300 е T
    @Test
    public void caseFTF() {
        double result = returnValue("P3", 9, 301, 0);
        assertEquals(-30 + 301 * 9, result);
    }

    //TC4: F F T -> samo discount > 0 e T
    @Test
    public void caseFFT() {
        double result = returnValue("P4", 9, 299, 0.3);
        assertEquals(-30 + 299 * (1 - 0.3) * 9, result);
    }

    //TC5: T T F -> samo discount > 0 е F
    @Test
    public void caseTTF() {
        double result = returnValue("P5", 11, 301, 0);
        assertEquals(-30 + 301 * 11, result);
    }

    //TC6: T F T -> samo price > 300 е F
    @Test
    public void caseTFT() {
        double result = returnValue("P6", 11, 299, 0.3);
        assertEquals(-30 + 299 * (1 - 0.3) * 11, result);
    }

    //TC7: F T T -> samo quantity > 10 e F
    @Test
    public void caseFTT() {
        double result = returnValue("P7", 9, 301, 0.3);
        assertEquals(-30 + 301 * (1 - 0.3) * 9, result);
    }

    //TC8: T T T -> site делови se F
    @Test
    public void caseTTT() {
        double result = returnValue("P8", 11, 301, 0.3);
        assertEquals(-30 + 301 * (1 - 0.3) * 11, result);
    }

}
