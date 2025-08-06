public class RecursionVsIteration {
    public static void main(String[] args) {
        int n = 30;
        int[] array = {1,2,3,4,5,6,7,8,9,10};
        String text = "Recursion and Iteration Comparison Example";
        String parenGood = "((()())())";
        String parenBad = "(()(()";

        System.out.println("1. 計算費氏數列第 " + n + " 項");
        long t1 = System.nanoTime();
        long fibR = fibRecursive(n);
        long t2 = System.nanoTime();
        long fibI = fibIterative(n);
        long t3 = System.nanoTime();
        System.out.println("  遞迴結果: " + fibR + "，耗時 " + (t2-t1)/1_000_000 + " ms");
        System.out.println("  迭代結果: " + fibI + "，耗時 " + (t3-t2)/1_000_000 + " ms");

        System.out.println("\n2. 陣列元素乘積");
        t1 = System.nanoTime();
        long prodR = productRecursive(array, 0);
        t2 = System.nanoTime();
        long prodI = productIterative(array);
        t3 = System.nanoTime();
        System.out.println("  遞迴結果: " + prodR + "，耗時 " + (t2-t1)/1_000_000 + " ms");
        System.out.println("  迭代結果: " + prodI + "，耗時 " + (t3-t2)/1_000_000 + " ms");

        System.out.println("\n3. 計算字串中元音字母數量");
        t1 = System.nanoTime();
        int vowR = countVowelRecursive(text, 0);
        t2 = System.nanoTime();
        int vowI = countVowelIterative(text);
        t3 = System.nanoTime();
        System.out.println("  遞迴結果: " + vowR + "，耗時 " + (t2-t1)/1_000_000 + " ms");
        System.out.println("  迭代結果: " + vowI + "，耗時 " + (t3-t2)/1_000_000 + " ms");

        System.out.println("\n4. 檢查括號配對是否正確");
        t1 = System.nanoTime();
        boolean okR1 = isBalancedRecursive(parenGood, 0, 0);
        boolean okR2 = isBalancedRecursive(parenBad, 0, 0);
        t2 = System.nanoTime();
        boolean okI1 = isBalancedIterative(parenGood);
        boolean okI2 = isBalancedIterative(parenBad);
        t3 = System.nanoTime();
        System.out.println("  遞迴 (\"" + parenGood + "\"): " + okR1 + "  (\"" + parenBad + "\"): " + okR2 + "，耗時 " + (t2-t1)/1_000_000 + " ms");
        System.out.println("  迭代 (\"" + parenGood + "\"): " + okI1 + "  (\"" + parenBad + "\"): " + okI2 + "，耗時 " + (t3-t2)/1_000_000 + " ms");
    }

    public static long fibRecursive(int n) {
        if (n <= 1) return n;
        return fibRecursive(n-1) + fibRecursive(n-2);
    }

    public static long fibIterative(int n) {
        if (n <= 1) return n;
        long a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            long c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    public static long productRecursive(int[] a, int idx) {
        if (idx >= a.length) return 1;
        return a[idx] * productRecursive(a, idx + 1);
    }

    public static long productIterative(int[] a) {
        long p = 1;
        for (int v : a) {
            p *= v;
        }
        return p;
    }

    public static int countVowelRecursive(String s, int idx) {
        if (idx >= s.length()) return 0;
        char c = Character.toLowerCase(s.charAt(idx));
        int add = (c=='a'||c=='e'||c=='i'||c=='o'||c=='u') ? 1 : 0;
        return add + countVowelRecursive(s, idx + 1);
    }

    public static int countVowelIterative(String s) {
        int count = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) >= 0) count++;
        }
        return count;
    }

    public static boolean isBalancedRecursive(String s, int idx, int balance) {
        if (balance < 0) return false;
        if (idx == s.length()) return balance == 0;
        char c = s.charAt(idx);
        if (c == '(') balance++;
        else if (c == ')') balance--;
        return isBalancedRecursive(s, idx + 1, balance);
    }

    public static boolean isBalancedIterative(String s) {
        int bal = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') bal++;
            else if (c == ')') bal--;
            if (bal < 0) return false;
        }
        return bal == 0;
    }
}

