public class AdvancedArrayRecursion {
    public static void main(String[] args) {
        int[] data = {3, 6, 8, 10, 1, 2, 1};
        int[] qs = data.clone();
        quickSort(qs, 0, qs.length - 1);
        System.out.print("QuickSort: ");
        printArray(qs);

        int[] sorted1 = {1, 3, 5, 7};
        int[] sorted2 = {2, 4, 6, 8};
        int[] merged = mergeRec(sorted1, sorted2);
        System.out.print("MergeRec: ");
        printArray(merged);

        int k = 4;
        int kth = kthSmallest(data.clone(), 0, data.length - 1, k);
        System.out.println("4th smallest: " + kth);

        int[] arr2 = {1, 2, 3, 4, 5};
        int target = 9;
        System.out.println("Has subarray sum 9: " + hasSubarraySum(arr2, target));
    }

    public static void quickSort(int[] a, int low, int high) {
        if (low < high) {
            int p = partition(a, low, high);
            quickSort(a, low, p - 1);
            quickSort(a, p + 1, high);
        }
    }

    private static int partition(int[] a, int low, int high) {
        int pivot = a[high], i = low;
        for (int j = low; j < high; j++) {
            if (a[j] < pivot) {
                swap(a, i++, j);
            }
        }
        swap(a, i, high);
        return i;
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }

    public static int[] mergeRec(int[] a, int[] b) {
        return mergeHelper(a, 0, b, 0, new int[a.length + b.length], 0);
    }

    private static int[] mergeHelper(int[] a, int ai, int[] b, int bi, int[] c, int ci) {
        if (ai >= a.length && bi >= b.length) {
            return c;
        }
        if (ai < a.length && (bi >= b.length || a[ai] <= b[bi])) {
            c[ci] = a[ai];
            return mergeHelper(a, ai + 1, b, bi, c, ci + 1);
        } else {
            c[ci] = b[bi];
            return mergeHelper(a, ai, b, bi + 1, c, ci + 1);
        }
    }

    public static int kthSmallest(int[] a, int low, int high, int k) {
        int p = partition(a, low, high);
        int leftSize = p - low + 1;
        if (k == leftSize) {
            return a[p];
        } else if (k < leftSize) {
            return kthSmallest(a, low, p - 1, k);
        } else {
            return kthSmallest(a, p + 1, high, k - leftSize);
        }
    }

    public static boolean hasSubarraySum(int[] a, int target) {
        return fromStart(a, 0, target);
    }

    private static boolean fromStart(int[] a, int start, int target) {
        if (start >= a.length) {
            return false;
        }
        if (checkSum(a, start, 0, target)) {
            return true;
        }
        return fromStart(a, start + 1, target);
    }

    private static boolean checkSum(int[] a, int idx, int current, int target) {
        if (current == target) {
            return true;
        }
        if (idx >= a.length) {
            return false;
        }
        return checkSum(a, idx + 1, current + a[idx], target);
    }

    private static void printArray(int[] a) {
        for (int v : a) {
            System.out.print(v + " ");
        }
        System.out.println();
    }
}

