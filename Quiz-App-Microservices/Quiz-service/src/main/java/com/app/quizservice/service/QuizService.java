package com.app.quizservice.service;
import com.app.quizservice.Feign.QuizInterface;
import com.app.quizservice.Model.QuestionWrapper;
import com.app.quizservice.Model.Quiz;
import com.app.quizservice.Model.Response;
import com.app.quizservice.dao.QuizDao;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao qd;
    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, Integer numQ, String title){
        List<Integer> questions= quizInterface.generate(category,numQ).getBody();
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        qd.save(quiz);


        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz=qd.findById(id);
        List<Integer> questionIds= quiz.get().getQuestions();
        List<QuestionWrapper> quizquestion= quizInterface.getQuestions(questionIds).getBody();
        return new ResponseEntity<>(quizquestion,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsByName(String name) {
        Quiz quiz=qd.findByTitle(name);
        List<Integer> questionIds= quiz.getQuestions();
        List<QuestionWrapper> quizquestion= quizInterface.getQuestions(questionIds).getBody();
        return new ResponseEntity<>(quizquestion,HttpStatus.OK);
    }

    public  ResponseEntity<Integer> caluclateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer> s=quizInterface.getscore(responses);
        return s;
    }
}
