// 題目：Combination Sum II
// 給定一組整數陣列 candidates 和目標值 target，
// 找出所有「不重複」的組合，使其元素總和等於 target。
// 每個元素在每組組合中只能使用一次。

import java.util.*;

public class lt_40_CombinationSumII {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // 測試案例 1
        int[] candidates1 = {10,1,2,7,6,1,5};
        int target1 = 8;
        System.out.println("Input: " + Arrays.toString(candidates1) + ", target = " + target1);
        System.out.println("Output: " + solution.combinationSum2(candidates1, target1));

        // 測試案例 2
        int[] candidates2 = {2,5,2,1,2};
        int target2 = 5;
        System.out.println("Input: " + Arrays.toString(candidates2) + ", target = " + target2);
        System.out.println("Output: " + solution.combinationSum2(candidates2, target2));
    }

    static class Solution {
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(candidates); // 先排序，方便去除重複組合
            backtrack(candidates, target, 0, new ArrayList<>(), res);
            return res;
        }

        private void backtrack(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> res) {
            if (target == 0) { // 找到一組合法解
                res.add(new ArrayList<>(path));
                return;
            }
            if (target < 0) return; // 超過目標值，直接返回

            for (int i = start; i < candidates.length; i++) {
                // 跳過相同數字，避免重複組合
                if (i > start && candidates[i] == candidates[i - 1]) continue;

                // 選擇當前數字
                path.add(candidates[i]);
                // 遞迴：下一層從 i+1 開始（因為同一個數字不能再用）
                backtrack(candidates, target - candidates[i], i + 1, path, res);
                // 回溯：移除最後選的數字
                path.remove(path.size() - 1);
            }
        }
    }
}

/*
解題思路：
1. 題目要求：找出 candidates 中所有「不重複」的組合，使其總和等於 target，每個數字只能用一次。
2. 由於可能有重複的數字，因此必須先排序，這樣才能方便地「去重」。
   - 當前層迴圈中，如果 candidates[i] == candidates[i-1]，且 i > start，表示這個數字已經被同層使用過，需要跳過。
3. 使用回溯法（Backtracking）：
   - 每次遞迴選擇一個數字，並遞迴處理剩餘 target。
   - 當 target == 0，代表找到一組解，加入結果。
   - 當 target < 0，代表總和超過 target，直接回溯。
4. 與 Combination Sum I 不同之處：
   - 這題每個數字只能用一次，所以遞迴時傳入 i+1，而不是 i。
   - 必須處理重複數字的情況（用排序 + 跳過相同數字技巧）。

時間複雜度：O(2^n)，最壞情況每個數字都有可能被選取或不選取。
空間複雜度：O(n)，主要來自遞迴的深度與 path 暫存。
*/

