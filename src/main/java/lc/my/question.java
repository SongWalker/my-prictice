package lc.my;

import java.util.*;

public class question {

    public static void main(String[] args) {
        String input = "xmvzi";
        int k = 2;
        String res = orderlyQueue(input, k);

        assert res == res;
    }

    private static String orderlyQueue(String input, int k) {
        char min;
        int minIndex = 0;
        int cursor = 0;
        LinkedList<Character> list = new LinkedList<Character>();
        for(char c:input.toCharArray()) {
            list.add(c);
        }
        Character[] sortedList = new Character[list.size()];
        list.toArray(sortedList);
        Arrays.sort(sortedList);
        min = sortedList[minIndex];
        char[] output = new char[list.size()];

        while (cursor < k) {
            int moveIndex = findMinIndex(k, cursor, list);
            Character moveElement = list.remove(moveIndex);
            list.add(moveElement);
            if(list.getFirst().charValue() == min) {
                output[cursor] = list.removeFirst();
                if(++minIndex < sortedList.length )
                {
                    min = sortedList[minIndex];
                }
                cursor++;
            }
            else {
                Character first = list.removeFirst();
                list.add(first);
            }
        }
        int i = cursor;
        while (!list.isEmpty()) {
            output[i++] = list.removeFirst();
        }
        return new String(output);
    }

    private static int findMinIndex(int k, int cursor, LinkedList<Character> list) {
        int index = k-cursor;
        int end = k;
        char min = list.getFirst();
        int minIndex = 0;
        for(Character c:list) {
            if(index++ >= end) {
                break;
            }
            if(c < min) {
                min = c;
                minIndex = index;
            }
        }
        return minIndex;
    }
}

/*
"xmvzi"
2

xvzim
vzimx
zimxv
imxvz

* */
