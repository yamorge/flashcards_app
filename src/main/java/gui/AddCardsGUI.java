package gui;

import constants.Constants;
import org.example.Flashcards;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.example.Flashcards.*;

public class AddCardsGUI extends JFrame {

    public AddCardsGUI(){
        super(Constants.APP_NAME);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Constants.FRAME_SIZE[0], Constants.FRAME_SIZE[1]);
        setLocationRelativeTo(null);

        addGuiComponents();
        setVisible(true);
    }

    private void addGuiComponents(){
        SpringLayout springLayout = new SpringLayout();
        JPanel addFlashcardPanel = new JPanel();
        addFlashcardPanel.setLayout(springLayout);

        // Question
        JLabel questionLabel = new JLabel("Question:");
        questionLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JTextField questionTextField = new JTextField(Constants.TEXTFIELD_SIZE);
        questionTextField.setFont(new Font("Dialog", Font.PLAIN, 22));

        springLayout.putConstraint(SpringLayout.WEST, questionLabel, 15, SpringLayout.WEST,addFlashcardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, questionLabel, 18, SpringLayout.NORTH,addFlashcardPanel);
        springLayout.putConstraint(SpringLayout.WEST, questionTextField, 120, SpringLayout.WEST,addFlashcardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, questionTextField, 15, SpringLayout.NORTH,addFlashcardPanel);

        // Answer
        JLabel answerLabel = new JLabel("Answer:");
        answerLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JTextField answerTextField = new JTextField(Constants.TEXTFIELD_SIZE);
        answerTextField.setFont(new Font("Dialog", Font.PLAIN, 22));

        springLayout.putConstraint(SpringLayout.WEST, answerLabel, 15, SpringLayout.WEST,addFlashcardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, answerLabel, 83, SpringLayout.NORTH,addFlashcardPanel);
        springLayout.putConstraint(SpringLayout.WEST, answerTextField, 120, SpringLayout.WEST,addFlashcardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, answerTextField, 80, SpringLayout.NORTH,addFlashcardPanel);

        // Buttons

        JButton addButton = new JButton("Add Flashcard");
        addButton.setFont(new Font("Dialog", Font.BOLD, 18));
        addButton.setBackground(Color.cyan);
        addButton.setForeground(Color.WHITE);
        addButton.setPreferredSize(new Dimension(220, 50));
        addButton.setHorizontalAlignment(SwingConstants.CENTER);

        JButton startButton = new JButton("Start Quiz");
        startButton.setFont(new Font("Dialog", Font.BOLD, 18));
        startButton.setBackground(Color.green);
        startButton.setForeground(Color.WHITE);
        startButton.setPreferredSize(new Dimension(220, 50));
        startButton.setHorizontalAlignment(SwingConstants.CENTER);

        springLayout.putConstraint(SpringLayout.WEST, addButton, 15, SpringLayout.WEST,addFlashcardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, addButton, 140, SpringLayout.NORTH,addFlashcardPanel);
        springLayout.putConstraint(SpringLayout.WEST, startButton, 260, SpringLayout.WEST,addFlashcardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, startButton, 140, SpringLayout.NORTH,addFlashcardPanel);



        // Max score
        JLabel maxScoreLabel = new JLabel("Max Score: " + Flashcards.maxScore);
        maxScoreLabel.setFont(new Font("Dialog", Font.BOLD, 30));

        springLayout.putConstraint(SpringLayout.WEST, maxScoreLabel, 140, SpringLayout.WEST,addFlashcardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, maxScoreLabel, 220, SpringLayout.NORTH,addFlashcardPanel);

        // Number of cards
        JLabel numCardsLabel = new JLabel("Number of cards: " + numCards);
        numCardsLabel.setFont(new Font("Dialog", Font.BOLD, 30));
        springLayout.putConstraint(SpringLayout.WEST, numCardsLabel, 85, SpringLayout.WEST,addFlashcardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, numCardsLabel, 260, SpringLayout.NORTH,addFlashcardPanel);

        // Delete button
        JButton deleteButton = new JButton("Delete Cards");
        deleteButton.setFont(new Font("Dialog", Font.BOLD, 25));
        deleteButton.setBackground(Color.gray);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setPreferredSize(new Dimension(240, 50));
        deleteButton.setHorizontalAlignment(SwingConstants.CENTER);

        springLayout.putConstraint(SpringLayout.WEST, deleteButton, 120, SpringLayout.WEST,addFlashcardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, deleteButton, 310, SpringLayout.NORTH,addFlashcardPanel);


        // Buttons logic
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String question = questionTextField.getText();
                String answer = answerTextField.getText();

                questionTextField.setText("");
                answerTextField.setText("");

                addFlashcard(question, answer);
                numCardsLabel.setText("Number of cards: " + numCards);

                addFlashcardPanel.repaint();

            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numCards > 0) {
                    dispose();
                    new FlashcardsGUI();
                }
            }
        });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCards();
                numCardsLabel.setText("Number of cards: " + numCards);
                addFlashcardPanel.repaint();
            }
        });

        addFlashcardPanel.add(questionLabel);
        addFlashcardPanel.add(questionTextField);
        addFlashcardPanel.add(answerLabel);
        addFlashcardPanel.add(answerTextField);
        addFlashcardPanel.add(addButton);
        addFlashcardPanel.add(startButton);
        addFlashcardPanel.add(maxScoreLabel);
        addFlashcardPanel.add(numCardsLabel);
        addFlashcardPanel.add(deleteButton);





        add(addFlashcardPanel);

    }

}
