package LeetCode;

public class lt_04_medianoftwosortedarrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 保證 nums1 是短的那個陣列
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int halfLen = (m + n + 1) / 2;

        int left = 0, right = m;

        while (left <= right) {
            int i = (left + right) / 2;
            int j = halfLen - i;

            int maxLeftA = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int minRightA = (i == m) ? Integer.MAX_VALUE : nums1[i];

            int maxLeftB = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int minRightB = (j == n) ? Integer.MAX_VALUE : nums2[j];

            if (maxLeftA <= minRightB && maxLeftB <= minRightA) {
                // 找到正確切割點
                if ((m + n) % 2 == 0) {
                    return ((double)Math.max(maxLeftA, maxLeftB) + Math.min(minRightA, minRightB)) / 2;
                } else {
                    return (double)Math.max(maxLeftA, maxLeftB);
                }
            } else if (maxLeftA > minRightB) {
                right = i - 1;  // i 太大，往左
            } else {
                left = i + 1;   // i 太小，往右
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted properly.");
    }
}
