package br.com.guilhermealvessilve.graph.algorithms.minimumisland;

import java.util.HashSet;
import java.util.Set;

import static java.lang.Integer.MAX_VALUE;

public class MinimumIslandDepthFirstSearch {

    private static final char WATER = 'W';

    public static void main(String[] args) {

        char[][] grid1 = {
            {'W', 'L', 'W', 'W', 'W'},
            {'W', 'L', 'W', 'W', 'W'},
            {'W', 'W', 'W', 'L', 'W'},
            {'W', 'W', 'L', 'L', 'W'},
            {'L', 'W', 'W', 'L', 'L'},
            {'L', 'L', 'W', 'W', 'W'},
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
            {'L', 'L'},
            {'W', 'W'},
            {'W', 'L'}
        };

        char[][] grid5 = {
            {'W', 'W'},
            {'W', 'W'},
            {'W', 'W'},
        };

        System.out.println("minimumIsland(grid1): " + minimumIsland(grid1)); // -> 2
        System.out.println("minimumIsland(grid2): " + minimumIsland(grid2)); // -> 1
        System.out.println("minimumIsland(grid3): " + minimumIsland(grid3)); // -> 9
        System.out.println("minimumIsland(grid4): " + minimumIsland(grid4)); // -> 1
        System.out.println("minimumIsland(grid5): " + minimumIsland(grid5)); // -> 0
    }

    public static int minimumIsland(char[][] grid) {

        int minSize = MAX_VALUE;
        var visited = new HashSet<String>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {

                int size = exploreSize(grid, row, col, visited);
                if (size > 0 && minSize > size) minSize = size;
            }
        }

        if (MAX_VALUE == minSize) return 0;
        return minSize;
    }

    private static int exploreSize(char[][] grid, int row, int col, Set<String> visited) {
        if (row < 0 || row >= grid.length) return 0;
        if (col < 0 || col >= grid[row].length) return 0;
        if (WATER == grid[row][col]) return 0;

        var pos = row + "," + col;
        if (visited.contains(pos)) return 0;
        visited.add(pos);

        int size = 1;
        size += exploreSize(grid, row + 1, col, visited);
        size += exploreSize(grid, row - 1, col, visited);
        size += exploreSize(grid, row, col + 1, visited);
        size += exploreSize(grid, row, col - 1, visited);

        return size;
    }
}
