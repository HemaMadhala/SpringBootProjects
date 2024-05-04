package com.app.QuizApp.dao;

import com.app.QuizApp.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
    Quiz findByTitle(String name);
}
