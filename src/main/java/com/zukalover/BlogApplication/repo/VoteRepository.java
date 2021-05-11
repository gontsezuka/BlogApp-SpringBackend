package com.zukalover.BlogApplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zukalover.BlogApplication.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {

}
