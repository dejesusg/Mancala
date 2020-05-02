/**
 * Board.java
 * @author Gabriel De Jesus
 * Instructor: W. John Thrasher
 * Semester: Summer 2018
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import java.awt.font.*;

public class Board extends JPanel {
    private BorderLayout layout;
    private JPanel cupPanel;
    private JTextField p1Mancala;
    private JTextField p2Mancala;
    private Stone[][] stones;
    private JPanel centeredPanel;
    private Player p1;
    private Player p2;
    private boolean winner;
    public Board(int cols) {
        JOptionPane.showMessageDialog(Board.this, "Rules of Mancala\nEach player has 6 cups on their side filled with" +
                " 4 stones.\nThe player also has 1 Mancala to their right which contains all captured stones.\n" +
                "On your turn, you will pick up all the stones and place a single stone into each subsequent cup" +
                " on your side.\n" +
                "If your final stone is placed into your Mancala, you earn a free turn.\n");
        setLayout(new BorderLayout());
        // creating objects
        p1 = new Player(1);
        p2 = new Player(2);
        stones = new Stone[2][cols];
        cupPanel = new JPanel(new GridLayout(2, cols));
        cupPanel.setBackground(new Color(57, 66, 103));
        centeredPanel = new JPanel(new BorderLayout(5, 5));
        centeredPanel.setBackground(new Color(57, 66, 103));
        p1Mancala = new JTextField(String.valueOf(p1.getScore()));
        p2Mancala = new JTextField(String.valueOf(p2.getScore()));
        // Setting Style of Buttons, Fields, Etc
        // Pastel Color themes are the best
        Font mancalaFont = new Font("SansSerif", Font.BOLD, 80);
        Font cupFont = new Font("SansSerif", Font.PLAIN, 64);
        // To be more "sophisticated" i could create a class to allow players
        // to select the color of their mancala/cups -- for now they are hard coded
        // player 1 mancala styles
        p1Mancala.setEditable(false);
        p1Mancala.setColumns(2);
        p1Mancala.setBorder(BorderFactory.createEmptyBorder());
        p1Mancala.setFont(mancalaFont);
        p1Mancala.setForeground(Color.BLACK);
        p1Mancala.setBackground(new Color(223, 228, 218));
        p1Mancala.setHorizontalAlignment(JLabel.CENTER);
        // player 2 mancala styles
        p2Mancala.setEditable(false);
        p2Mancala.setColumns(2);
        p2Mancala.setBorder(BorderFactory.createEmptyBorder());
        p2Mancala.setFont(mancalaFont);
        p2Mancala.setForeground(Color.BLACK);
        p2Mancala.setBackground(new Color(150, 161, 209));
        p2Mancala.setHorizontalAlignment(JLabel.CENTER);
        //
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < cols; j++) {
                stones[i][j] = new Stone(i, j, 4);
                ButtonHandler handler = new ButtonHandler();
                stones[i][j].addActionListener(handler);
                stones[i][j].setFont(cupFont);
                stones[i][j].setBorder(BorderFactory.createLineBorder(new Color(57, 66, 103)));
                if (i == 0) {
                    // row index 0 is player 2, disable their buttons until after player 1 clicks.
                    p2.setStones(stones[i][j],j);
                    stones[i][j].setBackground(new Color(150, 161, 209));
                    stones[i][j].setEnabled(false);
                }
                else {
                    stones[i][j].setBackground(new Color(223, 228, 218));
                    p1.setStones(stones[i][j],j);
                    stones[i][j].setEnabled(true);
                }
                stones[i][j].setForeground(Color.BLACK);
                cupPanel.add(stones[i][j]);
            }
        }
        centeredPanel.add(cupPanel, BorderLayout.CENTER);
        centeredPanel.add(p1Mancala, BorderLayout.EAST);
        centeredPanel.add(p2Mancala, BorderLayout.WEST);
        add(centeredPanel, BorderLayout.CENTER);
        setVisible(true);
        //repaint();
        if(!stones[0][0].isEnabled() && !stones[1][0].isEnabled())
            GetWinner(p1,p2);
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Stone clicked = (Stone) e.getSource();
            Player currentPlayer;
            Player opposingPlayer;
            if(clicked.getRowPosition() == 1) {
                currentPlayer = Board.this.p1;
                opposingPlayer = Board.this.p2;
            }
            else {
                currentPlayer = Board.this.p2;
                opposingPlayer = Board.this.p1;
            }

            //currentPlayer.playerScoresPoint();
            if(clicked.getStones() == 0)
                JOptionPane.showMessageDialog(Board.this,"There are no stones in this cup to move.");
            else {
                //GetWinner(p1,p2);
                if(clicked.stonesMoved(clicked.getRowPosition(), clicked.getColPosition(),
                        Board.this.stones, currentPlayer,opposingPlayer)[1]) {
                    GetWinner(p1,p2);
                    return;
                }
                else
                    if(clicked.stonesMoved(clicked.getRowPosition(), clicked.getColPosition(),
                        Board.this.stones, currentPlayer,opposingPlayer)[0] == false) {
                    currentPlayer.endTurn();
                    opposingPlayer.startTurn();
                    }
					
                if(currentPlayer == Board.this.p1) {
                    p1Mancala.setText(String.valueOf(p1.getScore()));
                }
                else if (currentPlayer == Board.this.p2) {

                    p2Mancala.setText(String.valueOf(p2.getScore()));
                }
                //repaint();
            }
        }
    }
    private void GetWinner(Player p1, Player p2) {
        p1Mancala.setText(String.valueOf(p1.getScore()));
        p2Mancala.setText(String.valueOf(p2.getScore()));
        if(p1.getScore() > p2.getScore())
            JOptionPane.showMessageDialog(Board.this, "Player 1 has won");
        else if(p1.getScore() < p2.getScore())
            JOptionPane.showMessageDialog(Board.this, "Player 2 has won");
        else
            JOptionPane.showMessageDialog(Board.this, "Game is TIED");
    }

}