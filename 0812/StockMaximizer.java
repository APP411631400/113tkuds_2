import java.util.*;

public class StockMaximizer {
    public static int maxProfit(int[] prices, int k) {
        if (prices == null || prices.length < 2 || k <= 0) return 0;
        PriorityQueue<Integer> minK = new PriorityQueue<>();
        int n = prices.length, i = 0;
        while (i < n - 1) {
            while (i < n - 1 && prices[i] >= prices[i + 1]) i++;
            int buy = prices[i];
            while (i < n - 1 && prices[i] <= prices[i + 1]) i++;
            int sell = prices[i];
            int profit = sell - buy;
            if (profit > 0) {
                minK.offer(profit);
                if (minK.size() > k) minK.poll();
            }
        }
        int sum = 0;
        for (int p : minK) sum += p;
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{2,4,1}, 2));          // 2
        System.out.println(maxProfit(new int[]{3,2,6,5,0,3}, 2));    // 7
        System.out.println(maxProfit(new int[]{1,2,3,4,5}, 2));      // 4
    }
}

