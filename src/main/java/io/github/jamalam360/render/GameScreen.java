package io.github.jamalam360.render;

import io.github.jamalam360.game.GameClient;
import io.github.jamalam360.game.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameScreen extends JFrame implements ActionListener {
    public static final int EMPTY = 0;
    public static final int CROSS = 1;
    public static final int NOUGHT = 2;
    private final GameManager gameClient;
    private final boolean isClient;
    private final JButton[] button = new JButton[9];

    public GameScreen(GameManager client) {
        this.gameClient = client;
        this.isClient = client instanceof GameClient;

        setTitle("Noughts and Crosses");
        setSize(316, 339);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        for (int i = 0; i <= 8; i++) {
            button[i] = new JButton();
            button[i].setBackground(Color.WHITE);
            button[i].setFont(new Font("Arial", Font.BOLD, 70));
            button[i].setFocusPainted(false);
            button[i].addActionListener(this);
            add(button[i]);
        }
        button[0].setBounds(0, 0, 100, 100);
        button[1].setBounds(100, 0, 100, 100);
        button[2].setBounds(200, 0, 100, 100);
        button[3].setBounds(0, 100, 100, 100);
        button[4].setBounds(100, 100, 100, 100);
        button[5].setBounds(200, 100, 100, 100);
        button[6].setBounds(0, 200, 100, 100);
        button[7].setBounds(100, 200, 100, 100);
        button[8].setBounds(200, 200, 100, 100);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void restartGame() {
        for (int i = 0; i <= 8; i++) {
            button[i].setText(String.valueOf(i));
            button[i].setFont(new Font("Arial", Font.BOLD, 0));
            button[i].setForeground(Color.black);
        }
    }

    private void restartGameNoWinner() {
        for (int i = 0; i <= 8; i++) {
            button[i].setText(String.valueOf(i));
            button[i].setFont(new Font("Arial", Font.BOLD, 0));
        }
    }

    public boolean isEmpty(JButton button) {
        return !(button.getText().equals("X") || button.getText().equals("O"));
    }

    public void updateState(int[] newState) {
        for (int i = 0; i < 9; i++) {
            if (newState[i] == EMPTY) {
                button[i].setText("");
            } else if (newState[i] == NOUGHT) {
                button[i].setText("O");
            } else if (newState[i] == CROSS) {
                button[i].setText("X");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == button[i] && isEmpty(button[i])) {
                gameClient.sendPacket("CLICK " + i);
            }
        }

        /*if (button[0].getText().equals(button[1].getText()) && button[1].getText().equals(button[2].getText())) {
            button[0].setForeground(Color.RED);
            button[1].setForeground(Color.RED);
            button[2].setForeground(Color.RED);
        } else if (button[0].getText().equals(button[3].getText()) && button[3].getText().equals(button[6].getText())) {
            button[0].setForeground(Color.RED);
            button[3].setForeground(Color.RED);
            button[6].setForeground(Color.RED);
        } else if (button[0].getText().equals(button[4].getText()) && button[4].getText().equals(button[8].getText())) {
            button[0].setForeground(Color.RED);
            button[4].setForeground(Color.RED);
            button[8].setForeground(Color.RED);
        } else if (button[1].getText().equals(button[4].getText()) && button[4].getText().equals(button[7].getText())) {
            button[1].setForeground(Color.RED);
            button[4].setForeground(Color.RED);
            button[7].setForeground(Color.RED);
        } else if (button[2].getText().equals(button[5].getText()) && button[5].getText().equals(button[8].getText())) {
            button[2].setForeground(Color.RED);
            button[5].setForeground(Color.RED);
            button[8].setForeground(Color.RED);
        } else if (button[3].getText().equals(button[4].getText()) && button[4].getText().equals(button[5].getText())) {
            button[3].setForeground(Color.RED);
            button[4].setForeground(Color.RED);
            button[5].setForeground(Color.RED);
        } else if (button[6].getText().equals(button[7].getText()) && button[7].getText().equals(button[8].getText())) {
            button[6].setForeground(Color.RED);
            button[7].setForeground(Color.RED);
            button[8].setForeground(Color.RED);
        }
        if (button[6].getText().equals(button[4].getText()) && button[4].getText().equals(button[2].getText())) {
            button[6].setForeground(Color.RED);
            button[4].setForeground(Color.RED);
            button[2].setForeground(Color.RED);

        }

        if ((button[0].getForeground() == Color.RED && button[1].getForeground() == Color.RED && button[2].getForeground() == Color.RED)
                || (button[0].getForeground() == Color.RED && button[3].getForeground() == Color.RED && button[6].getForeground() == Color.RED)
                || (button[0].getForeground() == Color.RED && button[4].getForeground() == Color.RED && button[8].getForeground() == Color.RED)
                || (button[1].getForeground() == Color.RED && button[4].getForeground() == Color.RED && button[7].getForeground() == Color.RED)
                || (button[2].getForeground() == Color.RED && button[5].getForeground() == Color.RED && button[8].getForeground() == Color.RED)
                || (button[3].getForeground() == Color.RED && button[4].getForeground() == Color.RED && button[5].getForeground() == Color.RED)
                || (button[6].getForeground() == Color.RED && button[7].getForeground() == Color.RED && button[8].getForeground() == Color.RED)
                || (button[2].getForeground() == Color.RED && button[4].getForeground() == Color.RED && button[6].getForeground() == Color.RED)) {
            restartGame();
        } else if ((button[0].getText().equals("X") || button[0].getText().equals("O"))
                && (button[1].getText().equals("X") || button[1].getText().equals("O"))
                && (button[2].getText().equals("X") || button[2].getText().equals("O"))
                && (button[3].getText().equals("X") || button[3].getText().equals("O"))
                && (button[4].getText().equals("X") || button[4].getText().equals("O"))
                && (button[5].getText().equals("X") || button[5].getText().equals("O"))
                && (button[6].getText().equals("X") || button[6].getText().equals("O"))
                && (button[7].getText().equals("X") || button[7].getText().equals("O"))
                && (button[8].getText().equals("X") || button[8].getText().equals("O"))) {
            restartGameNoWinner();
        }*/
    }
}
