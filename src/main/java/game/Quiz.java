package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quiz implements ActionListener {
    String[] questions = {
            "What is a stack?",
            "How does a set store objects?",
            "What method does a set use to add an object to the top?",
            "What method removes an object from the top in stack?",
            "What method is used to check an object at the top?"};
    String[][] options = {
            {"a LIFO data structure", "a FIFO data structure", "a dynamic array"},
            {"in a sort of the arrow", "in a sort of a queue", "in a sort of a vertical tree"},
            {"add", "push", "insert"},
            {"delete", "pop", "remove"},
            {"get", "push", "peek"}};
    char[] answers = {'A', 'C', 'B', 'B', 'C'};
    char guess;
    char answer;
    int index;
    int correctGuesses = 0;
    int totalQuestions = questions.length;
    int result;
    int seconds = 10;

    JFrame frame = new JFrame();
    JTextField textField = new JTextField();
    JTextArea textArea = new JTextArea();
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JLabel labelA = new JLabel();
    JLabel labelB = new JLabel();
    JLabel labelC = new JLabel();
    JLabel timeLabel = new JLabel();
    JLabel secondsLeft = new JLabel();
    JTextField numberRight = new JTextField();
    JTextField percentage = new JTextField();
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds--;
            secondsLeft.setText(String.valueOf(seconds));

            if (seconds <= 0) {
                displayAnswer();
            }
        }

    });


    public Quiz() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 650);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(null);
        frame.setResizable(false);

        textField.setBounds(0, 0, 650, 50);
        textField.setBackground(new Color(25, 25, 25));
        textField.setForeground(new Color(25, 255, 0));
        textField.setFont(new Font("Ink Free", Font.ITALIC, 30));
        textField.setBorder(BorderFactory.createBevelBorder(1));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);

        textArea.setBounds(0, 50, 650, 50);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(25, 25, 25));
        textArea.setForeground(new Color(25, 255, 0));
        textArea.setFont(new Font("MV Boli", Font.ITALIC, 25));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setEditable(false);

        buttonA.setBounds(0, 100, 100, 100);
        buttonA.setFont(new Font("MV Boli", Font.ITALIC, 35));
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");

        buttonB.setBounds(0, 200, 100, 100);
        buttonB.setFont(new Font("MV Boli", Font.ITALIC, 35));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");

        buttonC.setBounds(0, 300, 100, 100);
        buttonC.setFont(new Font("MV Boli", Font.ITALIC, 35));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");

        labelA.setBounds(125, 100, 500, 100);
        labelA.setBackground(new Color(50, 50, 50));
        labelA.setForeground(new Color(20, 255, 0));
        labelA.setFont(new Font("MV Boli", Font.PLAIN, 35));

        labelB.setBounds(125, 200, 500, 100);
        labelB.setBackground(new Color(50, 50, 50));
        labelB.setForeground(new Color(20, 255, 0));
        labelB.setFont(new Font("MV Boli", Font.PLAIN, 35));

        labelC.setBounds(125, 300, 500, 100);
        labelC.setBackground(new Color(50, 50, 50));
        labelC.setForeground(new Color(20, 255, 0));
        labelC.setFont(new Font("MV Boli", Font.PLAIN, 35));

        secondsLeft.setBounds(535, 510, 100, 100);
        secondsLeft.setBackground(new Color(25, 25, 25));
        secondsLeft.setForeground(new Color(255, 0, 0));
        secondsLeft.setFont(new Font("MV Boli", Font.BOLD, 60));
        secondsLeft.setBorder(BorderFactory.createBevelBorder(1));
        secondsLeft.setOpaque(true);
        secondsLeft.setHorizontalAlignment(JTextField.CENTER);
        secondsLeft.setText(String.valueOf(seconds));

        timeLabel.setBounds(535, 475, 100, 25);
        timeLabel.setBackground(new Color(50, 50, 50));
        timeLabel.setForeground(new Color(255, 0, 0));
        timeLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        timeLabel.setHorizontalAlignment(JTextField.CENTER);
        timeLabel.setText("timer");

        numberRight.setBounds(225, 224, 250, 100);
        numberRight.setBackground(new Color(25, 25, 25));
        numberRight.setForeground(new Color(25, 255, 0));
        numberRight.setFont(new Font("MV Boli", Font.PLAIN, 50));
        numberRight.setBorder(BorderFactory.createBevelBorder(1));
        numberRight.setHorizontalAlignment(JTextField.CENTER);
        numberRight.setEditable(false);

        percentage.setBounds(225, 325, 250, 100);
        percentage.setBackground(new Color(25, 25, 25));
        percentage.setForeground(new Color(25, 255, 0));
        percentage.setFont(new Font("MV Boli", Font.PLAIN, 50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);


        frame.add(timeLabel);
        frame.add(secondsLeft);
        frame.add(labelA);
        frame.add(labelB);
        frame.add(labelC);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(textArea);
        frame.add(textField);
        frame.setVisible(true);

        nexQuestion();
    }

    public void nexQuestion() {
        if (index >= totalQuestions) {
            results();
        } else {
            textField.setText("Question " + (index + 1));
            textArea.setText(questions[index]);
            labelA.setText(options[index][0]);
            labelB.setText(options[index][1]);
            labelC.setText(options[index][2]);
            timer.start();

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);

        if (e.getSource() == buttonA) {
            answer = 'A';
            if (answer == answers[index]) {
                correctGuesses++;
            }
        }
        if (e.getSource() == buttonB) {
            answer = 'B';
            if (answer == answers[index]) {
                correctGuesses++;
            }
        }
        if (e.getSource() == buttonC) {
            answer = 'C';
            if (answer == answers[index]) {
                correctGuesses++;
            }
        }
        displayAnswer();
    }

    public void displayAnswer() {
        timer.stop();
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);

        if (answers[index] != 'A') {
            labelA.setForeground(new Color(255, 0, 0));
        }
        if (answers[index] != 'B') {
            labelB.setForeground(new Color(255, 0, 0));
        }
        if (answers[index] != 'C') {
            labelC.setForeground(new Color(255, 0, 0));
        }
        Timer pause = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelA.setForeground(new Color(25, 255, 0));
                labelB.setForeground(new Color(25, 255, 0));
                labelC.setForeground(new Color(25, 255, 0));

                answer = ' ';
                seconds = 10;
                secondsLeft.setText(String.valueOf(seconds));
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                index++;
                nexQuestion();
            }
        });
        pause.setRepeats(false);
        pause.start();
    }

    public void results() {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);

        result = (int) ((correctGuesses / (double) totalQuestions) * 100);

        textField.setText("RESULTS!");
        textArea.setText(" ");
        labelA.setText("");
        labelB.setText("");
        labelC.setText("");
        numberRight.setText("(" + correctGuesses + "/" + totalQuestions + ")");
        percentage.setText(result + "%");

        frame.add(percentage);
        frame.add(numberRight);

    }
}
