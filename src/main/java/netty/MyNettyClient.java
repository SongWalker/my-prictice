package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

public class MyNettyClient {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);
        //Netty客户端为什么引入多线程呢？也要使用这个NioEventLoopGroup
        //因为在客户端当中，客户端做的也是多线程，异步的。链接服务器也做成了一个线程，通信也做成了一个线程
        //基于这样异步化的处理，性能更好。
        //异步处理IO操作。
        //避免我第一次通信的时间过长，导致后续通信的问题。
        //链接服务器和IO操作，进行独立了。为什么要独立呢？因为某一次IO操作，时间比较长，会影响后续操作
        //通道用的是一个通道，因为链接了一次，基于多个线程，可以保证后续IO不受先前IO影响。
        // 为什么client 引入事件 循环 组
        // client Netty 做多线程 异步
        // 连接服务端 一个线程
        // 通信 做成一个线程
        // 异步处理  连接 --->
        //          IO操作
        bootstrap.group(new NioEventLoopGroup());
        //增加一个Handler，我们基于这个处理数据，或者发送数据。
        bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                //只涉及一个编码
                ch.pipeline().addLast(new StringEncoder());
            }
        });
        //链接服务端。这是一个异步的处理，先连上之后，
        ChannelFuture connect = bootstrap.connect(new InetSocketAddress(8001));
        //这一步操作就是：等你连上，在进行通讯。
        connect.sync();//先让这个线程进行阻塞，等待这个线程连上服务器。阻塞的目的就是必须等到服务器连上之后，才能进行后续的两部的操作。
        //创建了新的线程 进行写操作
        Channel channel = connect.channel();
        channel.writeAndFlush("hello suns");
    }
}
