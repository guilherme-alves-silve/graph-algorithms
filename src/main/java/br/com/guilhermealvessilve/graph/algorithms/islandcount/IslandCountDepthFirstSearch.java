package br.com.guilhermealvessilve.graph.algorithms.islandcount;

import java.util.HashSet;
import java.util.Set;

public class IslandCountDepthFirstSearch {

    private static final char WATER = 'W';

    public static void main(String[] args) {

        char[][] grid1 = {
            {'W', 'L', 'W', 'W', 'W'},
            {'W', 'L', 'W', 'W', 'W'},
            {'W', 'W', 'W', 'L', 'W'},
            {'W', 'W', 'L', 'L', 'W'},
            {'L', 'W', 'W', 'L', 'L'},
            {'L', 'L', 'W', 'W', 'W'}
        };
        
        char[][] grid2 = {
          {'L', 'W', 'W', 'L', 'W'},
          {'L', 'W', 'W', 'L', 'L'},
          {'W', 'L', 'W', 'L', 'W'},
          {'W', 'W', 'W', 'W', 'W'},
          {'W', 'W', 'L', 'L', 'L'},
        };

        char[][] grid3 = {
          {'L', 'L', 'L'},
          {'L', 'L', 'L'},
          {'L', 'L', 'L'},
        };

         char[][] grid4 = {
          {'W', 'W'},
          {'W', 'W'},
          {'W', 'W'},
        };

        System.out.println("islandCount(grid1): " + islandCount(grid1)); // -> 3
        System.out.println("islandCount(grid2): " + islandCount(grid2)); // -> 4
        System.out.println("islandCount(grid3): " + islandCount(grid3)); // -> 1
        System.out.println("islandCount(grid4): " + islandCount(grid4)); // -> 0
    }

    public static int islandCount(char[][] grid) {

        int count = 0;
        var visited = new HashSet<String>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (explore(grid, row, col, visited)) {
                    ++count;
                }
            }
        }

        return count;
    }

    private static boolean explore(char[][] grid, int row, int col, Set<String> visited) {
        if (row < 0 || grid.length <= row) return false;
        if (col < 0 || grid[row].length <= col) return false;
        if (WATER == grid[row][col]) return false;

        var key = row + "," + col;
        if (visited.contains(key)) return false;
        visited.add(key);

        explore(grid, row + 1, col, visited);
        explore(grid, row - 1, col, visited);
        explore(grid, row, col + 1, visited);
        explore(grid, row, col - 1, visited);

        return true;
    }
}
