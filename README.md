# Simple Quiz App

A web-based quiz application built with **Spring Boot** and **Thymeleaf** that allows users to:

* Take quizzes **one question at a time** in random order
* Add new quiz questions dynamically
* Track progress with a **question counter**
* Time each question (60 seconds per question)
* View results with feedback based on score

---

## Features

```
1. Randomized Questions
   - Questions are shuffled each time the quiz starts.

2. One-by-One Navigation
   - Users can move forward and backward through questions.
   - Answers are saved when navigating between questions.

3. Timer
   - Each question has a 60-second countdown.
   - On timeout, the quiz automatically proceeds to results.

4. Progress Tracking
   - Displays "Question X of Y" to indicate progress.

5. Add New Questions
   - Users can add questions with title, four options, correct answer, and difficulty level.

6. Results Page
   - Shows total score and feedback based on performance:
       - Below 50% → Failed
       - 50%-75% → Pass
       - 75%-90% → Good
       - 90%-99% → Well Done
       - 100% → Excellent
```

---

## Technologies

```
- Backend: Spring Boot, Java
- Frontend: Thymeleaf, HTML, CSS, JavaScript
- Styling: CSS in style.css
- Session Management: HttpSession to store quiz state and answers
```

---

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com.example.quiz/
│   │       ├── controller/  # QuizController.java
│   │       ├── model/       # Quiz.java
│   │       └── service/     # QuizService.java
│   └── resources/
│       ├── templates/       # Thymeleaf HTML files
│       │   ├── index.html
│       │   ├── start.html
│       │   ├── questionsOneByOne.html
│       │   ├── addQuestion.html
│       │   └── results.html
│       └── static/
│           └── css/
│               └── style.css
```

---

## How to Run

```
1. Clone the repository:
   git clone <repository-url>

2. Build and run the Spring Boot application:
   mvn spring-boot:run
   or
   ./mvnw spring-boot:run

3. Open your browser and go to:
   http://localhost:8080/

4. Click "Start Quiz" to begin.
```

---

## Adding Questions via JSON (Optional)

```
You can load questions from a JSON file using QuizService.loadQuestionsFromJson(String path).

JSON example:
[
  {
    "title": "Which planet is known as the Red Planet?",
    "optionA": "Earth",
    "optionB": "Mars",
    "optionC": "Venus",
    "optionD": "Jupiter",
    "correctAnswer": "Mars",
    "difficulty": "easy"
  }
]
```
