import java.util.*;

public class AdvancedStringRecursion {
    public static void main(String[] args) {
        String s = "abc";
        System.out.println("原始字串: " + s);

        System.out.println("\n1. 所有排列組合：");
        List<String> perms = new ArrayList<>();
        permute("", s, perms);
        System.out.println(perms);

        System.out.println("\n2. 字串匹配演算法 (尋找 \"bc\")：");
        System.out.println(stringMatch(s, "bc") ? "找到" : "未找到");

        System.out.println("\n3. 遞迴移除重複字符 (對 \"abacb\")：");
        System.out.println(removeDuplicates("abacb"));

        System.out.println("\n4. 所有子字串組合：");
        List<String> subs = new ArrayList<>();
        substringsRec(s, subs);
        System.out.println(subs);
    }

    public static void permute(String prefix, String remaining, List<String> res) {
        if (remaining.length() == 0) {
            res.add(prefix);
        } else {
            for (int i = 0; i < remaining.length(); i++) {
                permute(
                    prefix + remaining.charAt(i),
                    remaining.substring(0, i) + remaining.substring(i + 1),
                    res
                );
            }
        }
    }

    public static boolean stringMatch(String text, String pattern) {
        if (pattern.length() == 0) return true;
        if (text.length() < pattern.length()) return false;
        if (text.substring(0, pattern.length()).equals(pattern)) return true;
        return stringMatch(text.substring(1), pattern);
    }

    public static String removeDuplicates(String s) {
        if (s.length() <= 1) return s;
        char c = s.charAt(0);
        String rest = removeDuplicates(s.substring(1));
        return c + rest.replaceAll(String.valueOf(c), "");
    }

    public static void substringsRec(String s, List<String> res) {
        if (s.length() == 0) return;
        for (int i = 1; i <= s.length(); i++) {
            res.add(s.substring(0, i));
        }
        substringsRec(s.substring(1), res);
    }
}

