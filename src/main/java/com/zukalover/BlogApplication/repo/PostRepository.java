package com.zukalover.BlogApplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zukalover.BlogApplication.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

}
