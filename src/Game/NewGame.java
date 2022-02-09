package Game;

public class NewGame {

    public static final int BOARDSIZE = 3;
    private final int MIN_MOVES_TO_WIN = 5;
    private int[][] board = new int[BOARDSIZE][BOARDSIZE];
    private int numPlayedMoves;

    public NewGame() {
        numPlayedMoves = 0;
        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                board[i][j] = 0;
            }
        }
    }
  public int[][] getBoard() {
        return this.board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }
    public boolean isCellEmpty(int rowIndex, int columnIndex) {
        return board[rowIndex][columnIndex] == 0;
    }

    public int getCellType(int rowIndex, int columnIndex) {
        return board[rowIndex][columnIndex];
    }

    public void displayBoard() {
        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                System.out.print(board[i][j]);
            }
            System.out.print("\n");
        }

        System.out.println("\n");
    }

    public int insertAndCheckMove(Move move) {
        if (isValidMove(move) == false) {
            return 4;
        }
        board[move.getRowIndex()][move.getColumnIndex()] = move.getType();
        numPlayedMoves++;
        displayBoard();
        if (numPlayedMoves >= MIN_MOVES_TO_WIN) {

            if (isWon(move)) {
                return move.getType();
            }

            if (numPlayedMoves == BOARDSIZE * BOARDSIZE) {
                return 0;
            }
        }

        return 3;
    }

    private boolean isValidMove(Move move) {
        return move.getRowIndex() >= 0 && move.getRowIndex() <= BOARDSIZE
                && move.getColumnIndex() >= 0 && move.getColumnIndex() <= BOARDSIZE
                && isCellEmpty(move.getRowIndex(), move.getColumnIndex());
    }

    private boolean wonMainDiagonal(Move move) {
        for (int iterMainDiagonal = 0; iterMainDiagonal < BOARDSIZE; iterMainDiagonal++) {
            if (board[iterMainDiagonal][iterMainDiagonal] != move.getType()) {
                return false;
            }
        }
        return true;
    }

    private boolean wonSecondaryDiagonal(Move move) {

        int iterColumn = 0;
        for (int iterRow = 2; iterRow >= 0; iterRow--) {
            if (board[iterRow][iterColumn] != move.getType()) {
                return false;
            }
            iterColumn++;
        }
        return true;
    }

    private boolean wonRow(Move move) {

        for (int i = 0; i < BOARDSIZE; i++) {
            if (board[move.getRowIndex()][i] != move.getType()) {
                return false;
            }
        }
        return true;
    }

    private boolean wonColumn(Move move) {
        for (int i = 0; i < BOARDSIZE; i++) {
            if (board[i][move.getColumnIndex()] != move.getType()) {
                return false;
            }
        }
        return true;
    }

    private boolean isWon(Move move) {

        return wonColumn(move)
                || wonRow(move)
                || wonMainDiagonal(move)
                || wonSecondaryDiagonal(move);

    }


}
