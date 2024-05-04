package com.app.QuizApp.service;

import com.app.QuizApp.Model.Question;
import com.app.QuizApp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao dao;

    public List<Question> getAllByCat(String s){

        return dao.findByCategory(s);
    }

    public ResponseEntity<List<Question>> getAllQuestions(){
        try {
            return new ResponseEntity<>(dao.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
    public  ResponseEntity<String> addQuestion(Question question){
        try {
            dao.save(question);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.NOT_ACCEPTABLE);
    }
    public List<Question> getAllByDiff(String s) {
        return dao.findByDifficultylevel(s);
    }

    public ResponseEntity<String> deleteQuestion(Integer id) {
        try {
            dao.deleteById(id);
            return new ResponseEntity<>("Success",HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }
}
