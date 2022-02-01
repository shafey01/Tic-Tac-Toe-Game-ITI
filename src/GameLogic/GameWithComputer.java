package Game;

import java.util.Scanner;

public class GameWithComputer {
    private NewGame gameToPlay;
    private Move playerMove;
    private Move computerMove;
    private int gameStatus;
    private boolean computerStarts;
    private ComputerPlayer computer;
    private Scanner scanUserInput;

    GameWithComputer() {
        scanUserInput = new Scanner(System.in);
        System.out.println("press 1 for x or 0 for o");
        if (scanUserInput.nextInt() == 1) {
            this.computerStarts = false;
        } else {
            this.computerStarts = true;
        }
        initPlayerMove();
        gameToPlay = new NewGame();
        computer = new ComputerPlayer(gameToPlay, this.computerStarts);
        gameStatus = 3;
        startGame();

    }

    private void initPlayerMove() {
        playerMove = new Move();
        if (computerStarts) {
            playerMove.setType(2);
        }

        else {
            playerMove.setType(1);
        }
    }

    private void startGame() {

        int rowIndex, columnIndex;
        if (computerStarts) {
            computerMove = computer.playMove(null);
            gameToPlay.insertAndCheckMove(computerMove);
        }

        while (gameStatus == 3) {

            do {
                System.out.println("Enter Row Index");
                rowIndex = scanUserInput.nextInt();
                System.out.println("Enter column Index");
                columnIndex = scanUserInput.nextInt();
                playerMove.setPosition(rowIndex, columnIndex);
                gameStatus = gameToPlay.insertAndCheckMove(playerMove);
                if (gameStatus == -1) {
                    System.out.println("Invalid Move");
                }
            } while (gameStatus == -1);

            if (gameStatus == 3) {
                computerMove = computer.playMove(playerMove);
                gameStatus = gameToPlay.insertAndCheckMove(computerMove);
            }
        }

        if (gameStatus == 0) {
            System.out.println("Game Over Nobody wins");
        }

        else if (gameStatus == playerMove.getType()) {
            System.out.println("Game over you win");
        }

        else {
            System.out.println("Game Over Computer wins");
        }

    }

}
