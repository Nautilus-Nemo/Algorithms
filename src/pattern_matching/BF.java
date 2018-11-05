package pattern_matching;

public class BF {
    public static int bfIndex(char[] S_,char[] T_){
        int i = 0;
        int j = 0;
        int SLen = S_.length;
        int TLen = T_.length;
        while(i < SLen && j < TLen){
            if(S_[i] == T_[j]){
                ++i;
                ++j;
            }else{
                i = i - j + 1;
                j = 0;
            }

        }
        if(j == T_.length)
            return i - j + 1;
        else
            return -1;
    }

    public static void main(String[] args) {
        char[] S = {'a','b','c','d','e'};
        char[] T = {'c','d','e'};
        int k = BF.bfIndex(S , T);
        if(k == -1)
            System.out.println("pattern matching is failure");
        else
            System.out.println("pattern matching is successful");
    }
}
