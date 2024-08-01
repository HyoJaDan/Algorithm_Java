package org.BackJoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//class Point{
//	int y;
//	int x;
//	public Point(int y,int x) {
//		this.y=y;
//		this.x=x;
//	}
//}
public class Problem20058 {
	static int mapSize;
	static int[][] direct= {{-1,0},{1,0},{0,1},{0,-1}};
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        mapSize = (int) Math.pow(2,N);
        int[][] map = new int[mapSize][mapSize];

        for(int i=0;i<mapSize;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<mapSize;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<Q;i++){
            int convertSize = 1 << Integer.parseInt(st.nextToken());
            rotate(map, convertSize);
//            printMap(map,"rotate");
            meltingIce(map);
//            printMap(map,"melting?Ice");
        }
        printMap2(map);
    }
    static int BFS(int[][]map, boolean[][] visited,int startY, int startX) {
    	Queue<Point> queue = new LinkedList<>();
    	queue.add(new Point(startY,startX));
    	visited[startY][startX]=true;
    	
    	int cnt=0;
    	while(!queue.isEmpty()) {
    		Point current = queue.poll();
    		cnt++;
    		
    		for(int i=0;i<4;i++) {
    			int dy = current.y+direct[i][0];
    			int dx = current.x+direct[i][1];
    			
    			if(dy<0 || dx<0 || dy>=mapSize || dx>= mapSize)
    				continue;
    			if(visited[dy][dx]==true || map[dy][dx]< 1)
    				continue;
    			
    			visited[dy][dx]=true;
    			queue.add(new Point(dy,dx));
    		}
    	}
    	return cnt;
    }
    
    
    static void printMap2(int[][]map) {
    	int ans=0;
    	boolean[][] visited	= new boolean[mapSize][mapSize];
    	int maxValue=0;
    	for(int i =0; i<mapSize; i++) {
        	for(int j=0;j<mapSize;j++) {
        		ans+=map[i][j];
        		
        		if(map[i][j]>0 && visited[i][j]==false) {
        			int value= BFS(map,visited,i,j);
        			if(value>maxValue)
        				maxValue=value;
        		}

        	}
        }
    	System.out.println(ans);
    	System.out.println(maxValue);
    }
    
    static void printMap(int[][]map,String str) {
    	System.out.println("================="+str+"========================");
    	int ans=0;
    	for(int k=0;k<mapSize;k++) {
        	for(int j=0;j<mapSize;j++) {
        		System.out.printf(map[k][j]+" ");
        		ans+=map[k][j];
        	}
        	System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println(ans);
    }
    
    static void meltingIce(int[][] map) {
    	ArrayList<Point> meltPoints = new ArrayList<>();
    	for(int i=0;i<mapSize;i++) {
    		for(int j=0;j<mapSize;j++) {
    			int numOfDry=0;
    			for(int k=0;k<4;k++) {
    				int y= i+direct[k][0];
    				int x= j+direct[k][1];
    				
    				if(x<0 || y<0 || x>=mapSize || y>=mapSize) {
    					numOfDry++;
    					continue;
    				}
    				if(map[y][x]==0)
    					numOfDry++;
    			}
    			if(numOfDry>=2)
    			{
    				meltPoints.add(new Point(i,j));
    			}
    		}
    	}
    	for(Point current : meltPoints){
    		map[current.y][current.x]-=1;
    		if(map[current.y][current.x]<0)map[current.y][current.x]=0;
    	}
    }
    /**
     * 시작 지점을 지정하는 함수
     * @param map
     * @param size
     */
    static void rotate(int[][] map,int convertSize) {
    	for(int i=0; i<mapSize; i+=convertSize) {
    		for(int j=0; j<mapSize; j+=convertSize) {
    			rotate90(map, convertSize, i, j);
    		}
    	}
    }
    static void rotate90(int[][] map, int convertSize, int startY, int startX) {
    	for(int i=0; i<convertSize/2; i++) {
    		for(int j=i; j<convertSize-i-1; j++) 
    		{
    			int temp = map[startY + i][startX + j]; // 왼쪽위 저장
    			// 왼쪽위 = 왼쪽아래
    			map[startY + i][startX + j] = map[startY + convertSize -1 -j][startX + i];
    			//왼쪽아래 = 오른쪽 아래
    			map[startY + convertSize -1 -j][startX + i] = map[startY + convertSize -1 -i][startX + convertSize-1-j];
    			//오른쪽 아래 = 오른쪽 위
    			map[startY + convertSize -1 -i][startX + convertSize-1-j] = map[startY + j][startX + convertSize -i -1];
    			//오른쪽 위 = 왼쪽위(temp)
    			map[startY + j][startX + convertSize -i -1] = temp;
    		}
    	}
    }
   
}
