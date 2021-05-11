package com.zukalover.BlogApplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zukalover.BlogApplication.model.SubPost;

@Repository
public interface SubPostRepository extends JpaRepository<SubPost, Long> {

}
