package io.github.jamalam360.network.client;

public record ClientSettings(int port, String serverAddress, String username) {
    public static class Builder {
        private int port = 8001;
        private String serverAddress;
        private String username;

        public Builder() {
        }

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public Builder server(String serverAddress) {
            this.serverAddress = serverAddress;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public ClientSettings build() {
            return new ClientSettings(port, serverAddress, username);
        }
    }
}
