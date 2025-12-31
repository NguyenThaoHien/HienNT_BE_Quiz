package com.example.quizapp.repository;
import com.example.quizapp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @EntityGraph(attributePaths = "roles")
    Optional<User> findByEmailAndActiveTrue(String email);
    @EntityGraph(attributePaths = "roles")
    Optional<User> findByIdAndActiveTrue(UUID id);
    Page<User> findAllByActiveTrue(Pageable pageable);

    @EntityGraph(attributePaths = {"roles"})
    Page<User> findAll(Specification<User> spec, Pageable pageable);
    boolean existsByEmail(String email);


}
