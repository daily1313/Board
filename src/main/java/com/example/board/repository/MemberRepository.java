package com.example.board.repository;

import com.example.board.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUsernameAndPassword(String username, String password);
    Optional<Member> findByUsername(String username);
    public boolean existsByUsername(String username);
}
