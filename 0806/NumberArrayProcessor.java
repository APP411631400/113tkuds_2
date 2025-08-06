import java.util.*;

public class NumberArrayProcessor {
    public static void main(String[] args) {
        int[] arr = {3, 5, 3, 2, 6, 2, 1};
        int[] unique = removeDuplicates(arr);
        System.out.print("移除重複後: ");
        printArray(unique);

        int[] sorted1 = {1, 3, 5, 7};
        int[] sorted2 = {2, 4, 6, 8};
        int[] merged = mergeSorted(sorted1, sorted2);
        System.out.print("合併排序陣列: ");
        printArray(merged);

        int frequent = mostFrequent(arr);
        System.out.println("出現頻率最高: " + frequent);

        int[][] parts = splitArray(arr);
        System.out.print("子陣列1: ");
        printArray(parts[0]);
        System.out.print("子陣列2: ");
        printArray(parts[1]);
    }

    public static int[] removeDuplicates(int[] a) {
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        for (int v : a) set.add(v);
        int[] res = new int[set.size()];
        int i = 0;
        for (int v : set) res[i++] = v;
        return res;
    }

    public static int[] mergeSorted(int[] a, int[] b) {
        int i = 0, j = 0, k = 0;
        int[] c = new int[a.length + b.length];
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) c[k++] = a[i++];
            else c[k++] = b[j++];
        }
        while (i < a.length) c[k++] = a[i++];
        while (j < b.length) c[k++] = b[j++];
        return c;
    }

    public static int mostFrequent(int[] a) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int v : a) count.put(v, count.getOrDefault(v, 0) + 1);
        int maxKey = a[0], maxCount = 0;
        for (Map.Entry<Integer, Integer> e : count.entrySet()) {
            if (e.getValue() > maxCount) {
                maxCount = e.getValue();
                maxKey = e.getKey();
            }
        }
        return maxKey;
    }

    public static int[][] splitArray(int[] a) {
        Integer[] b = Arrays.stream(a).boxed().toArray(Integer[]::new);
        Arrays.sort(b, Collections.reverseOrder());
        List<Integer> p1 = new ArrayList<>();
        List<Integer> p2 = new ArrayList<>();
        int sum1 = 0, sum2 = 0;
        for (int v : b) {
            if (sum1 <= sum2) {
                p1.add(v);
                sum1 += v;
            } else {
                p2.add(v);
                sum2 += v;
            }
        }
        return new int[][] {
            p1.stream().mapToInt(Integer::intValue).toArray(),
            p2.stream().mapToInt(Integer::intValue).toArray()
        };
    }

    public static void printArray(int[] a) {
        System.out.print("[");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if (i < a.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}

