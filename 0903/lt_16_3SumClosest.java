// 題目：16. 3Sum Closest
// 方法：排序 + 定錨一點 + 雙指針逼近
// 時間複雜度：O(n^2)，空間複雜度：O(1)

import java.util.*;

public class lt_16_3SumClosest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 輸入
        System.out.println("請輸入陣列長度 n：");
        int n = sc.nextInt();
        int[] nums = new int[n];
        System.out.println("請輸入 " + n + " 個整數：");
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
        System.out.println("請輸入目標值 target：");
        int target = sc.nextInt();
        sc.close();

        Solution sol = new Solution();
        int ans = sol.threeSumClosest(nums, target);
        System.out.println("最接近目標的三數總和 = " + ans);
    }
}

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // 排序
        int n = nums.length;

        int closest = nums[0] + nums[1] + nums[2]; // 初始值

        for (int i = 0; i < n - 2; i++) {
            int l = i + 1, r = n - 1;

            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];

                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
                }

                if (sum == target) return sum;
                else if (sum < target) l++;
                else r--;
            }
        }
        return closest;
    }
}

/*
解題思路：
- 先排序 → 再固定一個數 nums[i]。
- 另外兩數用雙指針在區間內尋找，並更新最接近 target 的總和。
- 若 sum == target，表示最佳解，直接返回。
- 時間 O(n^2)，空間 O(1)。
*/

