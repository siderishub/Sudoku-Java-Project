import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IndexFrame extends JFrame {

    private ReadUserStatistics stats;

    public IndexFrame() {
        stats = new ReadUserStatistics();
        makeFrame();
    }

    private void makeFrame(){
        setTitle("Sudoku");
        setSize(450, 300);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(2,3,0,0));

        JButton classic = new JButton("Play Classic");
        classic.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.darkGray));
        JButton killer = new JButton("Play Killer");
        killer.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.darkGray));
        JButton duidoku = new JButton("Play Duidoku");
        duidoku.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.darkGray));

        classic.addActionListener(actionEvent -> new ClassicFrame());

        killer.addActionListener(actionEvent -> new KillerFrame());

        duidoku.addActionListener(actionEvent -> new DuidokuFrame());

        add(classic);
        add(killer);
        add(duidoku);

        JButton classicStats = new JButton("Στατιστηκά Classic");
        classicStats.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.darkGray));
        JButton killerStats = new JButton("Στατιστηκά Killer");
        killerStats.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.darkGray));
        JButton duidokuStats = new JButton("Στατιστηκά Duidoku");
        duidokuStats.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.darkGray));

        classicStats.addActionListener(actionEvent -> stats.readClassic());

        killerStats.addActionListener(actionEvent -> stats.readKiller());

        duidokuStats.addActionListener(actionEvent -> stats.readDuidoku());

        add(classicStats);
        add(killerStats);
        add(duidokuStats);

        setVisible(true);
    }
}
