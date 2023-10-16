package com.xdu.client;

import com.xdu.entity.User;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.DefaultThreadFactory;

/**
 * @author tyeerth
 * @date 2023/9/5 - 上午11:10
 * @description
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup(8, new DefaultThreadFactory("NettyWorker", true));

        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                .addLast("clientHandler", new SimpleChannelInboundHandler<ByteBuf>() {
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                                        String responseStr = byteBuf.toString(CharsetUtil.UTF_8);
                                        System.out.println("PureNettyClient received response=" + responseStr);
                                    }
                                });
                    }
                });
        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8888).sync();
        Channel channel = channelFuture.sync().channel();

        //发送user对象的json串
//        User user = new User("tom", 10);
//        ByteBuf requestByteBuf = ByteBufAllocator.DEFAULT.buffer();
//        requestByteBuf.writeBytes(new byte[]{1, 2, 3, 4});
//        channel.writeAndFlush(requestByteBuf);
        channel.writeAndFlush(ByteBufAllocator.DEFAULT.buffer().writeBytes("wangwu".getBytes()));

        System.out.println("netty client send request success!");
        channelFuture.channel().closeFuture().sync();
    }
}
