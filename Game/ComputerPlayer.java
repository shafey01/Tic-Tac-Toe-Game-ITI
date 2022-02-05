package Game;

public class ComputerPlayer {

    private NewGame gameToPlay;
    private Move computerMove;
    private boolean computerStarts;

    ComputerPlayer(NewGame gameToPlay, boolean computerStarts) {
        this.gameToPlay = gameToPlay;
        this.computerStarts = computerStarts;
        initComputerMove();
    }

    public void initComputerMove() {
        computerMove = new Move();
        if (computerStarts) {
            computerMove.setType(1);
        }

        else {
            computerMove.setType(2);
        }
    }

    public Move playMove(Move playerMove) {
        if (playerMove == null) {
            computerMove.setMove(getValidMove());
        }

        else {
            if (loseRow(playerMove) != null) {
                computerMove.setMove(loseRow(playerMove));
            }

            else if (loseColumn(playerMove) != null) {
                computerMove.setMove(loseColumn(playerMove));
            }

            else if (loseMainDiagonal(playerMove) != null) {
                computerMove.setMove(loseMainDiagonal(playerMove));
            }

            else if (loseSecondaryDiagonal(playerMove) != null) {
                computerMove.setMove(loseSecondaryDiagonal(playerMove));
            }

            else {
                // System.out.println("here");
                computerMove.setMove(getValidMove());

            }
        }

<<<<<<< HEAD
<<<<<<< HEAD
        // if (computerStarts) {
        // computerMove.setType(1);
        // }

        // else {
        // computerMove.setType(2);
        // }

=======
>>>>>>> parent of b65a3d7 (Delete Game directory)
=======
>>>>>>> parent of b65a3d7 (Delete Game directory)
        return computerMove;

    }

    private Move getValidMove() {
        for (int i = 0; i < NewGame.BOARDSIZE; i++) {
            for (int j = 0; j < NewGame.BOARDSIZE; j++) {
                if (gameToPlay.isCellEmpty(i, j)) {
                    return new Move(i, j);
                }
            }
        }
        return null;
    }

    private Move loseMainDiagonal(Move playerMove) {
        int sameTypeCounter = 0;
        Move loseCell = null;
        for (int iterMainDiagonal = 0; iterMainDiagonal < NewGame.BOARDSIZE; iterMainDiagonal++) {
            if (gameToPlay.getCellType(iterMainDiagonal, iterMainDiagonal) == playerMove.getType()) {
                sameTypeCounter++;
            }

            else if (gameToPlay.isCellEmpty(iterMainDiagonal, iterMainDiagonal)) {
                loseCell = new Move(iterMainDiagonal, iterMainDiagonal);
            }

        }

        if ((sameTypeCounter == NewGame.BOARDSIZE - 1) && (loseCell != null)) {
            return loseCell;
        }
        return null;
    }

    private Move loseSecondaryDiagonal(Move playerMove) {
        int iterColumn = 0;
        int sameTypeCounter = 0;
        Move loseCell = null;
        for (int iterRow = 2; iterRow >= 0; iterRow--) {
            if (gameToPlay.getCellType(iterRow, iterColumn) == playerMove.getType()) {
                sameTypeCounter++;
            }

            else if (gameToPlay.isCellEmpty(iterRow, iterColumn)) {
                loseCell = new Move(iterRow, iterColumn);
            }
            iterColumn++;
        }

        if ((sameTypeCounter == NewGame.BOARDSIZE - 1) && (loseCell != null)) {
            return loseCell;
        }
        return null;
    }

    private Move loseRow(Move playerMove) {
        int sameTypeCounter = 0;
        Move loseCell = null;
        for (int i = 0; i < NewGame.BOARDSIZE; i++) {
            if (gameToPlay.getCellType(playerMove.getRowIndex(), i) == playerMove.getType()) {
                sameTypeCounter++;
            }

            else if (gameToPlay.isCellEmpty(playerMove.getRowIndex(), i)) {
                loseCell = new Move(playerMove.getRowIndex(), i);
            }
        }

        if ((sameTypeCounter == NewGame.BOARDSIZE - 1) && (loseCell != null)) {
            return loseCell;
        }

        return null;
    }

    private Move loseColumn(Move playerMove) {
        int sameTypeCounter = 0;
        Move loseCell = null;

        for (int i = 0; i < NewGame.BOARDSIZE; i++) {
            if (gameToPlay.getCellType(i, playerMove.getColumnIndex()) == playerMove.getType()) {
                sameTypeCounter++;
            }

            else if (gameToPlay.isCellEmpty(i, playerMove.getColumnIndex())) {
                loseCell = new Move(i, playerMove.getColumnIndex());
            }
        }

        if ((sameTypeCounter == NewGame.BOARDSIZE - 1) && (loseCell != null)) {
            return loseCell;
        }
        return null;
    }
}
