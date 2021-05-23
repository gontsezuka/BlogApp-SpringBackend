package com.zukalover.BlogApplication.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zukalover.BlogApplication.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

	@Query(value="SELECT * FROM comment WHERE postid = ?1", nativeQuery=true)
	public List<Comment> findCommentByPostId(Long postId);

	@Query(value="SELECT * FROM comment WHERE userid = ?1", nativeQuery=true)
	public List<Comment> findCommentByUser(Long userId);
}
