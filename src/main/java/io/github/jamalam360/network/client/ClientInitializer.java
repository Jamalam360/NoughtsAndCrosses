package io.github.jamalam360.network.client;

import io.github.jamalam360.game.GameClient;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ClientInitializer extends ChannelInitializer<NioSocketChannel> {
    private final GameClient gameClient;

    public ClientInitializer(GameClient client) {
        super();
        this.gameClient = client;
    }

    @Override
    protected void initChannel(NioSocketChannel ch) {
        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        ch.pipeline().addLast(new StringEncoder());
        ch.pipeline().addLast(new StringDecoder());
        ch.pipeline().addLast(new ClientHandler(gameClient));
    }
}
