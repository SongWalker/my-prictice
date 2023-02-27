package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

public class MyNettyServer {

    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.channel(NioServerSocketChannel.class);
        //创建了一组线程 通过死循环 监控状态 accept read write
        serverBootstrap.group(new NioEventLoopGroup());//创建了一组，后边会进行指定个数
        //ServerSocketChannel  SocketChannel
        //childHandler 配置hanlder，ChannelInitializer需要配置一个管道的初始化器。这个初始化器初始化的是SocketChannel
        //为什么要为这个管道做初始化？SocketChannel已经接通了，状态已经监控了，该进行后续通过流水线用Handler对管道进行处理了。
        //拿到每一个管道，
        serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            /*
                 channel 接通 监控 accept rw 处理 通过流水线 用handler进行处理 。
             */
            protected void initChannel(NioSocketChannel ch) throws Exception {
                //ByteBuf 字节--->字符
                //ch.pipeline().addLast(new StringDecoder());
                //任何读取数据的Handler都叫做 ChannelInboundHandlerAdapter，他给我们提供了适配器设计模式，我们就直接使用了。
                //   ch.pipeline().addLast(new StringDecoder());这个Handler处理完之后，就会将数据传递给channelRead中的msg当中。

                //基于适配器模式，提供给我们的自定义的Handler
                ch.pipeline().addLast(new StringDecoder(), new ChannelInboundHandlerAdapter() {
                    @Override
                    //msg是将上部解码之后得到的，传递过来的。
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        //可以在这里使用各种JSON库转JAva对象啦。
                        //经过第一步之后，反过来的数据是解码的字符串数据
                        System.out.println("result = " + msg);
                    }
                });
            }
        });
        serverBootstrap.bind(8001);

    }
}
