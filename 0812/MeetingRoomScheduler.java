import java.util.*;

public class MeetingRoomScheduler {
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int[] it : intervals) {
            if (!pq.isEmpty() && pq.peek() <= it[0]) pq.poll();
            pq.offer(it[1]);
        }
        return pq.size();
    }

    public static int maxTotalMeetingTime(int[][] intervals, int rooms) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        boolean[] used = new boolean[intervals.length];
        int total = 0;
        for (int r = 0; r < rooms; r++) {
            int end = -1;
            for (int i = 0; i < intervals.length; i++) {
                if (!used[i] && intervals[i][0] >= end) {
                    used[i] = true;
                    total += intervals[i][1] - intervals[i][0];
                    end = intervals[i][1];
                }
            }
        }
        return total;
    }

    public static void main(String[] args) {
        int[][] a = {{0,30},{5,10},{15,20}};
        int[][] b = {{9,10},{4,9},{4,17}};
        int[][] c = {{1,5},{8,9},{8,9}};
        int[][] d = {{1,4},{2,3},{4,6}};
        System.out.println(minMeetingRooms(a));
        System.out.println(minMeetingRooms(b));
        System.out.println(minMeetingRooms(c));
        System.out.println(maxTotalMeetingTime(d, 1));
    }
}

