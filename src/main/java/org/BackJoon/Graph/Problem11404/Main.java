package main.java.org.BackJoon.Graph.Problem11404;

public class Main {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        int[] result = zigzagTraverse(matrix);

        for (int num : result) {
            System.out.print(num + " ");
        }
    }

    public static int[] zigzagTraverse(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[] result = new int[n * m];
        int index = 0;

        for (int d = 0; d < n + m - 1; d++) {
            if (d % 2 == 0) {
                int r = d < n ? d : n - 1;
                int c = d - r;
                while (r >= 0 && c < m) {
                    result[index++] = matrix[r--][c++];
                }
            } else {
                int c = d < m ? d : m - 1;
                int r = d - c;
                while (c >= 0 && r < n) {
                    result[index++] = matrix[r++][c--];
                }
            }
        }

        return result;
    }
}
