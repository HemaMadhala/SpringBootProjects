package com.app.QuizApp.service;

import com.app.QuizApp.Model.Question;
import com.app.QuizApp.Model.QuestionWrapper;
import com.app.QuizApp.Model.Quiz;
import com.app.QuizApp.Model.Response;
import com.app.QuizApp.dao.QuestionDao;
import com.app.QuizApp.dao.QuizDao;
import com.sun.net.httpserver.Authenticator;
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
    QuestionDao dao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title){
        List<Question> questions=dao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        qd.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz=qd.findById(id);
        List<Question> questionsFromDb = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUsers = new ArrayList<>();
        for(Question q:questionsFromDb){
            QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionForUsers.add(qw);
        }
        return new ResponseEntity<>(questionForUsers,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsByName(String name) {
        Quiz quiz= qd.findByTitle(name);
        List<Question> questionsFromDb=quiz.getQuestions();
        List<QuestionWrapper> questionForUsers = new ArrayList<>();
        for(Question q:questionsFromDb){
            QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionForUsers.add(qw);
        }
        return new ResponseEntity<>(questionForUsers,HttpStatus.OK);
    }

    public  ResponseEntity<Integer> caluclateResult(Integer id, List<Response> responses) {
        Optional<Quiz> quiz=qd.findById(id);
        List<Question> questionsFromDb=quiz.get().getQuestions();
        int right=0, i=0;
        for(Response r: responses){
            if(r.getResponse().equals(questionsFromDb.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }
        return new  ResponseEntity<>(right,HttpStatus.OK);
    }
}
