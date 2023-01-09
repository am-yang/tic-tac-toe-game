import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe {

    private final int DIMENSION;
    private JFrame gameFrame;
    private JButton[][] buttons;
    private JLabel winMessage;
    private int buttonY = 20;
    private int buttonX = 20;
    private boolean xTurn = true; // X player is always first

    public TicTacToe(int dimension){
        DIMENSION = dimension;
        initialiseGameFrame();
        winMessage = new JLabel();
        winMessage.setBounds(170, 550, 200, 50);
        winMessage.setFont(new Font("Courier New", Font.PLAIN, 30));
        winMessage.setForeground(new Color(34,139,34));
        gameFrame.getContentPane().add(winMessage);
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
                buttons[row][column].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (xTurn){
                            // Button must be empty to be a valid move
                            if (((JButton)e.getSource()).getText().equals("")){
                                ((JButton)e.getSource()).setText("X");
                                xTurn = false;
                                if (isWinner("X")){
                                    winMessage.setText("X WINS!!!");
                                }
                            }
                        }
                        else {
                            if (((JButton)e.getSource()).getText().equals("")){
                                ((JButton)e.getSource()).setText("O");
                                xTurn = true;
                                if (isWinner("O")){
                                    winMessage.setText("O WINS!!!");
                                }
                            }
                        }
                    }
                });
                gameFrame.getContentPane().add(buttons[row][column]);
                buttonX += size;
            }
            buttonX = 20;
            buttonY += size;
        }
    }

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
        // Now checking for diagonal wins
        for (int index = 0; index < DIMENSION; index++){
            if (buttons[index][index].getText().equals(currPlayer)){
                rightDiagonalCount++;
            }
            else {
                break;
            }
        }
        for (int index = 0; index < DIMENSION; index++){
            if (buttons[index][DIMENSION - 1 - index].getText().equals(currPlayer)){
                leftDiagonalCount++;
            }
            else {
                break;
            }
        }
        return rowCount == DIMENSION || columnCount == DIMENSION || rightDiagonalCount == DIMENSION ||
                leftDiagonalCount == DIMENSION;

    }

    private void initialiseGameFrame(){
        gameFrame  = new JFrame("Tic-tac-toe game");
        gameFrame.setBackground(Color.PINK);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(650, 650);
        gameFrame.setLayout(null);
        gameFrame.setVisible(true);
    }


}
