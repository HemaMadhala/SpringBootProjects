package com.app.QuizApp.Controller;

import com.app.QuizApp.Model.QuestionWrapper;
import com.app.QuizApp.Model.Response;
import com.app.QuizApp.service.QuizService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService qs;

    @PostMapping("create")
    public ResponseEntity<String> creteQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return qs.createQuiz(category,numQ,title);
    }

    @GetMapping("getid/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return qs.getQuizQuestions(id);
    }
    @GetMapping("getname/{name}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable String name){
        return qs.getQuizQuestionsByName(name);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return qs.caluclateResult(id,responses);

    }
}
