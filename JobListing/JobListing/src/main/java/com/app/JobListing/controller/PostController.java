package com.app.JobListing.controller;

import com.app.JobListing.repository.PostRepo;
import com.app.JobListing.model.Post;
import com.app.JobListing.repository.SearchRepo;
import com.app.JobListing.repository.SearchRepoIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostRepo repo;
    @Autowired
    SearchRepoIMPL s;

/*    @RequestMapping(value="/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }*/

    public List<Post> getAllPosts(){

        return repo.findAll();
    }

    @GetMapping("/posts/{text}")
    public List<Post> search(@PathVariable String text){
        return s.findByTex(text);
    }
}
