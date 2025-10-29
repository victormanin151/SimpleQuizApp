package com.example.simpleQuiz.service;

import com.example.simpleQuiz.model.Quiz;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
                "Programming Language",
                "",
                "hard"));

        questions.add(new Quiz(currId++,
                "Which planet is known as the Red Planet?",
                "Earth",
                "Mars",
                "Venus",
                "Jupiter",
                "Mars",
                "",
                "easy"));

        questions.add(new Quiz(currId++,
                "What is the largest ocean on Earth?",
                "Atlantic Ocean",
                "Indian Ocean",
                "Arctic Ocean",
                "Pacific Ocean",
                "Pacific Ocean",
                "",
                "easy"));

        questions.add(new Quiz(currId++,
                "Who wrote the play 'Romeo and Juliet'?",
                "William Shakespeare",
                "Mark Twain",
                "Charles Dickens",
                "Jane Austen",
                "William Shakespeare",
                "",
                "medium"));

        questions.add(new Quiz(currId++,
                "Which element has the chemical symbol 'O'?",
                "Osmium",
                "Oxygen",
                "Gold",
                "Iron",
                "Oxygen",
                "",
                "easy"));

        questions.add(new Quiz(currId++,
                "What is the capital city of Japan?",
                "Tokyo",
                "Kyoto",
                "Osaka",
                "Hiroshima",
                "Tokyo",
                "",
                "easy"));

        questions.add(new Quiz(currId++,
                "Which planet has the most moons?",
                "Mars",
                "Saturn",
                "Jupiter",
                "Neptune",
                "Jupiter",
                "",
                "medium"));

        questions.add(new Quiz(currId++,
                "What is the square root of 144?",
                "10",
                "12",
                "14",
                "16",
                "12",
                "",
                "easy"));

        questions.add(new Quiz(currId++,
                "Which language is primarily spoken in Brazil?",
                "Spanish",
                "Portuguese",
                "French",
                "English",
                "Portuguese",
                "",
                "easy"));

        questions.add(new Quiz(currId++,
                "Who painted the Mona Lisa?",
                "Vincent van Gogh",
                "Leonardo da Vinci",
                "Pablo Picasso",
                "Michelangelo",
                "Leonardo da Vinci",
                "",
                "medium"));
        questions.add(new Quiz(currId++,
                "What is the smallest prime number that is also a twin prime with both neighbors being primes?",
                "3",
                "5",
                "7",
                "11",
                "5",
                "",
                "hard"));
        questions.add(new Quiz(currId++,
                "Did i start doing this project late?",
                "Yes",
                "Yes very late",
                "Should have started yesterday",
                "Already failed Alen",
                "Already failed Alen",
                "",
                "hard"));
    }
    public List<Quiz> getAllQuestions() {
        List<Quiz> shuffled = new ArrayList<>(questions);
        Collections.shuffle(shuffled);
        return shuffled;
    }

    public void addQuestion(String title, String optionA, String optionB, String optionC, String optionD, String correctAnswer,String userAnswer, String questionDifficulty){
        Quiz newQuiz = new Quiz(currId++, title, optionA, optionB, optionC, optionD, correctAnswer, userAnswer, questionDifficulty);
        questions.add(newQuiz);
    }

    public Quiz getQuestionById(int id) {
        for (Quiz q : questions){
            if(q.getId() == id){
                return q;
            }
        }
        return null;
    }

    public Quiz getQuestionByIndex(List<Quiz> shuffled, int index) {
        if(index >= 0 && index < shuffled.size()) {
            return shuffled.get(index);
        }
        return null;
    }

    public void loadQuestionsFromJson(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Map<String, String>> jsonQuestions = mapper.readValue(
                    new File(path),
                    new TypeReference<List<Map<String, String>>>() {}
            );

            for (Map<String, String> q : jsonQuestions) {
                addQuestion(
                        q.get("title"),
                        q.get("optionA"),
                        q.get("optionB"),
                        q.get("optionC"),
                        q.get("optionD"),
                        q.get("correctAnswer"),
                        null,
                        q.get("difficulty")
                );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void init() {
        loadQuestionsFromJson("src/main/resources/files/questions.json");
    }

}
