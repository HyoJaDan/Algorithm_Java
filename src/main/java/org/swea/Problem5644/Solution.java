package main.java.org.swea.Problem5644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class PQ implements Comparable<PQ>{
	int cost;
	int target;
;
	public PQ(int cost, int target) {
		this.cost=cost;
		this.target=target;
	}
	@Override
	public int compareTo(PQ other) {
		return Integer.compare(other.cost, this.cost);
	}
}

class Point{
	int y;
	int x;
	public Point(int y, int x) {
		this.y=y;
		this.x=x;
	}
	public int getDistance(Point other) {
		return Math.abs(this.x-other.x) + Math.abs(this.y-other.y);
	}
	
	public Point move(int newDirection, Point currentPosition){
		switch(newDirection) {
		case 0:
			return this;
		case 1: //up
			return new Point(currentPosition.y-1,currentPosition.x);
		case 2: // right
			return new Point(currentPosition.y,currentPosition.x+1);
		case 3: // down
			return new Point(currentPosition.y+1,currentPosition.x);
		case 4: // left
			return new Point(currentPosition.y,currentPosition.x-1);
		}
		
		return new Point(0,0);
	}
}

class Charger{
	Point position;
	int c; //충전범위
	int p; //성능
	
	public Charger(int x,int y, int c, int p){
		position = new Point(y-1,x-1);
		this.c=c;
		this.p=p;
	}
	
	public int getChargerToSmartPhoneDistance(Point smartPhonePosition) {
		int distance = position.getDistance(smartPhonePosition);
		if(distance <= c) {
			return p;
		}
		return 0;
	}
}
//smartPhone
public class Solution {
	private static Point currentPositionA,currentPositionB;
	private static int M,A,cost,T;
	private static Charger[] chargers;
	private static int[] smartPhone1_MoveMent,smartPhone2_MoveMent;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int TC=1; TC<=T; TC++) {
			init(br);
			
			getCost();
			//움직이기
			for(int i=0;i<M;i++) {
				currentPositionA = currentPositionA.move(smartPhone1_MoveMent[i], currentPositionA);
				currentPositionB = currentPositionB.move(smartPhone2_MoveMent[i], currentPositionB);
				
				getCost();
			}
			System.out.println("#"+TC+" "+cost);
		}
		
	}
	public static void init(BufferedReader br) throws IOException{
		currentPositionA = new Point(0,0);
		currentPositionB = new Point(9,9);
		cost =0 ;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		smartPhone1_MoveMent = new int[M];
		smartPhone2_MoveMent = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++) {
			smartPhone1_MoveMent[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++) {
			smartPhone2_MoveMent[i] = Integer.parseInt(st.nextToken());
		}

		chargers = new Charger[A];
		for(int i=0;i<A;i++) {
			st = new StringTokenizer(br.readLine());
			chargers[i] = new Charger(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
	}
	public static void getCost() {
	    ArrayList<PQ> listA = new ArrayList<>();
	    ArrayList<PQ> listB = new ArrayList<>();

	    // 사용자 A와 B 각각에 대해 충전 가능한 BC를 리스트에 추가
	    for (int j = 0; j < A; j++) {
	        int costA = chargers[j].getChargerToSmartPhoneDistance(currentPositionA);
	        int costB = chargers[j].getChargerToSmartPhoneDistance(currentPositionB);

	        if (costA > 0) listA.add(new PQ(costA, j));
	        if (costB > 0) listB.add(new PQ(costB, j));
	    }

	    // 각 리스트를 성능에 따라 정렬 (내림차순)
	    Collections.sort(listA);
	    Collections.sort(listB);

	    if (listA.isEmpty() && listB.isEmpty()) return; // A와 B 모두 충전할 수 없는 경우
	    else if (listA.isEmpty()) cost += listB.get(0).cost; // A는 충전 불가, B만 충전 가능
	    else if (listB.isEmpty()) cost += listA.get(0).cost; // B는 충전 불가, A만 충전 가능
	    else { 
	        // A와 B 모두 충전 가능한 경우
	        if (listA.get(0).target != listB.get(0).target) {
	            cost += listA.get(0).cost + listB.get(0).cost;
	        } else {
	            int maxCost = listA.get(0).cost;
	            if (listA.size() > 1) maxCost = Math.max(maxCost, listA.get(1).cost + listB.get(0).cost);
	            if (listB.size() > 1) maxCost = Math.max(maxCost, listB.get(1).cost + listA.get(0).cost);
	            cost += maxCost;
	        }
	    }
	}

}