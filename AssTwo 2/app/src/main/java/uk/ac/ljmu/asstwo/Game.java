package uk.ac.ljmu.asstwo;



public class Game {
    private final int  totalTurn=10;
    private  int player1Turn=5;
    private  int player2Turn=5;
    private int totalTurnRemaining=10;
    private int player1Value=0;
    private int player2Value=0;
    public boolean player1TurnStatus=true;
    public int getTotalTurn() {
        return totalTurn;
    }

    public void resetGame(){
        this.player1Turn=5;
        this.player2Turn=5;
        this.totalTurnRemaining=10;
        this.player1Value=0;
        this.player2Value=0;
        this.player1TurnStatus=true;
    }
public void decrementPlayer1Turn(){
    this.player1Turn--;
}
    public void decrementPlayer2Turn(){
        this.player2Turn--;
    }
    public int getRemainingTurn(){
        return this.totalTurnRemaining;
    }
    public void setRemainingTurn(int r){
        this.totalTurnRemaining=r;
    }
    public void decrementTurn(){
        this.totalTurnRemaining--;
    }

    public int getPlayer1Value() {
        return player1Value;
    }

    public void setPlayer1Value(int player1Value) {
        this.player1Value = player1Value;
    }

    public int getPlayer2Value() {
        return player2Value;
    }

    public void setPlayer2Value(int player2Value) {
        this.player2Value = player2Value;
    }

    public int getPlayer1Turn() {
        return player1Turn;
    }

    public void setPlayer1Turn(int player1Turn) {
        this.player1Turn = player1Turn;
    }

    public int getPlayer2Turn() {
        return player2Turn;
    }

    public void setPlayer2Turn(int player2Turn) {
        this.player2Turn = player2Turn;
    }
}
