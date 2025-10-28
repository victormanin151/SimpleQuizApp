package com.example.simpleQuiz.controller;

import com.example.simpleQuiz.model.Quiz;
import com.example.simpleQuiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class QuizController {

    private final QuizService service;

    @Autowired
    public QuizController(QuizService service) {
        this.service = service;
    }
    @GetMapping("/start")
    public String start(){
        return "start";
    }


    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("Quiz", service.getAllQuestions());
        return "index";
    }

    @PostMapping("/results")
    public String submitQuiz(@RequestParam Map<String,String> answers, Model model) {

        int score = 0;
        List<Quiz> questions = service.getAllQuestions();

        for (Quiz q : questions) {
            String selected = answers.get("q" + q.getId()); // get user choice
            if (selected != null && selected.equalsIgnoreCase(q.getCorrectAnswer())) {
                score++;
            }
        }

        model.addAttribute("score", score);
        model.addAttribute("total", questions.size());
        return "results";
    }
}
