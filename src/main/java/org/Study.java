package main.java.org;

import java.util.ArrayList;
import java.util.Collections;

class parseUpToDown implements Comparable<parseUpToDown>{
    int a;
    int b;
    int c;

    @Override
    public int compareTo(parseUpToDown other) {
        // 내림차순 정렬
        if(this.a != other.a) {
            return Integer.compare(other.a, this.a);
        } else if(this.b != other.b) {
            return Integer.compare(other.b, this.b);
        } else {
            return Integer.compare(other.c, this.c);
        }
    }

    public parseUpToDown(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}

class ParseDownToUp implements Comparable<ParseDownToUp>{
    int a;
    int b;
    int c;

    @Override
    public int compareTo(ParseDownToUp other) {
        // 오름차순 정렬
        if(this.a != other.a) {
            return Integer.compare(this.a, other.a);
        } else if(this.b != other.b) {
            return Integer.compare(this.b, other.b);
        } else {
            return Integer.compare(this.c, other.c);
        }
    }

    public ParseDownToUp(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}

public class Study {

    public static void main(String[] args) {
        // 내림차순 정렬 리스트
        ArrayList<parseUpToDown> parseUpToDown = new ArrayList<>();
        parseUpToDown.add(new parseUpToDown(2,2,3));
        parseUpToDown.add(new parseUpToDown(2,1,3));
        parseUpToDown.add(new parseUpToDown(2,2,6));
        parseUpToDown.add(new parseUpToDown(1,7,3));
        parseUpToDown.add(new parseUpToDown(6,6,2));
        parseUpToDown.add(new parseUpToDown(5,9,1));

        Collections.sort(parseUpToDown);

        for(int i = 0; i < parseUpToDown.size(); i++) {
            System.out.println(parseUpToDown.get(i).a+" "+parseUpToDown.get(i).b+" "+parseUpToDown.get(i).c);
        }

        System.out.println();
        System.out.println();

        // 오름차순 정렬 리스트
        ArrayList<ParseDownToUp> parseDownToUp = new ArrayList<>();
        parseDownToUp.add(new ParseDownToUp(2,2,3));
        parseDownToUp.add(new ParseDownToUp(2,1,3));
        parseDownToUp.add(new ParseDownToUp(2,2,6));
        parseDownToUp.add(new ParseDownToUp(1,7,3));
        parseDownToUp.add(new ParseDownToUp(6,6,2));
        parseDownToUp.add(new ParseDownToUp(5,9,1));

        Collections.sort(parseDownToUp);

        for(int i = 0; i < parseDownToUp.size(); i++) {
            System.out.println(parseDownToUp.get(i).a+" "+parseDownToUp.get(i).b+" "+parseDownToUp.get(i).c);
        }
    }
}
class EE implements Comparable<EE>{
	int a,b,c;
	
	@Override
	public int compareTo(EE other) {
		if(this.a == other.a) {
			return Integer.compare(this.a, other.a);
		} else {
			return Integer.compare(this.b, other.b);
		}
		
	}
}