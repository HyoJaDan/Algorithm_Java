package main.java.org.swea.Problem4014;

import java.io.*;
import java.util.*;

class Point{
	int height;
	int length;
	public Point(int height,int length) {
		this.height=height;
		this.length=length;
	}
}

public class Solution {
	static int[][] map;
	static int length,n;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=t;tc++) {
			init(br);
			
			int ans=0;
			ans += calculateRow();

			ans += calculateColumn();
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	private static int calculateRow() {
		int ans=0;
		for(int i=0;i<n;i++) {
			List<Point> mountain = new ArrayList<>();
			Point point = new Point(map[i][0],1);
			for(int j=1;j<n;j++) {
				if(map[i][j]==point.height)
					point.length++;
				else {
					mountain.add(point);
					point = new Point(map[i][j],1);
				}
			}
			mountain.add(point);
			
			boolean cango=true;
			for(int j=0;j<mountain.size()-1;j++) {
				Point before = mountain.get(j);
				Point after = mountain.get(j+1);
				if(Math.abs(before.height - after.height)>1) {
					cango=false;
					break;
				}
				else {
					if(before.height>after.height) {
						if(after.length<length) {
							cango=false;
							break;
						} else {
							after.length-=length;
						}
					} else if(before.height<after.height) {
						if(before.length<length) {
							cango=false;
							break;
						} else {
							before.length-=length;
						}
					}
				}
			}
			if(cango) {
				ans++;
				
			}
		}

		return ans;
	}
	private static int calculateColumn() {
		int ans=0;
		for(int j=0;j<n;j++) {
			List<Point> mountain = new ArrayList<>();
			Point point = new Point(map[0][j],1);
			for(int i=1;i<n;i++) {
				if(map[i][j]==point.height)
					point.length++;
				else {
					mountain.add(point);
					point = new Point(map[i][j],1);
				}
			}
			mountain.add(point);
			
			boolean cango=true;
			for(int i=0;i<mountain.size()-1;i++) {
				Point before = mountain.get(i);
				Point after = mountain.get(i+1);
				if(Math.abs(before.height - after.height)>1) {
					cango=false;
					break;
				}
				else {
					if(before.height>after.height) {
						if(after.length<length) {
							cango=false;
							break;
						} else {
							after.length-=length;
						}
					} else if(before.height<after.height) {
						if(before.length<length) {
							cango=false;
							break;
						} else {
							before.length-=length;
						}
					}
				}
			}
			if(cango) {
				ans++;

			}
		}

		return ans;
	}
	public static void init(BufferedReader br) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		length = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
