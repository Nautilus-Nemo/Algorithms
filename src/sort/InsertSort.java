package sort;

public class InsertSort {
    public void insertSort(int[] r){
        //0号单元用作哨兵
        int len = r.length;
        int j;
        for (int i = 2; i < len; i++) {
            //暂存待插关键码，设置哨兵
            r[0] = r[i];
            //寻找插入位置
            for (j = i - 1; r[0] < r[j]; j--) {
                //记录后移
                r[j + 1] = r[j];
            }
            //退出循环，说明找到了插入位置，因为r[j]刚刚比较完毕，
            //所以j+1为正确的插入位置
            r[j+1] = r[0];
        }
    }

    public static void main(String[] args) {
        int[] src = {2, 1, 8, 6, 9, 4, 5};
        System.out.println("0号元素充当哨兵,未插入排序前:");
        for (int i = 1; i < src.length; i++) {
            System.out.print(" " + src[i] + " ");
        }
        System.out.println();
        InsertSort obj = new InsertSort();
        //更改原数组插入排序
        obj.insertSort(src);
        System.out.println("0号元素充当哨兵,进行插入排序后:");
        for (int i = 1; i < src.length; i++) {
            System.out.print(" " + src[i] + " ");
        }
    }
}
