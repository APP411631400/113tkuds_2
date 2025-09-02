// 題目：13. Roman to Integer
// 方法：從左到右掃描，遇到「小值在大值左邊」時，表示要減去小值
// 時間複雜度：O(n)，n = 字串長度（<= 15）
// 空間複雜度：O(1)

import java.util.*;

public class lt_13_RomanToInteger {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 測試輸入
        System.out.println("請輸入一個羅馬數字 (1 <= 數值 <= 3999): ");
        String s = sc.nextLine().toUpperCase();
        sc.close();

        Solution sol = new Solution();
        int ans = sol.romanToInt(s);
        System.out.println("轉換結果 = " + ans);
    }
}

class Solution {
    public int romanToInt(String s) {
        // 建立羅馬符號對應表
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int total = 0;
        int prev = 0; // 前一個字元對應的值

        // 從右到左掃描，決定加或減
        for (int i = s.length() - 1; i >= 0; i--) {
            int val = map.get(s.charAt(i));

            // 如果當前值 < 前一個值，代表要減去（IV=4, IX=9, XL=40...）
            if (val < prev) {
                total -= val;
            } else {
                total += val;
            }
            prev = val;
        }
        return total;
    }
}

/*
解題思路：
1. 羅馬數字規則：通常是由大到小排列，若小數字出現在大數字左邊，則表示減法。
   例如：
   - IV = 4 (5 - 1)
   - IX = 9 (10 - 1)
   - XL = 40 (50 - 10)
   - CM = 900 (1000 - 100)
2. 演算法：
   - 從字串最後一位開始往前掃描，維護前一位的數值。
   - 若當前數值 < 前一位數值 → 減去（例如 IV）。
   - 否則加上。
3. 時間複雜度 O(n)，字串長度最長 15，因此效率沒問題。
*/

