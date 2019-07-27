# Android_DiceGame
Dice Game developed for Android 

# Dice Game

# Created by Maj Alshibly


![alt text](https://i.imgur.com/1G9HAI3.png)


1. Two dice that roll which have been animated to show a rolling effect

1. Start Roll: This button switches to &quot;Stop roll&quot; when clicked and is the main trigger that allows the dice to roll and play the game.

1. Reload Game: This function allows to save reload the previously saved state which saves the stats of the game such as the remaining turns, scores and total turn played.

1. Save Game: This button is what saves the game in the first place which should be pressed when you what to exit the game so you are able to reload the data again

1. Stats: Shows the total turn which can be changed in the code, remaining turns, how many turns each player has made and the pink bar below shows whose turn it is. The stats below that show the score of the players in real time, the bar below shows the state of the game which changes when the game is over.

1. Reset game: Once pressed, the stats of the game are reproduced back to their original state to be played again.

1. End Game: This function is pressed when the players would like to exit the game without the need to use the default android function to exit the game.

![alt text](https://i.imgur.com/aRPThDr.png)

These are the images of the dice which have been created to be used in the game.

# Development Process

The developed code is solely made with Java. Every app that is created through android development is java including its APIs. The game that was been ported has been divided into separate paths to make it easier to be read instead of containing it all into one path which would have a wall of text that nobody wants. I have also made use of a GLKit for improved textures within the game. The paths are divided by their respectful sections of the game, this makes it easier for other people to read the code and move through them easier.

The main objective of the game is to win through luck by achieving the highest numbers with the dice. We created a piece of code to generate random numbers to determine the value when rolling dice

The game is played until round 10 and then it outputs the winner by choosing the player with the highest amount points. The game ends and the user is allowed to select either quit game or reset the match to play again. Here is the code which ends the game after the remaining round which should be less than round 1:

After the round ends. The user is allowed to reset the game. This works by retrieving the previous values that the game started with. The code which retrieves the points are here:

All the values are reset and the players are able to play again.

Once the game has been launched, it should begin by showing the default stats and end-requirements. We have begun by adding the total turns of the game which should be 10 and the required amount of roles for each player which should 5 tries each. The &#39;totalTurnRemaining&#39; displays the amount of turns that are remaining until the game is over. Each player begins with 0 points which is shown as the &#39;playerXValue=0&#39;

The &#39;resetGame&#39; function is displayed when the user uses the reset game button which retrieves the original stats of the game once it has been played before.

We have designed the cube with the help of a GLKit and we applied the sizes of the dice by using the vertices and indices

Rolling the dice was a requirement for the game to work, therefore we added an animated dice to show the effect of a person rolling the rice when the &#39;Start Role&#39; button is clicked. Once clicked, it shows an animated effect for the dice that display a rolling effect.

We have added the data storage feature to the game as two functions which allow the game&#39;s stats to be saved manually once a button is clicked and then be able to be restored by pressing Reload Game

Once the Save Game button is pressed, the stats which are listed are then saved into the database. This takes effect after the game has been played to be able to be loaded again once the game is reopened

Once the game has been closed, the user is able to reopen it again and even recover the past data once the &quot;Reload Game&quot; button is pressed. The code below shows how it is done.

The save data can only be used once and if the user clicks on the Save Game button while there was previously saved data, it is automatically dropped and removed
