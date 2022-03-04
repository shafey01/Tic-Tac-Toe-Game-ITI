/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase.GamePkg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Menna
 */
public class GameTable {

    int id;
    int winnerId;
    int[][] map = null;

    public GameTable() {
        this.map = new int[3][3];
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWinnerId(int winnerId) {
        this.winnerId = winnerId;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public int getId() {
        return id;
    }

    public int getWinnerId() {
        return winnerId;
    }

    public int[][] getMap() {
        return map;
    }

    public static void main(String[] args) {
        GameTable g = new GameTable();
        g.map[0][0] = 1;
        g.map[0][1] = 2;
        g.map[0][2] = 0;
        g.map[1][0] = 0;
        g.map[1][1] = 1;
        g.map[1][2] = 2;
        g.map[2][0] = 1;
        g.map[2][1] = 1;
        g.map[2][2] = 1;
        String s = Arrays.deepToString(g.map);
        System.out.println(s);
        String[] rows = s.split("\\[|],|\\]");
        for (String w : rows) {
            System.out.println(w);
        }
        String[][] matrix = new String[rows.length][];
        int r = 0, i = 0;
        for (String row : rows) {

            if (!(row.equals("")) && row.length() != 1) {
                matrix[r++] = row.split(",\\s");
            }

        }
        for (r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                g.map[r][c] = Integer.parseInt(matrix[r][c]);
            }
        }
    }
}
