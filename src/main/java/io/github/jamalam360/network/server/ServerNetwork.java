package io.github.jamalam360.network.server;

import io.github.jamalam360.game.GameHost;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServerNetwork {
    private final GameHost gameHost;
    private final ServerSettings settings;
    private Channel channel;

    public ServerNetwork(GameHost host, ServerSettings settings) {
        this.gameHost = host;
        this.settings = settings;
    }

    public void run() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ServerInitializer(gameHost, settings));

            channel = b.bind(settings.port()).sync().channel();
            channel.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public void sendPacket(String content) {
        channel.writeAndFlush(content);
    }
}
