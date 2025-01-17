package gui;

import constants.Constants;
import org.example.Flashcards;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;

import static org.example.Flashcards.*;

public class FlashcardsGUI extends JFrame {

    private String[] flashcard;
    private boolean isFront = true;


    public FlashcardsGUI(){
        super(Constants.APP_NAME);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Constants.FRAME_SIZE[0], Constants.FRAME_SIZE[1]);
        setLocationRelativeTo(null);

        addGuiComponents();
        setVisible(true);
    }

    private void addGuiComponents(){

        flashcard = nextCard();
        // using arrays because we will be using these var in anon class
        String[] question = new String[1];
        String[] answer = new String[1];


        if(flashcard != null) {
            question[0] = flashcard[0];
            answer[0] = flashcard[1];
        }

        SpringLayout springLayout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setLayout(springLayout);

        // Making a card
        JPanel card = new JPanel();
        BoxLayout boxLayout = new BoxLayout(card, BoxLayout.Y_AXIS);
        card.setLayout(boxLayout);
        card.setBackground(Color.ORANGE);
        card.setPreferredSize(new Dimension(200, 300));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
        card.setBorder(border);

        // add "glue" to center the text
        card.add(Box.createVerticalGlue()); // glue above text
        JLabel cardText = new JLabel(question[0]);
        cardText.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(cardText);
        card.add(Box.createVerticalGlue()); // glue below text

        springLayout.putConstraint(SpringLayout.WEST, card, 150, SpringLayout.WEST, panel);
        springLayout.putConstraint(SpringLayout.NORTH, card, 75, SpringLayout.NORTH, panel);

        // click listener
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isFront){
                    isFront = false;
                    card.setBackground(Color.PINK);
                    cardText.setText(answer[0]);
                }
                else{
                    isFront = true;
                    card.setBackground(Color.orange);
                    cardText.setText(question[0]);
                }
            }
        });

        panel.add(card);

        // adding score
        JLabel labelScore = new JLabel("Score:" + Flashcards.score);
        labelScore.setFont(new Font("Dialog", Font.BOLD, 30));

        springLayout.putConstraint(SpringLayout.WEST, labelScore, 175,SpringLayout.WEST, panel);
        springLayout.putConstraint(SpringLayout.NORTH, labelScore, 25, SpringLayout.NORTH, panel);

        panel.add(labelScore);

        // Buttons
        JButton correctButton = new JButton("Correct");
        correctButton.setFont(new Font("Dialog", Font.BOLD, 18));
        correctButton.setBackground(Color.green);
        correctButton.setForeground(Color.WHITE);
        correctButton.setPreferredSize(new Dimension(150, 50));
        correctButton.setHorizontalAlignment(SwingConstants.CENTER);

        JButton mistakeButton = new JButton("Fail");
        mistakeButton.setFont(new Font("Dialog", Font.BOLD, 18));
        mistakeButton.setBackground(Color.red);
        mistakeButton.setForeground(Color.WHITE);
        mistakeButton.setPreferredSize(new Dimension(150, 50));
        mistakeButton.setHorizontalAlignment(SwingConstants.CENTER);

        springLayout.putConstraint(SpringLayout.WEST, correctButton, 50, SpringLayout.WEST, panel);
        springLayout.putConstraint(SpringLayout.NORTH, correctButton, 400, SpringLayout.NORTH, panel);
        springLayout.putConstraint(SpringLayout.WEST, mistakeButton, 300, SpringLayout.WEST, panel);
        springLayout.putConstraint(SpringLayout.NORTH, mistakeButton, 400, SpringLayout.NORTH, panel);

        // buttons logic
        correctButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isItFinal()) {
                    Flashcards.setScore("plus");
                    labelScore.setText("Score: " + score);

                    flashcard = nextCard();

                    if (flashcard != null) {
                        question[0] = flashcard[0];
                        answer[0] = flashcard[1];
                    }
                    isFront = true;

                    card.setBackground(Color.orange);
                    cardText.setText(question[0]);
                    panel.repaint();
                } else {
                    setScore("plus");
                    if (score > maxScore) maxScore = score;
                    setScore("zero");
                    resetIterator();
                    dispose();
                    new AddCardsGUI();
                }
            }
        });

        mistakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isItFinal()) {
                    Flashcards.setScore("minus");

                    labelScore.setText("Score: " + score);

                    flashcard = nextCard();

                    if (flashcard != null) {
                        question[0] = flashcard[0];
                        answer[0] = flashcard[1];
                    }
                    isFront = true;

                    card.setBackground(Color.orange);
                    cardText.setText(question[0]);
                    panel.repaint();
                }else {
                    Flashcards.setScore("minus");
                    if (score > maxScore) maxScore = score;
                    resetIterator();
                    dispose();
                    new AddCardsGUI();
                }

            }
        });


        panel.add(correctButton);
        panel.add(mistakeButton);




        add(panel);



    }


}
