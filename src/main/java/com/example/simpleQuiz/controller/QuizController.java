package com.example.simpleQuiz.controller;

import com.example.simpleQuiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuizController {

    private final QuizService service;

    @Autowired
    public QuizController(QuizService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model){
        //model.addAttribute("Quiz",));
        model.addAttribute("Quiz", service.getAllQuestions());
        return "index";
    }
}
