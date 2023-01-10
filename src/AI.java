import javax.swing.*;
import java.util.Random;

/**
 * Separate AI class to compartmentalise methods related to AI player
 */

public class AI {

    private final String DIFFICULTY;
    private final int DIMENSION;

    public AI(int dimension, String difficulty){
        DIMENSION = dimension;
        DIFFICULTY = difficulty;
    }

    public void play(JButton[][] board){
        if (DIFFICULTY.equals("Easy")){
            easyMode(board);
        }
        else if (DIFFICULTY.equals("Medium")){
            mediumMode(board);
        }
        else {
            // Minimax logic goes here
        }
    }

    private void easyMode(JButton[][] board){
        Outer:
        for (int row = 0; row < DIMENSION; row++){
            for (int column = 0; column < DIMENSION; column++){
                if (board[row][column].getText().isEmpty()){
                    board[row][column].setText("O");
                    break Outer;
                }
            }
        }
    }

    private void mediumMode(JButton[][] board){
        Random random = new Random();
        int row = random.nextInt(DIMENSION);
        int column = random.nextInt(DIMENSION);
        while (!board[row][column].getText().isEmpty()){
            row = random.nextInt(DIMENSION);
            column = random.nextInt(DIMENSION);
        }
        board[row][column].setText("O");
    }
}
