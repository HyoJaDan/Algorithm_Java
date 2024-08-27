package main.java.org.BackJoon.Implementation.Problem17135;

/**
 * 2024-08-27 02:30~04:47
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Enimy{
	int y;
	int x;
	public Enimy(int y,int x) {
		this.y=y;
		this.x=x;
	}
    Enimy(Enimy other) {
        this.y = other.y;
        this.x = other.x;
    }
	public void move() {
		this.y+=1;
	}
	// 궁수 좌표 받음
	public int hitDistance(int y, int x,int d) {
		int hit = Math.abs(y-this.y)+Math.abs(x-this.x);
		if(hit <= d) return hit;
		else return 0;
	}
}

public class Main {
	private static ArrayList<Enimy> enimiesSave = new ArrayList<>();
	private static int N,M,D,ans=0;
	private static int[][] map;
	private static int[] gungsu = new int[3];
	public static void main(String[] args) throws IOException{
		init();
		
		startGame(0,0);
		System.out.println(ans);
	}
	private static void startGame(int level, int now) {
		if(level==3) {
			LinkedList<Enimy> enimies = new LinkedList<>();
	        for (Enimy e : enimiesSave) {
	            enimies.add(new Enimy(e)); // 각 요소를 깊은 복사하여 새 리스트에 추가
	        }
			ans = Math.max(ans, shoot(enimies));
			return;
		}
		
		for(int i=now;i<M;i++) {
			gungsu[level]=i;
			startGame(level+1,i+1);
		}
	}
	private static int shoot(LinkedList<Enimy> enimies) {
		int ans=0;
		for(int i=0;i<=N;i++) {	
			Set<Integer> removeIndex = new TreeSet<>();
			for(int currentGungsu : gungsu) {
				int minHitDistance = Integer.MAX_VALUE, minHitTarget=-1;
				
				for(int j=0; j<enimies.size(); j++) {
					int hitDistance = enimies.get(j).hitDistance(N, currentGungsu, D);
					if(hitDistance!=0 && hitDistance < minHitDistance) {
						minHitDistance = hitDistance;
						minHitTarget = j;
					}
				}
				if (minHitTarget!=-1) removeIndex.add(minHitTarget);
			}


			for (int j = enimies.size() - 1; j >= 0; j--) {
			    if (removeIndex.contains(j)) {
			        ans++;
			        enimies.remove(j);
			    }
			}
			for (int j = enimies.size() - 1; j >= 0; j--) {
				enimies.get(j).move();
				if(enimies.get(j).y==N) enimies.remove(j);
			}
		}
		
		return ans;
	}
	public static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		for(int i=N-1;i>=0;i--) {
//			for(int j=0;j<M;j++) {
//				if(map[i][j]==1){
//					enimiesSave.add(new Enimy(i,j));
//				}
//			}
//		}
		for(int j=0;j<M;j++) {
			for(int i=N-1;i>=0;i--) {
				if(map[i][j]==1){
					enimiesSave.add(new Enimy(i,j));
				}
			}
		}
	}
}

