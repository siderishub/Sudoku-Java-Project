/**
 * Author George Michail 3292
 *
 * The Killer game mode frame
 */

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public class KillerFrame extends JFrame {
    private KillerSudokuBoard board;
    private LogicKillerSudoku logic;
    private JTextField[][] cells;
    private KillerPuzzles puzzles;
    final static private Color[] colors = {Color.yellow, Color.green, Color.red, Color.blue, Color.lightGray, Color.magenta, Color.cyan};

    /**
     *
     */
    public KillerFrame() {
        String userName = JOptionPane.showInputDialog("Εισάγετε όνομα χρήστη:");
        if (userName == null) return;
        puzzles = new KillerPuzzles(userName);
        String puzzleFile = puzzles.choosePuzzle();
        if (puzzleFile.equals( "allCompleted" )) return;
        board = new BoardFiles().killerFiles( puzzleFile );
        logic = new LogicKillerSudoku(board);
        cells = new JTextField[9][9];
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                cells[i][j] = new JTextField(Integer.toString(board.getvalue(i, j)));
                cells[i][j].setBackground(colors[ board.getColorOfElement(i, j) - 1 ]);
                cells[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                if (board.getvalue(i, j) == 0){
                    cells[i][j].setText("");
                }
                cells[i][j].setFont(new Font("Verdana", Font.BOLD, 20));
            }
        }

        JPanel sumPanel = new JPanel();
        //sumPanel.setSize(500, 100);
        TitledBorder borderSum = BorderFactory.createTitledBorder("Άθροισμα επιλεγμένης περιοχής");
        sumPanel.setBorder(borderSum);
        sumPanel.setLayout(new GridLayout(1,1));

        JTextField sumField = new JTextField("Πατήστε ένα κελί για να ξεκινήσετε.");
        sumField.setEditable(false);
        sumField.setFont(new Font("Arial", Font.BOLD, 25));
        sumField.setHorizontalAlignment(SwingConstants.CENTER);

        sumPanel.add(sumField);

        add(sumPanel, BorderLayout.NORTH);

        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                int finalI = i;
                int finalJ = j;
                cells[i][j].addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent focusEvent) {
                        //showSum(finalI, finalJ);
                        sumField.setText( Integer.toString( board.getElementAreaSum(finalI, finalJ) ));
                    }

                    @Override
                    public void focusLost(FocusEvent focusEvent) {
                        if (!cells[finalI][finalJ].getText().equals("")){
                            try{
                                int value = Integer.parseInt(cells[finalI][finalJ].getText());
                                boolean inserted = logic.insert(finalI+1, finalJ+1, value);
                                if (!inserted){
                                    cells[finalI][finalJ].setText("");
                                    showWarning("Αυτός ο αριθμός δεν ταιριάζει εδώ.");
                                }
                            }catch (NumberFormatException e){
                                showWarning("Πρέπει να εισάγετε αριθμούς από 1 έως 9 και όχι κείμενο.");
                                cells[finalI][finalJ].setText("");
                            }
                        }else {
                            logic.delete(finalI+1, finalJ+1);
                        }
                        if (logic.full()) {
                            JOptionPane.showMessageDialog(null,  "Συγχαρητήρια! Ολοκληρώσατε αυτό το παζλ." , "Τέλος", JOptionPane.INFORMATION_MESSAGE);
                            puzzles.completedTheLast();
                            dispose();
                        }
                    }
                });
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
        setSize(500,600);
        setLocationRelativeTo(null);
        setTitle("Killer Sudoku");
        setVisible(true);
        JPanel panel1 = new JPanel();

        BorderLayout borderLayout = new BorderLayout(5,5);
        setLayout( borderLayout );

        panel1.setLayout( new GridLayout(9, 9 , 3, 3));
        add(panel1, BorderLayout.SOUTH);
        panel1.setPreferredSize(new Dimension(500, 500));

        TitledBorder border = BorderFactory.createTitledBorder("Πατήστε δεξί κλικ σε ένα κελί για βοήθεια.");
        panel1.setBorder(border);

        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                JPopupMenu jPopupMenu = new JPopupMenu();
                JMenuItem help = new JMenuItem("Show help");
                int finalI = i;
                int finalJ = j;
                help.addActionListener(actionEvent -> showHelp(finalI, finalJ));
                jPopupMenu.add(help);
                cells[finalI][finalJ].setComponentPopupMenu(jPopupMenu);
                panel1.add(cells[i][j]);
            }
        }
    }

    /**
     *
     * @param text
     */
    private void showWarning(String text){
        JOptionPane.showMessageDialog(this, text, "Σφάλμα", JOptionPane.WARNING_MESSAGE);
    }

    /**
     *
     * @param x
     * @param y
     */
    private void showHelp(int x, int y){
        boolean[] fits = logic.choices(x+1, y+1);
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
