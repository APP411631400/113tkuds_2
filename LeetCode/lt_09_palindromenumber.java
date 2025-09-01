package LeetCode;

public class lt_09_palindromenumber {
    public boolean isPalindrome(int x) {
        // 負數或尾數為 0（但不是 0 本身）都不是回文
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversedHalf = 0;
        while (x > reversedHalf) {
            reversedHalf = reversedHalf * 10 + x % 10;
            x /= 10;
        }

        // 如果是奇數位數，reversedHalf 會多一位，要去掉中間那位再比較
        return x == reversedHalf || x == reversedHalf / 10;
    }
}
