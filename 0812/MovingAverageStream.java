import java.util.*;

public class MovingAverageStream {
    private int size;
    private Queue<Integer> window;
    private double sum;
    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minPQ;
    private PriorityQueue<Integer> maxPQ;
    private Map<Integer, Integer> delayedMin;
    private Map<Integer, Integer> delayedMax;

    public MovingAverageStream(int size) {
        this.size = size;
        this.window = new LinkedList<>();
        this.sum = 0;
        this.minHeap = new PriorityQueue<>();
        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.minPQ = new PriorityQueue<>();
        this.maxPQ = new PriorityQueue<>(Collections.reverseOrder());
        this.delayedMin = new HashMap<>();
        this.delayedMax = new HashMap<>();
    }

    public double next(int val) {
        window.offer(val);
        sum += val;
        minHeap.offer(val);
        maxHeap.offer(val);
        if (window.size() > size) {
            int removed = window.poll();
            sum -= removed;
            delayedMin.put(removed, delayedMin.getOrDefault(removed, 0) + 1);
            delayedMax.put(removed, delayedMax.getOrDefault(removed, 0) + 1);
            prune(minHeap, delayedMin);
            prune(maxHeap, delayedMax);
        }
        addNum(val);
        if (maxPQ.size() > minPQ.size() + 1) minPQ.offer(maxPQ.poll());
        if (maxPQ.size() < minPQ.size()) maxPQ.offer(minPQ.poll());
        return sum / window.size();
    }

    public double getMedian() {
        if (maxPQ.size() == minPQ.size()) return (maxPQ.peek() + minPQ.peek()) / 2.0;
        return maxPQ.peek();
    }

    public int getMin() {
        prune(minHeap, delayedMin);
        return minHeap.peek();
    }

    public int getMax() {
        prune(maxHeap, delayedMax);
        return maxHeap.peek();
    }

    private void prune(PriorityQueue<Integer> heap, Map<Integer, Integer> delayed) {
        while (!heap.isEmpty() && delayed.containsKey(heap.peek())) {
            int num = heap.peek();
            int cnt = delayed.get(num);
            if (cnt == 1) delayed.remove(num);
            else delayed.put(num, cnt - 1);
            heap.poll();
        }
    }

    private void addNum(int num) {
        if (maxPQ.isEmpty() || num <= maxPQ.peek()) maxPQ.offer(num);
        else minPQ.offer(num);
        if (maxPQ.size() > minPQ.size() + 1) minPQ.offer(maxPQ.poll());
        if (maxPQ.size() < minPQ.size()) maxPQ.offer(minPQ.poll());
    }

    public static void main(String[] args) {
        MovingAverageStream ma = new MovingAverageStream(3);
        System.out.println(ma.next(1));
        System.out.println(ma.next(10));
        System.out.println(ma.next(3));
        System.out.println(ma.next(5));
        System.out.println(ma.getMedian());
        System.out.println(ma.getMin());
        System.out.println(ma.getMax());
    }
}

