/**
 * Stone.java
 * @author Gabriel De Jesus
 * Instructor: W. John Thrasher
 * Semester: Summer 2018
 */
import javax.swing.*;

public class Stone extends JButton {
    private int value;
    private int rowPosition;
    private int colPosition;
	public static boolean gameState[] = {false,false};
	
    public Stone(int r, int c, int v) {
        rowPosition = r;
        colPosition = c;
        value = v;
        this.setText(String.valueOf(value));
    }
    public int getStones() {
        return value;
    }
    public int getRowPosition() {
        return rowPosition;
    }
    public int getColPosition() {
        return colPosition;
    }
    public void setValue(int v) {
        this.value = v;
    }

    public boolean[] stonesMoved(int x, int y, Stone[][] stoneArray, Player cur, Player opp) {
		// reset game conditions after a button is pressed
		gameState[0] = false;
		gameState[1] = false;
        int stonesLeft = stoneArray[x][y].getStones();
        stoneArray[x][y].value = 0;
        stoneArray[x][y].setText("0");
        int bottomIndex = y+1;
        int topIndex = y-1;
        int botLeft = 0;
        int topRight = 5;
        int leftOverStones = 0;
        boolean oppPlayerNoStones = true;
        boolean[] gameState = {false,false};
        int curplayerRow = 0;
        int oppplayerRow = 0;
        if(cur.getPlayerNumber() == 1) {
            curplayerRow = 1;
            oppplayerRow = 0;
        }
        else {
            curplayerRow = 0; // player 2 actually resides on top row (row index 0)
            oppplayerRow = 1;
        }
        // Check if a row's cups are all empty, this means the other player wins the remaining stones
        for(int i = 0; i < 6; i++) {
            if(stoneArray[oppplayerRow][i].getStones() != 0)
               oppPlayerNoStones = false;
        }
        if(oppPlayerNoStones) {
            for(int i = 0; i < 6; i++) {
                leftOverStones += Integer.parseInt(stoneArray[curplayerRow][i].getText());
                stoneArray[curplayerRow][i].setText("0");

            }
            cur.addStones(leftOverStones); // adding the remaining stones to the opposing player's score
            gameState[1] = true; // game over
            cur.endTurn();
            opp.endTurn();
            return gameState;
        }
        if (x == 1 && stonesLeft > 0) { // 2nd row aka player 1, this means fill left to right until a mancala
            do {
                for (int i = bottomIndex; i < 6; i++) {
                    if (stonesLeft > 0) {
                        stoneArray[1][i].value++;
                        stoneArray[1][i].setText(String.valueOf(stoneArray[1][i].getStones()));
                        //bottomIndex++;
                        --stonesLeft;
                    } 
                }
                if (stonesLeft > 0) { // if stones still left after filling cups, then player will score
                    cur.playerScoresPoint();
                    --stonesLeft;
                }
                if (stonesLeft == 0) { // if we dont have stone after dropping one into mancala we go again, do not do below
					//JOptionPane.showMessageDialog(this, "Player 1 Free Turn");
					gameState[0] = true;
                    return gameState;
                }
                if (stonesLeft > 0) { // if still stones left, start filling opponent cups
                    for (int i = topRight; i >= 0; i--) {
                        if (stonesLeft > 0) {
                            stoneArray[0][topRight].value++;
                            stoneArray[0][topRight].setText(String.valueOf(stoneArray[0][topRight].getStones()));
                            --stonesLeft;
                            topRight--;
                        } 
                    }
                }
                if (stonesLeft > 0) { // we filled opponent cups and still have stones left over.
                    for (int i = 0; i < 6; i++) {
                        if (stonesLeft > 0) {
                            stoneArray[1][i].value++;
                            stoneArray[1][i].setText(String.valueOf(stoneArray[1][i].getStones()));
                            --stonesLeft;
                        }
                    }
                }
            } while(stonesLeft > 0);// end player 1 logic
			return gameState;
        }

        // player 2 logic start
        if (x == 0 && stonesLeft > 0) { // 1st row aka player 2, this means fill right to left until a mancala
            do {
				for (int i = topIndex; i >= 0; i--) {
					if (stonesLeft > 0) {
						stoneArray[0][i].value++;
						stoneArray[0][i].setText(String.valueOf(stoneArray[0][i].getStones()));
						//topIndex--;
						--stonesLeft;
					}
				}
				if (stonesLeft > 0) { // if stones still left after filling cups, then player will score
					cur.playerScoresPoint();
					--stonesLeft;
				}
				if (stonesLeft == 0) { // if we dont have stone after dropping one into mancala we go again, do not do below
					//JOptionPane.showMessageDialog(this, "Player 2 Free Turn");
					gameState[0] = true;
					return gameState;
				}
				if (stonesLeft > 0) { // if still stones left, start filling opponent cups
					for (int i = botLeft; i < 6; i++) {
						if (stonesLeft > 0) {
							stoneArray[1][botLeft].value++;
							stoneArray[1][botLeft].setText(String.valueOf(stoneArray[1][botLeft].getStones()));
							--stonesLeft;
							botLeft++;
						} 
					}
				}
				if (stonesLeft > 0) { // we filled opponent cups and still have stones left over.
					for (int i = 5; i >= 0; i--) {
						if (stonesLeft > 1) {
							stoneArray[0][i].value++;
							stoneArray[0][i].setText(String.valueOf(stoneArray[0][i].getStones()));
							--stonesLeft;
						} 
					}
				}
            } while (stonesLeft >0);// end player 2 logic
			gameState[0] = false;
			return gameState;
        }
        return gameState;
    }
}
