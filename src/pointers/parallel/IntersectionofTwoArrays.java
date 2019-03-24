package pointers.parallel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntersectionofTwoArrays {
    /**
     * 1/20/2019
     * Paralle pointers
     *
     * @param nums1: an integer array
     * @param nums2: an integer array
     * @return: an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> list = new ArrayList<>();
        int left = 0, right = 0;

        while (left < nums1.length && right < nums2.length) {
            if (nums1[left] < nums2[right]) {
                left++;
            } else if (nums1[left] > nums2[right]) {
                right++;
            } else {
                list.add(nums1[left]);
                while (left < nums1.length - 1 && nums1[left] == nums1[left + 1]) {
                    left++;
                }

                while (right < nums2.length - 1 && nums2[right] == nums2[right + 1]) {
                    right++;
                }

                left++;
                right++;
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i <= result.length - 1; i++) {
            result[i] = list.get(i);
        }

        return result;
    }
}
