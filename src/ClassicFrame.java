import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Author George Michail 3292
 *
 * The classic game mode frame
 */
public class ClassicFrame extends JFrame {
    private JTextField[][] cells;
    private LogicBasic board;
    private ClassicPuzzles puzzles;

    /**
     *
     */
    public ClassicFrame() {
        String userName = JOptionPane.showInputDialog("Εισάγετε όνομα χρήστη:");
        if (userName == null) return; //Για να μην ανοίγει το παράθυρο όταν ο χρήστης πατάει cancel στο παράθυρο εισαγωγής
        BoardFiles files = new BoardFiles();
        puzzles = new ClassicPuzzles(userName);
        String puzzleFile = puzzles.choosePuzzle(); //Όνομα αρχείου
        if (puzzleFile.equals( "allCompleted" )) {
            return;
        }
        board = new LogicBasic(files.sudokuFiles( puzzleFile ));
        cells = new JTextField[9][9];
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++) {
                cells[i][j] = new JTextField(Integer.toString(board.getElement(i,j)));
                //cells[i][j].setBackground(Color.magenta);
                cells[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                cells[i][j].setFont(new Font("Verdana", Font.BOLD, 20));
                if  (board.getElement(i,j)!=0 ){
                    cells[i][j].setText(Integer.toString(board.getElement(i,j)));
                    cells[i][j].setEditable(false);
                }
                else {
                    cells[i][j].setText("");
                }
            }
        }
        makeFrame();
    }

    /**
     *
     */
    private void makeFrame(){
        setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(500,550);
        setLocationRelativeTo(null);
        setTitle("Classic Sudoku");
        //FlowLayout flowLayout = new FlowLayout();
        //setLayout(flowLayout);

        JPanel panel1 = new JPanel();
        //panel1.setSize(550,550);

        GridLayout pane1Grid = new GridLayout(9,9,3,3);
        panel1.setLayout(pane1Grid);

        TitledBorder border = BorderFactory.createTitledBorder("Πατήστε δεξί κλικ σε ένα κελί για βοήθεια");
        panel1.setBorder(border);

        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                panel1.add(cells[i][j]);
            }
        }

        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                int finalI = i;
                int finalJ = j;
                if (board.getElement(i,j) == 0) {
                    JPopupMenu jPopupMenu = new JPopupMenu();
                    JMenuItem help = new JMenuItem("Show help");
                    help.addActionListener(actionEvent -> showHelp(finalI, finalJ));
                    jPopupMenu.add(help);
                    cells[i][j].addFocusListener(new FocusListener() {
                        @Override
                        public void focusGained(FocusEvent focusEvent) {
                        }

                        @Override
                        public void focusLost(FocusEvent focusEvent) {
                            if (!cells[finalI][finalJ].getText().equals("")){
                                try{
                                    int value = Integer.parseInt(cells[finalI][finalJ].getText());
                                    boolean inserted = board.insert(finalI+1, finalJ+1, value );
                                    if (!inserted){
                                        cells[finalI][finalJ].setText("");
                                        showWarning("Αυτός ο αριθμός δεν ταιριάζει εδώ.");
                                    }
                                }catch (NumberFormatException e){
                                    showWarning("Πρέπει να εισάγετε αριθμούς από 1 έως 9 και όχι κείμενο.");
                                    cells[finalI][finalJ].setText("");
                                }
                            }else {
                                board.delete(finalI+1,finalJ+1);
                            }
                            if (board.full()) {
                                JOptionPane.showMessageDialog(null,  "Συγχαρητήρια! Ολοκληρώσατε αυτό το παζλ." , "Τέλος", JOptionPane.INFORMATION_MESSAGE);
                                puzzles.completedTheLast();
                                dispose();
                            }
                        }
                    });
                    cells[finalI][finalJ].setComponentPopupMenu(jPopupMenu);
            }
            }
        }

        add(panel1, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * Δημιουργία παράθυρου προηδοποίησης
     * @param text το κείμενο που θα εμφανιστεί στο παράθυρο
     */
    private void showWarning(String text){
        JOptionPane.showMessageDialog(this, text, "Σφάλμα", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Δημιουργία παράθυρου βοήθειας
     * @param x
     * @param y
     */
    private void showHelp(int x, int y){
        boolean[] fits = board.choices(x+1, y+1);
        StringBuilder numbers = new StringBuilder();
        for (int i=0; i<9; i++){
            if (fits[i]){
                numbers.append(" ");
                numbers.append(i+1);
            }
        }
        JOptionPane.showMessageDialog(this, "Ταιριάζουν:" + numbers , "Βοήθεια", JOptionPane.INFORMATION_MESSAGE);
    }
}
