package pattern_matching;
import java.util.ArrayList;
import java.util.List;

public class KMP {
    public static List<Integer> KMPIndex(char[] S,char[] T) {
        List<Integer> indexs = new ArrayList<>();
        int m = 0, n = 0;
        int sLen = S.length;
        int tLen = T.length;
        int[] next = getNext(T);
        while (m < sLen) {
            n = 0;
            while (m < sLen && n < tLen) {
                //S[m] == T[n]
                //j==-1说明在之前匹配中不存在可匹配部分字符
                //直接m向前偏移一位，j重新从0匹配
                if (n == -1 || S[m] == T[n]) {
                    ++m;
                    ++n;
                } else {
                    n = next[n];
                    //n = next[m];
                }
            }
            if (n == tLen)
                indexs.add(m - n);
            else {
                indexs.add(-1);
                return indexs;
            }
        }
        return indexs;
    }
    private static int[] getNext(char[] T_){
        int[] next_=new int[T_.length];
        next_[0] = -1;
        int k = -1;
        int j = 0;
        //T_.length
        while(j < T_.length - 1){
            if(k == -1 || T_[k] == T_[j]){
                ++ k;
                ++ j;
                next_[j] = k;
            }else{
                k = next_[k];
            }
        }
        return next_;
    }

    public static void main(String[] args) {
        char[] S = {'a','b','c','d','e','f','g','c','d','e'};
        char[] T = {'c','d','e'};
        List<Integer> indexs_ = KMP.KMPIndex(S , T);
        for (Integer zi : indexs_){
            System.out.println(zi);
        }
    }
}
