package leetcode;

/**
 * 包含最多水的容器
 * container with most water
 */
public class MostWaterContainer_11 {
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int water = 0;
        while(i <= j){
            water = Math.max(water, (j - i) * Math.min(height[i], height[j]));
            //为什么可以前移[++i]或者[--j]呢？即不存在其它包含未前移i时或者为前移j，这时容器包含的水最多吗?
            //1.首先前移的必然是@min(height[i],height[j])中的i或者j。
            //2.假设height[i]<height[j]，即++i,显然，从最大长度开始，包含height[i]`未前移i`必然小于当前water容量
            if (height[i] < height[j]) {
                ++i;
            } else {
                --j;
            }
        }
        return water;
    }

    public static void main(String[] args) {
        int[] src = {1,8,6,2,5,4,8,3,7};
        MostWaterContainer_11 m = new MostWaterContainer_11();
        int water = m.maxArea(src);
        System.out.println(water);
    }
}
