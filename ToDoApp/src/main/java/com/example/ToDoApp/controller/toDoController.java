package com.example.ToDoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ToDoApp.ToDoService;
import com.example.ToDoApp.model.ToDo;

import jakarta.annotation.PostConstruct;

@Controller
public class toDoController {

	@Autowired
	ToDoService service;
	
	@GetMapping({"/","viewToDoList"})
	public String viewAllToDoItems(Model model, @ModelAttribute("message") String message) {
		model.addAttribute("list", service.getAllToDOItems());
		model.addAttribute("msg", message);
		return "viewToDoList";
	}
	
	@GetMapping("/updateToDoStatus/{id}")
	public String updateToDoStatus(@PathVariable Long id, RedirectAttributes redirect) {
		if(service.updateStatus(id)) {
			redirect.addFlashAttribute("message", "Update Success");
			return "redirect:/viewToDoList";
		}
		redirect.addFlashAttribute("message", "Update Failure");
		return "redirect:/viewToDoList";
	}
	
	@GetMapping("/addToDoItem")
	public String addToDoItem(Model model) {
		model.addAttribute("todo", new ToDo());
		return "addToDoItem";
	}
	
	@PostMapping("/saveToDoItem")
	public String saveToDoItem(ToDo todo, RedirectAttributes redirect) {
		if(service.saveUpdate(todo)) {
			redirect.addFlashAttribute("message", "Update Success");
			return "redirect:/viewToDoList";
		}
		redirect.addFlashAttribute("message", "Update Failure");
		return "redirect:/addToDoItem";
	}
	@GetMapping("editToDoItem/{id}")
	public String editToDoItem(@PathVariable Long id, Model model) {
		model.addAttribute("todo", service.getToDoItemById(id));
		return "editToDoItem";
	}
	@PostMapping("/editSaveToDoItem")
	public String editSaveToDoItem(ToDo todo, RedirectAttributes redirect) {
		if(service.saveUpdate(todo)) {
			redirect.addFlashAttribute("message", "Update Success");
			return "redirect:/viewToDoList";
		}
		redirect.addFlashAttribute("message", "Update Failure");
		return "redirect:/editToDoItem";
	}
	@GetMapping("/deleteToDoItem/{id}")
	public String deleteToDoItem(@PathVariable Long id,RedirectAttributes redirect ) {
		 if(service.deleteItem(id)) {
			 redirect.addFlashAttribute("message", "Delete Success");
		 }
		 redirect.addFlashAttribute("message", "Delete Failure");
			return "redirect:/viewToDoList";
	}
	
}
