import javax.swing.*;
import java.util.Random;

/**
 * Separate AI class to compartmentalise methods related to AI player
 */

public class AI {

    private final String DIFFICULTY;
    private final int DIMENSION;
    private final static String AI_TURN = "O";
    private final static String PLAYER_TURN = "X";
    private final static String EMPTY_BUTTON = "";
    private final static int MINIMIZER_SCORE = -10;
    private final static int MAXIMIZER_SCORE = 10;
    private final static int NO_SCORE = 0;


    public AI(int dimension, String difficulty){
        DIMENSION = dimension;
        DIFFICULTY = difficulty;
    }

    public void play(JButton[][] board){
        switch (DIFFICULTY) {
            case "Easy" -> easyMode(board);
            case "Medium" -> mediumMode(board);
            case "Difficult" -> difficultMode(board);
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

    /**
     * Function to determine the most optimal move for the AI player
     */

    private void difficultMode(JButton[][] board) {
        int minValue = Integer.MAX_VALUE; // AI player is the minimising player
        int value;
        int rowPosition = 0;
        int columnPosition = 0;
        for (int row = 0; row < DIMENSION; row++){
            for (int column = 0; column < DIMENSION; column++){
                if (board[row][column].getText().isEmpty()){
                    // Each empty board position is put to the test
                    board[row][column].setText(AI_TURN);
                    value = minimax(board, 0, true);
                    // Since AI player is the minimising player, the board position that derives the lowest score
                    // is the position that the AI will take
                    if (value < minValue){
                        minValue = value;
                        rowPosition = row;
                        columnPosition = column;
                    }
                    board[row][column].setText(EMPTY_BUTTON);
                }
            }
        }
        if (!isDraw(board)){
            board[rowPosition][columnPosition].setText(AI_TURN);
        }
    }

    private int minimax(JButton[][] board, int depth, boolean isMaximizingPlayer) {
        int value = heuristicFunction(board);
        // Terminal node reached; we have a winner
        if (value == MAXIMIZER_SCORE || value == MINIMIZER_SCORE){
            return value;
        }
        // Terminal node reached; we do not have a winner
        if (value == NO_SCORE && isDraw(board)){
            return NO_SCORE;
        }

        if (!isMaximizingPlayer){
            value = Integer.MAX_VALUE;
            for (int row = 0; row < DIMENSION; row++){
                for (int column = 0; column < DIMENSION; column++){
                    if (board[row][column].getText().isEmpty()){
                        board[row][column].setText(AI_TURN);
                        value = Math.min(value, minimax(board, depth + 1, true));
                        board[row][column].setText("");
                    }
                }
            }
        }
        else {
            value = Integer.MIN_VALUE;
            for (int row = 0; row < DIMENSION; row++) {
                for (int column = 0; column < DIMENSION; column++) {
                    if (board[row][column].getText().isEmpty()){
                        board[row][column].setText(PLAYER_TURN);
                        value = Math.max(value, minimax(board, depth + 1, false));
                        board[row][column].setText("");
                    }
                }
            }
        }
        return value;
    }

    /**
     * Function to estimate the value of the current board state
     */
    private int heuristicFunction(JButton[][] board) {
        int count  = 0;
        // Checking if there are wins across rows
        for (int row = 0; row < DIMENSION; row++){
            String player = board[row][0].getText();
            for (int column = 0; column < DIMENSION; column++){
                if (board[row][column].getText().equals(player)){
                    count++;
                }
            }
            if (count == DIMENSION && board[row][0].getText().equals(AI_TURN)){
                return MINIMIZER_SCORE;
            }
            else if (count == DIMENSION && board[row][0].getText().equals(PLAYER_TURN)){
                return MAXIMIZER_SCORE;
            }
            count = 0;
        }

        // Checking if there are wins across columns
        for (int column = 0; column < DIMENSION; column++){
            String player = board[0][column].getText();
            for (int row = 0; row < DIMENSION; row++){
                if (board[row][column].getText().equals(player)){
                    count++;
                }
            }
            if (count == DIMENSION && board[0][column].getText().equals(AI_TURN)){
                return MINIMIZER_SCORE;
            }
            else if (count == DIMENSION && board[0][column].getText().equals(PLAYER_TURN)){
                return MAXIMIZER_SCORE;
            }
            count = 0;
        }

        // Checking if there are wins across right diagonal
        String player = board[0][0].getText();
        for (int position = 0; position < DIMENSION; position++) {
            if (board[position][position].getText().equals(player)){
                count++;
            }
        }
        if (count == DIMENSION && board[0][0].getText().equals(AI_TURN)) {
            return MINIMIZER_SCORE;
        }
        else if (count == DIMENSION && board[0][0].getText().equals(PLAYER_TURN)){
            return MAXIMIZER_SCORE;
        }
        count = 0;

        // Checking if there are wins across left diagonal
        player = board[0][DIMENSION - 1].getText();
        for (int position = 0; position < DIMENSION; position++) {
            if (board[position][DIMENSION - 1 - position].getText().equals(player)){
                count++;
            }
        }
        if (count == DIMENSION && board[0][DIMENSION - 1].getText().equals(AI_TURN)) {
            return MINIMIZER_SCORE;
        }
        else if (count == DIMENSION && board[0][DIMENSION - 1].getText().equals(PLAYER_TURN)){
            return MAXIMIZER_SCORE;
        }

        // No current wins
        return NO_SCORE;
    }

    /**
     * Checking if game has reached a draw. A draw occurs if we have no winners and there are no
     * more spaces left on the game board.
     */
    public boolean isDraw(JButton[][] board){
        int count = 0;
        for (int row = 0; row < DIMENSION; row++){
            for (int column = 0; column < DIMENSION; column++){
                if (board[row][column].getText().isEmpty()){
                    count++;
                }
            }
        }
        return count == 0;
    }
}
