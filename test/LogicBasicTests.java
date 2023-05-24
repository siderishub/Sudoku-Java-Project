import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;


public class LogicBasicTests {
    public LogicBasicTests() {

    }
    @Test
    public void insert() {
        int[][] array={{0,8,3,0,0,2,4,1,0},
                {2,0,4,0,0,5,0,0,0},
                {0,1,0,0,7,4,0,2,8},
                {3,0,0,4,9,0,1,5,0},
                {0,0,7,0,1,0,0,0,6},
                {9,0,0,7,5,3,0,8,0},
                {8,4,0,0,0,0,6,0,0},
                {5,0,0,0,4,0,0,3,1},
                {1,3,6,0,2,0,5,0,0}};
        int[][] test;
        LogicBasic g1 = new LogicBasic(array);
        assertTrue(g1.insert(1,1,7));
        assertTrue(g1.insert(1,1,7));
        assertFalse(g1.insert(1,1,10));
        assertFalse(g1.insert(1,1,8));
        test=g1.getBoard();
        assertEquals(7,test[0][0], 0.001);
    }

    @Test
    public void delete() {
        int[][] test;
        LogicBasic g1 = new LogicBasic();
        assertTrue(g1.delete(1,2));
        test=g1.getBoard();
        assertEquals(0,test[0][1], 0.001);
    }

    @Test
    public void full() {
        int[][] array = {{0, 8, 3, 0, 0, 2, 4, 1, 0},
                {2, 0, 4, 0, 0, 5, 0, 0, 0},
                {0, 1, 0, 0, 7, 4, 0, 2, 8},
                {3, 0, 0, 4, 9, 0, 1, 5, 0},
                {0, 0, 7, 0, 1, 0, 0, 0, 6},
                {9, 0, 0, 7, 5, 3, 0, 8, 0},
                {8, 4, 0, 0, 0, 0, 6, 0, 0},
                {5, 0, 0, 0, 4, 0, 0, 3, 1},
                {1, 3, 6, 0, 2, 0, 5, 0, 0}};
        LogicBasic g1 = new LogicBasic(array);
        assertFalse(g1.full());
        int[][] array2 = {{5,3,4,6,7,8,9,1,2},
                {6,7,2,1,9,5,3,4,8},
                {1,9,8,3,4,2,5,6,7},
                {8,5,9,7,6,1,4,2,3},
                {4,2,6,8,5,3,7,9,1},
                {7,1,3,9,2,4,8,5,6},
                {9,6,1,5,3,7,2,8,4},
                {2,8,7,4,1,9,6,3,5},
                {3,4,5,2,8,6,1,7,9}};
        LogicBasic g2 = new LogicBasic(array2);
        assertTrue(g2.full());
    }

    @Test
    public void choices() {
        int[][] array={{0,8,3,0,0,2,4,1,0},
                {2,0,4,0,0,5,0,0,0},
                {0,1,0,0,7,4,0,2,8},
                {3,0,0,4,9,0,1,5,0},
                {0,0,7,0,1,0,0,0,6},
                {9,0,0,7,5,3,0,8,0},
                {8,4,0,0,0,0,6,0,0},
                {5,0,0,0,4,0,0,3,1},
                {1,3,6,0,2,0,5,0,0}};
        boolean[] test={false,false,false,false,false,true,true,false,false};
        LogicBasic g1 = new LogicBasic(array);
        Assert.assertArrayEquals(test,g1.choices(1,1));
    }

    @Test
    public void getElement(){
        int[][] array={{0,8,3,0,0,2,4,1,0},
                {2,0,4,0,0,5,0,0,0},
                {0,1,0,0,7,4,0,2,8},
                {3,0,0,4,9,0,1,5,0},
                {0,0,7,0,1,0,0,0,6},
                {9,0,0,7,5,3,0,8,0},
                {8,4,0,0,0,0,6,0,0},
                {5,0,0,0,4,0,0,3,1},
                {1,3,6,0,2,0,5,0,0}};
        LogicBasic g1 = new LogicBasic(array);
        assertEquals(8,g1.getElement(0,1));
    }
}
