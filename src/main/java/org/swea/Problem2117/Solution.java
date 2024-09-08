package main.java.org.swea.Problem2117;

/**
 * 2024-09-03 14:33
 * @author SSAFY
 *
 */
import java.util.ArrayList;
import java.util.Scanner;

class Point {
	int row;
	int col;
	Point(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

public class Solution {


	static ArrayList<Point> points;
	static int gridSize, income;
	static int[] operationCost = new int[41];
	static int maxProfit;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int testCases = scanner.nextInt();

		for (int k = 1; k < 41; k++) {
			operationCost[k] = k * k + (k - 1) * (k - 1);
		}

		for (int t = 1; t <= testCases; t++) {
			gridSize = scanner.nextInt();
			income = scanner.nextInt();
			points = new ArrayList<>();
			maxProfit = 0;

			for (int i = 0; i < gridSize; i++) {
				for (int j = 0; j < gridSize; j++) {
					if (scanner.nextInt() == 1) {
						points.add(new Point(i, j));
					}
				}
			}

			calculateMaxProfit();
			System.out.println("#" + t + " " + maxProfit);
		}

	}

	private static void calculateMaxProfit() {

		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				int[] customerCounts = new int[gridSize + gridSize];
				calculateDistances(customerCounts, i, j);

				int totalCustomers = 0;
				for (int k = 0; k < gridSize + gridSize; k++) {
					totalCustomers += customerCounts[k];
					if (totalCustomers * income >= operationCost[k + 1] && totalCustomers > maxProfit) {
						maxProfit = totalCustomers;
					}
				}
			}
		}
	}

	private static void calculateDistances(int[] customerCounts, int row, int col) {

		for (int i = 0; i < points.size(); i++) {
			customerCounts[Math.abs(points.get(i).row - row) + Math.abs(points.get(i).col - col)]++;
		}

	}
}
