import java.util.*;

public class PriorityQueueWithHeap {
    static class Task {
        String name;
        int priority;
        Task(String n, int p) { name = n; priority = p; }
    }

    private final PriorityQueue<Task> pq = new PriorityQueue<>((a, b) -> b.priority - a.priority);

    public void addTask(String name, int priority) {
        pq.add(new Task(name, priority));
    }

    public Task executeNext() {
        return pq.poll();
    }

    public Task peek() {
        return pq.peek();
    }

    public void changePriority(String name, int newPriority) {
        List<Task> tmp = new ArrayList<>();
        while (!pq.isEmpty()) {
            Task t = pq.poll();
            if (t.name.equals(name)) t.priority = newPriority;
            tmp.add(t);
        }
        pq.addAll(tmp);
    }

    public static void main(String[] args) {
        PriorityQueueWithHeap q = new PriorityQueueWithHeap();
        q.addTask("備份", 1);
        q.addTask("緊急修復", 5);
        q.addTask("更新", 3);
        while (q.peek() != null) {
            System.out.println(q.executeNext().name);
        }
    }
}

