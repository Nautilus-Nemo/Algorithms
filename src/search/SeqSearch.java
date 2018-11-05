package search;
public class SeqSearch<T> {
    public int seqSearch(T[] src,T dis){
        if (!src[0].equals(dis))
            throw new RuntimeException("未设置监视哨");
        int len = src.length - 1;
        while (!src[len].equals(dis)){
            --len;
        }
        return len;
    }

    public static void main(String[] args) {
        //导致异常两种情况
        //1：未设置监视哨
        //2：比较对象未实现equals
        Integer[] src_= {3,1,2,3,4,5,6,7};
        SeqSearch<Integer> ss = new SeqSearch<>();
        int res = ss.seqSearch(src_, 3);
        System.out.println(res);
    }
}