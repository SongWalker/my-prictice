package lc.my;

import java.util.HashMap;
import java.util.Map;

/*
*找出字符串中第一个匹配项的下标
*
输入：haystack = "sadbutsad", needle = "sad"
输出：0
解释："sad" 在下标 0 和 6 处匹配。
第一个匹配项的下标是 0 ，所以返回 0 。
* */
public class lc28 {

    public static void main(String[] args) {
        int res = strStr("a", "a");
        System.out.println(res);
    }

    public static int strStr(String str, String subStr) {
        int index = 0;
        int i = 0;
        int j = subStr.length();
        int next = 0;
        Map<Character, Integer> map = getMap(subStr);
        while (i < str.length() && index < subStr.length()) {
            if(str.charAt(i) == subStr.charAt(index)) {
                i++;
                index++;
                continue;
            }
            else {
                if(j >= str.length()) {
                    break;
                }
                if(map.containsKey(str.charAt(j))) {
                    next += (subStr.length() - map.get(str.charAt(j)));
                }
                else {
                    next += subStr.length();
                }
                i = next;
                j = next + subStr.length();
                index = 0;
            }
        }
        if(index == subStr.length()) {
            return next;
        }
        return -1;
    }

    private static Map getMap(String subStr) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for(int i = 0;i<subStr.length();i++) {
            map.put(subStr.charAt(i), i);
        }
        return map;
    }
}
