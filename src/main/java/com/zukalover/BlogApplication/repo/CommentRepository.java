package com.zukalover.BlogApplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zukalover.BlogApplication.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

}
