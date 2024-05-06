package com.app.questionservice.service;
import com.app.questionservice.Model.Question;
import com.app.questionservice.Model.QuestionWrapper;
import com.app.questionservice.Model.Response;
import com.app.questionservice.dao.QuestionDao;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            System.out.println("Failed");
        }
        return new ResponseEntity<>("Failed", HttpStatus.EXPECTATION_FAILED);
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

    public ResponseEntity<List<Integer>> findRandomQuestionsBycat(String category, Integer numQ) {
        List<Integer> questions=dao.findRandomQuestionsBycat(category,numQ);
        return new ResponseEntity<>(questions,HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsById(List<Integer> id) {
        List<QuestionWrapper> wrappers=new ArrayList<>();

        try {
            for(Integer i:id) {
                Optional<Question> question = dao.findById(i);
                QuestionWrapper wrapper = new QuestionWrapper(question.get().getId(), question.get().getQuestionTitle(), question.get().getOption1(), question.get().getOption2(), question.get().getOption3(), question.get().getOption4());
                wrappers.add(wrapper);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(wrappers,HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> caluclatescore(List<Response> responses) {
        int count=0;
        for(Response r:responses){
            Optional<Question> question=dao.findById(r.getId());
            if(question.get().getRightAnswer().equals(r.getResponse())){
                count++;
            }
        }
        return new ResponseEntity<>(count,HttpStatus.OK);
    }
}
