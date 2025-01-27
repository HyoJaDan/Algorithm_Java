import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args){
        TreeMap<String, String> list = new TreeMap<String, String>();

        String[] key = { "a", "b", "c", "d", "e" };
        String[] value = { "apple", "banana", "candy", "dog", "enum" };

        // 데이터 삽입
        for (int i = 0; i < key.length; i++)
            list.put(key[i], value[i]);

        System.out.println(list.ceilingEntry("A")); // "a=apple"
        System.out.println(list.ceilingKey("A"));   // "a"

        System.out.println(list.floorEntry("z"));   // "e=enum"
        System.out.println(list.floorKey("z"));     // "e"

        //[ higherEntry() / higherKey() / lowerEntry() / lowerKey() ]
        // 위의 메소드와 비슷하지만, "같거나"가 빠짐
        System.out.println(list.higherEntry("a"));  // "b=banana"
        System.out.println(list.higherKey("a"));    // "b"
        System.out.println(list.lowerEntry("e"));   // "d=dog"
        System.out.println(list.lowerKey("e"));     // "d"
    }
}