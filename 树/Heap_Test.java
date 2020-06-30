package Heap;

import java.util.Scanner;

class MaxHeap
{
    // 用数组存储，完全二叉树
    int Max_size = 20;
    int[] elements; // 存放元素的数组
    int size; // 当前的size
    int Capacity; // 最大容量
    public MaxHeap()
    {
        // Max_size = max_size;
        elements = new int[Max_size + 1]; // 数据从1开始存放
        size = 0;
        Capacity = Max_size;
        // elements[0] = (int)Double.MAX_VALUE; // 哨兵，0号位放一个最大的数，方便比较(最大堆)
        elements[0] = -1 * (int)Double.MAX_VALUE; // 哨兵，0号位放一个最小的数，方便比较(最小堆)
    }
    // 插入
    public void Insert(int val)
    {
        if(isFull())
        {
            System.out.println("Is full");
            return;
        }
        size ++; // size加1
        int i = size; // i指向size，即当前的新增元素
        for(; elements[i/2] < val; i = i / 2) // 向父节点比较，因为有哨兵所以不用加上i>1的调价(ele[0]是最大的)
        {
            elements[i] = elements[i/2]; //如果父节点小，放到i的位置
        }
        elements[i] = val;
    }
    public void Insert_small(int val)
    {   // 小根堆的插入
        if(isFull())
        {
            System.out.println("Is full");
            return;
        }
        size ++; // size加1
        int i = size; // i指向size，即当前的新增元素
        for(; elements[i/2] > val; i = i / 2) // 向父节点比较，因为有哨兵所以不用加上i>1的调价(ele[0]是最小的)
        {
            elements[i] = elements[i/2]; //如果父节点大，放到i的位置，下沉
        }
        elements[i] = val;
    }
    // 删除，返回一个最大值（树顶）
    public int Delete()
    {
        if(isEmpty())
        {
            System.out.println("Is empty");
            return -1;
        }
        int max_item = elements[1]; // 获取树顶
        int temp = elements[size]; // 最后一个元素替换到树顶,size--
        size--;
        int parent, child;
        //给temp找位置，要满足堆的要求. parent*2 <= size则是判断parent有没有左孩子
        for(parent = 1; parent*2 <= size; parent = child)
        {
            child = parent * 2; //左儿子的下标
            if(child != size && elements[child] < elements[child +1]) // if child == size，child是最后一个点，parent无右儿子
            {
                child ++;
            }
            // child变成左右儿子中最大的一个的小标
            if(temp > elements[child]){
                break; //比左右儿子大，满足对的性质
            }
            else {
                // 移动到下一层，左右儿子大的放上来
                elements[parent] = elements[child];
            }
        }
        elements[parent] = temp;
        return max_item;
    }
    public void BuildHeap(){
        // 假设element已经有数据了，进行调整
        // 从最后一个有孩子的节点开始，从右往左从下往上调整
        for(int i = size / 2; i >= 1; i--){
            adjust(i);
        }
    }
    public void adjust(int index){
        //调整，与删除过程类似
        int temp = elements[index];
        int parent, child;
        for(parent = index; parent * 2 <= size; parent = child)
        {
            // 找到左右孩子较大的下标
            child = parent * 2;
            if(child != size && elements[child] < elements[child + 1]){
                child ++;
            }
            if(temp > elements[child]){
                break;
            }
            else {
                elements[parent] = elements[child];
            }
        }
        elements[parent] = temp;
    }
    public void swap(int[] ele, int i, int j)
    {
        int temp = ele[i];
        ele[i] = ele[j];
        ele[j] = temp;
    }
    public boolean isFull()
    {
        if (size == Capacity)
        {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean isEmpty()
    {
        if (size == 0)
        {
            return true;
        }
        else {
            return false;
        }
    }
    public void Print_element(){
        for(int i = 1; i <= size; i++)
        {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }
    public void print_path(int index){
        for(int i = index; i > 0; i = i/2){
            System.out.print(elements[i]);
            if(i != 1)
            {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}

// 最大堆MaxHeap
public class Heap_Test
{
    public static void main(String[] args){
        /*MaxHeap heap = new MaxHeap();
        heap.Insert(55);
        heap.Insert(66);
        heap.Insert(44);
        heap.Insert(33);
        heap.Insert(11);
        heap.Insert(22);
        heap.Insert(88);
        heap.Insert(99);
        heap.Print_element();

        heap.Delete();
        heap.Print_element();
        heap.Delete();
        heap.Print_element();
        heap.Delete();
        heap.Print_element();
        heap.Delete();
        heap.Print_element();
        heap.Delete();
        heap.Print_element();*/

        MaxHeap heap = new MaxHeap();
        int[] data = {11, 22, 33, 44, 55, 66, 77, 88, 99};
        for(int i = 0; i < data.length; i++)
        {
            heap.size++;
            heap.elements[heap.size] = data[i];
        }
        heap.Print_element();
        heap.BuildHeap();
        heap.Print_element();

        // 小根堆练习题目
        MaxHeap min_heap = new MaxHeap();
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        for(int i = 0; i < N; i++)
        {
            min_heap.Insert_small(sc.nextInt());
        }
        for(int i = 0; i < K; i++)
        {
            min_heap.print_path(sc.nextInt());
        }
    }
}
