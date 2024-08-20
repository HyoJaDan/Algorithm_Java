package swea.datastructure;

import java.util.Hashtable;
import java.util.Scanner;

/**
 * Hash table은 키를 값에 매핑할 수 있는 구조인, 연관 배열 추가에 사용되는 자료 구조이다. 
 * Hash table은 Hash 함수를 사용하여 색인(index, Key)을 버킷(bucket)이나 슬롯(slot)의 배열로 계산한다.
 * 문제 : 주어진 N개의 key, data쌍을 Hash table에 입력한 후, Q개의 key를 입력 받아 key에 해당하는
 *       data를 각 줄에 출력하시오. (1<=N, Q<=4096)
 * Key : 최대 64개의 문자열
 * Data : 최대 128개의 문자열
 * 입력
2 // 테스트 케이스 수 T
8 // 입력 데이터 수 N
Attract Charm //key data
Gather Collect
Fundamental Essential
Abundant Plentiful
Achieve Accomplish
Assign Allocate
Surprise Amaze
Assess Estimate
3 // 검색할 데이터 수 Q
Attract
Surprise
education
10
Bear Stand
Famous Noted
Characteristic Attribute
Decrease Diminish
Defect Flaw
Depict Describe
Eager Avid
Flourish Thrive
Huge Tremendous
Important Crucial
5
treasure
Bear
Defect
Huge
hydrogen

 * 출력
#1
Charm
Amaze
not find
#2
not find
Stand
Flaw
Tremendous
not find
 * @author SSAFY
 *
 */
class HashTable {
    class Hash {
        String key;
        String data;
    }
 
    int capacity;
    Hash tb[];
     
    public HashTable(int capacity){
        this.capacity = capacity;
        tb = new Hash[capacity];
        for (int i = 0; i < capacity; i++){
            tb[i] = new Hash();
        }
    }
     
    private int hash(String str)
    {
        int hash = 5381;
         
        for (int i = 0; i < str.length(); i++)
        {
            int c = (int)str.charAt(i);
            hash = ((hash << 5) + hash) + c;
        }
        if (hash < 0) hash *= -1;
        return hash % capacity;
    }
     
    public String find(String key){
        int h = hash(key);
        int cnt = capacity;
        while(tb[h].key != null && (--cnt) != 0)
        {
            if (tb[h].key.equals(key)){
                return tb[h].data;
            }
            h = (h + 1) % capacity;
        }
        return null;
    }
     
    boolean add(String key, String data)
    {
        int h = hash(key);
        while(tb[h].key != null)
        {
            if (tb[h].key.equals(key)){
                return false;
            }
            h = (h + 1) % capacity;
        }
        tb[h].key = key;
        tb[h].data = data;
        return true;
    }
}
     
 
public class HashDS
{
    final static int MAX_TABLE = 4096;
     
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++)
        {
            HashTable tb = new HashTable(MAX_TABLE);
            int N = sc.nextInt();
            for (int i = 0; i < N; i++)
            {
                String k = sc.next();
                String d = sc.next();
                tb.add(k, d);
            }
            System.out.printf("#%d\n", test_case);
            int Q = sc.nextInt();
            for (int i = 0; i < Q; i++)
            {
                String k = sc.next();
                String d = tb.find(k);
                if (d != null)
                {
                    System.out.printf("%s\n", d);
                }
                else
                {
                    System.out.printf("not find\n");
                }
            }
        }
        sc.close();
    }
}

