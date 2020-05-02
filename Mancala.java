/**
 * Mancala.java
 * @author Gabriel De Jesus
 * Instructor: W. John Thrasher
 * Semester: Summer 2018
 */
import javax.swing.JFrame;

public class Mancala {

    public static void main(String[] args) {
        GameFrame gFrame = new GameFrame();
        gFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        gFrame.setSize( 800, 600 ); // set frame size
        gFrame.setVisible( true ); // display frame
    }
}