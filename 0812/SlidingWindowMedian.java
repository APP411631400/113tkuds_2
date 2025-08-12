import java.util.*;

public class SlidingWindowMedian {
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private Map<Integer, Integer> delayedMax = new HashMap<>();
    private Map<Integer, Integer> delayedMin = new HashMap<>();
    private int sizeMax = 0, sizeMin = 0;
    private int k;

    private void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
            sizeMax++;
        } else {
            minHeap.offer(num);
            sizeMin++;
        }
        rebalance();
    }

    private void removeNum(int num) {
        if (!maxHeap.isEmpty() && num <= maxHeap.peek()) {
            delayedMax.put(num, delayedMax.getOrDefault(num, 0) + 1);
            sizeMax--;
            if (!maxHeap.isEmpty() && maxHeap.peek() == num) prune(maxHeap, delayedMax);
        } else {
            delayedMin.put(num, delayedMin.getOrDefault(num, 0) + 1);
            sizeMin--;
            if (!minHeap.isEmpty() && minHeap.peek() == num) prune(minHeap, delayedMin);
        }
        rebalance();
    }

    private void rebalance() {
        if (sizeMax > sizeMin + 1) {
            minHeap.offer(maxHeap.poll());
            sizeMax--;
            sizeMin++;
            prune(maxHeap, delayedMax);
        } else if (sizeMax < sizeMin) {
            maxHeap.offer(minHeap.poll());
            sizeMin--;
            sizeMax++;
            prune(minHeap, delayedMin);
        }
    }

    private void prune(PriorityQueue<Integer> heap, Map<Integer, Integer> delayed) {
        while (!heap.isEmpty()) {
            int x = heap.peek();
            Integer c = delayed.get(x);
            if (c == null || c == 0) break;
            heap.poll();
            if (c == 1) delayed.remove(x);
            else delayed.put(x, c - 1);
        }
    }

    private double getMedian() {
        if ((k & 1) == 1) return maxHeap.peek();
        long a = maxHeap.peek();
        long b = minHeap.peek();
        return (a + b) / 2.0;
    }

    public static double[] medianSlidingWindow(int[] nums, int k) {
        SlidingWindowMedian sw = new SlidingWindowMedian();
        sw.k = k;
        int n = nums.length;
        if (n == 0 || k == 0) return new double[0];
        double[] ans = new double[n - k + 1];
        for (int i = 0; i < k; i++) sw.addNum(nums[i]);
        ans[0] = sw.getMedian();
        for (int i = k; i < n; i++) {
            sw.addNum(nums[i]);
            sw.removeNum(nums[i - k]);
            ans[i - k + 1] = sw.getMedian();
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a = {1,3,-1,-3,5,3,6,7};
        int[] b = {1,2,3,4};
        System.out.println(Arrays.toString(medianSlidingWindow(a,3)));
        System.out.println(Arrays.toString(medianSlidingWindow(b,2)));
    }
}

