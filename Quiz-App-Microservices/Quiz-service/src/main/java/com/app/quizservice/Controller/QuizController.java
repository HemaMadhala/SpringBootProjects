package com.app.quizservice.Controller;
import com.app.quizservice.Model.QuestionWrapper;
import com.app.quizservice.Model.QuizDTO;
import com.app.quizservice.Model.Response;
import com.app.quizservice.service.QuizService;
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
    public ResponseEntity<String> creteQuiz(@RequestBody QuizDTO quizDTO){
        return qs.createQuiz(quizDTO.getCategoryName(),quizDTO.getNumQ(),quizDTO.getTitle());
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
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> responses){
        return qs.caluclateResult(id,responses);

    }
}
