import java.util.*;

public class KthSmallestElement {
    public static int kthSmallestMaxHeap(int[] nums, int k) {
        if (k < 1 || k > nums.length) throw new IllegalArgumentException();
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, Collections.reverseOrder());
        for (int x : nums) {
            pq.offer(x);
            if (pq.size() > k) pq.poll();
        }
        return pq.peek();
    }

    public static int kthSmallestMinHeap(int[] nums, int k) {
        if (k < 1 || k > nums.length) throw new IllegalArgumentException();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int x : nums) pq.offer(x);
        int v = 0;
        for (int i = 0; i < k; i++) v = pq.poll();
        return v;
    }

    public static void main(String[] args) {
        int[] a = {7, 10, 4, 3, 20, 15};
        int[] b = {1};
        int[] c = {3, 1, 4, 1, 5, 9, 2, 6};
        System.out.println(kthSmallestMaxHeap(a, 3));
        System.out.println(kthSmallestMinHeap(a, 3));
        System.out.println(kthSmallestMaxHeap(b, 1));
        System.out.println(kthSmallestMinHeap(c, 4));
    }
}

