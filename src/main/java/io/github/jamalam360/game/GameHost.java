package io.github.jamalam360.game;

import io.github.jamalam360.network.server.ServerNetwork;
import io.github.jamalam360.network.server.ServerSettings;
import io.github.jamalam360.render.GameScreen;

public class GameHost implements GameManager {
    public static final int EMPTY = 0;
    public static final int CROSS = 1;
    public static final int NOUGHT = 2;
    private final ServerNetwork network;
    public final GameScreen screen;
    private final int[] gameState = new int[9];

    public GameHost(String username) throws InterruptedException {
        this.screen = new GameScreen(this);
        this.network = new ServerNetwork(this, new ServerSettings.Builder().username(username).build());
        this.network.run();
    }

    public void sendPacket(String content) {
        network.sendPacket(content);
    }

    public void onClick(int index, boolean fromClient) {
        if (gameState[index] == EMPTY) {
            if (fromClient) {
                gameState[index] = NOUGHT;
            } else {
                gameState[index] = CROSS;
            }

            StringBuilder packetContent = new StringBuilder();
            packetContent.append("STATE ");

            for (int i : gameState) {
                packetContent.append(i);
                packetContent.append(" ");
            }

            packetContent.deleteCharAt(packetContent.length() - 1);

            sendPacket(packetContent.toString());
            screen.updateState(gameState);
        }
    }
}
