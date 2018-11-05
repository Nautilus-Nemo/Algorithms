package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 时间复杂度
 *  O(n)：迭代次数为n次，哈希表查询平均时间`amortized`为O(1)
 * 额外空间复杂度
 *  o(n)：额外空间来自存取在hashmap的元素,这个存储最多n个元素
 */
public class TwoSum_1 {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        int[] testNums = {2, 7, 11, 15};
        int testTarget = 9;
        for(int i : twoSum(testNums,testTarget)){
            System.out.println(i);
        }
    }
}
