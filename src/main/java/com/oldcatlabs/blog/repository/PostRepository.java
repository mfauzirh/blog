package com.oldcatlabs.blog.repository;

import com.oldcatlabs.blog.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<Post> findFirstBySlugAndIsDeleted(String slug, boolean isDeleted);
    Page<Post> findAllByIsDeleted(boolean isDeleted, Pageable pageable);
}
