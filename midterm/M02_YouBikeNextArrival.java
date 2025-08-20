package midterm;

import java.io.*;
import java.util.*;

public class M02_YouBikeNextArrival {
    static int toMinutes(String t) {
        String[] p = t.split(":");
        return Integer.parseInt(p[0]) * 60 + Integer.parseInt(p[1]);
    }

    static String toHHMM(int m) {
        int h = m / 60, mm = m % 60;
        return String.format("%02d:%02d", h, mm);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        int[] times = new int[n];
        for (int i = 0; i < n; i++) times[i] = toMinutes(br.readLine().trim());
        int query = toMinutes(br.readLine().trim());

        int l = 0, r = n - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (times[mid] > query) {
                ans = times[mid];
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        if (ans == -1) System.out.println("No bike");
        else System.out.println(toHHMM(ans));
    }
}
