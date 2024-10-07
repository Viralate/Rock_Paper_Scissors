import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
    private JTextField playerWinsField, computerWinsField, tiesField;
    private JTextArea resultsArea;
    private int playerWins = 0, computerWins = 0, ties = 0;

    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors Game");
        setSize(500, 400);
        setLayout(new BorderLayout());

        // Create the main panel with buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4)); // 4 buttons: Rock, Paper, Scissors, Quit
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Choose your move"));

        // Initialize buttons with images
        JButton rockButton = new JButton(new ImageIcon("src/rock.jpg"));
        JButton paperButton = new JButton(new ImageIcon("src/paper.jpg"));
        JButton scissorsButton = new JButton(new ImageIcon("src/scissors.jpeg"));
        JButton quitButton = new JButton("Quit"); // Declare and initialize quitButton

        // Add buttons to the panel
        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(quitButton);

        // Add listeners for buttons
        rockButton.addActionListener(e -> playGame("Rock"));
        paperButton.addActionListener(e -> playGame("Paper"));
        scissorsButton.addActionListener(e -> playGame("Scissors"));
        quitButton.addActionListener(e -> System.exit(0)); // Quit the game

        // Create the stats panel with labels and text fields
        JPanel statsPanel = new JPanel(new GridLayout(3, 2));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Stats"));

        statsPanel.add(new JLabel("Player Wins:"));
        playerWinsField = new JTextField("0", 5);
        playerWinsField.setEditable(false);
        statsPanel.add(playerWinsField);

        statsPanel.add(new JLabel("Computer Wins:"));
        computerWinsField = new JTextField("0", 5);
        computerWinsField.setEditable(false);
        statsPanel.add(computerWinsField);

        statsPanel.add(new JLabel("Ties:"));
        tiesField = new JTextField("0", 5);
        tiesField.setEditable(false);
        statsPanel.add(tiesField);

        // Create the results area
        resultsArea = new JTextArea(10, 30);
        resultsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsArea);

        // Add components to the frame
        add(buttonPanel, BorderLayout.NORTH);
        add(statsPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void playGame(String playerChoice) {
        String computerChoice = getComputerChoice();
        String result = determineWinner(playerChoice, computerChoice);

        // Update the stats and results area
        resultsArea.append(playerChoice + " vs " + computerChoice + ": " + result + "\n");
        updateStats(result);
    }

    private String getComputerChoice() {
        String[] choices = {"Rock", "Paper", "Scissors"};
        Random rand = new Random();
        return choices[rand.nextInt(choices.length)];
    }

    private String determineWinner(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            return "It's a tie!";
        } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                   (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                   (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            return "Player wins!";
        } else {
            return "Computer wins!";
        }
    }

    private void updateStats(String result) {
        if (result.contains("Player wins!")) {
            playerWins++;
            playerWinsField.setText(String.valueOf(playerWins));
        } else if (result.contains("Computer wins!")) {
            computerWins++;
            computerWinsField.setText(String.valueOf(computerWins));
        } else {
            ties++;
            tiesField.setText(String.valueOf(ties));
        }
    }
}
