package com.app.questionservice.Controller;
import com.app.questionservice.Model.Question;
import com.app.questionservice.Model.QuestionWrapper;
import com.app.questionservice.Model.Response;
import com.app.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuesController {

    @Autowired
    QuestionService questionservice;
    @Autowired
    Environment environment;
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

    @PostMapping("generate")
    public ResponseEntity<List<Integer>> generate(@RequestParam String category, @RequestParam Integer numQ){
        return questionservice.findRandomQuestionsBycat(category, numQ);
    }

    @PostMapping("getquestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> id){
        System.out.println(environment.getProperty("local.server.port"));
        return questionservice.getQuestionsById(id);
    }

    @PostMapping("getscore")
    public ResponseEntity<Integer> getscore(@RequestBody List<Response> responses){
        return questionservice.caluclatescore(responses);
    }
}
