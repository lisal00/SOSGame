import org.junit.*;

import static org.junit.Assert.assertEquals;

public class Testing {
    @Test
    public void addStringTest(){
        var add = new Main();
        assertEquals("dog Cat", add.addString("dog", " Cat"));
    }

    @Test
    public void addIntTest(){
        var add = new Main();
        assertEquals(10, add.addInt(5, 5));
    }
}
