package sort;

public class SelectSort {
    public void selectSort(int[] src){
        int len = src.length - 1;
        for (int i = 1; i < len; i++) {
            int index = i;
            for (int j = i + 1; j <= len; j++) {
                //选择排序变成稳定排序，当挑选出当前最小元素时，
                //和要交换的元素做一次比较，如果相等就不比较
                //但时间性能从n->2n
                if (src[j] < src[index]) {
                    index = j;
                }
            }
            if (index != i){
                src[0] = src[index];
                src[index] = src[i];
                src[i] = src[0];
            }
        }
    }

    public static void main(String[] args) {
        int[] testSrc = {1,23,56,32,41,87,69};
        SelectSort testSS = new SelectSort();
        testSS.selectSort(testSrc);
        for (int i = 1; i < testSrc.length; i++) {
            System.out.println(testSrc[i]);
        }
    }
}
