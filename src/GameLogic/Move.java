package Game;

public class Move {
    private int rowIndex;
    private int columnIndex;
    private int type;

    public Move() {
        this.rowIndex = this.columnIndex = this.type = -1;
    }

    public Move(int rowIndex, int columnIndex, int type) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.type = type;
    }

    public Move(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public Move(int type) {
        this.type = type;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getType() {
        return type;
    }

    public void setPosition(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setMove(Move move) {
        this.rowIndex = move.rowIndex;
        this.columnIndex = move.columnIndex;
    }
}
