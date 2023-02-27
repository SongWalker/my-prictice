package work.theadpool.demo.Task;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置一些简单的对话
 */
public class Dialog {

    private static Map<String, String> dialogDic = new HashMap<String, String>();

    private static String DEFAULT_ANSWER = "请换个方式提问";

    static {
        dialogDic.put("你好", "你也好");
        dialogDic.put("今天天气不错", "同意");
        dialogDic.put("你的名字", "自动应答机器人");
    }

    public static String answer(String question) {
        if(dialogDic.containsKey(question)) {
            return dialogDic.get(question);
        }
        else {
            return DEFAULT_ANSWER;
        }
    }
}
