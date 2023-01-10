import javax.swing.*;
import java.awt.*;

public class Introduction {

    private JButton startButton;
    private JLabel prompt;
    private JLabel introMessage;
    private JFrame introFrame;
    private final Integer[] POSSIBLE_BOUNDS = {3, 4, 5, 6, 7, 8, 9};
    private final String[] LEVELS = {"Easy", "Medium", "Difficult"};
    private JComboBox<Integer> boundsSelector;
    private JComboBox<String> difficultySelector;
    private JLabel difficultyPrompt;

    public Introduction(){
        initialiseIntroMessage();
        initialisePromptMessage();
        initialiseBoundsBox();
        initialiseDifficultyPrompt();
        initialiseDifficultySelector();
        initialiseStartButton();
        initialiseFrame();
    }

    private void initialiseDifficultyPrompt(){
        difficultyPrompt = new JLabel("Choose a difficulty:");
        difficultyPrompt.setBounds(140, 230, 350, 30);
        difficultyPrompt.setFont(new Font("Courier New", Font.PLAIN, 20));
    }

    private void initialiseDifficultySelector(){
        difficultySelector = new JComboBox<>(LEVELS);
        difficultySelector.setBounds(230, 270, 100, 60);
    }

    private void initialiseIntroMessage(){
        introMessage = new JLabel("TIC-TAC-TOE GAME:D!!");
        introMessage.setBounds(80, 30, 450, 40);
        introMessage.setFont(new Font("Courier New", Font.BOLD, 35));
    }

    private void initialisePromptMessage(){
        prompt = new JLabel("Choose game dimensions:");
        prompt.setBounds(140, 100, 350, 30);
        prompt.setFont(new Font("Courier New", Font.PLAIN, 20));
    }

    private void initialiseBoundsBox(){
        boundsSelector = new JComboBox<>(POSSIBLE_BOUNDS);
        boundsSelector.setBounds(230, 140, 100, 60);
    }

    private void initialiseStartButton(){
        startButton = new JButton("Start Game");
        startButton.setBounds(200, 400, 150, 60);
        startButton.setFocusable(false);
        startButton.setFont(new Font("Courier New", Font.PLAIN, 20));
        startButton.addActionListener(e -> {
            int dimensionSelected = boundsSelector.getItemAt(boundsSelector.getSelectedIndex());
            String difficultySelected = difficultySelector.getItemAt(difficultySelector.getSelectedIndex());
            introFrame.dispose();
            new TicTacToe(dimensionSelected, difficultySelected);
        });
    }

    private void initialiseFrame(){
        introFrame = new JFrame("Tic-tac-toe game");
        introFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        introFrame.setSize(650, 650);
        introFrame.getContentPane().add(prompt);
        introFrame.getContentPane().add(boundsSelector);
        introFrame.getContentPane().add(difficultySelector);
        introFrame.getContentPane().add(difficultyPrompt);
        introFrame.getContentPane().add(startButton);
        introFrame.getContentPane().add(introMessage);
        introFrame.setLayout(null);
        introFrame.setVisible(true);
    }


    public static void main(String[] args) {
        new Introduction();
    }
}