/**
 * Η λογική της ClassicSudoku
 * @author Αντώνης Σιδέρης
 */
public class LogicBasic{
    private int[][] board;

    /**
     * Ο κονστράκτορας
     * @param board ο πίνακας του Sudoku
     */
    public LogicBasic(int[][] board){
        this.board=board;
    }
    /**
     * Ο κονστράκτορας χωρίς παραμέτρους
     * θέτει τον πίνακα ίδιο με το πρώτο πάζλ
     */
    public LogicBasic() {
        board = new BoardFiles().sudokuFiles("Classic1");
    }

    /**
     * Διαγράφει ένα σημείο απο τον πίνακα
     * @param line η γραμμή του σημείου
     * @param column η στήλη του σημείου
     * @return true αν το διέγραψε
     */
    public boolean delete(int line, int column){
        board[line-1][column-1]=0;
        return true;
    }

    /**
     * Επιστρέφει τον πίνακα
     * @return ο πίνακας
     */
    public int[][] getBoard(){
        return board;
    }

    /**
     * Ελέγχει αν ο πίνακας γέμισε,δηλαδή αν τελείωσε το παιχνίδι
     * @return true αν είναι γεμάτος αλλιώς false
     */
    public boolean full() {
        int i, j;
        for (i = 0; i < 9; i++) {
            for (j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Ελέγχει αν μπορεί να εισαχθεί μία τιμή
     * @param line η γραμμή του σημείου
     * @param column η στήλη του σημείου
     * @param value η τιμή του σημείου
     * @return true αν μπορεί να εισαχθεί αλλιώς false
     */
    private boolean checker(int line, int column, int value){
        int i,j;
        int l,c;
        for(j=0;j<9;j++){
            if(board[line-1][j]==value){ return false;}
        }
        for(i=0;i<9;i++){
            if(board[i][column-1]==value){ return false; }
        }
        l=(line-1) / 3;
        c=(column-1) / 3;
        for(i=(l*3);i<(l*3)+3;i++){
            for(j=(c*3);j<(c*3)+3;j++){
                if(board[i][j]==value){ return false; }
            }
        }
        return true;
    }

    /**
     * Εισάγει μία τιμή σε ένα σημείο
     * @param line η γραμμή του σημείου
     * @param column η στήλη του σημείου
     * @param value η τιμή του σημείου
     * @return true αν μπορεί να εισαχθεί αλλιώς false
     */
    public boolean insert(int line,int column,int value){
        if(value<1 || value>9 ){
            return false;
        }
        if(value==board[line-1][column-1]){
            return true;
        }
        if(checker(line,column,value)){
            board[line-1][column-1]=value;
            return true;
        }
        return false;
    }

    /**
     * Βλέπει όλες τις πιθανές επιλογές τιμών για μία περιοχή
     * @param line η γραμμή του σημείου
     * @param column η στήλη του σημείου
     * @return εναν πίνακα με τις πιθανές επιλογές
     */
    public boolean[] choices(int line,int column){
        int z;
        boolean[] numbers={true,true,true,true,true,true,true,true,true};
        for(z=1;z<=9;z++){
            if(!checker(line,column,z)){numbers[z-1]=false;}
        }
        return numbers;
    }

    /**
     * Επιστρεύει την τιμή ενός σημείου
     * @param x η γραμμή του σημείου
     * @param y η στήλη του σημείου
     * @return την τιμή του σημείου
     */
    public int getElement(int x, int y) { return board[x][y]; }
}

