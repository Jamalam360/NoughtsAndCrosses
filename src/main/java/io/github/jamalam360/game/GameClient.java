package io.github.jamalam360.game;

import io.github.jamalam360.network.client.ClientNetwork;
import io.github.jamalam360.network.client.ClientSettings;
import io.github.jamalam360.render.GameScreen;

public class GameClient implements GameManager {
    private final ClientNetwork network;
    public final GameScreen screen;

    public GameClient(String hostname, String username) {
        this.screen = new GameScreen(this);
        this.network = new ClientNetwork(this, new ClientSettings.Builder().username(username).server(hostname).build());
        this.network.run();
    }

    public void sendPacket(String content) {
        network.sendPacket(content);
    }

    public void updateState(int[] state) {
        screen.updateState(state);
    }
}
