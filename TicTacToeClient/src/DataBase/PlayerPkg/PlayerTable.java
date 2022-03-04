/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase.PlayerPkg;

/**
 *
 * @author user
 */
public class PlayerTable {

    int gameId;
    int playerId;
    int gameScore;

    public PlayerTable(int gameId, int playerId, int gameScore) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.gameScore = gameScore;
    }

    public PlayerTable() {
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }

    public int getGameId() {
        return gameId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getGameScore() {
        return gameScore;
    }

}
