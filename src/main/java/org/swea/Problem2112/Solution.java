package main.java.org.swea.Problem2112;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int D, W, K, minCount;
	static int[][] film;
	static int[][] backupFilm;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			film = new int[D][W];
			backupFilm = new int[D][W];

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
					backupFilm[i][j] = film[i][j];
				}
			}

			minCount = Integer.MAX_VALUE;
			dfs(0, 0);
			System.out.println("#" + t + " " + minCount);
		}
	}

	static void dfs(int depth, int count) {
		if (checkFilm()) {
			minCount = Math.min(minCount, count);
			return;
		}
		if (depth == D || count >= minCount) return;

		dfs(depth + 1, count); // 현재 막에 약품 투입 없이 다음으로 넘어감

		applyMedicine(depth, 0); // A 약품 투입
		dfs(depth + 1, count + 1);
		restoreFilm(depth); // 원래 상태로 복구

		applyMedicine(depth, 1); // B 약품 투입
		dfs(depth + 1, count + 1);
		restoreFilm(depth); // 원래 상태로 복구
	}

	static boolean checkFilm() {
		if(K==1) return true;
		for (int j = 0; j < W; j++) {
			boolean passed = false;
			int maxCount = 1;
			for (int i = 1; i < D; i++) {
				if (film[i][j] == film[i - 1][j]) {
					maxCount++;
					if (maxCount >= K) {
						passed = true;
						break;
					}
				} else {
					maxCount = 1;
				}
			}
			if (!passed) return false;
		}
		return true;
	}

	static void applyMedicine(int depth, int type) {
		for (int j = 0; j < W; j++) {
			film[depth][j] = type;
		}
	}

	static void restoreFilm(int depth) {
		for (int j = 0; j < W; j++) {
			film[depth][j] = backupFilm[depth][j];
		}
	}
}
