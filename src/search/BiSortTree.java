package search;
public class BiSortTree {
    public BiNode<Integer> root;
    private static class BiNode<T>{
        private T data;
        private BiNode lChild;
        private BiNode rChild;
    }
    //创建一个二叉排序树
    public BiSortTree(int[] a){
        int len = a.length;
        for (int i = 0; i < len; i++) {
            BiNode<Integer> tem = new BiNode<>();
            tem.data = a[i];
            tem.lChild = null;
            tem.rChild = null;
            root = insertBST(root,tem);
        }
    }

    //插入一个结点
    //注意java中引用的真正意义，因为不能存在指针，递归所以只能通过返回值来达到链接的作用

    /**
     * @param root_ 引用
     * @param s 需要插入的结点
     * @return
     */
    public BiNode<Integer> insertBST(BiNode<Integer> root_, BiNode<Integer> s) {
        if (root_ == null)
            root_ = s;
        else if (s.data < root_.data)
            root_.lChild = insertBST(root_.lChild, s);
        else
            root_.rChild = insertBST(root_.rChild, s);
        return root_;
    }
    //删除结点f的左孩子p
    /**
     * @param p 需要删除的结点，属于父亲结点的左孩子
     * @param f 需要删除的结点的父亲结点
     */
    public void deleteBST(BiNode<Integer> p,BiNode f){
        //p为叶子
        if (p.lChild == null && p.rChild == null){
            f.lChild = null;
        //p只有左子树
        //}else if (p.lChild != null && p.rChild == null){
        }else if (p.rChild == null){
            f.lChild = p.lChild;
        }else if (p.lChild == null){
            f.rChild = p.rChild;
        }else{
            BiNode<Integer> par = p;
            BiNode<Integer> s = p.rChild;
            while(s.lChild != null){
                par = s;
                s = s.lChild;
            }
            p.data = s.data;
            if (p == par){
                //特殊情况
                //p.rChild = null;
                p.rChild = s.rChild;
            }else{
                par.lChild = s.rChild;
            }
        }
    }
    //查找值为k的结点
    public BiNode<Integer> searchBST(BiNode<Integer> root_,int k){
        if (root_ == null) return null;
        if (root_.data == k) return root_;
        else if(root_.data < k) return searchBST(root_.rChild,k);
        else return searchBST(root_.lChild,k);
    }

    public static void main(String[] args) {
        int[] test = {1,4,3,5,2};
         /*1  \\
          //  4  \\
        3           5
        //
       2                  */
         //查找结点3
        BiSortTree b = new BiSortTree(test);
        BiNode<Integer> res = b.searchBST(b.root,3);
        System.out.println(res.data);
        //删除结点2
        b.deleteBST(res.lChild, res);
        res = b.searchBST(b.root, 2);
        if (res != null) {
            System.out.println(res.data);
        }else {
            System.out.println("null");
        }
    }
}
