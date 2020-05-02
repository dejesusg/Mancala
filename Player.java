/**
 * Player.java
 * @author Gabriel De Jesus
 * Instructor: W. John Thrasher
 * Semester: Summer 2018
 */
public class Player {
    private int score;
    private boolean turn;
    private int playerNumber;
    private Stone[] playerStones;
    public Player(int p) {
        playerStones = new Stone[6];
        score = 0;
        playerNumber = p;
    }
    public int getScore() {
        return score;
    }
    public int getPlayerNumber() {
        return playerNumber;
    }
    public void playerScoresPoint() { score++; }
    public void setStones(Stone s, int i) {
        playerStones[i] = s;
    }

    public void addStones(int i) {
        score += this.playerStones[i].getStones();
        this.playerStones[i].setValue(0);
        this.playerStones[i].setText("0");
    }

    public void endTurn() {
        for(int i = 0; i < 6; i++)
            playerStones[i].setEnabled(false);
    }
    public void startTurn() {
        for(int i = 0; i < 6; i++)
            playerStones[i].setEnabled(true);
    }
}
