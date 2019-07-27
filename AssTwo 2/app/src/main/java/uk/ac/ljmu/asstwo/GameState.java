package uk.ac.ljmu.asstwo;



public class GameState {
    private int totalTurnRem;
    private int player1Turn;
    private int player2Turn;
    private int player1Value;
    private int player2Value;
    private int playerState;
    private int id;
    public int getTotalTurnRem() {
        return totalTurnRem;
    }

    public void setTotalTurnRem(int totalTurnRem) {
        this.totalTurnRem = totalTurnRem;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerState() {
        return playerState;
    }

    public void setPlayerState(int playerState) {
        this.playerState = playerState;
    }
}
