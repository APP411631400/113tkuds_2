package LeetCode;

public class lt_08_stringtointeger(atoi) {
    public int myAtoi(String s) {
        int i = 0, n = s.length();
        int sign = 1;
        int result = 0;

        // 1. 忽略前導空白
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // 2. 判斷符號位
        if (i < n) {
            char c = s.charAt(i);
            if (c == '+') {
                sign = 1;
                i++;
            } else if (c == '-') {
                sign = -1;
                i++;
            }
        }

        // 3. 讀數字累積結果
        while (i < n) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') break;

            int digit = c - '0';

            // 4. 判斷是否溢位
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        // 5. 帶上符號回傳
        return result * sign;
    }
}
