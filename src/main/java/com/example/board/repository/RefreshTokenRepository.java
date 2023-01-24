package com.example.board.repository;

import com.example.board.entity.RefreshToken;
import java.lang.StackWalker.Option;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByKey(String key);
}
