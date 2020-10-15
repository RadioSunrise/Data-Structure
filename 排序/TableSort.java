package Sort;

import java.util.Scanner;

// site: https://blog.csdn.net/liyuanyue2017/article/details/84400411
public class TableSort {
    public static void main(String[] args){
        // 输入 + 构造nums
        int N;
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int[] nums = new int[N];
        for(int i = 0; i < N; i++){
            nums[i] = sc.nextInt();
        }
        // 表排序找环，统计每个环的交换次数
        int sum = 0;
        int temp = 0;
        // 访问数组visited
        boolean[] visited = new boolean[N];
        for(int i = 0; i < N; i++){
            // 访问没有访问过的环
            while (nums[i] != i && !visited[i]){
                int j = i;
                do{
                    // 临时计数++
                    temp++;
                    // 设置访问位
                    visited[j] = true;
                    if(nums[i] == 0){
                        temp -= 2;
                    }
                    j = nums[j];
                }while (i != j);
                temp++;
                sum += temp;
            }
        }
        System.out.println(sum);
    }
}
