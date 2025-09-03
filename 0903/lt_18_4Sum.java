// 題目：18. 4Sum
// 方法：排序 + 兩層定錨 + 內層雙指針
// 時間複雜度：O(n^3)，空間複雜度：O(1)

import java.util.*;

public class lt_18_4Sum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 輸入
        System.out.println("請輸入陣列長度 n：");
        int n = sc.nextInt();
        int[] nums = new int[n];
        System.out.println("請輸入 " + n + " 個整數（可含負數）：");
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
        System.out.println("請輸入 target：");
        int target = sc.nextInt();
        sc.close();

        Solution sol = new Solution();
        List<List<Integer>> ans = sol.fourSum(nums, target);

        System.out.println("所有不重複四元組：");
        for (List<Integer> q : ans) System.out.println(q);
    }
}

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;        // 去重：i

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; // 去重：j

                int l = j + 1, r = n - 1;

                while (l < r) {
                    long sum = 0L + nums[i] + nums[j] + nums[l] + nums[r]; // 防溢位

                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        int lv = nums[l], rv = nums[r];
                        while (l < r && nums[l] == lv) l++; // 去重：l
                        while (l < r && nums[r] == rv) r--; // 去重：r
                    } else if (sum < target) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
        }
        return res;
    }
}

/*
解題思路：
- 排序 → 固定 i、j → 用雙指針在尾段找兩數，使五者和等於 target。
- 去重：i、j 層遇到相同值直接 continue；命中一組後，l/r 也要跳過重複值。
- 因為數值可到 1e9，四數加總需用 long；target 仍為 int。
*/

