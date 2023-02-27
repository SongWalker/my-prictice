package work.theadpool.demo.Task;

import work.theadpool.Task;

import java.io.*;
import java.net.Socket;

/**
 * 用于演示tcp请求和响应的task
 * 实现一个简单的自动应答机器人
 */
public class TcpMessageTask extends Task {

    private Socket socket;

    public TcpMessageTask(Socket socket) {
        super(socket);
        this.socket = socket;
    }

    /**
     * 实现一个简单的功能，接收客户端的消息并返回
     */
    @Override
    public void run() {
        processMessage();
    }

    /**
     * 根据socket获取客户端发来的消息，使用Dialog类自动应答
     * @return
     */
    private void processMessage() {
        InputStream is=null;
        InputStreamReader isr=null;
        BufferedReader br=null;
        OutputStream os=null;
        PrintWriter pw=null;
        try {
            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String info = null;
            StringBuilder sb = new StringBuilder();
            //读消息
            while((info = br.readLine()) != null){
                sb.append(info);
            }
            String message = sb.toString();
            notifyAll(String.format("客户端消息:[%s]", message));
            String response = this.handleMessage(message); //服务器自动应答的消息
            notifyAll(String.format("服务端应答:[%s]", response));
            //给客户端响应的消息
            socket.shutdownInput();
            os = socket.getOutputStream();
            pw = new PrintWriter(os);
            pw.write(response);
            pw.flush();
        } catch (Exception e) {
            // TODO: handle exception
        } finally{
            //关闭资源
            try {
                if(pw!=null)
                    pw.close();
                if(os!=null)
                    os.close();
                if(br!=null)
                    br.close();
                if(isr!=null)
                    isr.close();
                if(is!=null)
                    is.close();
                if(socket!=null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 对客户端传来的message进行简单处理后返回
     * @param clientMsg
     * @return
     */
    private String handleMessage(String clientMsg) {
        //从字典查询对话
        String response = Dialog.answer(clientMsg);
        return response;
    }
}
