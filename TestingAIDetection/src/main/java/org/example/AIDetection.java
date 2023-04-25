package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.json.JSONObject;

import java.io.IOException;

public class AIDetection extends JFrame{
    private JTextArea articleInput;
    private JButton analyzeButton;
    private JLabel resultLabel;
    private OpenAIClient openAIClient;

    public AIDetection() {
        openAIClient = new OpenAIClient("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");
        createUIComponents();
    }

    private void createUIComponents() {
        setTitle("AI-generated News Article Detector");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Article input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input news article"));

        articleInput = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(articleInput);
        inputPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(inputPanel, BorderLayout.CENTER);

        // Result panel
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());

        analyzeButton = new JButton("Analyze");
        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String article = articleInput.getText();
                double probability = analyzeArticle(article); // Replace with your own model or API
                resultLabel.setText(String.format("AI-generated probability: %.2f%%", probability * 100));
            }
        });
        resultPanel.add(analyzeButton, BorderLayout.WEST);

        resultLabel = new JLabel(" ");
        resultPanel.add(resultLabel, BorderLayout.CENTER);

        mainPanel.add(resultPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private double analyzeArticle(String article) {
        try {
            String completion = openAIClient.getCompletion(article);
            JSONObject jsonResponse = new JSONObject(completion);
            // Extract probability value from the completion response
            // You need to update this code to parse the actual response from the API
            double probability = jsonResponse.getDouble("probability");
            return probability;
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error: Unable to analyze the article. Please check your API key and internet connection.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return 0.0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AIDetection app = new AIDetection();
                app.setVisible(true);
            }
        });
    }
}