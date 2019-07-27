package uk.ac.ljmu.asstwo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class AssTwo extends AppCompatActivity {
    public static int dieValue1;
    public static int dieValue2;
    private static Random rnd=new Random();
    private OGLView oglView;
    private OGL2View oglView1;
    private TextView totalTurn;
    private TextView turnRemaining;
    private TextView player1Turn;
    private TextView player2Turn;
    private TextView playerNameForTurn;
    public static boolean isDraw=true;
    private Button rollButton;
    private TextView player1Value;
    private TextView player2Value;
    private TextView gameState;
    private Game game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asstwo);
        game=new Game();
        oglView = (OGLView) findViewById(R.id.oglView);
        oglView1 = (OGL2View) findViewById(R.id.oglView1);
        totalTurn=(TextView)findViewById(R.id.totalTurn);
        turnRemaining=(TextView)findViewById(R.id.turnRemaining);
        player1Turn=(TextView)findViewById(R.id.player1TotalTurn);
        player2Turn=(TextView)findViewById(R.id.player2TotalTurn);
        playerNameForTurn=(TextView)findViewById(R.id.playerNameForTurn);

        player1Value=(TextView)findViewById(R.id.player1Value);
        player2Value=(TextView)findViewById(R.id.player2Value);
        gameState=(TextView)findViewById(R.id.gameState);

        totalTurn.setText("Total Turn: "+game.getTotalTurn());
        turnRemaining.setText("Remaining Turn: "+game.getRemainingTurn());
        player1Turn.setText("Player 1 Turn Rem.: "+game.getPlayer1Turn());
        player2Turn.setText("Player 2 Turn Rem.: "+game.getPlayer2Turn());
        playerNameForTurn.setText("Turn: Player 1");
        player1Value.setText("Player 1 Value: "+game.getPlayer1Value());
        player2Value.setText("Player 2 Value: "+game.getPlayer2Value());
        rollButton=(Button)findViewById(R.id.btnRoll);
        gameState.setText("Game State: Playing");
    }

    @Override
    protected void onPause() {
        super.onPause();
        oglView.onPause();
        oglView1.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        oglView.onResume();
        oglView1.onResume();
    }
    public void endGame(View v){
        System.exit(0);
    }
    public void rollDice(View v){
        isDraw=!isDraw;
        dieValue1=rnd.nextInt(6) + 1;
        dieValue2=rnd.nextInt(6) + 1;
        if(!isDraw) {
            game.decrementTurn();
            if(game.player1TurnStatus){
                game.decrementPlayer1Turn();
                game.setPlayer1Value(game.getPlayer1Value()+dieValue1+dieValue2);
                playerNameForTurn.setText("Turn: Player 2");
                player1Value.setText("Player 1 Value: "+game.getPlayer1Value());

            }else{
                game.decrementPlayer2Turn();
                game.setPlayer2Value(game.getPlayer2Value()+dieValue1+dieValue2);
                playerNameForTurn.setText("Turn: Player 1");
                player2Value.setText("Player 2 Value: "+game.getPlayer2Value());
            }
            game.player1TurnStatus=!game.player1TurnStatus;
            turnRemaining.setText("Remaining Turn: "+game.getRemainingTurn());
            player1Turn.setText("Player 1 Turn: "+game.getPlayer1Turn());
            player2Turn.setText("Player 2 Turn: "+game.getPlayer2Turn());

            Toast.makeText(getApplicationContext(), dieValue1 + "-" + dieValue2, Toast.LENGTH_SHORT).show();

            rollButton.setText("Start Roll");

        }else{
            rollButton.setText("Stop Roll");
        }

        if(game.getRemainingTurn()<1){
            if(game.getPlayer1Value()>game.getPlayer2Value())
                gameState.setText("Game Over: Player 1 Win");
            else
                gameState.setText("Game Over: Player 2 Win");
        }
    }

    public void resetGame(View v){
        game.resetGame();
        totalTurn.setText("Total Turn: "+game.getTotalTurn());
        turnRemaining.setText("Remaining Turn: "+game.getRemainingTurn());
        player1Turn.setText("Player 1 Turn Rem.: "+game.getPlayer1Turn());
        player2Turn.setText("Player 2 Turn Rem.: "+game.getPlayer2Turn());
        player1Value.setText("Player 1 Value: "+game.getPlayer1Value());
        player2Value.setText("Player 2 Value: "+game.getPlayer2Value());
        gameState.setText("Game State: Playing");
    }

    public void saveGameState(View v){
        //saving game state
        DatabaseHandler db = new DatabaseHandler(this);
        GameState gameState=new GameState();
        gameState.setId(1);
        gameState.setTotalTurnRem(game.getRemainingTurn());
        gameState.setPlayer1Turn(game.getPlayer1Turn());
        gameState.setPlayer2Turn(game.getPlayer2Turn());
        gameState.setPlayer1Value(game.getPlayer1Value());
        gameState.setPlayer2Value(game.getPlayer2Value());
        if(game.player1TurnStatus){
            gameState.setPlayerState(1);
        }else{
            gameState.setPlayerState(0);
        }
        if(db.isExist()<1) {
            db.saveGameState(gameState);
        }else{
           db.updateContact(gameState);
        }


    }
    public void reloadGameSate(View v){

        game.resetGame();
        DatabaseHandler db = new DatabaseHandler(this);
        GameState gameSavedState=db.getGameState(1);
        game.setPlayer1Turn(gameSavedState.getPlayer1Turn());
        game.setPlayer2Turn(gameSavedState.getPlayer2Turn());
        game.setPlayer1Value(gameSavedState.getPlayer1Value());
        game.setPlayer2Value(gameSavedState.getPlayer2Value());
        game.setRemainingTurn(gameSavedState.getTotalTurnRem());
        if(gameSavedState.getPlayerState()==1){
            game.player1TurnStatus=true;
            playerNameForTurn.setText("Turn: Player 2");
        }else{
            game.player1TurnStatus=false;
            playerNameForTurn.setText("Turn: Player 1");
        }
//setting data into ui
        totalTurn.setText("Total Turn: "+game.getTotalTurn());
        turnRemaining.setText("Remaining Turn: "+game.getRemainingTurn());
        player1Turn.setText("Player 1 Turn Rem.: "+game.getPlayer1Turn());
        player2Turn.setText("Player 2 Turn Rem.: "+game.getPlayer2Turn());
        player1Value.setText("Player 1 Value: "+game.getPlayer1Value());
        player2Value.setText("Player 2 Value: "+game.getPlayer2Value());
        gameState.setText("Game State: Playing");
        if(game.getRemainingTurn()<1){
            if(game.getPlayer1Value()>game.getPlayer2Value())
                gameState.setText("Game Over: Player 1 Win");
            else
                gameState.setText("Game Over: Player 2 Win");
        }
    }

}
