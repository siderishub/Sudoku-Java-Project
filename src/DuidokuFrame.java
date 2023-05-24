/**
 * author George Michail 3292
 *
 * The Duidoku game mode frame
 */

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public class DuidokuFrame extends JFrame {
    private LogicDuidoku board;
    private JTextField cells[][];
    private boolean used[][];
    private boolean computerWon;
    private String userName;
    private DuidokuFiles progress;


    /**
     *
     */
    public DuidokuFrame() {
        board = new LogicDuidoku();
        cells = new JTextField[4][4];
        used = new boolean[4][4];
        computerWon = false;

        userName = JOptionPane.showInputDialog("Εισάγετε όνομα χρήστη:");
        if (userName == null) return;
        progress = new DuidokuFiles(userName);

        setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(300,350);
        setLocationRelativeTo(null);
        setTitle("Duidoku");
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        TitledBorder mainBorder = BorderFactory.createTitledBorder("Πατήστε δεξί κλικ σε ένα κελί για βοήθεια");
        mainPanel.setBorder( mainBorder );
        mainPanel.setLayout(new GridLayout(4,4,0,0));
        mainPanel.setPreferredSize(new Dimension(300, 300));

        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                used[i][j] = false;
                int finalI = i;
                int finalJ = j;
                cells[finalI][finalJ] = new JTextField();
                cells[finalI][finalJ].setHorizontalAlignment(SwingConstants.CENTER);
                cells[finalI][finalJ].setFont(new Font("Verdana", Font.BOLD, 20));

                JPopupMenu jPopupMenu = new JPopupMenu();
                JMenuItem help = new JMenuItem("Show help");
                help.addActionListener(actionEvent -> showHelp(finalI, finalJ));
                jPopupMenu.add(help);
                cells[finalI][finalJ].setComponentPopupMenu(jPopupMenu);

                cells[finalI][finalJ].addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent focusEvent) {

                    }

                    @Override
                    public void focusLost(FocusEvent focusEvent) {
                        if (!cells[finalI][finalJ].getText().equals("") && !used[finalI][finalJ]){
                            try{
                                int value = Integer.parseInt(cells[finalI][finalJ].getText());
                                boolean inserted = board.insert(finalI+1, finalJ+1, value );
                                if (!inserted){
                                    cells[finalI][finalJ].setText("");
                                    showWarning("Αυτός ο αριθμός δεν ταιριάζει εδώ.");
                                } else {
                                    cells[finalI][finalJ].setEditable(false);
                                    used[finalI][finalJ] = true;
                                    computerWon = false;
                                    LogicDuidokuPlayed played = board.computerPlayer();
                                    if (played.isPlayed()) {
                                        cells[played.getX()][played.getY()].setText(Integer.toString(played.getValue()));
                                        cells[played.getX()][played.getY()].setEditable(false);
                                        cells[played.getX()][played.getY()].setBackground(Color.lightGray);
                                        used[played.getX()][played.getY()] = true;
                                        computerWon = true;
                                    }
                                }
                            }catch (NumberFormatException e){
                                showWarning("Πρέπει να εισάγετε αριθμούς από 1 έως 4 και όχι κείμενο.");
                                cells[finalI][finalJ].setText("");
                            }
                        }
                        for (int k=0; k<4; k++) {
                            for (int l=0; l<4; l++) {
                                if (!hasAFit(k, l) && !used[k][l]) {
                                    cells[k][l].setEditable(false);
                                    cells[k][l].setBackground(Color.red);
                                    used[k][l] = true;
                                }
                            }
                        }
                        if (board.isOver()) {
                            dispose();
                            if (computerWon) {
                                finish(false);
                            } else {
                                finish(true);
                            }
                        }
                    }
                });
                mainPanel.add(cells[i][j]);
            }
        }
        cells[0][1].setBorder(BorderFactory.createMatteBorder(1,1,1,3, Color.black));
        cells[3][1].setBorder(BorderFactory.createMatteBorder(1,1,1,3, Color.black));
        cells[2][0].setBorder(BorderFactory.createMatteBorder(3,1,1,1, Color.black));
        cells[2][1].setBorder(BorderFactory.createMatteBorder(3,1,1,3, Color.black));
        cells[1][1].setBorder(BorderFactory.createMatteBorder(1,1,3,3, Color.black));
        cells[1][0].setBorder(BorderFactory.createMatteBorder(1,1,3,1, Color.black));
        cells[0][2].setBorder(BorderFactory.createMatteBorder(1,3,1,1, Color.black));
        cells[1][2].setBorder(BorderFactory.createMatteBorder(1,3,3,1, Color.black));
        cells[2][2].setBorder(BorderFactory.createMatteBorder(3,3,1,1, Color.black));
        cells[2][3].setBorder(BorderFactory.createMatteBorder(3,1,1,1, Color.black));
        cells[1][3].setBorder(BorderFactory.createMatteBorder(1,1,3,1, Color.black));
        cells[3][2].setBorder(BorderFactory.createMatteBorder(1,3,1,1, Color.black));
        cells[0][0].setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
        cells[0][3].setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
        cells[3][0].setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
        cells[3][3].setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));

        add(mainPanel, BorderLayout.SOUTH);
        setVisible(true);
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
    private void showHelp(int x, int y) {
        boolean[] fits = board.choices(x+1, y+1);
        StringBuilder numbers = new StringBuilder();
        for (int i=0; i<4; i++){
            if (fits[i]){
                numbers.append(" ");
                numbers.append(i+1);
            }
        }
        JOptionPane.showMessageDialog(this, "Ταιριάζουν:" + numbers , "Βοήθεια", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    private boolean hasAFit(int x, int y) {
        boolean[] fits = board.choices(x+1, y+1);
        for (int i=0; i<4; i++) {
            if (fits[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Εμφανίζει το μύνημα νίκης ή ήττας
     * @param won
     */
    private void finish(boolean won) {
        if (won) {
            progress.addWin();
            JOptionPane.showMessageDialog(null, "Κερδίσατε!", "Τέλος", JOptionPane.INFORMATION_MESSAGE);
        } else {
            progress.addLoss();
            JOptionPane.showMessageDialog(null, "Xάσατε", "Τέλος", JOptionPane.INFORMATION_MESSAGE);
        }
        dispose();
    }
}
