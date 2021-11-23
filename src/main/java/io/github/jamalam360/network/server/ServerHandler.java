package io.github.jamalam360.network.server;

import io.github.jamalam360.game.GameHost;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<String> {
    private final GameHost gameHost;
    private final ServerSettings settings;
    private String clientName;

    public ServerHandler(GameHost host, ServerSettings settings) {
        this.gameHost = host;
        this.settings = settings;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        if (msg.contains("HANDSHAKE")) {
            clientName = msg.substring(msg.indexOf(" "));
            gameHost.screen.setOpponent(clientName);
            ctx.writeAndFlush("HANDSHAKE " + settings.username() + "\n\r");
        } else if (msg.contains("CLICK")) {
            gameHost.onClick(Integer.parseInt(msg.substring(msg.indexOf(" "))), true);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
