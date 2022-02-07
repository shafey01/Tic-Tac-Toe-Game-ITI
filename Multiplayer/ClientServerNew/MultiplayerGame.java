package ClientServerNew;

import Game.Move;
import Game.NewGame;

public class MultiplayerGame {

    private NewGame gameToPlay;

    public MultiplayerGame() {
        gameToPlay = new NewGame();
    }

    public int playMove(Move newMove) {
        int gameStatus;
        gameStatus = gameToPlay.insertAndCheckMove(newMove);
        return gameStatus;

    }
}
