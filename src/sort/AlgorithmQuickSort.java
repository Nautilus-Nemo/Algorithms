package sort;

import sort.Utils.UtilArrayItemSwap;

public class AlgorithmQuickSort {
    public  <T extends Comparable<? super T>> T[] quickSort(T[] targetArr,int left,int right){
        UtilArrayItemSwap<T> utilSwap = new UtilArrayItemSwap<T>();
        int i = left + 1;
//      int j = right - 1;
        int j = right;
        T provit = targetArr[left];
        //添加
        if (left >= right)
            return targetArr;
        while (true){
            //存在防止越界的作用
            //哨兵
            while (targetArr[j].compareTo(provit) > 0){
                --j;
            }
            while (targetArr[i].compareTo(provit) < 0 && i < j){
                ++i;
            }
            if(i >= j)
                break;
            utilSwap.swap(targetArr,i,j);
            //防止死循环，当两边i和j对应数组的值等于left对应数组的值
            //当出现例0,1,2,3,4,5的情况下，j直接移动至left，因i=left+1，所以打断，不用担心越界
            //当出现例9,0,1,2,3,4,5的情况下，i移动至5,对应的值为4，j不变，对应的值为5，通过++i实现打断，且不用担心i越界由i<j的条件
            //通过换j对应的值来实现把left交换至中间，即当targetArr[i]!=provit，一定要++i来打断循环，否则使用--j，
            // 未--j前targetArr[j]可能大于provit，但在条件下targetArr[j].compareTo(provit) > 0实现j下移
            //重要:下述4行代码主要实现j移动至对应值小于或等于provit的情况，为下述代码交换做准备utilSwap.swap(targetArr,left,j);
            //在遇见i=j的情况下，为了实现重要的目标，必须要下述写法，不可颠倒
            if(targetArr[i] == provit)
                --j;
            else
                ++i;
        }
        utilSwap.swap(targetArr, left, j);
        //从j对应存放值为中间值考虑
        if(left < i - 1){
            quickSort(targetArr,left,i - 1);
        }
        if(j + 1 < right){
            quickSort(targetArr,j + 1,right);
        }
        return targetArr;
    }

    public static void main(String[] args) {
        Integer[] sour= {7,4,3,89,12,8,1};
        AlgorithmQuickSort qs=new AlgorithmQuickSort();
        qs.quickSort(sour, 0, sour.length-1);
        for(int i=0;i<sour.length;i++) {
            System.out.println(sour[i]);
        }
    }

}
