// 題目：12. Integer to Roman
// 方法：貪心法 (Greedy) + 對照表
// 時間複雜度：O(1) （數字最多 3999，最多處理 15 個符號）
// 空間複雜度：O(1)

import java.util.*;

public class lt_12_IntegerToRoman {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 輸入數字
        System.out.println("請輸入一個數字 (1 <= num <= 3999): ");
        int num = sc.nextInt();
        sc.close();

        Solution sol = new Solution();
        String roman = sol.intToRoman(num);
        System.out.println("轉換結果 = " + roman);
    }
}

class Solution {
    public String intToRoman(int num) {
        // 對應表：包含常規與特殊（subtractive form）數字
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {
            "M", "CM", "D", "CD", "C", "XC",
            "L", "XL", "X", "IX", "V", "IV", "I"
        };

        StringBuilder sb = new StringBuilder();
        // 從大到小檢查，能減就減
        for (int i = 0; i < values.length && num > 0; i++) {
            while (num >= values[i]) {
                num -= values[i];       // 減去對應數值
                sb.append(symbols[i]);  // 拼上符號
            }
        }
        return sb.toString();
    }
}

/*
解題思路：
1. 羅馬數字有 7 個基本符號，外加 6 個特殊 subtractive form（如 IV, IX, XL, XC, CD, CM）。
2. 將所有可能的數值與符號從大到小排列。
3. 從最大值開始，不斷減去能用的數值，並將對應符號加入結果。
4. 直到數字變為 0 為止。
5. 因為最大數是 3999，所以最多執行 15 次左右的拼接，效率可視為 O(1)。
*/

