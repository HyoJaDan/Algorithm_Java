package main.java.org.swea.Problem5656;
import java.io.*;
import java.util.*;

class Position {
    int row, col, value;
    
    public Position(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }
}

public class Solution {
    static int maxMoves, width, height, minBlocks;
    static int[][] grid;
    static int[] rowDir = {-1, 1, 0, 0};
    static int[] colDir = {0, 0, 1, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= testCases; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            maxMoves = Integer.parseInt(st.nextToken());
            width = Integer.parseInt(st.nextToken());
            height = Integer.parseInt(st.nextToken());
            
            grid = new int[height + 1][width + 1];
            int blockCount = 0;
            
            for (int i = 1; i <= height; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= width; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                    if (grid[i][j] > 0) blockCount++;
                }
            }

            minBlocks = blockCount + 1;
            int[][] backupGrid = new int[height + 1][width + 1];
            
            for (int x = 1; x <= height; x++) {
                for (int y = 1; y <= width; y++) {
                    backupGrid[x][y] = grid[x][y];
                }
            }
            
            for (int i = 1; i <= width; i++) {
                for (int j = 1; j <= height; j++) {
                    if (grid[j][i] != 0) {
                        destroyBlocks(0, j, i, blockCount);
                        
                        for (int x = 1; x <= height; x++) {
                            for (int y = 1; y <= width; y++) {
                                grid[x][y] = backupGrid[x][y];
                            }
                        }
                        
                        break;
                    }
                }
            }
            
            System.out.println("#" + tc + " " + minBlocks);
        }
    }
    
    static void destroyBlocks(int destroyed, int row, int col, int remaining) {
        Queue<Position> queue = new LinkedList<>();
        int currentRow = row;
        int currentCol = col;
        
        if (destroyed == maxMoves) {
            if (remaining < minBlocks)
                minBlocks = remaining;
            return;
        }
            
        int[][] tempGrid = new int[height + 1][width + 1];
        
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= width; j++) {
                tempGrid[i][j] = grid[i][j];
            }
        }
        
        queue.add(new Position(currentRow, currentCol, grid[currentRow][currentCol]));
        grid[currentRow][currentCol] = 0;
        remaining--;
        
        while (!queue.isEmpty()) {
            Position current = queue.poll();
            
            for (int i = 1; i < current.value; i++) {
                for (int j = 0; j < 4; j++) {
                    int nextRow = current.row + i * rowDir[j];
                    int nextCol = current.col + i * colDir[j];
                    
                    if (nextRow <= 0 || nextRow > height || nextCol <= 0 || nextCol > width)
                        continue;
                    
                    if (grid[nextRow][nextCol] == 0)
                        continue;
                    
                    else if (grid[nextRow][nextCol] > 1)
                        queue.add(new Position(nextRow, nextCol, grid[nextRow][nextCol]));
                    
                    grid[nextRow][nextCol] = 0;
                    remaining--;
                }
            }
        }
        
        for (int j = 1; j <= width; j++) {
            int dropPosition = height;
            while (dropPosition > 1) {
                if (grid[dropPosition][j] == 0) {
                    int next = dropPosition - 1;
                    while (next > 1 && grid[next][j] == 0) 
                        next--;
                    
                    grid[dropPosition][j] = grid[next][j];
                    grid[next][j] = 0;
                }
                dropPosition--;
            }
        }
        
        if (remaining <= 0) {
            minBlocks = 0;
            return;
        }
        
        for (int x = 1; x <= height; x++) {
            for (int y = 1; y <= width; y++) {
                tempGrid[x][y] = grid[x][y];
            }
        }
        
        for (int i = 1; i <= width; i++) {
            for (int j = 1; j <= height; j++) {
                if (grid[j][i] != 0) {
                    destroyBlocks(destroyed + 1, j, i, remaining);
                    for (int x = 1; x <= height; x++) {
                        for (int y = 1; y <= width; y++) {
                            grid[x][y] = tempGrid[x][y];
                        }
                    }
                    break;
                }
            }
        }
    }
}
