package com.app.JobListing.repository;

import com.app.JobListing.model.Post;

import java.util.List;

public interface SearchRepo {
    public List<Post> findByTex(String text);
}
