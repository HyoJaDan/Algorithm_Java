package main.java.org;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Study {

    public static void main(String[] args) {
    	TreeSet<Integer> set = new TreeSet<>();
    	
    	set.add(1);
    	set.add(2);
    	set.add(3);
    	System.out.println(set.higher(2)); // 3
    	System.out.println(set.lower(2)); // 1
    }
}