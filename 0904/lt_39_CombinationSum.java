// 題目：Combination Sum
// 給定一組互異的正整數 candidates 和一個目標整數 target，
// 找出所有可以讓數字和為 target 的組合。
// 每個數字可以被選擇無限次，但組合必須唯一。

import java.util.*;

public class lt_39_CombinationSum {

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 測試用例
        int[] candidates1 = {2, 3, 6, 7};
        int target1 = 7;
        System.out.println("Input: [2,3,6,7], target=7");
        System.out.println("Output: " + solution.combinationSum(candidates1, target1));
        // 預期輸出 [[2,2,3],[7]]

        int[] candidates2 = {2, 3, 5};
        int target2 = 8;
        System.out.println("Input: [2,3,5], target=8");
        System.out.println("Output: " + solution.combinationSum(candidates2, target2));
        // 預期輸出 [[2,2,2,2],[2,3,3],[3,5]]
    }

    static class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> res = new ArrayList<>();
            backtrack(candidates, target, 0, new ArrayList<>(), res);
            return res;
        }

        // 回溯法：嘗試所有可能組合
        private void backtrack(int[] candidates, int remain, int start, List<Integer> path, List<List<Integer>> res) {
            if (remain == 0) { // 找到一組符合的組合
                res.add(new ArrayList<>(path));
                return;
            }
            if (remain < 0) { // 剩餘目標小於0，無效
                return;
            }
            for (int i = start; i < candidates.length; i++) {
                path.add(candidates[i]); // 選擇 candidates[i]
                // i 而不是 i+1，因為允許重複使用同一數字
                backtrack(candidates, remain - candidates[i], i, path, res);
                path.remove(path.size() - 1); // 回溯，移除最後選擇
            }
        }
    }
}

/*
解題思路：
1. 題目要求找出所有「數字和為 target 的組合」。
2. 因為每個數字可重複使用，適合用回溯法 (Backtracking)。
3. 我們從索引 start 開始，逐一嘗試 candidates[i]，並遞迴減少 target。
4. 若 target == 0，代表找到一組組合，加入結果。
5. 若 target < 0，代表無效，直接回溯。
6. 為避免重複組合，我們固定從當前索引開始往後選擇數字。
7. 時間複雜度大約為 O(2^n)，但因為有剪枝實際效率更高。
*/

