package leetcode;

import java.util.Scanner;

/**
 * 实现组合C(n,k)
 */
public class Combinations {
    /***循环法***/
    public int next_comb(int[] comb_,final int n, final int k){
        int i = k - 1;
        final int e = n - k;
        //
        do {
            //一开始从最后一位开始递增
            //注意一开始，即随着推移会从倒数第二位开始递增，直至从第一位
            comb_[i] = comb_[i] + 1;
            //一开始防止最后一位大于n-1
            //注意一开始，即随着推移会从防止倒数第二位大于n-2，直至防止第一位大于等于e
        }while ( (comb_[i] > e + i) && (i--) != 0 );


        if (comb_[0] > e){
            return 0;
        }

        for ( ; i + 1 < k; i++) {
            comb_[i + 1] = comb_[i] + 1;
        }

        return 1;
    }
    /***循环法***/

    /***递回法***/
    public void calculate(int now,int[] comb_,final int n,final int k){
        if (now == k){
            printComb(comb_);
            return ;
        }
        for (int i = 0; i < n; i++) {
            comb_[now] = i;
            if (arrSame(comb_,now)) {
                calculate(now + 1, comb_, n, k);
            }
        }
    }

    //打印
    private void printComb(int[] comb__){
        for (int i = 0; i < comb__.length; i++) {
            System.out.print(comb__[i] + ",");
        }
        System.out.println();
    }

    //判断是否满足comb__[now_] <= comb__[now_ - 1]
    private boolean arrSame(int[] comb__,int now_){
        if (now_ > 0 && comb__[now_] <= comb__[now_ - 1]){
            return false;
        }
        return true;
    }
    /***递回法***/

    /***迭代法，固定k**/
    public static void iterate_fix_k(final int n){
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    System.out.println(i + " " + j + " " + k );
                }
            }
        }
    }
    /***迭代法，固定k**/


    public static void main(String[] args) {
/*        *//***循环法***/
//        System.out.println("Comb(n,k):");
//        Scanner read = new Scanner(System.in);
//        final int n = read.nextInt();
//        final int k = read.nextInt();
//        //杜绝掉非法参数
//        if (n < k && k < 0){
//            throw new IllegalArgumentException("n < k or k < 0");
//        }
//        Combinations testComb = new Combinations();
//        int[] comb = new int[n];
//        //初始化
//        for (int i = 0; i < k; i++) {
//            comb[i] = i;
//        }
//
//        do {
//            for (int i = 0; i < k; System.out.print((++i < k)?',':'\n') ) {
//                System.out.print(comb[i]);
//            }
//        }while (testComb.next_comb(comb,n,k) == 1);
        /***循环法***/

        /***递回法***/
//        System.out.println("Comb(n,k):");
//        Scanner read = new Scanner(System.in);
//        final int n = read.nextInt();
//        final int k = read.nextInt();
//        //杜绝掉非法参数
//        if (n < k && k < 0){
//            throw new IllegalArgumentException("n < k or k < 0");
//        }
//        Combinations testComb = new Combinations();
//        int[] comb = new int[k];
//        testComb.calculate(0,comb,n,k);
        /***递回法***/

        /***迭代法，固定k**/
        iterate_fix_k(5);
        /***迭代法，固定k**/
    }
}
