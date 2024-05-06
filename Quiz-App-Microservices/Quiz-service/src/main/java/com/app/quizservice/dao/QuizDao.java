package com.app.quizservice.dao;


import com.app.quizservice.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
    Quiz findByTitle(String name);
}
