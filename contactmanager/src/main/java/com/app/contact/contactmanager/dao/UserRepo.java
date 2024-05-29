package com.app.contact.contactmanager.dao;
import com.app.contact.contactmanager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

}
