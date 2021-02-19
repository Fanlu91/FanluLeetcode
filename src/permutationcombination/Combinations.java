//package permutationcombination;
//// Source : https://leetcode.com/problems/combinations/
//// Id     : 77
//// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
//// Date   : 2021/2/19
//// Topic  : combination
//// Level  : Medium
//// Other  :
//// Tips   :
//// Links  :
//// Result :
//
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//
//public class Combinations {
//    public List<List<Integer>> combine(int n, int k) {
//        List<List<Integer>> ans = new LinkedList<>();
//        List<Integer> list = new ArrayList<>(n);
//        for (int i = 1; i < n + 1; i++) {
//            list.add(i);
//        }
//        backtracking(list, new ArrayList<Integer>(), k, ans);
//
//        return ans;
//    }
//
//    private void backtracking(List<Integer> list, List<Integer> tmp, int k, List<List<Integer>> ans) {
//
//        if (k == 0) {
//            ans.add(tmp);
//            return;
//        }
//
//        for (int i = 0; i < list.size(); i++) {
//            int tmpInt = list.get(i);
//            tmp.add(tmpInt);
//            list.remove(i);
//            backtracking(list, tmp, k, ans);
//            list.add(i, tmpInt);
//        }
//    }
//}