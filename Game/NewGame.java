package Game;

public class NewGame {
    private int[][] board = new int[3][3];

    public NewGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 0;
            }
        }
    }

    public void displayBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public void Insert(int rowIndex, int columnIndex, int choice) {
        board[rowIndex][columnIndex] = choice;
    }

    // private boolean hasUp(int rowIndex) {
    // return (rowIndex - 1) >= 0;
    // }

    // private boolean hasdown(int rowIndex) {
    // return (rowIndex + 1) >= 0;
    // }

    // private boolean hasLeft(int columnIndex) {
    // return (columnIndex - 1) >= 0;
    // }

    // private boolean hasRight(int columnIndex) {
    // return (columnIndex - 1) >= 0;
    // }

    private boolean checkDiagonals(int choice) {
        // int i = 0, j = 0;
        boolean diagonal1, diagonal2;
        for (int iterDiagonal1 = 0; iterDiagonal1 < 3; iterDiagonal1++)
        {
            if (board[iterDiagonal1][iterDiagonal1] != choice)
            {
                diagonal1 = false;
            }
        }

        for (int iterDiagonal2 = 2; iterDiagonal2 >= 0; iterDiagonal2--)
        {
            if (board[iterDiagonal1][iterDiagonal1] != choice)
            {
                diagonal1 = false;
            }

        }
        return
    }

    private boolean checkRow(int rowIndex, int choice) {

        for (int i = 0; i < 3; i++) {
            if (board[rowIndex][i] != choice) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int columnIndex, int choice) {
        for (int i = 0; i < 3; i++) {
            if (board[i][columnIndex] != choice) {
                return false;
            }
        }
        return true;
    }

    public boolean checkGameOver(int rowIndex, int columnIndex) {
        if 
        
        return false;

    }

}
