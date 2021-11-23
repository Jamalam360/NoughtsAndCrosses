package io.github.jamalam360;

import io.github.jamalam360.render.menu.MainMenuScreen;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //new Server(new ServerSettings.Builder().port(8001).username("Server").build()).run();
        //new Client(new ClientSettings.Builder().server("localhost").port(8001).username("Client").build()).run();

        MainMenuScreen app = new MainMenuScreen();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}
