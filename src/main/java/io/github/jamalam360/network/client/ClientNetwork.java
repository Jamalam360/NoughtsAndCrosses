package io.github.jamalam360.network.client;

import io.github.jamalam360.game.GameClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientNetwork {
    private final GameClient gameClient;
    private final ClientSettings settings;
    private Channel channel;

    public ClientNetwork(GameClient client, ClientSettings settings) {
        this.gameClient = client;
        this.settings = settings;
    }

    public void run() {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInitializer(gameClient));

            this.channel = bootstrap.connect(settings.serverAddress(), settings.port()).sync().channel();
            channel.writeAndFlush("HANDSHAKE " + settings.username());
        } catch (Exception ignored) {
        } finally {
            group.shutdownGracefully();
        }
    }

    public void sendPacket(String content) {
        this.channel.writeAndFlush(content);
    }
}
