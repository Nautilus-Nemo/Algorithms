package leetcode;

/**
 * 时间复杂度
 *  O(m+n) 递归深度为两个排序List的长度
 * 额外空间
 *  O(1)
 * 递归实现
 */
public class MergedTwoSortedLists_21 {
    private class ListNode{
        int val;
        ListNode next;
        public ListNode(int val_){
            this.val = val_;
        }
    }

    public ListNode mergedTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null){
            return l1;
        }
        if (l1.val <= l2.val){
            l1.next = mergedTwoLists(l1.next,l2);
            return l1;
        }else{
            l2.next = mergedTwoLists(l1,l2.next);
            return l2;
        }
    }

    public static void main(String[] args) {

    }
}
