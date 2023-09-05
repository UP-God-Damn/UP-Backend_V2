package com.dsm.up_backend_v2.domain.post.domain.repository;

import com.dsm.up_backend_v2.domain.post.domain.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
