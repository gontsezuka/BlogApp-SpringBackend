package com.zukalover.BlogApplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zukalover.BlogApplication.model.SubPost;

@Repository
public interface SubPostRepository extends JpaRepository<SubPost, Long> {

	@Query(value="SELECT * FROM subpost WHERE name = ?1",nativeQuery=true)
	public SubPost findSubPostByName(String subPostName);
}
