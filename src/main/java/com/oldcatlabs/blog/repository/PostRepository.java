package com.oldcatlabs.blog.repository;

import com.oldcatlabs.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllByIsDeleted(boolean isDeleted);
    Optional<Post> findFirstBySlug(String slug);
    Optional<Post> findFirstBySlugAndIsDeleted(String slug, boolean isDeleted);
}
