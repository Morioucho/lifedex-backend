package com.morioucho.lifedex.repository;

import com.morioucho.lifedex.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
