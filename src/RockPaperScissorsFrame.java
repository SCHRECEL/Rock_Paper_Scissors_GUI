import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {

    private JButton rockButton, paperButton, scissorsButton, quitButton;
    private JTextArea resultsTextArea;
    private JLabel playerWinsLabel, computerWinsLabel, tiesLabel;
    private int playerWins, computerWins, ties;
    private JPanel statsPanel;

    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel gamePanel = new JPanel();
        gamePanel.setBorder(BorderFactory.createTitledBorder("Choose Your Move"));
        rockButton = new JButton("Rock", new ImageIcon(new ImageIcon("C:\\Users\\Earl\\IdeaProjects\\Rock_Paper_Scissors_GUI\\src\\rock.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        paperButton = new JButton("Paper", new ImageIcon(new ImageIcon("C:\\Users\\Earl\\IdeaProjects\\Rock_Paper_Scissors_GUI\\src\\paper1.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        scissorsButton = new JButton("Scissors", new ImageIcon(new ImageIcon("C:\\Users\\Earl\\IdeaProjects\\Rock_Paper_Scissors_GUI\\src\\scissors.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        quitButton = new JButton("Quit");
        gamePanel.add(rockButton);
        gamePanel.add(paperButton);
        gamePanel.add(scissorsButton);
        gamePanel.add(quitButton);
        add(gamePanel, BorderLayout.NORTH);

        statsPanel = new JPanel(new GridLayout(3, 2));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Stats"));
        playerWinsLabel = new JLabel("Player Wins:");
        computerWinsLabel = new JLabel("Computer Wins:");
        tiesLabel = new JLabel("Ties:");
        statsPanel.add(playerWinsLabel);
        statsPanel.add(new JTextField());
        statsPanel.add(computerWinsLabel);
        statsPanel.add(new JTextField());
        statsPanel.add(tiesLabel);
        statsPanel.add(new JTextField());
        add(statsPanel, BorderLayout.CENTER);

        resultsTextArea = new JTextArea();
        resultsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsTextArea);
        add(scrollPane, BorderLayout.SOUTH);

        rockButton.addActionListener(new GameButtonListener("Rock"));
        paperButton.addActionListener(new GameButtonListener("Paper"));
        scissorsButton.addActionListener(new GameButtonListener("Scissors"));
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(RockPaperScissorsFrame.this, "Thanks for playing!");
                System.exit(0);
            }
        });

        pack();
        setVisible(true);
    }

    private class GameButtonListener implements ActionListener {
        private String playerMove;

        public GameButtonListener(String move) {
            this.playerMove = move;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] moves = {"Rock", "Paper", "Scissors"};
            Random random = new Random();
            int computerIndex = random.nextInt(3);
            String computerMove = moves[computerIndex];

            String result = determineWinner(playerMove, computerMove);
            updateStats(result);
            updateResultsTextArea(playerMove, computerMove, result);
        }

        private String determineWinner(String playerMove, String computerMove) {
            if (playerMove.equals(computerMove)) {
                return "Tie";
            } else if ((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
                    (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
                    (playerMove.equals("Scissors") && computerMove.equals("Paper"))) {
                return "Player Wins";
            } else {
                return "Computer Wins";
            }
        }

        private void updateStats(String result) {
            if (result.equals("Player Wins")) {
                playerWins++;
            } else if (result.equals("Computer Wins")) {
                computerWins++;
            } else {
                ties++;
            }

            // Use the existing statsPanel instead of creating a new one
            ((JTextField) statsPanel.getComponent(1)).setText(String.valueOf(playerWins));
            ((JTextField) statsPanel.getComponent(3)).setText(String.valueOf(computerWins));
            ((JTextField) statsPanel.getComponent(5)).setText(String.valueOf(ties));
        }

        private void updateResultsTextArea(String playerMove, String computerMove, String result) {
            resultsTextArea.append(playerMove + " vs. " + computerMove + " - " + result + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RockPaperScissorsFrame();
            }
        });
    }
}
