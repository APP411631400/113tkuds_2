// 題目：15. 3Sum
// 方法：排序 + 定錨一點 + 兩指針
// 時間複雜度：O(n^2)；空間複雜度：O(1)（不計輸出）
//
// 輸入格式（自行測試方便）：
// 第一行：n
// 第二行：n 個整數（以空白分隔）
// 例：
// 6
// -1 0 1 2 -1 -4
//
// 輸出：每個三元組一行，例如 [-1, -1, 2]

import java.util.*;

public class lt_15_3Sum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("請輸入陣列長度 n：");
        int n = sc.nextInt();
        int[] nums = new int[n];
        System.out.println("請輸入 " + n + " 個整數：");
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
        sc.close();

        Solution sol = new Solution();
        List<List<Integer>> res = sol.threeSum(nums);

        System.out.println("所有不重複三元組：");
        for (List<Integer> t : res) {
            System.out.println(t);
        }
    }
}

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重：錨點
            if (nums[i] > 0) break; // 之後都更大，無解

            int target = -nums[i];
            int l = i + 1, r = nums.length - 1;

            while (l < r) {
                int sum = nums[l] + nums[r];
                if (sum == target) {
                    ans.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    int leftVal = nums[l], rightVal = nums[r];
                    while (l < r && nums[l] == leftVal) l++; // 去重：左
                    while (l < r && nums[r] == rightVal) r--; // 去重：右
                } else if (sum < target) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return ans;
    }
}

/*
解題思路：
- 排序後固定 i，轉化為 two-sum（target = -nums[i]）問題，區間使用雙指針。
- 去重重點：i 要避重；每次收錄答案後，l/r 也要跳過相同數字。
- 例：nums = [-1,0,1,2,-1,-4] → 排序後 [-4,-1,-1,0,1,2] → 結果 [[-1,-1,2],[-1,0,1]]。
*/

