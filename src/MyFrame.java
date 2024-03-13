import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyFrame extends JFrame implements ActionListener {
    JLabel intro;
    JButton button;
    JTextField textField;
    JLabel guess;
    JLabel result;
    JLabel newGame;
    int generatedNumber = (int) (Math.random() * 1000);
    int attempts = 0;
    MyFrame(){
        newGame = new JLabel();
        intro = new JLabel();
        button = new JButton();
        textField = new JTextField();
        guess = new JLabel();
        result = new JLabel();
        ImageIcon logo = new ImageIcon("Prodigy InfoTech Logo.png");

        intro.setPreferredSize(new Dimension(600, 50));
        intro.setFont(new Font("Consolas", Font.BOLD, 16));
        intro.setText("I am thinking of a number between 1 and 1,000. Can you guess it?");
        intro.setHorizontalAlignment(JLabel.CENTER);
        intro.setForeground(Color.GREEN);

        newGame.setPreferredSize(new Dimension(600, 30));
        newGame.setFont(new Font("Consolas", Font.PLAIN, 15));

        guess.setPreferredSize(new Dimension(165, 30));
        guess.setText("Enter number guess: ");
        guess.setFont(new Font("Consolas", Font.PLAIN, 15));
        guess.setForeground(Color.GREEN);
        textField.setPreferredSize(new Dimension(75,30));
        textField.setForeground(Color.GREEN);
        textField.setFont(new Font("Consolas", Font.PLAIN, 15));
        textField.setBackground(Color.DARK_GRAY);
        textField.setCaretColor(Color.WHITE);
        button.setPreferredSize(new Dimension(90,30));
        button.setBackground(Color.DARK_GRAY);
        button.setFont(new Font("Consolas", Font.PLAIN, 15));
        button.setForeground(Color.GREEN);
        result.setPreferredSize(new Dimension(600, 50));
        result.setForeground(Color.GREEN);
        result.setBackground(Color.BLACK);
        result.setFont(new Font("Consolas", Font.PLAIN, 15));
        result.setHorizontalAlignment(JLabel.CENTER);
        result.setVerticalTextPosition(JLabel.CENTER);
        result.setVerticalAlignment(JLabel.CENTER);
        result.setHorizontalTextPosition(JLabel.CENTER);

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() != KeyEvent.VK_BACK_SPACE && (e.getKeyChar() < '0' || e.getKeyChar() > '9') && (e.getKeyCode() < 37 || e.getKeyCode() > 40))
                    e.consume();
            }
        });

        button.setFocusable(false);
        button.addActionListener(this);
        button.setText("Guess");
        this.setSize(600, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Guessing Game");
        FlowLayout flow = new FlowLayout();
        flow.setVgap(10);
        this.setLayout(flow);
        this.setIconImage(logo.getImage());
        this.add(intro);
        this.add(guess);
        this.add(textField);
        this.add(button);
        this.add(result);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.black);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            attempts++;
            int guess;
            try {
                guess = Integer.parseInt(textField.getText());
                if (guess == generatedNumber) {
                    result.setText("<html>You guessed the number correctly in " + attempts + " attempts. :D<br/>I am now thinking of a new number, can you guess it?</html>");
                    generatedNumber = (int) (Math.random() * 1000);
                    attempts = 0;
                }
                else if (guess > generatedNumber && guess < 1001)
                    result.setText("Incorrect guess. I am thinking of a smaller number.");
                else if (guess < generatedNumber && guess > 0)
                    result.setText("Incorrect guess. I am thinking of a larger number.");
                else if(guess > 1000)
                    result.setText("Seriously?...");
            } catch (Exception NumberFormatException) {
                result.setText("You didn't enter anything.");
            }
            textField.setText("");

        }
    }
}
