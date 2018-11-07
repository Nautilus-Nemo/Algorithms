package leetcode;
import java.util.*;

public class Permutaions {
    public List permute(int[] num) {
        int len = num.length;
        List<String> res = new ArrayList<>();
        int[] visited = new int[len];
        Stack<Integer> out = new Stack<>();
        permuteDFS(num,0,visited,out,res);
        return res;
    }

    private void permuteDFS(int[] num_, int level, int[] visited_, Stack out_, List res_) {
        if (level == num_.length) res_.add(out_.toString());
        else {
            for (int i = 0; i < num_.length; i++) {
                if (visited_[i] == 0) {
                    visited_[i] = 1;
                    out_.push(num_[i]);
                    System.out.println("before" + i + " " + out_.toString());
                    permuteDFS(num_, level + 1, visited_, out_, res_);
                    visited_[i] = 0;
                    out_.pop();
                    System.out.println("after" + i + " " + out_.toString());
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] num = {1,2,3};
        Permutaions testP = new Permutaions();
        List permutions = testP.permute(num);
        Iterator it = permutions.iterator();
//        while (it.hasNext()){
//            System.out.println(it.next());
//        }
    }
}
