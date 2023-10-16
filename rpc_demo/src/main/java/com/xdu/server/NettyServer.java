package com.xdu.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.DefaultThreadFactory;

/**
 * @author tyeerth
 * @date 2023/9/5 - 上午10:46
 * @description
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1, new DefaultThreadFactory("NettyServerBoss", true));
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(8, new DefaultThreadFactory("NettyServerWorker", true));
        bootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                // 业务处理
                                .addLast("serverHandler", new SimpleChannelInboundHandler<ByteBuf>() {
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf requestByteBuf) {
//                                        ByteBuf byteBuf = msg instanceof ByteBuf ? ((ByteBuf) msg) : null;
//                                        if (byteBuf != null) {
//                                            byte[] buf = new byte[16];
//                                            ByteBuf len = byteBuf.readBytes(buf, 0, byteBuf.readableBytes());
//                                            log.debug(new String(buf));
                                        String requestStr = requestByteBuf.toString(CharsetUtil.UTF_8);
                                        System.out.println("收到数据"+requestStr);

                                        // 服务端响应echo
                                        ByteBuf byteBuf = Unpooled.copiedBuffer("返回数据" + requestStr,CharsetUtil.UTF_8);
                                        channelHandlerContext.writeAndFlush(byteBuf);
                                    }
                                });
                    }
                })
        ;
        ChannelFuture channelFuture = bootstrap.bind("127.0.0.1", 8888).sync();
        System.out.println("Netty start ....");
        //一直阻塞在这里，因为发起的请求是同步请求
        channelFuture.channel().closeFuture().sync();
    }
}
