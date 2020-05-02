/**
 * GameFrame.java
 * @author Gabriel De Jesus
 * Instructor: W. John Thrasher
 * Semester: Summer 2018
 */
import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private JDesktopPane mainWindow;

    public GameFrame() {
        super("Mancala by Gabriel De Jesus");
        mainWindow = new JDesktopPane();
        Board b = new Board(6); // traditional 6 column game split into 2 sets of 7 (6 cups + 1 mancala per player)
        add(b);
        pack();
    }
}
