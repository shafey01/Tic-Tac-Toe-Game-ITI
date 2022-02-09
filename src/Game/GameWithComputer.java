package Game;

public class GameWithComputer {
    private NewGame gameToPlay;
    private Move playerMove;
    private Move computerMove;
    // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    // private int gameStatus;
    // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    private boolean computerStarts;
    private ComputerPlayer computer;

    public GameWithComputer(Boolean computerStarts) {
        gameToPlay = new NewGame();
        this.computerStarts = computerStarts;
        computer = new ComputerPlayer(gameToPlay, this.computerStarts);
        initPlayerMove();
        computerFirstMove();
        // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
        // gameStatus = 3;
        // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    }

    private void computerFirstMove() {
        if (computerStarts) {
            computerMove = computer.playMove(null);
            gameToPlay.insertAndCheckMove(computerMove);
        }
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

    public Move getComputerMove() {
        return computerMove;
    }

    // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    public int playMove(Move newMove) {

        playerMove.setPosition(newMove.getRowIndex(), newMove.getColumnIndex());
        int gameStatus = gameToPlay.insertAndCheckMove(playerMove);
        if (gameStatus == playerMove.getType()) {
            return 1;
        }
        return gameStatus;
    }

    // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    public int AIplayMove() {
        computerMove = computer.playMove(playerMove);
        int gameStatus = gameToPlay.insertAndCheckMove(computerMove);
        System.out.println("Statue" + gameStatus);
        if (gameStatus == computerMove.getType()) {
            return -1;
        }
        return gameStatus;
    }

//  public int[][] getBoard() {
//        return this.gameToPlay.getBoard();
//    }
//    
//    public void setBoard(int[][] board) {
//        this.gameToPlay.setBoard(board);
////        computer.setBoard(board);
//    }
    // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
}
