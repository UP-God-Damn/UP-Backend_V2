package com.dsm.up_backend_v2.domain.comment.domain.repository;

import com.dsm.up_backend_v2.domain.comment.domain.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
