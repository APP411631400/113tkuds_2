public class SelectionSortImplementation {

    static class SortStats {
        long comparisons = 0;
        long swaps = 0;
    }

    public static void main(String[] args) {
        int[] data = {64, 25, 12, 22, 11};
        int[] arr1 = data.clone();
        System.out.println("Selection Sort:");
        SortStats sel = selectionSort(arr1);
        System.out.println("Sorted result:");
        printArray(arr1);
        System.out.println("Comparisons: " + sel.comparisons + "  Swaps: " + sel.swaps);

        int[] arr2 = data.clone();
        System.out.println("\nBubble Sort:");
        SortStats bub = bubbleSort(arr2);
        System.out.println("Sorted result:");
        printArray(arr2);
        System.out.println("Comparisons: " + bub.comparisons + "  Swaps: " + bub.swaps);

        if (sel.comparisons < bub.comparisons)
            System.out.println("\nSelection sort used fewer comparisons.");
        else if (sel.comparisons > bub.comparisons)
            System.out.println("\nBubble sort used fewer comparisons.");
        else
            System.out.println("\nBoth used the same number of comparisons.");

        if (sel.swaps < bub.swaps)
            System.out.println("Selection sort used fewer swaps.");
        else if (sel.swaps > bub.swaps)
            System.out.println("Bubble sort used fewer swaps.");
        else
            System.out.println("Both used the same number of swaps.");
    }

    public static SortStats selectionSort(int[] a) {
        SortStats stats = new SortStats();
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                stats.comparisons++;
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int t = a[min];
                a[min] = a[i];
                a[i] = t;
                stats.swaps++;
            }
            System.out.print("Round " + (i + 1) + ": ");
            printArray(a);
        }
        return stats;
    }

    public static SortStats bubbleSort(int[] a) {
        SortStats stats = new SortStats();
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                stats.comparisons++;
                if (a[j] > a[j + 1]) {
                    int t = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = t;
                    stats.swaps++;
                }
            }
            System.out.print("Pass " + (i + 1) + ": ");
            printArray(a);
        }
        return stats;
    }

    public static void printArray(int[] a) {
        for (int v : a) {
            System.out.print(v + " ");
        }
        System.out.println();
    }
}

