import javax.swing.*;
import java.awt.*;

public class TicTacToe {

    private final int DIMENSION;
    private JFrame gameFrame;
    private JButton[][] buttons;
    private int buttonY = 20;
    private int buttonX = 20;
    private final AI aiPlayer;

    public TicTacToe(int dimension, String difficulty){
        DIMENSION = dimension;
        aiPlayer = new AI(dimension, difficulty);
        initialiseGameFrame();
        initialiseButtons();
    }

    private void initialiseButtons() {
        int size = (15 - DIMENSION) * 10; // Simple formula to roughly resize buttons as game dimensions increase
        buttons = new JButton[DIMENSION][DIMENSION];
        for (int row = 0; row < DIMENSION; row++){
            for (int column = 0; column < DIMENSION; column++){
                buttons[row][column] = new JButton();
                buttons[row][column].setBounds(buttonX, buttonY, size, size);
                buttons[row][column].setFont(new Font("Courier New", Font.BOLD, 20));
                buttons[row][column].addActionListener(e -> {
                    // Button must be empty to be a valid move
                    if (((JButton)e.getSource()).getText().equals("")){
                        ((JButton)e.getSource()).setText("X");
                        aiPlayer.play(buttons);
                    }
                });
                gameFrame.getContentPane().add(buttons[row][column]);
                buttonX += size;
            }
            buttonX = 20;
            buttonY += size;
        }
    }

    private void initialiseGameFrame(){
        gameFrame  = new JFrame("Tic-tac-toe game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(650, 650);
        gameFrame.setLayout(null);
        gameFrame.setVisible(true);
    }


}