package sort;

public class ShellSort {
    public void shellSort(int[] src){
        //0号单元用作暂存单元
        int len = src.length - 1;
        int j;
        for (int d = len/2; d >= 1; d = d/2){
            for (int i = d + 1;i <= len;i++) {
                src[0] = src[i];
                for (j = i - d;j>0 && src[0]<src[j];j = j - d){
                    src[j + d] = src[j];
                }
                src[j + d] = src[0];
            }
        }
    }

    public static void main(String[] args) {
        int[] testSrc = {11,3,64,2,9,6,5};
        ShellSort testSS = new ShellSort();
        testSS.shellSort(testSrc);
        for (int i = 1; i < testSrc.length; i++) {
            System.out.print(testSrc[i]+" ");
        }
    }
}
