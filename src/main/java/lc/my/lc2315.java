package lc.my;

public class lc2315 {

    public static void main(String[] args) {
        int ret = countAsterisks("yo|uar|e**|b|e***au|tifu|l");
        System.out.println(ret);
    }

    public static int countAsterisks(String s) {
        int starCount = 0;
        char split = '|';
        char star = '*';
        boolean countFlag = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == split) {
                countFlag = !countFlag;
            }
            if (countFlag && c == star) {
                starCount++;
            }
        }
        return starCount;
    }
}
