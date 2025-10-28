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
                "",
                "hard"));

        questions.add(new Quiz(currId++,
                "Which planet is known as the Red Planet?",
                "Earth",
                "Mars",
                "Venus",
                "Jupiter",
                "B",
                "",
                "easy"));

        questions.add(new Quiz(currId++,
                "What is the largest ocean on Earth?",
                "Atlantic Ocean",
                "Indian Ocean",
                "Arctic Ocean",
                "Pacific Ocean",
                "D",
                "",
                "easy"));

        questions.add(new Quiz(currId++,
                "Who wrote the play 'Romeo and Juliet'?",
                "William Shakespeare",
                "Mark Twain",
                "Charles Dickens",
                "Jane Austen",
                "A",
                "",
                "medium"));

        questions.add(new Quiz(currId++,
                "Which element has the chemical symbol 'O'?",
                "Osmium",
                "Oxygen",
                "Gold",
                "Iron",
                "B",
                "",
                "easy"));

        questions.add(new Quiz(currId++,
                "What is the capital city of Japan?",
                "Tokyo",
                "Kyoto",
                "Osaka",
                "Hiroshima",
                "A",
                "",
                "easy"));

        questions.add(new Quiz(currId++,
                "Which planet has the most moons?",
                "Mars",
                "Saturn",
                "Jupiter",
                "Neptune",
                "C",
                "",
                "medium"));

        questions.add(new Quiz(currId++,
                "What is the square root of 144?",
                "10",
                "12",
                "14",
                "16",
                "B",
                "",
                "easy"));

        questions.add(new Quiz(currId++,
                "Which language is primarily spoken in Brazil?",
                "Spanish",
                "Portuguese",
                "French",
                "English",
                "B",
                "",
                "easy"));

        questions.add(new Quiz(currId++,
                "Who painted the Mona Lisa?",
                "Vincent van Gogh",
                "Leonardo da Vinci",
                "Pablo Picasso",
                "Michelangelo",
                "B",
                "",
                "medium"));
        questions.add(new Quiz(currId++,
                "What is the smallest prime number that is also a twin prime with both neighbors being primes?",
                "3",
                "5",
                "7",
                "11",
                "C",
                "",
                "hard"));
        questions.add(new Quiz(currId++,
                "Did i start doing this project late?",
                "Yes",
                "Yes very late",
                "Should have started yesterday",
                "Already failed Alen",
                "D",
                "",
                "hard"));
    }

    public List<Quiz> getAllQuestions() {
        return questions;
    }

    public void addQuestion(String title, String optionA, String optionB, String optionC, String optionD, String correctAnswer,String userAnswer, String questionDifficulty){
        Quiz newQuiz = new Quiz(currId++, title, optionA, optionB, optionC, optionD, correctAnswer, userAnswer, questionDifficulty);
        questions.add(newQuiz);
    }

}
