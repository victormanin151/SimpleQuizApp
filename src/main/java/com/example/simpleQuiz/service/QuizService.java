package com.example.simpleQuiz.service;

import com.example.simpleQuiz.model.Quiz;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {
    private List<Quiz> questions = new ArrayList<Quiz>();
    private int currId = 1;

    public QuizService() {

        questions.add(new Quiz(currId++,
                "What is Java?",
                "Programming Language",
                "Coffee",
                "Island",
                "Car",
                "A",
                false,
                "hard"));

        questions.add(new Quiz(currId++,
                "Which planet is known as the Red Planet?",
                "Earth",
                "Mars",
                "Venus",
                "Jupiter",
                "B",
                false,
                "easy"));
    }

    public List<Quiz> getAllQuestions() {
        return questions;
    }


}
