import java.util.*;

public class ValidMaxHeapChecker {
    public static boolean isValidMaxHeap(int[] a) {
        return firstViolationIndex(a) == -1;
    }

    public static int firstViolationIndex(int[] a) {
        int n = a.length;
        for (int i = 0; i <= (n - 2) / 2; i++) {
            int l = 2 * i + 1, r = 2 * i + 2;
            if (l < n && a[i] < a[l]) return l;
            if (r < n && a[i] < a[r]) return r;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] tests = {
            {100, 90, 80, 70, 60, 75, 65},
            {100, 90, 80, 95, 60, 75, 65},
            {50},
            {}
        };
        for (int[] t : tests) {
            int idx = firstViolationIndex(t);
            if (idx == -1) {
                System.out.println(Arrays.toString(t) + " -> true");
            } else {
                int p = (idx - 1) / 2;
                System.out.println(Arrays.toString(t) + " -> false (index " + idx + " " + t[idx] + " > parent " + t[p] + ")");
            }
        }
    }
}

