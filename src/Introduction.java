import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Introduction {

    private JButton startButton;
    private JLabel prompt;
    private JLabel introMessage;
    private JFrame introFrame;
    private Integer[] POSSIBLE_BOUNDS = {3, 4, 5, 6, 7, 8, 9};
    private JComboBox<Integer> selectionBox;
    private TicTacToe game;

    public Introduction(){
        initialiseIntroMessage();
        initialisePromptMessage();
        initialiseSelectionBox();
        initialiseStartButton();
        initialiseFrame();
    }

    private void initialiseIntroMessage(){
        introMessage = new JLabel("TIC-TAC-TOE GAME:D!!");
        introMessage.setBounds(60, 30, 450, 40);
        introMessage.setFont(new Font("Courier New", Font.BOLD, 35));
    }

    private void initialisePromptMessage(){
        prompt = new JLabel("Choose game dimensions:");
        prompt.setBounds(120, 100, 350, 30);
        prompt.setFont(new Font("Courier New", Font.PLAIN, 20));
    }

    private void initialiseSelectionBox(){
        selectionBox = new JComboBox<>(POSSIBLE_BOUNDS);
        selectionBox.setBounds(180, 190, 100, 60);
    }

    private void initialiseStartButton(){
        startButton = new JButton("Start Game");
        startButton.setBounds(160, 300, 150, 60);
        startButton.setFocusable(false);
        startButton.setFont(new Font("Courier New", Font.PLAIN, 20));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dimensionSelected = selectionBox.getItemAt(selectionBox.getSelectedIndex());
                introFrame.dispose();
                game = new TicTacToe(dimensionSelected);
            }
        });
    }

    private void initialiseFrame(){
        introFrame = new JFrame("Tic-tac-toe game");
        introFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        introFrame.setSize(600, 600);
        introFrame.getContentPane().add(prompt);
        introFrame.getContentPane().add(selectionBox);
        introFrame.getContentPane().add(startButton);
        introFrame.getContentPane().add(introMessage);
        introFrame.setLayout(null);
        introFrame.setVisible(true);
    }


    public static void main(String[] args) {
        new Introduction();
    }
}