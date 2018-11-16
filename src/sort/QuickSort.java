package sort;

import sort.Utils.UtilArrayItemSwap;

public class QuickSort {
     
     
     /**
      * 第一种方法
      *   自我书写错误
      * - 未设置递归基，导致递归无限循环
      * - 未真正在一趟把排序的数据分割成独立的两部分，即为达到i<j
      *
      * @param source 需要排序的int类型数组
      * @param left 数组左边界
      * @param right 数组右边界
      */
    public static void sort(int[] source, int left,int right) {
        if (left >= right) {
            return;
        }
        int i=left;
        int j=right;
        int key=source[left];
        while(i<j) {
            while (i < j && key <= source[j]) {
                --j;
            }
            source[i] = source[j];
            while (i < j && key >= source[i]) {
                ++i;
            }
            source[j]=source[i];
        }
        source[i] = key;
        sort(source, left, i - 1);
        sort(source, i + 1, right);
    }
     
     
    /**
     * 第二种方法
     * @param targetArr 实现Comparable接口的泛型数组
     * @param start 泛型数组左边界
     * @param end  泛型数组右边界
     * @return
     */
    public <T extends Comparable<? super T>> T[] quickSort(T[] targetArr, int start, int end) {
        int i = start + 1, j = end;
        T key = targetArr[start];
        UtilArrayItemSwap<T> sUtil = new UtilArrayItemSwap<>();
        if (start >= end)
            return (targetArr);
        /*
         * 从i++和j--两个方向搜索不满足条件的值并交换
         * 条件为：i++方向小于key，j--方向大于key
         */
        while (true) {
            while (targetArr[j].compareTo(key) > 0)
                j--;
            while (targetArr[i].compareTo(key) < 0 && i < j)
                i++;
            if (i >= j)
                break;
            sUtil.swap(targetArr, i, j);
            // 维持在两边正好等于key时能够进行下一次增长比较
            if (targetArr[i] == key) {
                j--;
            } else {
                i++;
            }
        }
        /* 关键数据放到‘中间’ */
        sUtil.swap(targetArr, start, j);
        if (start < i - 1) {
            this.quickSort(targetArr, start, i - 1);
        }
        if (j + 1 < end) {
            this.quickSort(targetArr, j + 1, end);
        }
        return targetArr;
    }
     
    /**
     *简单测试
     */
    public static void main(String[] args) {
        Integer[] sour= {2,7,8,9,4,10,4,6};
        QuickSort qs=new QuickSort();
        qs.quickSort(sour, 0, sour.length-1);
        for(int i=0;i<sour.length;i++) {
            System.out.println(sour[i]);
        }
    }
}
