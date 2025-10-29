package com.example.simpleQuiz.controller;

import com.example.simpleQuiz.model.Quiz;
import com.example.simpleQuiz.service.QuizService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
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
        String correctText;
        switch (correctAnswer) {
            case "A" -> correctText = optionA;
            case "B" -> correctText = optionB;
            case "C" -> correctText = optionC;
            case "D" -> correctText = optionD;
            default -> correctText = "";
        }

        service.addQuestion(title, optionA, optionB, optionC, optionD, correctText, null, difficulty);
        return "redirect:/";
    }

    @GetMapping("/quiz")
    public String showQuestion(@RequestParam(value = "index", defaultValue = "0") int index,
                               HttpSession session, Model model) {

        List<Quiz> shuffled = (List<Quiz>) session.getAttribute("shuffledQuestions");
        if (shuffled == null) {
            shuffled = service.getAllQuestions();
            session.setAttribute("shuffledQuestions", shuffled);
        }

        Map<Integer, String> answers = (Map<Integer, String>) session.getAttribute("answers");
        if (answers == null) {
            answers = new HashMap<>();
            session.setAttribute("answers", answers);
        }

        Quiz q = service.getQuestionByIndex(shuffled, index);
        if (q == null) {
            return "redirect:/results";
        }

        if (answers.containsKey(q.getId())) {
            q.setUserAnswer(answers.get(q.getId()));
        }

        model.addAttribute("question", q);
        model.addAttribute("index", index);

        return "questionsOneByOne";
    }

    @PostMapping("/quiz")
    public String submitAnswer(@RequestParam int index,
                               @RequestParam int questionId,
                               @RequestParam(required = false) String answer,
                               @RequestParam String action,
                               HttpSession session) {

        Map<Integer, String> answers = (Map<Integer, String>) session.getAttribute("answers");
        if (answers == null) {
            answers = new HashMap<>();
        }

        if (answer != null && !answer.isEmpty()) {
            answers.put(questionId, answer);
            session.setAttribute("answers", answers);
        }

        List<Quiz> shuffled = (List<Quiz>) session.getAttribute("shuffledQuestions");

        int nextIndex = index;
        if ("next".equals(action)) {
            nextIndex++;
            if (nextIndex >= shuffled.size()) {
                return "redirect:/results";
            }
        } else if ("prev".equals(action)) {
            nextIndex--;
        }

        return "redirect:/quiz?index=" + nextIndex;
    }

    @GetMapping("/results")
    public String showResults(HttpSession session, Model model) {
        List<Quiz> shuffled = (List<Quiz>) session.getAttribute("shuffledQuestions");
        Map<Integer, String> answers = (Map<Integer, String>) session.getAttribute("answers");

        int score = 0;
        if (shuffled != null && answers != null) {
            for (Quiz q : shuffled) {
                String userAnswer = answers.get(q.getId());
                if (userAnswer != null && userAnswer.equalsIgnoreCase(q.getCorrectAnswer())) {
                    score++;
                }
            }
        }

        model.addAttribute("score", score);
        model.addAttribute("total", shuffled.size());

        session.removeAttribute("shuffledQuestions");
        session.removeAttribute("answers");

        return "results";
    }
}
