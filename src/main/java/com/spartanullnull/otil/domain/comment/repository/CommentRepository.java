package com.spartanullnull.otil.domain.comment.repository;

import com.spartanullnull.otil.domain.comment.entity.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(Long postId);
}
