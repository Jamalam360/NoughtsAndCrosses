package io.github.jamalam360.network.server;

import io.github.jamalam360.game.GameHost;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ServerInitializer extends ChannelInitializer<NioSocketChannel> {
    private final GameHost gameHost;
    private final ServerSettings settings;

    public ServerInitializer(GameHost host, ServerSettings settings) {
        this.gameHost = host;
        this.settings = settings;
    }

    @Override
    protected void initChannel(NioSocketChannel ch) {
        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        ch.pipeline().addLast(new StringEncoder());
        ch.pipeline().addLast(new StringDecoder());
        ch.pipeline().addLast(new ServerHandler(gameHost, settings));
    }
}
