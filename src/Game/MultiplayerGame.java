/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

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
 public int[][] getBoard() {
        return this.gameToPlay.getBoard();
    }

    public void setBoard(int[][] board) {
        this.gameToPlay.setBoard(board);
    }
}

