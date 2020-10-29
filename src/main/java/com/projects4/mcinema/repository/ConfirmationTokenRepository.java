package com.projects4.mcinema.repository;

import com.projects4.mcinema.security.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long> {
    Optional<ConfirmationToken> findConfirmationTokenByConfirmationToken(String token);
}
