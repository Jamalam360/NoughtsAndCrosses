package io.github.jamalam360.render.menu;

import io.github.jamalam360.game.GameClient;
import io.github.jamalam360.game.GameHost;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuScreen extends JFrame implements ActionListener {
    private final JButton hostButton;
    private final JButton joinButton;

    public MainMenuScreen() {
        setTitle("Main Menu");
        setSize(250, 80);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        hostButton = new JButton();
        hostButton.setText("Host Game");
        hostButton.addActionListener(this);
        hostButton.setBounds(15, 0, 100, 35);
        add(hostButton);

        joinButton = new JButton();
        joinButton.setText("Join Game");
        joinButton.addActionListener(this);
        joinButton.setBounds(125, 0, 100, 35);
        add(joinButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(joinButton)) {
            new GameClient(JOptionPane.showInputDialog("Enter Host Name"), JOptionPane.showInputDialog("Enter User Name"));
        } else if (e.getSource().equals(hostButton)) {
            try {
                new GameHost(JOptionPane.showInputDialog("Enter User Name"));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
