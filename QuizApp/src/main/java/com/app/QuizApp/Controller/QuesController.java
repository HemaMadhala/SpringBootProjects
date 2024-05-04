package com.app.QuizApp.Controller;

import com.app.QuizApp.Model.Question;
import com.app.QuizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuesController {

    @Autowired
    QuestionService questionservice;
    @GetMapping("allquestions")
    public ResponseEntity<List<Question>> allQuestions(){

        return questionservice.getAllQuestions();
    }
    @GetMapping("category/{s}")
    public List<Question> getAllByCat(@PathVariable String s) {
        return questionservice.getAllByCat(s);
    }
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionservice.addQuestion(question);
    }

    @GetMapping("difficulty/{s}")
    public List<Question> getAllBydiff(@PathVariable String s) {
        return questionservice.getAllByDiff(s);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id){
        return questionservice.deleteQuestion(id);
    }
}
