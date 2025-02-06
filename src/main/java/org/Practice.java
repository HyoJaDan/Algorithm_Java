import java.util.*;
public class Practice {
    public static void main(String[] args){
        TreeMap<Integer, Integer> map = new TreeMap<>();

        map.put(1,3);
        map.put(2,4);
        map.put(3,6);
        map.putIfAbsent(1,4);

        System.out.println(map.get(1));
        System.out.println(map.getOrDefault(4,3));

        Set<Integer> keys = map.keySet();
        for(Integer now : keys){

        }
        Iterator iter = keys.iterator();
        while(iter.hasNext()){

        }


    }
}