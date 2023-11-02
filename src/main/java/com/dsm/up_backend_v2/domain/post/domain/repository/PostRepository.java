package com.dsm.up_backend_v2.domain.post.domain.repository;

import com.dsm.up_backend_v2.domain.post.domain.Post;
import com.dsm.up_backend_v2.domain.post.domain.type.Major;
import com.dsm.up_backend_v2.domain.post.domain.type.State;
import com.dsm.up_backend_v2.domain.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
    Page<Post> findAllByTitleContainingOrderByIdDesc(String title, Pageable pageable);
    Page<Post> findAllByTitleContainingAndMajorOrderByIdDesc(String title, Major major, Pageable pageable);
    Page<Post> findAllByStateAndTitleContainingAndMajorOrderByIdDesc(State state, String title, Major major, Pageable pageable);
    Page<Post> findAllByStateAndTitleContainingOrderByIdDesc(State state, String title, Pageable pageable);
    Page<Post> findAllByUserOrderByIdDesc(User user, Pageable pageable);
    long countByUser(User user);
}
