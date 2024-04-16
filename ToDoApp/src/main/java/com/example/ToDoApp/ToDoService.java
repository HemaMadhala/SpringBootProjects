package com.example.ToDoApp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import com.example.ToDoApp.model.ToDo;

@Service
public class ToDoService {
	
	@Autowired
	ToDoRepo repo;
	
	public List<ToDo> getAllToDOItems(){
		ArrayList<ToDo> list =new ArrayList<>();
		repo.findAll().forEach(todo->list.add(todo));
		return list;
	}
	
	public ToDo getToDoItemById(Long id){
		return repo.findById(id).get();
	}
	
	public boolean updateStatus(Long id){
		ToDo todo=getToDoItemById(id);
		todo.setStatus("Completed");
		return saveUpdate(todo);
	}
	public boolean saveUpdate(ToDo todo){
		ToDo update = repo.save(todo);
		
		if(getToDoItemById(update.getId())!=null) {
			return true;
		}
		return false;
	}
	public boolean deleteItem(Long id){
		repo.deleteById(id);
		if(repo.findById(id)==null) {
			return true;
		}
		return false;
	}
}
