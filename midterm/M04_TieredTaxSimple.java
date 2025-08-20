package midterm;

import java.io.*;
import java.util.*;

public class M04_TieredTaxSimple {
    static long tax(long x){
        long t = 0;
        long a = Math.min(x, 120000);
        t += a * 5 / 100;
        if (x > 120000){
            long b = Math.min(x, 500000) - 120000;
            t += b * 12 / 100;
        }
        if (x > 500000){
            long c = Math.min(x, 1000000) - 500000;
            t += c * 20 / 100;
        }
        if (x > 1000000){
            long d = x - 1000000;
            t += d * 30 / 100;
        }
        return t;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        long sum = 0;
        StringBuilder out = new StringBuilder();
        for(int i=0;i<n;i++){
            long income = Long.parseLong(br.readLine().trim());
            long t = tax(income);
            sum += t;
            out.append("Tax: ").append(t).append('\n');
        }
        out.append("Average: ").append(sum / n);
        System.out.print(out.toString());
    }
}

/*
 * Time Complexity: O(n)
 * 說明：每筆收入用常數段距逐段累加，單筆 O(1)；總共 n 筆為 O(n)。
 */
