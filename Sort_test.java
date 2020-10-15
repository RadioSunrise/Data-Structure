package Sort;

import java.util.Arrays;
import java.util.Random;

public class Sort_test
{
    public static void quick_sort_2(int[] arr, int head, int rear)
    {
        if(head < rear)
        {
            int i = partition_3(arr, head, rear);
            quick_sort_2(arr,head,i - 1);
            quick_sort_2(arr,i + 1, rear);
        }
    }
    public static int partition(int[] arr, int head, int rear)
    {
        // 基于挖坑填坑实现的partition函数
        // 设置左右位置和枢值
        //Swap(s[l], s[(l + r) / 2]); 用中值当枢轴也可以的
        int i = head, j = rear;
        // 随机选取枢值位置，防止极端的升序或者逆序情况
        Random random = new Random();
        int key_pos = head + random.nextInt(rear - head + 1);
        swap(arr, key_pos, head);
        int key = arr[head];
        while(j > i)
        {
            while(j > i && arr[j] >= key)
            {
                j --;
            }
            if(j > i)
            {
                arr[i] = arr[j];
            }
            while (j > i && arr[i] <= key)
            {
                i++;
            }
            if(j > i)
            {
                arr[j] = arr[i];
            }
        }
        arr[i] = key;
        return i;
    }
    public static int partition_swap(int[] arr, int head, int rear)
    {
        // 用swap实现的partition函数

        // 随机找位置
        Random random = new Random();
        int key_pos = head + random.nextInt(rear - head + 1);
        //把rear和key_pos内容交换，用交换后的尾部元素作为枢轴值
        swap(arr, key_pos, rear);

        //循环
        int pivot = arr[head];
        int index = head + 1;
        for (int i = index; i <= rear; i++) {
            if (arr[i] < pivot) {
                swap(arr, i, index);
                index++;
            }
            //System.out.println("Now arr is " + Arrays.toString(arr) + "index is pointing: " + arr[index] + ", i is pointing: " + arr[i]);
        }
        swap(arr, head, index - 1);
        return index - 1;
    }
    public static int partition_3(int[] arr, int head, int rear)
    {
        Random random = new Random();
        int key_pos = head + random.nextInt(rear - head + 1);
        swap(arr, head, key_pos);

        int key = arr[head];
        int i = head + 1;
        for(int j = head + 1; j <= rear; j++)
        {
            if(arr[j] < key)
            {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, head, i - 1);
        return (i-1);
    }
    public static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void quicksort_temp(int[] A, int head, int rear)
    {
        if(head >= rear)
        {
            return;
        }
        int index = partition_temp(A, head, rear);
        quicksort_temp(A, head, index-1);
        quicksort_temp(A, index + 1, rear);
    }
    public static int partition_temp(int[] A, int head, int rear)
    {
        int i = head;
        int j = rear;
        int key = A[i];
        while(i < j)
        {
            while(i< j && A[j] <= key)
            {
                j--;
            }
            if(i <j)
            {
                A[i] = A[j];
            }
            while(i < j && A[i] >= key)
            {
                i++;
            }
            if(i < j)
            {
                A[j] = A[i];
            }
        }
        A[i] = key;
        return i;
    }
    public static void merge_sort(int[] arr, int[] temp, int head, int rear){
        if(head >= rear){
            return;
        }
        int mid = head + ((rear - head) >> 1);
        // 左边归并排序
        merge_sort(arr, temp, head, mid);
        // 右边归并排序
        merge_sort(arr, temp, mid + 1, rear);
        // 如果两边排序完后整个序列已经有序，就不需要合并操作了
        if(arr[mid] <= arr[mid + 1]){
            return;
        }
        // 合并
        merge(arr, temp, head, mid + 1, rear);
    }
    public static void merge(int[] arr, int[] temp, int left_head, int right_head, int right_rear){
        // left_head是左边的起始位置
        // right_head是右边的起始位置
        int i = left_head;
        int left_rear = right_head - 1;
        int j = right_head;
        int k = left_head;
        /*for( ; k < temp.length && i <= right_head -1 && j <= right_rear; k++){
            if(arr[i] < arr[j]){
                temp[k] = arr[i];
                i++;
            }
            else{
                temp[k] = arr[j];
                j++;
            }
        }
        // 左边合并完了，复制右边剩余元素
        if(i == right_head){
            while (j <= right_rear){
                temp[k] = arr[j];
                k++;
                j++;
            }
        }
        else if(j > right_rear) {
            while (i <= right_head -1){
                temp[k] = arr[i];
                k++;
                i++;
            }
        }*/
        while(i <= left_rear && j <= right_rear){
            //小于等于的就是稳定的排序
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while(i <= left_rear){
            temp[k++] = arr[i++];
        }
        while(j <= right_rear){
            temp[k++] = arr[j++];
        }
        // 从temp数组放回原数组
        for(k = left_head; k <= right_rear; k++){
            arr[k] = temp[k];
        }
    }

    public static void mergeSort(int[] nums, int[] temp, int left, int right){
        // 递归结束条件
        if(left >= right){
            return;
        }
        int mid = left + ((right - left) / 2);
        merge_sort(nums, temp, left, mid);
        merge_sort(nums, temp, mid + 1, right);
        // 如果已经有序了，就不用合并了
        if(nums[mid] <= nums[mid + 1]){
            return;
        }
        merge(nums, temp, left, mid + 1, right);
    }
    public static void mergeNew(int[] nums, int[] temp, int left, int right, int rightEnd){
        int leftEnd = right - 1;
        // 从left的位置开始在temp中放数据
        int k = left;
        int i = left;
        int j = right;
        // 合并
        while (i <= leftEnd && j <= rightEnd){
            // 小于等于 稳定
            if (nums[i] <= nums[j]){
                temp[k++] = nums[i++];
            }
            else {
                temp[k++] = nums[j++];
            }
        }
        // 其中一个到结尾了
        while(i <= leftEnd){
            temp[k++] = nums[i++];
        }
        while(j <= rightEnd){
            temp[k++] = nums[j++];
        }
        // 从辅助数组temp中copy回nums里面
        for(k = left; k <= rightEnd; k++){
            nums[k] = temp[k];
        }
    }
    public static void merge1(int[] nums, int[] temp, int left, int right, int rightEnd){
        // 非递归版归并排序用的merge函数
        int leftEnd = right - 1;
        // 从left的位置开始在temp中放数据
        int k = left;
        int i = left;
        int j = right;
        // 合并
        while (i <= leftEnd && j <= rightEnd){
            // 小于等于 稳定
            if (nums[i] <= nums[j]){
                temp[k++] = nums[i++];
            }
            else {
                temp[k++] = nums[j++];
            }
        }
        // 其中一个到结尾了
        while(i <= leftEnd){
            temp[k++] = nums[i++];
        }
        while(j <= rightEnd){
            temp[k++] = nums[j++];
        }
        // merge1的结果是不归并到nums的，归并到temp中
    }
    public static void mergeIterate(int[] nums){
        // 非递归版的归并排序
        int n = nums.length;
        int[] temp = new int[n];
        // 一段的长度length，初值为1
        int length = 1;
        // 每次循环做两次mergePass，保证最终结果可以存在nums里面
        while(length < n){
            // 第一次mergePass之后导到temp里面
            mergePass(nums, temp, length);
            // 每次扩大1倍
            length = length * 2;
            // 再第一次mergePass，将结果导到nums里
            mergePass(temp, nums, length);
            length = length * 2;
        }
    }
    public static void mergePass(int[] nums, int[] temp, int length){
        int n = nums.length;
        // length是当前有序子列的长度
        // 一段是length，i每次增长两段
        int i;
        // 只处理到倒数第二对，剩下可能不够两段，再单独处理
        for(i = 0; i < n - 2 * length; i += 2 * length){
            merge1(nums, temp, i, i + length, i + 2*length - 1);
        }
        // for执行完了，之前的段都归并完了，剩下判断最后剩余的部分
        if(i + length < n){
            // 说明最后不止1个子列
            // 最后两个子列全部归并
            merge1(nums, temp, i, i + length, n-1);
        }
        // 最后只剩下一个子列
        // 全部导到temp里面
        else {
            for(int j = i; j < n; j++){
                temp[j] = nums[j];
            }
        }
    }

    /**
     * 堆排序，排序成升序序列，那么将nums构造成最大堆
     * @param nums
     */
    public static void heapSort(int[] nums){
        int n = nums.length;
        // 调整建最大堆，类似buildHeap
        for(int index = (n / 2) - 1; index >= 0; index--){
            // 每个有孩子的结点都adjust
            adjust(nums, n, index);
        }
        // 进行排序，类似deleteMax
        // 最后一次不用i=0做，已经排好了
        for(int i = n - 1; i > 0; i--){
            // 把堆顶和堆的末尾元素交换
            swap(nums, i, 0);
            // 从调整根节点即index = 0
            // 此时堆的大小为i
            adjust(nums, i, 0);
        }
    }

    /**
     * 维护堆的adjust函数
     * @param nums 数组
     * @param size 当前堆的大小（不一定是nums的大小）
     * @param index 要调整的结点下标（结点下标是0到n-1的）
     */
    public static void adjust(int[] nums, int size, int index){
        // 把当前index的值保存起来
        int temp = nums[index];
        int parent, child;
        // for结束条件是左child还在范围内
        for(parent = index; (parent * 2 + 1) < size; parent = child){
            // 下标从0开始，所以child为 2*i+1 和 2*i+2
            child = parent * 2 + 1;
            // 找到左右孩子的最大值
            // 有右孩子且有孩子比左孩子大
            if((child < size - 1) && (nums[child] < nums[child + 1])){
                child ++;
            }
            // 判断，如果temp比最大的孩子结点大，则不用调整
            if(temp >= nums[child]){
                break;
            }
            else {
                nums[parent] = nums[child];
            }
        }
        nums[parent] = temp;
    }

    public static void bubbleSort(int[] nums){
        // 判断是否有改变的flag
        boolean flag = false;
        int n = nums.length;
        // 外层循环，每一循环把最大的一个放到底部
        for(int i = 0; i < n; i++){
            for(int j = 1; j < n - i; j++){
                // 相邻两个数如果前面的大，交换
                if(nums[j - 1] > nums[j]){
                    swap(nums, j - 1, j );
                    // 有修改
                    flag = true;
                }
            }
            System.out.print("This round: ");
            System.out.println(Arrays.toString(nums));
            // 如果这一趟下来没有修改过，提前退出
            if(!flag){
                return;
            }
            // 重置flag
            flag = false;
        }
    }

    public static void bubbleSort2(int[] nums){
        int n = nums.length;
        for(int i = 0; i < n; i++){
            boolean flag = false;
            for (int j = 1; j < n - i; j++){
                if(nums[j-1] > nums[j]){
                    swap(nums, j, j-1);
                    flag = true;
                }
            }
            if(flag == false){
                return;
            }
        }
    }

    public static void insertSort(int[] nums){
        int n = nums.length;
        // i表示当前待排元素
        //看成排序手牌，假设第0张牌已经拿在手上了，所以从1开始0
        for(int i = 1; i < n; i++){
            // 获取当前值nums[i]
            int temp = nums[i];
            // 从i开始前找找，找到i应该放在什么位置
            // 同时移动（向后挪一个位置）
            int j = i;
            // 如果前面的数比temp大，则移位
            for(j = i; j > 0 && nums[j - 1] > temp; j--){
                // 向后移位
                nums[j] = nums[j - 1];
            }
            // j移动结束，此时j指向的位置就是nums[i]应该存放的位置
            nums[j] = temp;
        }
    }

    public static void insertSort2(int[] nums){
        for(int i = 1; i < nums.length; i++) {
            int j = i;
            int temp = nums[i];
            for(; j > 0 && nums[j-1] > temp; j--){
                nums[j] = nums[j-1];
            }
            nums[j] = temp;
        }
    }

    public static void shellSort(int[] nums){
        int n = nums.length;
        // 间隔序列D
        for(int D = n / 2; D > 0; D = D / 2) {
            // 内层循环是插入排序
            // i从D开始,因为间隔D
            for(int i = D; i < n; i++){
                int temp = nums[i];
                // temp和nums[j-D]比较，间隔D个
                int j = i;
                for(j = i; j >= D && nums[j - D] > temp; j-=D){
                    nums[j] = nums[j - D];
                }
                nums[j] = temp;
            }
        }
    }

    public static void quickSort(int[] nums, int head, int rear){
        if(head >= rear){
            return;
        }
        int mid = partitionNew(nums, head, rear);
        quickSort(nums, head, mid - 1);
        quickSort(nums, mid + 1, rear);

    }

    public static int partitionNew(int[] nums, int head, int rear){
        // 随机选一个数，交换head
        /*Random random = new Random();
        int pivotIndex = head + random.nextInt(rear - head + 1);
        swap(nums, head, pivotIndex);*/

        // 取中位数left, mid, rear
        int mid = head + ((rear - head) /  2);
        int pivotIndex = -1;
        if((nums[head] - nums[mid]) * (nums[head] - nums[rear]) <= 0){
            pivotIndex = head;
        }
        else if((nums[mid] - nums[head]) * (nums[mid] - nums[rear]) <= 0){
            pivotIndex = mid;
        }
        else{
            pivotIndex = rear;
        }
        swap(nums, head, pivotIndex);

        // 取pivot
        int pivot = nums[head];
        int i = head;
        int j = rear;
        while(i < j){
            // j向左找一个比pivot小的
            while (i < j && nums[j] >= pivot){
                j--;
            }
            nums[i] = nums[j];
            // i向右找一个比pivot大的
            while (i < j && nums[i] <= pivot){
                i++;
            }
            nums[j] = nums[i];
        }
        // i就是pivot的位置
        nums[i] = pivot;
        return i;
    }

    /**
     * 三分法找主元的快排
     * @param nums
     * @param left
     * @param right
     */
    public static void quickSortMedianThree(int[] nums, int left, int right){
        if(right <= left){
            return;
        }
        int pivot = medianThree(nums, left, right);
        // pivot肯定是
        int i = left;
        int j = right - 1;
        for (; ;){
            // i和j是先++ 或者 --，所以i从left开始，right从right-1开始
            while (i < j && nums[++i] < pivot){
            }
            while (i < j && nums[--j] > pivot){
            }
            if(i >= j) {
                break;
            }
            swap(nums, i, j);
        }
        // pivot放在right - 1的位置
        // 最后放到i的位置
        swap(nums, i, right - 1);
        // 划分
        quickSortMedianThree(nums, left, i - 1);
        quickSortMedianThree(nums, i + 1, right);
    }
    /**
     * 快排中pivot的选取，取头中尾三个数的中位数写法
     * @param nums
     * @param left
     * @param right
     * @return
     */
    public static int medianThree(int[] nums, int left, int right){
        int mid = left + ((right - left) / 2);
        // 判断，令nums[left]小于nums[mid]
        if(nums[left] > nums[mid]){
            swap(nums, left, mid);
        }
        // 左边和右边比较，左边大则交换
        if(nums[left] > nums[right]){
            swap(nums, left, right);
        }
        // 上两个if之后可以保证nums[left]是三者之中最小的
        // 之后比较mid和right，让nums[left] <= nums[mid] <= nums[right]
        if(nums[mid] > nums[right]){
            swap(nums, mid, right);
        }
        // 作为pivot返回的中位数放在nums[mid]
        // 还有把nums[mid]藏到右边
        // 减少后序的判断，之后只需要考虑nums[left + 1]到nums[right - 1]
        swap(nums, mid, right - 1);
        // 返回pivot
        return nums[right - 1];
    }



    public static void main(String[] args)
    {
        int[] arr_ori = {1,2,3,4,5,6,7,8,9,10,10};
        // int[] arr_ori = {7,8,5,6,3,4,1,2,9,10};
        int n = arr_ori.length;

        // int[] arr = arr_ori;

        int[] arr = new int[n];
        int r;
        for(int i = 0; i < arr.length; i++)
        {
            r = (int)(Math.random() * n);
            arr[i] = arr_ori[r];
            arr_ori[r] = arr_ori[n-1];
            n = n-1;
        }
        System.out.println(Arrays.toString(arr));
        /*quicksort_temp(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));*/

        int[] temp = new int[arr.length];
        mergeIterate(arr);
        // arr = new int[] {6, 4, 9, 5, 8, 2, 1, 10, 10, 3, 7};
        // quickSortMedianThree(arr, 0, arr.length - 1);

        System.out.println(Arrays.toString(arr));
    }

}
