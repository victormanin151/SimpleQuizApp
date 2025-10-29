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
    @GetMapping("/")
    public String start(){
        return "index";
    }

    @GetMapping("/start")
    public String index(Model model){
        model.addAttribute("Quiz", service.getAllQuestions());
        return "start";
    }

//    @GetMapping("/quiz"){
//        public String questionsOneByOne(){
//            return "questionsOneByOne";
//        }
//    }

    @GetMapping("/addQuestion")
    public String addQuestion(){
        return "addQuestion";
    }

    @PostMapping("/results")
    public String submitQuiz(@RequestParam Map<String,String> answers, Model model) {

        int score = 0;
        List<Quiz> questions = service.getAllQuestions();

        for (Quiz q : questions) {
            String selected = answers.get("q" + q.getId());
            if (selected != null && selected.equalsIgnoreCase(q.getCorrectAnswer())) {
                score++;
            }
        }

        model.addAttribute("score", score);
        model.addAttribute("total", questions.size());
        return "results";
    }

    @PostMapping("/addQuestion")
    public String addQuestion(
            @RequestParam String title,
            @RequestParam String optionA,
            @RequestParam String optionB,
            @RequestParam String optionC,
            @RequestParam String optionD,
            @RequestParam String correctAnswer,
            @RequestParam String difficulty
    ){
        service.addQuestion(title, optionA, optionB, optionC, optionD, correctAnswer, null, difficulty);
        return "redirect:/";
    }
}
