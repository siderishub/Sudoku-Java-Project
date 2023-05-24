import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogicKillerSudokuTests {
    public LogicKillerSudokuTests() {

    }
    @Test
    public void insert() {
        LogicKillerSudoku g1 = new LogicKillerSudoku(new BoardFiles().killerFiles("Killer1"));
        assertTrue(g1.insert(1,1,2));
        assertTrue(g1.insert(1,1,2));
        assertFalse(g1.insert(1,1,10));
        assertFalse(g1.insert(1,1,8));
        KillerSudokuBoard board=g1.getBoard();
        assertEquals(2,board.getvalue(0,0), 0.001);
    }

    @Test
    public void delete() {
        LogicKillerSudoku g1 = new LogicKillerSudoku();
        g1.insert(1,1,2);
        assertTrue(g1.delete(1,1));
        assertEquals(0,g1.getElement(0,0), 0.001);
    }

    @Test
    public void full() {
        LogicKillerSudoku g1 = new LogicKillerSudoku(new BoardFiles().killerFiles("Killer1"));
        assertFalse(g1.full());
    }

    @Test
    public void choices() {
        boolean[] test={true,true,true,false,false,false,false,false,false};
        LogicKillerSudoku g1 = new LogicKillerSudoku(new BoardFiles().killerFiles("Killer1"));
        Assert.assertArrayEquals(test,g1.choices(1,1));
    }

    @Test
    public void getElementfromKillerSudokuArea() {
        KillerSudokuArea area = new KillerSudokuArea(3,1,1);
        BoardCoordinates b = new BoardCoordinates();
        area.add(b);
        assertEquals(b,area.getElement(0));
    }
    @Test
    public void getXandgetYfromKillerSudokuElement(){
        KillerSudokuElement element=new KillerSudokuElement(0,0,3,1);
        assertEquals(0,element.getX());
        assertEquals(0,element.getY());
    }
}
