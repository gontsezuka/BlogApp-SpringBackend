package com.zukalover.BlogApplication.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zukalover.BlogApplication.model.Post;
import com.zukalover.BlogApplication.model.User;
import com.zukalover.BlogApplication.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {

	Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User user);
	
	@Query(value="SELECT * FROM vote WHERE postid = ?1", nativeQuery=true)
	List<Vote> findAllVotesByPostId(Long postId);
}
