package com.app.quizservice.Feign;

import com.app.quizservice.Model.QuestionWrapper;
import com.app.quizservice.Model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("Question-Service")
public interface QuizInterface {
    @PostMapping("question/generate")
    public ResponseEntity<List<Integer>> generate(@RequestParam String category, @RequestParam Integer numQ);

    @PostMapping("question/getquestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> id);

    @PostMapping("question/getscore")
    public ResponseEntity<Integer> getscore(@RequestBody List<Response> responses);
}
