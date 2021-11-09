package com.reverse.postservice.repositories;

import com.reverse.postservice.models.Like;
import com.reverse.postservice.models.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeDao extends JpaRepository<Like, Integer> {
//    @Query(value = "select count(post_id)\n" +
//            "from user_likes\n" +
//            "where post_id = :id", nativeQuery = true)
//    Long countByPostId(@Param("id") int id);

    //Does the same as above with generated query
    Long countByLikeId_PostId(int id);

    @Query(value = "SELECT user_id FROM user_likes where post_id = :id", nativeQuery = true)
    List<Integer> findAllUsersForPostId(@Param("id") int postId);
}
