package com.app.questionservice.dao;
import com.app.questionservice.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer>{

    List<Question> findByCategory(String s);

    List<Question> findByDifficultylevel(String s);

    /*@Query(value = "SELECT * FROM question q where q.category=:category ORDER BY RANDOM() limit :numQ", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numQ);*/

    @Query(value = "SELECT q.id FROM question q where q.category=:category ORDER BY RANDOM() limit :numQ",nativeQuery = true)
    List<Integer> findRandomQuestionsBycat(String category, int numQ);
}
