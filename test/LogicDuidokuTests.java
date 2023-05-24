import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class LogicDuidokuTests {
    public LogicDuidokuTests() {

    }
    @Test
    public void insert() {
        int[][] test;
        LogicDuidoku g1 = new LogicDuidoku();
        assertFalse(g1.insert(1,1,10));
        assertTrue(g1.insert(1,1,2));
        assertFalse(g1.insert(1,2,2));
        test=g1.getBoard();
        assertEquals(2,test[0][0], 0.001);
    }


    @Test
    public void choices() {
        int[][] array={{2,4,0,1},
                {1,0,2,3},
                {0,1,3,0},
                {4,3,1,2}};
        boolean[] test={false,false,false,true};
        LogicDuidoku g1 = new LogicDuidoku(array);
        Assert.assertArrayEquals(test,g1.choices(3,4));
    }

    @Test
    public void isOver() {
        int[][] array ={{2,4,0,1},
                {1,0,2,3},
                {0,1,3,4},
                {4,3,1,2}};
        LogicDuidoku g1 = new LogicDuidoku(array);
        assertTrue(g1.isOver());
        int[][] array1={{2,4,0,1},
                {1,0,2,3},
                {0,1,3,0},
                {4,3,1,2}};
        LogicDuidoku g2 = new LogicDuidoku(array1);
        assertFalse(g2.isOver());
    }

    @Test
    public void computerPlayer() {
        int[][] array1={{2,4,0,1},
                {1,0,2,3},
                {0,1,3,0},
                {4,3,1,2}};
        int[][] array2={{2,4,0,1},
                {1,0,2,3},
                {0,1,3,4},
                {4,3,1,2}};
        LogicDuidoku g1 = new LogicDuidoku(array1);
        LogicDuidokuPlayed result= g1.computerPlayer();
        assertEquals(2,result.getX());
        assertEquals(3,result.getY());
        assertEquals(4,result.getValue());
        assertTrue(result.isPlayed());
        Assert.assertArrayEquals(array2,g1.getBoard());
        LogicDuidokuPlayed result2=g1.computerPlayer();
        assertFalse(result2.isPlayed());
    }
    @Test
    public void getElement(){
        int[][] array={{2,4,0,1},
                {1,0,2,3},
                {0,1,3,0},
                {4,3,1,2}};
        LogicDuidoku g1 = new LogicDuidoku(array);
        assertEquals(2,g1.getElement(0,0));
    }
}
