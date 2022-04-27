package binarysearch;
// Source : https://leetcode.com/problems/find-k-closest-elements/
// Id     : 658
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/3/2
// Topic  : binarysearch 
// Level  : Medium+
// Other  :
// Tips   :
// Links  :
// Result : 100% 20.97%

import java.util.ArrayList;
import java.util.List;

public class FindKClosestElements {
    /*public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int l = 0, r = arr.length - 1, mid = -1;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (arr[mid] == x)
                break;
            if (arr[mid] > x)
                r = mid - 1;
            else
                l = mid + 1;
        }
        List<Integer> res = new LinkedList<>();
        if (arr[mid] == x) {
            l = mid;
            r = mid;
            while (k > 1) {
                if (l > -1 && r < arr.length) {
                    if (Math.abs(arr[l] - x) <= Math.abs(arr[r] - x)) {
                        l--;
                    } else {
                        r++;
                    }
                } else {
                    if (l == -1)
                        r++;
                    if (r == arr.length)
                        l--;
                }
                k--;
            }
        }

        return res;

    }*/

    // 4ms
    public List<Integer> findClosestElements1(int[] arr, int k, int x) {
//    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int l = 0, r = k;
        while (r < arr.length) {
            int tmp = Math.abs(arr[r] - x);
            r++;
            while (tmp < Math.abs(arr[l] - x) && r - l > k) {
                l++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        // 根据最优的左边界获得答案
        for (int i = l; i < l + k; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<>();
        int i = 0;
        for (; i < arr.length - k; i++) {
            if (arr[i] >= x)
                break;//最小的数大于x
            if (arr[i + k] < x)
                continue;//最的大数小于x
            if (Math.abs(arr[i] - x) <= Math.abs(arr[i + k] - x))
                break;
        }
        for (int j = 0; j < k; j++) {
            list.add(arr[j + i]);
        }
        return list;
    }//滑动窗口，大小固定为连续的k


    // 3ms
    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
//    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int l = 0, r = arr.length - k,mid=-1;

        // 定位左区间端点的边界值
        while (l < r) {
            mid = l + (r - l) / 2;
            if(x - arr[mid]== arr[mid + k] - x ){
                break;
            }

            if (x - arr[mid] > arr[mid + k] - x) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = mid; i < l + k; i++) {
            res.add(arr[i]);
        }
        return res;
    }

}