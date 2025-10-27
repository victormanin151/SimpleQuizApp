package com.example.simpleQuiz.model;

public class Quiz {
    private int id;
    private String title;
    private String answers;
    private String correctAnswer;
    private boolean isAnswered;


    public Quiz(){
    }

    public Quiz(int id, String title, String answers, String correctAnswer, boolean isAnswered){
        this.id = id;
        this.title = title;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        this.isAnswered = isAnswered;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String Title){
        this.title = title;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
