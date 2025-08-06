public class RecursiveMathCalculator {
    public static void main(String[] args) {
        System.out.println("C(5,2) = " + combination(5, 2));
        System.out.println("Catalan(5) = " + catalan(5));
        System.out.println("Hanoi moves for 3 = " + hanoi(3));
        System.out.println("Is 12321 palindrome? " + isPalindrome(12321));
        System.out.println("Is 12345 palindrome? " + isPalindrome(12345));
    }

    public static long combination(int n, int k) {
        if (k == 0 || k == n) return 1;
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    public static long catalan(int n) {
        if (n <= 1) return 1;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += catalan(i) * catalan(n - 1 - i);
        }
        return sum;
    }

    public static long hanoi(int n) {
        if (n == 0) return 0;
        return 2 * hanoi(n - 1) + 1;
    }

    public static boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) return false;
        }
        return true;
    }
}

