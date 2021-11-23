package io.github.jamalam360;

import io.github.jamalam360.render.menu.MainMenuScreen;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MainMenuScreen app = new MainMenuScreen();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}
