package leetcode;
import java.util.*;

/**
 * 任何实现equals类且都可以进行深度为K路径长度的检索
 *
 * @param <T> 泛型类
 */
public class BTAllNodeDistanceK<T> {
    private T dist[];
    private static int controlDiVa = 0;
    List<T> list;
    //错误使用 Map<TreeNode<T> node,TreeNode<T> parents> parents;
    Map<TreeNode<T>, TreeNode<T>>  parents;

    //Definition for a binary tree node.
    public static class TreeNode<T> {
        T val;
        TreeNode<T> left;
        TreeNode<T> right;

        TreeNode(T x) {
            val = x;
        }

        public TreeNode() {
        }
    }

    public BTAllNodeDistanceK(T sour[]) {
        // TODO Auto-generat d constructor stub
        this.dist = sour;
    }

    public List<T> distanceK(TreeNode<T> root, TreeNode<T> target, int K) {
        if (root == null || target == null)
            return null;
        list = new ArrayList<T>();
        preOrder(root, target, list, K);
        return list;
    }

    private int preOrder(TreeNode<T> root, TreeNode<T> target, List<T> list, int K) {
        if (root == null)
            return -1;
        if (root.val.equals(target.val)) {
            if (K == 0) {
                list.add(root.val);
                return 1;
            }
            getNodesFromChildren(root.left, list, K - 1);
            getNodesFromChildren(root.right, list, K - 1);
            return 1;
        } else {
            //回溯作用，能够回溯到祖先和祖先的右孩子
            int ret = preOrder(root.left, target, list, K);
            if (ret >= 0) {
                if (ret == K) {
                    list.add(root.val);
                    return ret + 1;
                }
                getNodesFromChildren(root.right, list, K - (ret + 1));
                return ret + 1;
            }
            //回溯作用，能够回溯到祖先和祖先的右孩子
            ret = preOrder(root.left, target, list, K);
            if (ret >= 0) {
                if (ret == K) {
                    list.add(root.val);
                    return ret + 1;
                }
                getNodesFromChildren(root.left, list, K - (ret + 1));
                return ret + 1;
            }
        }

        return -1;
    }

    /**
     * @param root（1）满足root.val==target.val （2）祖先结点 K=K-向上回溯祖先的数目
     * @param list                          添加满足值
     * @param K                             距离K的路径
     *                                      实现根据以树根节点root进行中序遍历查找距离K路径的结点，并把满足的结点的值添加进list
     */
    private void getNodesFromChildren(TreeNode<T> root, List<T> list, int K) {
        if (root == null)
            return;
        if (K == 0) {
            list.add(root.val);
            return;
        }
        getNodesFromChildren(root.left, list, K - 1);
        getNodesFromChildren(root.right, list, K - 1);
    }

    /**
     * 创造一颗二叉树
     *
     * @param tN
     * @return tN指向引用和调用函数root值引用不同，所以需要返回值使调用函数的root引用地址保持和tN引用地址一样
     */
    public TreeNode<T> createTree(TreeNode<T> tN) {
        T tem = dist[controlDiVa];
        controlDiVa++;
        if (tem == null) {
            return tN = null;
        } else {
            tN = new TreeNode<T>();
            tN.val = tem;
            tN.left = createTree(tN.left);
            tN.right = createTree(tN.right);
        }
        return tN;
    }

    /**
     * 实现中序排序
     *
     * @param root 根结点
     */
    public void inOrder(TreeNode<T> root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    public static void main(String[] args) {
        /****************************第一种方法查找********************************
         *
         int k=2;
         //必须满足上述条件的数组才能使用createTree方法建立一颗二叉树
         Integer[] sour= {1,2,3,null,null,null,134,5,null,null,6,null,null};
         **
         *                            1
         *                     ////////\\\\\\\\
         *                    2               134
         *                ////\\\\         ////\\\\
         *               3       null      5       6
         *             //\\              //\\     //\\
         *         null   null        null  null  null null
         *
         TreeNode<Integer> root=new TreeNode<Integer>();
         TreeNode<Integer> target=new TreeNode<Integer>();
         target.val=2;
         target.left=null;
         target.right=null;
         BTAllNodeDistanceK<Integer> a=new BTAllNodeDistanceK<Integer>(sour);
         root=a.createTree(root);
         a.distanceK(root,target,k);
         Iterator<Integer> ite=a.list.iterator();
         System.out.println("距离路径为"+k+"的值为：");
         while(ite.hasNext()) {
         System.out.println(ite.next());
         }
         ******************************************************************/
        Integer[] sour = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        /* 必须满足的树的结构
                                       3
                               //////// \\\\\\\\
                              5                 1
                          //// \\\\         //// \\\\
                         6          2      0         8
                       // \\      // \\
                    null   null  7     4
        */
        TreeNode<Integer> root;
        TreeNode<Integer> target;
        BTAllNodeDistanceK<Integer> a = new BTAllNodeDistanceK<>(sour);
        root = a.createTree1();
        System.out.println("1.测试是否能够生成二叉树：");
        a.inOrder(root);
        System.out.println();
        System.out.println("2.测试是否能够查找到在指定树中指定树结点值的结点：");
        target = a.dfsForTarget(root, 5);
        System.out.println("target.val:" + target.val);
        if (target.left != null)
            System.out.println("if target.left != null target.left.val: "+target.left.val);
        if (target.right != null)
            System.out.println("if target.right != null target.left.val: "+target.right.val);
        a.list=a.distanceK1(root,target,1);
        Iterator<Integer> ite=a.list.iterator();
        System.out.println("距离路径为"+1+"的值为：");
        while(ite.hasNext()) {
            System.out.println(ite.next());
        }
    }

    public List<T> distanceK1(TreeNode<T> root, TreeNode<T> target, int K) {
        parents = new HashMap();
        //dfs(root, parents);
        dfs(root, null);

        List<TreeNode<T>> queue = new LinkedList<>();
        queue.add(null);
        queue.add(target);

        //防止回溯到已经遍历过的结点
        Set<TreeNode<T>> seen = new HashSet();
        seen.add(null);
        seen.add(target);

        int dist = 0;
        while (!queue.isEmpty()) {
            //移除队列的首元素（first）
            TreeNode<T> node = ((LinkedList<TreeNode<T>>) queue).poll();
            //每次null路径进1
            if (node == null) {
                if (dist == K) {
                    List<T> ans = new ArrayList<>();
                    for (TreeNode<T> t : queue)
                        ans.add(t.val);
                    return ans;
                }
                ++dist;
                ((LinkedList<TreeNode<T>>) queue).offer(null);
            } else {
                if (!seen.contains(node.left)) {
                    seen.add(node.left);
                    //添加元素到尾(tails)
                    ((LinkedList<TreeNode<T>>) queue).offer(node.left);
                }
                if (!seen.contains(node.right)) {
                    seen.add(node.right);
                    ((LinkedList<TreeNode<T>>) queue).offer(node.right);
                }
                TreeNode<T> parent = parents.get(node);
                if (!seen.contains(parent)) {
                    seen.add(parent);
                    ((LinkedList<TreeNode<T>>) queue).offer(parent);
                }
            }
        }

        return new ArrayList<>();
    }

    /**
     * 创造一颗二叉树,存储值的数组需要满足特定结构
     *
     * @return root
     */
    public TreeNode<T> createTree1() {
        int j = dist.length;
        int i = 1;
        if (j == 0) {
            return null;
        }
        TreeNode<T> tN = new TreeNode<>();
        tN.val = dist[0];

        List<TreeNode<T>> queue = new LinkedList<>();

        queue.add(tN);
        //申明在作用域的上一层
        TreeNode<T> parent;
        TreeNode<T> tem;
        while ((2 * i - 1) < j) {
/***********************错误代码*************************************
 *  parent不可能为null，因为每次可进入队列的元素必然new TreeNode<T>一次
 *******************************************************************/
//            parent=((LinkedList<TreeNode<T>>) queue).poll();
//            if(parent==null)
//                throw new NullPointerException("不存在parent为null的结点");
//            tem = new TreeNode<>();
//            tem.val = dist[2 * i - 1];
//            parent.left = tem;
//            ((LinkedList<TreeNode<T>>) queue).offer(tem);
/***********************正确代码*************************************
 *  添加数组元素是否为null的判定
 *******************************************************************/

            parent = ((LinkedList<TreeNode<T>>) queue).poll();

            if (parent == null)
                throw new NullPointerException("不存在父亲为null的结点");
            if (dist[2 * i - 1] == null)
                ((LinkedList<TreeNode<T>>) queue).offer(null);
            else {
                tem = new TreeNode<>();
                tem.val = dist[2 * i - 1];
                parent.left = tem;
                ((LinkedList<TreeNode<T>>) queue).offer(tem);
            }
            if (2 * i < j) {
                if (dist[2 * i] == null)
                    ((LinkedList<TreeNode<T>>) queue).offer(null);
                else {
                    tem = new TreeNode<>();
                    tem.val = dist[2 * i];
                    parent.right = tem;
                    ((LinkedList<TreeNode<T>>) queue).offer(tem);
                }
            }
            i++;
        }
        return tN;
    }

    /**
     * :leetcode提供解决方案
     * 把所有结点的父结点标记
     *
     * @param node   孩子结点
     * @param parent 父亲结点
     */
    private void dfs(TreeNode<T> node, TreeNode<T> parent) {
        if (node != null) {
            parents.put(node, parent);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }

    /**
     * :自己模仿写的
     * 把所有结点的父结点标记
     *
     * @param root_    父结点
     * @param parents_ 以孩子结点为key,父亲结点为value的标记集合
     */
    private void dfs1(TreeNode<T> root_,HashMap parents_) {
        //排除第一次进入时和达到null结点出现root_为null的情况
        if (root_ == null)
            return;
        //递归基
        if (root_.left != null) {
            parents_.put(root_.left, root_);
            dfs1(root_.left, parents_);
        }
        if (root_.right != null) {
            parents_.put(root_.right, root_);
            dfs1(root_.right, parents_);
        }
        return;
    }


    public TreeNode<T> dfsForTarget(TreeNode<T> root, T val) {
        TreeNode<T> tem;
        if (root != null) {
            if (root.val.equals(val)) {
                return root;
            }
            tem = dfsForTarget(root.left, val);
            if (tem != null) {
                return tem;
            }
            tem = dfsForTarget(root.right, val);
            if (tem != null)
                return tem;
        }
        return null;
    }
}