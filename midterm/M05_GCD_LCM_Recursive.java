package midterm;

import java.io.*;
import java.util.*;

public class M05_GCD_LCM_Recursive {
    static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong(), b = sc.nextLong();
        long g = gcd(a, b);
        long l = (a / g) * b;
        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }
}


/*
 * Time Complexity: O(log min(a, b))
 * 說明：遞迴歐幾里得算法，每次取餘數使數值快速下降；LCM 以 a/g*b 先除後乘避免溢位。
 */
