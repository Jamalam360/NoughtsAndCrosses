package io.github.jamalam360.network.server;

import io.github.jamalam360.network.client.ClientSettings;

public record ServerSettings(int port, String username) {
    public static class Builder {
        private int port = 8001;
        private String username;

        public Builder() {
        }

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public ServerSettings build() {
            return new ServerSettings(port, username);
        }
    }
}

