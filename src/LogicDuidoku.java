import java.util.Arrays;
/**
 * Η λογική της Duidoku
 * @author Αντώνης Σιδέρης
 */
public class LogicDuidoku {
    private int[][] board;
    /**
     * Ο κονστράκτορας
     * @param board ο πίνακας του Sudoku
     */
    public LogicDuidoku (int[][] board){
        this.board=board;
    }
    /**
     * Ο κονστράκτορας χωρίς παραμέτρους
     * θέτει τον πίνακα 0 σε όλα τα σημεία
     */
    public LogicDuidoku() {
        board = new int[4][4];
        for (int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                board[i][j] = 0;
            }
        }
    }

    /**
     * Επιστρέφει τον πίνακα
     * @return ο πίνακας
     */
    public int[][] getBoard(){
        return board;
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
        for(j=0;j<4;j++){
            if(board[line-1][j]==value){ return false;}
        }
        for(i=0;i<4;i++){
            if(board[i][column-1]==value){ return false; }
        }
        l=(line-1) / 2;
        c=(column-1) / 2;
        for(i=(l*2);i<(l*2)+2;i++){
            for(j=(c*2);j<(c*2)+2;j++){
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
        if(value<1 || value>4){
            return false;
        }
        if(checker(line,column,value)){
            board[line-1][column-1]=value;
            return true;
        }
        return false;
    }
    public boolean[] choices(int line,int column){
        int z;
        boolean[] numbers={true,true,true,true};
        for(z=1;z<=4;z++){
            if(!checker(line,column,z)){numbers[z-1]=false;}
        }
        return numbers;
    }
    /**
     * Ελέγχει αν τελείωσε το παιχνίδι
     * @return true αν τελείωσε αλλιώς false
     */
    public boolean isOver(){
        int i,j;
        boolean[] nochoice={false,false,false,false};
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                if(board[i][j]==0) {
                    if (!Arrays.equals(choices(i+1, j+1), nochoice)) { return false; }
                }
            }
        }
        return true;
    }

    /**
     * Υπολογίζει την κίνηση του υπολογιστή, η οποία επιλέγεται με τυχαίες ορθές επιλογές
     * @return αντικείμενο LogicDuidokuPlayed το οποίο αποθηκεύει την γραμμή του σημείου και την στήλη του σημείου που επέλεξε,
     * την τιμή που έθεσε και αν μπόρεσε να παίξει
     */
    public LogicDuidokuPlayed computerPlayer(){
        int i,j,z;
        boolean[] nochoice={false,false,false,false};
        boolean[] available;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                if(board[i][j]==0) {
                    if (!Arrays.equals(available=choices(i+1, j+1), nochoice)) {
                        for(z=0;z<4;z++){
                            if(available[z]){
                                board[i][j]=z+1;
                                return new LogicDuidokuPlayed(i,j,z+1);
                            }
                        }
                    }
                }
            }
        }
        return new LogicDuidokuPlayed(false);
    }
    /**
     * Επιστρεύει την τιμή ενός σημείου
     * @param x η γραμμή του σημείου
     * @param y η στήλη του σημείου
     * @return την τιμή του σημείου
     */
    public int getElement(int x, int y) { return board[x][y]; }
}
