// 題目：11. Container With Most Water
// 方法：雙指針（Two Pointers）
// 時間複雜度：O(n)，空間複雜度：O(1)

import java.util.*;

public class lt_11_ContainerWithMostWater {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 測試輸入：長度 n 與高度陣列
        System.out.println("請輸入陣列長度 n：");
        int n = sc.nextInt();
        int[] height = new int[n];
        System.out.println("請輸入陣列元素：");
        for (int i = 0; i < n; i++) {
            height[i] = sc.nextInt();
        }
        sc.close();

        Solution sol = new Solution();
        int ans = sol.maxArea(height);
        System.out.println("最大盛水面積 = " + ans);
    }
}

class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int best = 0;

        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int width = right - left;
            best = Math.max(best, h * width);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return best;
    }
}

/*
解題思路：
1. 面積公式：A = min(hL, hR) * (r - l)。
2. 從兩端開始夾：每次計算面積，更新最大值。
3. 移動較短邊 → 才可能找到更高的邊提高面積。
4. 只需一次線性掃描，時間 O(n)，空間 O(1)。
*/
