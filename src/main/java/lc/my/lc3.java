package lc.my;

import java.util.HashMap;
import java.util.Map;

/*
给定一个字符串s，请你找出其中不含有重复字符的 最长子串 的长度。
输入: s = "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
public class lc3 {

    public static void main(String[] args) {
        System.out.println( lengthOfLongestSubstring("abba"));
    }

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int maxLen = 0;
        int count;
        for(int i=0, left = 0; i<s.length(); i++) {
            Character c = s.charAt(i);
            if(map.containsKey(c) && map.get(c) >= left) {
                left = map.get(c) + 1;
            }
            count = i - left + 1;
            maxLen = count > maxLen ? count:maxLen;
            map.put(c, i);
        }

        return maxLen;
    }
}
