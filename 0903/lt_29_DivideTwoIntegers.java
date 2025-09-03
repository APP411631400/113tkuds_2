// 題目：29. Divide Two Integers
// 給定兩個整數 dividend 和 divisor，在不能使用乘法、除法和取餘數運算子的情況下，求出它們的商。
// 除法結果需向零截斷，並確保結果在 32-bit signed integer 範圍內。

public class lt_29_DivideTwoIntegers {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // 測試案例
        System.out.println(solution.divide(10, 3));   // 輸出 3
        System.out.println(solution.divide(7, -3));   // 輸出 -2
        System.out.println(solution.divide(-2147483648, -1)); // 輸出 2147483647 (避免 overflow)
    }
}

class Solution {
    public int divide(int dividend, int divisor) {
        // 處理邊界：INT_MIN / -1 會溢出
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // 紀錄結果的正負號
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // 轉換為 long 避免溢出，並取絕對值
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);
        long result = 0;

        // 使用位移加速計算 (類似二分法)
        while (a >= b) {
            long temp = b, multiple = 1;
            while (a >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            a -= temp;
            result += multiple;
        }

        // 回傳結果，套用正負號
        return negative ? (int) -result : (int) result;
    }
}

/*
解題思路：
1. 題目要求實作除法，禁止使用乘法(*)、除法(/)、取餘數(%)。
2. 我們用「位移法」模擬除法：
   - 每次將 divisor 不斷左移，找到最大能減去 dividend 的倍數。
   - 減掉後累加到結果，再繼續計算剩餘 dividend。
3. 重點處理：
   - overflow：當 dividend = -2^31 且 divisor = -1，結果超出 int 範圍，回傳 Integer.MAX_VALUE。
   - 使用 long 避免溢出，最後轉回 int。
4. 複雜度：
   - 時間 O(log n)，因為 divisor 每次翻倍。
   - 空間 O(1)。
*/


