import javax.swing.*;
import java.awt.*;

public class TicTacToe {

    private final int DIMENSION;
    private JFrame gameFrame;
    private JButton[][] buttons;
    private int buttonY = 20;
    private int buttonX = 20;
    private AI aiPlayer;

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

//    public void oTurn(){
//        if (DIFFICULTY.equals("Easy")){
//            Outer:
//            for (int row = 0; row < DIMENSION; row++){
//                for (int column = 0; column < DIMENSION; column++){
//                    if (buttons[row][column].getText().isEmpty()){
//                        buttons[row][column].setText("O");
//                        break Outer;
//                    }
//                }
//            }
//        }
//        else if (DIFFICULTY.equals("Medium")){
//            Random random = new Random();
//            int row = random.nextInt(DIMENSION);
//            int column = random.nextInt(DIMENSION);
//            while (!buttons[row][column].getText().isEmpty()){
//                row = random.nextInt(DIMENSION);
//                column = random.nextInt(DIMENSION);
//            }
//            buttons[row][column].setText("O");
//        }
//    }

    private boolean isWinner(String currPlayer){
        int rowCount = 0;
        int columnCount = 0;
        int rightDiagonalCount = 0;
        int leftDiagonalCount = 0;
        // First checking if there are horizontal wins
        RowOuterLoop:
        for (int row = 0; row < DIMENSION; row++){
            for (int column = 0; column < DIMENSION; column++){
                if (buttons[row][column].getText().equals(currPlayer)){
                    rowCount++;
                    if (rowCount == DIMENSION){
                        break RowOuterLoop;
                    }
                }
                else {
                    rowCount = 0;
                    break;
                }
            }
        }
        if (rowCount == DIMENSION){
            return true;
        }
        // Now checking for vertical wins
        ColumnOuterLoop:
        for (int column = 0; column < DIMENSION; column++) {
            for (int row = 0; row < DIMENSION; row++) {
                if (buttons[row][column].getText().equals(currPlayer)){
                    columnCount++;
                    if (columnCount == DIMENSION){
                        break ColumnOuterLoop;
                    }
                }
                else {
                    columnCount = 0;
                    break;
                }
            }
        }
        if (columnCount == DIMENSION){
            return true;
        }
        // Now checking for diagonal wins
        for (int index = 0; index < DIMENSION; index++){
            if (buttons[index][index].getText().equals(currPlayer)){
                rightDiagonalCount++;
            }
            else {
                break;
            }
        }
        if (rightDiagonalCount == DIMENSION){
            return true;
        }

        for (int index = 0; index < DIMENSION; index++){
            if (buttons[index][DIMENSION - 1 - index].getText().equals(currPlayer)){
                leftDiagonalCount++;
            }
            else {
                break;
            }
        }
        if (leftDiagonalCount == DIMENSION){
            return true;
        }
        return false;

    }

    private void initialiseGameFrame(){
        gameFrame  = new JFrame("Tic-tac-toe game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(650, 650);
        gameFrame.setLayout(null);
        gameFrame.setVisible(true);
    }


}