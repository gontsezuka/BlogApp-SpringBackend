package com.zukalover.BlogApplication.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zukalover.BlogApplication.model.VerificationToken;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {

	@Query(value="SELECT * FROM token WHERE token = ?1",nativeQuery=true)
	Optional<VerificationToken> findByToken(String token);
}
