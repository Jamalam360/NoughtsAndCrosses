package io.github.jamalam360.network.client;

import io.github.jamalam360.game.GameClient;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler<String> {
    private final GameClient gameClient;

    private String serverName;

    public ClientHandler(GameClient client) {
        super();
        this.gameClient = client;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        if (msg.contains("HANDSHAKE") && serverName == null) {
            serverName = msg.substring(msg.indexOf(" "));
        } else if (msg.contains("STATE")) {
            String arrString = msg.substring(msg.indexOf(" "));
            int index = 0;
            int[] arr = new int[9];
            for (int i = 0; i < 9; i++) {
                arr[i] = Integer.parseInt(String.valueOf(arrString.charAt(index)));
                index += 2;
            }
            gameClient.updateState(arr);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
