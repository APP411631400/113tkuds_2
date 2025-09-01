package LeetCode;

import java.util.HashMap;

public class lt_01_twosum {
    public int[] twoSum(int[] nums, int target) {
        // 建立一個 HashMap 來儲存數值和對應的索引
        HashMap<Integer, Integer> map = new HashMap<>();

        // 遍歷陣列
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i]; // 補數

            // 如果補數已經在 map 中，就代表找到答案
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }

            // 否則，把目前的數字和索引放進 map
            map.put(nums[i], i);
        }

        // 根據題目保證一定有解，這裡理論上不會被執行
        return new int[] {};
    }
}
