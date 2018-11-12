package leetcode;
import jdk.jshell.execution.Util;

import java.util.*;

public class Permutaions {
    /******************** solution 1 ******************************/

    public List permuteS1(int[] num) {
        int len = num.length;
        List<String> res = new ArrayList<>();
        int[] visited = new int[len];
        Stack<Integer> out = new Stack<>();
        permuteDFS1(num,0,visited,out,res);
        return res;
    }

    private void permuteDFS1(int[] num_, int level, int[] visited_, Stack out_, List res_) {
        if (level == num_.length) res_.add(out_.toString());
        else {
            for (int i = 0; i < num_.length; i++) {
                if (visited_[i] == 0) {
                    visited_[i] = 1;
                    out_.push(num_[i]);
                    System.out.println("before" + i + " " + out_.toString());
                    permuteDFS1(num_, level + 1, visited_, out_, res_);
                    visited_[i] = 0;
                    out_.pop();
                    System.out.println("after" + i + " " + out_.toString());
                }
            }
        }
    }

    /******************** solution 2 ******************************/
    public List permuteS2(Vector<Integer> num){
        Stack<String> res = new Stack<>();
        permuteDFS2(num,0,res);
        return res;
    }

    private void permuteDFS2(Vector<Integer> num_,int start,Stack res_) {
        if (start >= num_.size()) res_.push(num_.toString());
        for (int i = start; i < num_.size(); i++) {
            //swap between start and i
            int tem = num_.get(start);
            num_.set(start,num_.get(i));
            num_.set(i,tem);
            System.out.println("before" + i + " " + num_.toString());
            permuteDFS2(num_,(start + 1),res_);
            tem = num_.get(start);
            num_.set(start,num_.get(i));
            num_.set(i,tem);
            System.out.println("after" + i + " " + num_.toString());
        }
    }

    public static void main(String[] args) {
        int[] num1 = {1,2,3};
        Permutaions testP = new Permutaions();

//        List permutions1 = testP.permuteS1(num1);
//        Iterator it1 = permutions1.iterator();
//        while (it1.hasNext()){
//            System.out.println(it1.next());
//        }

        Vector<Integer> num2 = new Vector<>();
        num2.add(1);
        num2.add(2);
        num2.add(3);
        List permutions2 = testP.permuteS2(num2);
//    Iterator it2 = permutions2.iterator();
//        while (it2.hasNext()){
//        System.out.println(it2.next());
//    }
}
}
