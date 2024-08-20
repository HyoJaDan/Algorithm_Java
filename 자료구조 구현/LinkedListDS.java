package swea.datastructure;

import java.util.Scanner;

/**
 * Linked List는 각 노드가 데이터와 포인터를 가지고 한 줄로 연결되어 있는 방식으로 데이터를 저장하는 자료 구조
 * 문제:각 테스트케이스는 N 개의 쿼리가 입력으로 주어지고
 * 각 쿼리는 2 개의 숫자가 입력으로 주어지며, 첫번째 숫자는 쿼리의 종류를 나타내며, 두번째 숫자는 처리해야할 숫자를 나타낸다.
 * 쿼리의 종류는 2 가지이며, list 에 특정 숫자를 삽입하는 쿼리는 1, list 내 특정 숫자를 가진 노드를 모두 삭제하고
 * 삭제한 노드의 개수를 출력하는 쿼리는 2 로 주어진다.
 * 입력
 2 // TestCase 숫자
10 // N
1 1 // mode : insert, 숫자 : 1
1 2 // mode : insert, 숫자 : 2
1 3
1 4
1 1
2 2 // mode : erase, 숫자 : 2
1 3
1 1
2 1
2 3
5
1 100
2 100
1 200
1 200
2 200
 * 출력
#1 1 3 2
#2 1 2
 * @author SSAFY
 *
 */
class ListNode
{
    int data;
    ListNode prev;
    ListNode next;
 
    ListNode(int _data) 
    {
        data = _data;
        prev = null;
        next = null;
    }
 
    static ListNode list_insert(ListNode _head, ListNode new_node)
    {
        ListNode next = _head.next;
 
        _head.next = new_node;
        new_node.next = next;
        new_node.prev = _head;
     
        if (next != null)
        {
            next.prev = new_node;
        }
 
        return new_node;
    }
 
    static int list_erase(ListNode head, int _data)
    {
        ListNode it = head.next;
        int ret = 0;
 
        while (it != null)
        {
            if (it.data == _data)
            {
                ListNode prev = it.prev;
                ListNode next = it.next;
                it = it.next;
 
                prev.next = next;
                if (next != null)
                {
                    next.prev = prev;
                }
             
                ret++;
            }
            else
            {
                it = it.next;
            }
        }
 
        return ret;
    }
};
 
public class LinkedListDS 
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
 
        int T = sc.nextInt();
 
        for (int test_case = 1; test_case <= T; test_case++)
        {
            int N = sc.nextInt();
 
            ListNode head = new ListNode(0);
 
            System.out.printf("#%d", test_case);
            for (int i = 0; i < N; i++)
            {
                int mode = sc.nextInt();
                int data = sc.nextInt();
 
                if (mode == 1)
                {
                    ListNode node = new ListNode(data);
 
                    ListNode.list_insert(head, node);
                }
                else if (mode == 2)
                {
                    System.out.printf(" %d", ListNode.list_erase(head, data));
                }
            }
 
            System.out.printf("\n");
        }
    }
 
}

