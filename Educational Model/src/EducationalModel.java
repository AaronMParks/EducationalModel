import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EducationalModel {
    // declare private instance variables for the class
    private JFrame frame;           // main window
    private JPanel cards;           // panel to hold different "cards" or screens
    private CardLayout cardLayout;  // layout manager for the cards panel
    private int[] answers;          // array to hold user's answers
    private int currentQuestion;    // index of the current question
    // arrays to hold quiz questions, answer options, correct answers, and explanations
    private final String[] questions = {
            "What is threat modeling?",
            "What is reality vertigo?",
            "How can artificial intelligence detect and create fake news?",
            "How can people combat fake news?",
            "Why is it important to ensure that at least some of what people see online is true?"
    };
    private final String[][] options = {
            {
                    "The process of identifying potential threats and vulnerabilities from an adversary's point of view, and exploring potential mitigations to these threats.",
                    "The process of creating fake news articles using machine learning algorithms.",
                    "The process of detecting fake news articles created by machines.",
                    "The process of analyzing patterns in real news articles to identify potential propaganda."
            },
            {
                    "The phenomenon where computers can generate such convincing content that regular people may have a hard time figuring out what's true anymore.",
                    "The phenomenon where people become dizzy when they use virtual reality headsets.",
                    "The phenomenon where people become confused when reading news articles.",
                    "The phenomenon where people have trouble distinguishing between reality and fiction."
            },
            {
                    "By using machine learning algorithms to analyze patterns and generate text that closely resembles real news articles.",
                    "By hiring humans to write fake news articles.",
                    "By analyzing social media data and creating fake news articles.",
                    "By using fact-checking tools to verify the accuracy of news articles."
            },
            {
                    "By being more cautious of emotionally-based information, investigating before sharing posts, relying on accurate news sources, and using fact-checking tools.",
                    "By blindly trusting any news articles they come across.",
                    "By avoiding social media platforms altogether.",
                    "By only relying on news articles that confirm their preexisting beliefs."
            },
            {
                    "To protect free speech.",
                    "To ensure that people are entertained.",
                    "To promote critical thinking.",
                    "To prevent the spread of false information and its negative consequences, such as spreading misinformation, inciting violence, and eroding public trust in institutions."
            }
    };
    private final String[] correctAnswers = {
            "a",
            "a",
            "a",
            "a",
            "d"
    };
    private final String[] explanations = {
            "Answer: a) The process of identifying potential threats and vulnerabilities from an adversary's point of view, and exploring potential mitigations to these threats.",
            "Answer: a) The phenomenon where computers can generate such convincing content that regular people may have a hard time figuring out what's true anymore.",
            "Answer: a) By using machine learning algorithms to analyze patterns and generate text that closely resembles real news articles.",
            "Answer: a) By being more cautious of emotionally-based information, investigating before sharing posts, relying on accurate news sources, and using fact-checking tools.",
            "Answer: d) To prevent the spread of false information and its negative consequences, such as spreading misinformation, inciting violence, and eroding public trust in institutions."
    };

    public static void main(String[] args) {
        // Start the application by creating
        SwingUtilities.invokeLater(() -> new EducationalModel().createAndShowGUI());
    }

    // method to create and show the graphical user interface
    private void createAndShowGUI() {
        // create the main window
        frame = new JFrame("Quiz App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        // create the panel that will hold the different "cards" or screens
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        // create an array to hold the user's answers
        answers = new int[questions.length];

        // create the information screens
        createInformationScreens();
        // create the question screens
        createQuestionScreens();
        // create the results screen
        createResultScreen();

        // add the card panel to the main window and show the window
        frame.add(cards);
        frame.setVisible(true);
    }

    // method to create the information screens
    private void createInformationScreens() {
        // create an array to hold the text for each information screen
        String[] informationTexts = {
                // Information screen 1
                "<html><p><b>So what exactly is Grover?</b></p><p>Grover is an AI-powered AI fake news generator & detector tool developed by researchers at University of Washington. It can create false headlines, news sources, authors, etc. just to make a piece of fake news look more convincing. Grover doesn't just stop at identifying its own fake news, but fake news by other AI models by identify patterns and features that are characteristic of fake news.</p><p><b>How Grover is used today?</b></p><p>Grover is now largely used to recognize and battle fake news. It is a useful tool for identifying and exposing the methods and strategies employed by disinformation distributors due to its capacity to produce convincing phony headlines, news sources, writers, and other components of a news item.</p><p>With addition to being used to spot fake news, Grover also aids with media literacy and critical thinking instruction. Grover can teach users to be more discerning information consumers and less likely to be duped by false or misleading information by exposing them to the characteristics and patterns of fake news. This can lessen the harm that misleading information causes to both people and society as a whole.</p></html>",
                // Information screen 2
                "<html><p><b>Why is Grover so important?</b></p><p>AI models like Grover are coming far more important as AI development rapidly advances. Grover helps researchers understand the risks & threats created machines and work to find ways to prevent it.</p><p>By exposing users to the tactics and traits of fake news, Grover can also help people develop their media literacy and critical thinking abilities.</p><p>In the end, Grover may help to create information ecosystems that are more dependable and trustworthy.</p></html>"
        };
        // loop through the information texts array
        for (int i = 0; i < informationTexts.length; i++) {
            // create a panel to hold the information screen
            JPanel infoPanel = new JPanel(new BorderLayout());
            // create a label to display the information text
            JLabel infoLabel = new JLabel(informationTexts[i], SwingConstants.CENTER);
            infoLabel.setText(informationTexts[i]);
            infoLabel.setVerticalAlignment(JLabel.TOP);
            infoLabel.setHorizontalAlignment(JLabel.CENTER);
            infoLabel.setVerticalTextPosition(JLabel.TOP);
            infoLabel.setHorizontalTextPosition(JLabel.CENTER);
            infoLabel.setEnabled(true);

            // create a button to go to the next screen (or start the quiz)
            JButton nextButton = new JButton(i == 0 ? "Next" : "Start Quiz");
            nextButton.addActionListener(e -> cardLayout.next(cards));

            // add the label and button to the panel
            infoPanel.add(infoLabel, BorderLayout.CENTER);
            infoPanel.add(nextButton, BorderLayout.PAGE_END);

            // add the panel to the card panel with a unique identifier
            cards.add(infoPanel, "info" + (i + 1));
        }
    }

    // method to create the question screens
    private void createQuestionScreens() {
        // loop through each question
        for (int i = 0; i < questions.length; i++) {
            // create the question screen for the current question
            createQuestionScreen(i);
        }
    }

    // method to create a single question screen
    private void createQuestionScreen(int index) {
        // create a panel to hold the question screen
        JPanel questionPanel = new JPanel(new BorderLayout());

        // create a label to display the question text
        JLabel questionLabel = new JLabel("<html><p>" + questions[index] + "</p></html>", SwingConstants.CENTER);
        questionPanel.add(questionLabel, BorderLayout.PAGE_START);

        // create a panel to hold the answer options
        JPanel optionsPanel = new JPanel(new GridLayout(0, 1));
        // create a button group for the answer options
        ButtonGroup optionsGroup = new ButtonGroup();
        // loop through each answer option for the current question
        for (int i = 0; i < options[index].length; i++) {
            // create a radio button for the answer option
            JRadioButton optionButton = new JRadioButton("<html><p>" + (char) ('a' + i) + ") " + options[index][i] + "</p></html>");
            // add the radio button to the button group and the options panel
            optionsGroup.add(optionButton);
            optionsPanel.add(optionButton);
        }
        // add the options panel to the question panel
        questionPanel.add(optionsPanel, BorderLayout.CENTER);

        // create a panel to hold the back and submit buttons
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backButton = new JButton("Back");
        JButton submitButton = new JButton("Submit");
        buttonsPanel.add(backButton);
        buttonsPanel.add(submitButton);
        questionPanel.add(buttonsPanel, BorderLayout.PAGE_END);

        // add action listeners to the back and submit buttons
        backButton.addActionListener(e -> goBack());
        submitButton.addActionListener(e -> {
            // get the index of the selected answer option
            int selectedOption = -1;
            for (int i = 0; i < options[index].length; i++) {
                if (((JRadioButton) optionsPanel.getComponent(i)).isSelected()) {
                    selectedOption = i;
                    break;
                }
            }
            // if an answer option was selected, submit the answer
            if (selectedOption != -1) {
                submitAnswer(index, selectedOption);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select an option.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // add the question panel to the card panel with a unique identifier
        cards.add(questionPanel, "question" + (index + 1));
    }

    // method to create the results screen
    private void createResultScreen() {
        // create a panel to hold the results screen
        JPanel resultPanel = new JPanel(new BorderLayout());

        // create a label to display the results text
        JLabel resultLabel = new JLabel("", SwingConstants.CENTER);

        // create a button to go back to the start of the quiz
        JButton backButton = new JButton("Back to Start");
        backButton.addActionListener(e -> {
            // go back to the first card and reset the current question
            cardLayout.first(cards);
            currentQuestion = 0;
        });

        // add the label and button to the results panel
        resultPanel.add(resultLabel, BorderLayout.CENTER);
        resultPanel.add(backButton, BorderLayout.PAGE_END);

        // add the results panel to the card panel with a unique identifier
        cards.add(resultPanel, "results");

        // update the results screen with the current results
        updateResultScreen(resultLabel);
    }

    // method to submit an answer to a question
    private void submitAnswer(int index, int selectedOption) {
        // set the answer for the current question to correct (1) if the selected option is the correct answer, else incorrect (0)
        answers[index] = selectedOption == correctAnswers[index].charAt(0) - 'a' ? 1 : 0;
        // move to the next question if there are more questions, else show the results screen
        currentQuestion++;
        if (currentQuestion < questions.length) {
            cardLayout.next(cards);
        } else {
            cardLayout.show(cards, "results");
            updateResultScreen((JLabel) ((JPanel)cards.getComponent(cards.getComponentCount()-1)).getComponent(0));
        }
    }

    // method to go back to the previous question
    private void goBack() {
        // decrement the current question and show the previous card
        if (currentQuestion > 0) {
            currentQuestion--;
        }
        cardLayout.previous(cards);
    }

    // method to update the results screen with the current results
    private void updateResultScreen(JLabel resultLabel) {
        // count the number of correct answers
        int correctAnswersCount = 0;
        for (int answer : answers) {
            correctAnswersCount += answer;
        }
        // calculate the percentage of correct answers
        double percentage = ((double) correctAnswersCount / questions.length) * 100;

        // create a string with the results text
        StringBuilder resultText = new StringBuilder("<html><h2>Results:</h2><ul>");
        for (int i = 0; i < questions.length; i++) {
            resultText.append("<li>").append(answers[i] == 1 ? "Correct" : "Incorrect").append(": ").append(questions[i]).append("</li>");
        }
        resultText.append("</ul><p>Score: ").append(correctAnswersCount).append("/").append(questions.length).append(" (").append(percentage).append("%)</p></html>");
        // set the results label text to the results text string
        resultLabel.setText(resultText.toString());
    }
}




