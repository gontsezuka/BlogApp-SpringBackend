package com.zukalover.BlogApplication.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zukalover.BlogApplication.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
	
	@Query(value="SELECT * FROM post WHERE post_id = ?1",nativeQuery=true)
	public Post findPostById(Long postId);

	@Query(value="SELECT * FROM post WHERE id = ?1",nativeQuery=true)
	public List<Post> findAllPostsBySubPostId(Long subPostId);
	
}
