/* reference site:
http://www.jeepxie.net/article/269798.html
https://blog.csdn.net/liyuanyue2017/article/details/83269991
https://blog.csdn.net/yeternity/article/details/72794956
*/
import java.rmi.UnexpectedException;
import java.util.Scanner;

class ArrayNode
{
    int data;
    int next;

    ArrayNode()
    {
        data = 0;
        next = -1;
    }
}
public class Reversing_Linked_List_Test
{
    public static void main(String[] args)
    {
        int Max_size = 100050;
        ArrayNode[] arr = new ArrayNode[Max_size];
        Scanner sc = new Scanner(System.in);
        int head_index = sc.nextInt();
        int N = sc.nextInt();
        int K = sc.nextInt();
        int temp_add, temp_data, temp_next;
        for(int i = 0; i < N; i++)
        {
            temp_add = sc.nextInt();
            arr[temp_add] = new ArrayNode();
            arr[temp_add].data = sc.nextInt();
            arr[temp_add].next = sc.nextInt();
        }
        //获取真实链表的节点数量
        N = Numbers_true(arr, head_index);
        //每k个都反转(k>1)
        /*if(K > 1)
        {
            head_index = LinkedListReverse(head_index, arr, N, K);
        }*/
        head_index = Reverse_LinkedList(arr, head_index, N, K);
        Display(head_index,arr);
    }
    public static int Numbers_true(ArrayNode[] arr, int head)
    {
        int n = 0;
        while(head != -1)
        {
            head = arr[head].next;
            n ++;
        }
        return n;
    }
    /*
    复杂+容易出错
     */
    public static int LinkedListReverse(int head, ArrayNode[] arr, int N, int K)
    {
        int total_reverse = 1; //反转多少段链表
        int count = 1; //链表反转内部计数
        int save = head; //保存原链表的head(上一段的head/反转后新末尾)
        int save_2 = head;//(下一段的head/反转后新末尾)
        int temp; //反转过程保存中间next
        int new_head = head;
        int old_head = arr[head].next;
        int flag = 0;
        while (total_reverse <= (N / K))
        {
            while(count < K)
            {
                temp = arr[old_head].next;
                int a = arr[old_head].data;
                //arr[old_head].next = new_head;
                arr[old_head] = new ArrayNode();
                arr[old_head].data = a;
                arr[old_head].next = new_head;
                new_head = old_head;
                old_head = temp;
                count ++;
            }
            // System.out.println("new_head is pointing: " + arr[new_head].data + ", and old_head is pointing: " + arr[old_head].data);
            if(total_reverse == 1) //第一次反转一段链表
            {
                head = new_head; //head等于第一段反转链表的new_head
                arr[save].next = old_head; //之前保存的原链表的head的next指向old_head
            }
            else //从第2次反转开始
            {
                arr[save].next = new_head;
                save = save_2;
            }
            if(old_head == -1) //old_head到达原链表的末尾
            {
                arr[save_2].next = -1;
                flag = 1; //都完成反转了
            }
            else //反转一段后还没有结束
            {
                new_head = old_head;
                old_head = arr[old_head].next; // old和new后移
                save_2 = new_head; //save2保存下一段的原头(逆转后的尾部)
            }
            count = 1;
            total_reverse ++;
        }
        if(flag == 0) //剩余的一段小于K，不反转了，将上一段的新末尾和剩余的部分接上
        {
            arr[save].next = new_head;
        }
        return  head;
    }
    public static void Display(int head, ArrayNode[] arr)
    {
        while(head != -1)
        {
            System.out.printf("%5d %d %5d", head, arr[head].data, arr[head].next);
            System.out.println();
            head = arr[head].next;
        }
    }

    /*
    反转整个链表
    @return: 整个反转完之后的链表头结点位置
     */
    public static int Reverse_LinkedList(ArrayNode[] arr, int head, int N, int K)
    {
        int List_head; //整个链表的head位置
        int Unreversed_head = head; //未反转的head位置
        int Tail; //用于连接下一段逆转的链表
        // 第一次先从head开始判断是不是需要反转
        if(need_reverse(arr, Unreversed_head, K))
        {
            List_head = reverse_part(arr, Unreversed_head, K); //整个链表的头就是第一段反转完的头
            Tail = Unreversed_head;
            Unreversed_head = arr[Tail].next;
        }
        else //不需要反转
        {
            return head;
        }
        while(need_reverse(arr, Unreversed_head, K)) //从第二段开始
        {
            arr[Tail].next = reverse_part(arr, Unreversed_head, K); //上一段的表尾和下一段的表头连接
            Tail = Unreversed_head; // 更新Tail，逆转前的表头就是逆转后的表尾，且新Tail已经接上了未逆转的部分
            Unreversed_head = arr[Tail].next; //Tail的next就是下一段需要逆序的head
        }
        return List_head;
    }

    /*
    统计从当前节点开始到null还有多少个节点，如果大于等于k就可以返回true
     */
    public static boolean need_reverse(ArrayNode[] arr, int start_pos, int K)
    {
        if(start_pos == -1)
        {
            return false;
        }
        for(int i = 1; arr[start_pos].next != -1; start_pos = arr[start_pos].next)
        {
            i++;
            if(i == K)
            {
                return true;
            }
        }
        return false;
    }

    /*
    从位置start_pos开始反转K个节点
    @return: 反转后的新头节点位置new_head
     */
    public static int reverse_part(ArrayNode[] arr, int start_pos, int K)
    {
        int head = start_pos;
        int new_head = head;
        int old_head = arr[head].next;
        int count = 1;
        int temp;
        while(count < K)
        {
            temp = arr[old_head].next;
            arr[old_head].next = new_head;
            new_head = old_head;
            old_head = temp;
            count ++;
        }
        arr[head].next = old_head; //原来的第一个节点逆转后变为最后一个节点，并连接剩下未逆转的元素
        return new_head;
    }
}
/*
用例1:
00100 6 4

00000 4 99999

00100 1 12309

68237 6 -1

33218 3 00000

99999 5 68237

12309 2 33218

用例2:
11111 8 4

44444 4 55555

11111 1 22222

66666 6 77777

33333 3 44444

55555 5 66666

22222 2 33333

77777 7 88888

88888 8 -1

用例3:
11111 9 4

44444 4 55555

11111 1 22222

66666 6 77777

33333 3 44444

55555 5 66666

22222 2 33333

77777 7 88888

88888 8 99999

99999 9 -1
 */
