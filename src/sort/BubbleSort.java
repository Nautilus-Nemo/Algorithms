package sort;

public class BubbleSort {
    public void bubbleSort(int[] src){
        int exchange = src.length - 1;
        while (exchange != 0){
            int bound = exchange;
            //防止在正序的时候无限循环
            exchange = 0;
            for (int i = 1; i < bound; i++) {
                if (src[i] > src[i + 1]){
                    src[0] = src[i+1];
                    src[i+1] = src[i];
                    src[i] = src[0];
                    exchange = i;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] testSrc = {11,3,64,2,9,6,5};
        BubbleSort testbs = new BubbleSort();
        testbs.bubbleSort(testSrc);
        for (int i = 1; i < testSrc.length; i++) {
            System.out.print(testSrc[i] + "");
        }
    }
}
